package cn.nju.edu.eshop.order.service.impl;

import cn.nju.edu.eshop.bean.Orders;
import cn.nju.edu.eshop.bean.OrderItem;
import cn.nju.edu.eshop.mq.ActiveMQUtil;
import cn.nju.edu.eshop.order.mapper.OrderItemMapper;
import cn.nju.edu.eshop.order.mapper.OrderMapper;
import cn.nju.edu.eshop.service.OrderService;
import cn.nju.edu.eshop.util.RedisUtil;
import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import tk.mybatis.mapper.entity.Example;

import javax.jms.*;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    RedisUtil redisUtil;

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    OrderItemMapper orderItemMapper;

    @Autowired
    ActiveMQUtil activeMQUtil;

    @Override
    public String checkTradeCode(String userId, String tradeCode) {
        Jedis jedis = null;
        try {
            jedis = redisUtil.getJedis();
            String tradeKey = "user:" + userId + ":tradeCode";


            //String tradeCodeFromCache = jedis.get(tradeKey);// 使用lua脚本在发现key的同时将key删除，防止并发订单攻击
            //对比防重删令牌
            String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
            Long eval = (Long) jedis.eval(script, Collections.singletonList(tradeKey), Collections.singletonList(tradeCode));

            if (eval != null && eval != 0) {
                jedis.del(tradeKey);
                return "success";
            } else {
                return "fail";
            }
        } finally {
            assert jedis != null;
            jedis.close();
        }
    }

    @Override
    public String genTradeCode(String userId) {
        Jedis jedis = redisUtil.getJedis();

        String tradeKey = "user:" + userId + ":tradeCode";

        String tradeCode = UUID.randomUUID().toString();

        jedis.setex(tradeKey, 60 * 15, tradeCode);

        jedis.close();

        return tradeCode;
    }

    @Override
    public void saveOrder(Orders orders) {
        // 保存订单表
        orderMapper.insertSelective(orders);
        String orderId = orders.getId();
        // 保存订单详情
        List<OrderItem> orderItemList = orders.getOrderItemList();
        for (OrderItem orderItem : orderItemList) {
            orderItem.setOrderId(orderId);
            orderItemMapper.insertSelective(orderItem);
            // 删除购物车数据
            // cartService.delCart();
        }
    }

    @Override
    public Orders getOrderByOutTradeNo(String outTradeNo) {
        Orders orders = new Orders();
        orders.setOrderSn(outTradeNo);
        return orderMapper.selectOne(orders);
    }

    @Override
    public void updateOrder(Orders orders) {
        Example e = new Example(Orders.class);
        e.createCriteria().andEqualTo("orderSn", orders.getOrderSn());
        Orders ordersUpdate = new Orders();
        ordersUpdate.setStatus("1");
        // 发送一个订单已支付的队列，提供给库存消费
        Connection connection = null;
        Session session = null;
        try{
            connection = activeMQUtil.getConnectionFactory().createConnection();
            session = connection.createSession(true,Session.SESSION_TRANSACTED);
            Queue payhment_success_queue = session.createQueue("ORDER_PAY_QUEUE");
            MessageProducer producer = session.createProducer(payhment_success_queue);
            TextMessage textMessage=new ActiveMQTextMessage();//字符串文本
            //MapMessage mapMessage = new ActiveMQMapMessage();// hash结构

            // 查询订单的对象，转化成json字符串，存入ORDER_PAY_QUEUE的消息队列
            Orders ordersParam = new Orders();
            ordersParam.setOrderSn(orders.getOrderSn());
            Orders ordersResponse = orderMapper.selectOne(ordersParam);

            OrderItem orderItemParam = new OrderItem();
            orderItemParam.setOrderSn(ordersParam.getOrderSn());
            List<OrderItem> select = orderItemMapper.select(orderItemParam);
            ordersResponse.setOrderItemList(select);
            textMessage.setText(JSON.toJSONString(ordersResponse));

            orderMapper.updateByExampleSelective(ordersUpdate,e);
            producer.send(textMessage);
            session.commit();
        }catch (Exception ex){
            // 消息回滚
            try {
                session.rollback();
            } catch (JMSException e1) {
                e1.printStackTrace();
            }
        }finally {
            try {
                connection.close();
            } catch (JMSException e1) {
                e1.printStackTrace();
            }
        }
    }
}
