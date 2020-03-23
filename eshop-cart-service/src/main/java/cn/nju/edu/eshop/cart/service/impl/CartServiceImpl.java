package cn.nju.edu.eshop.cart.service.impl;

import cn.nju.edu.eshop.bean.CartItem;
import cn.nju.edu.eshop.cart.mapper.CartItemMapper;
import cn.nju.edu.eshop.service.CartService;
import cn.nju.edu.eshop.util.RedisUtil;
import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class CartServiceImpl implements CartService {
    @Autowired
    RedisUtil redisUtil;

    @Autowired
    CartItemMapper omsCartItemMapper;

    @Override
    public CartItem ifCartExistByUser(String userId, String skuId) {

        CartItem cartItem = new CartItem();
        cartItem.setUserId(userId);
        cartItem.setProductId(skuId);
        CartItem cartItem1 = omsCartItemMapper.selectOne(cartItem);
        return cartItem1;

    }

    @Override
    public void addCart(CartItem omsCartItem) {
        if (StringUtils.isNotBlank(omsCartItem.getUserId())) {
            omsCartItemMapper.insertSelective(omsCartItem);//避免添加空值
        }
    }

    @Override
    public void updateCart(CartItem omsCartItemFromDb) {

        Example e = new Example(CartItem.class);
        e.createCriteria().andEqualTo("id",omsCartItemFromDb.getId());

        omsCartItemMapper.updateByExampleSelective(omsCartItemFromDb,e);

    }

    @Override
    public void flushCartCache(String memberId) {

        CartItem omsCartItem = new CartItem();
        omsCartItem.setUserId(memberId);
        List<CartItem> omsCartItems = omsCartItemMapper.select(omsCartItem);

        // 同步到redis缓存中
        Jedis jedis = redisUtil.getJedis();

        Map<String,String> map = new HashMap<>();
        for (CartItem cartItem : omsCartItems) {
            cartItem.setTotalPrice(cartItem.getPrice().multiply(cartItem.getQuantity()));
            map.put(cartItem.getProductId(), JSON.toJSONString(cartItem));
        }

        jedis.del("user:"+memberId+":cart");
        jedis.hmset("user:"+memberId+":cart",map);

        jedis.close();
    }

    @Override
    public List<CartItem> cartList(String userId) {
        Jedis jedis = null;
        List<CartItem> cartItems = new ArrayList<>();
        try {
            jedis = redisUtil.getJedis();

            List<String> hvals = jedis.hvals("user:" + userId + ":cart");

            for (String hval : hvals) {
                CartItem CartItem = JSON.parseObject(hval, CartItem.class);
                cartItems.add(CartItem);
            }

        }catch (Exception e){
            // 处理异常，记录系统日志
            e.printStackTrace();
            //String message = e.getMessage();
            //logService.addErrLog(message);
            return null;
        }finally {
            jedis.close();
        }

        return cartItems;
    }

    @Override
    public void checkCart(CartItem cartItem) {

        Example e = new Example(CartItem.class);

        e.createCriteria().andEqualTo("memberId",cartItem.getUserId()).andEqualTo("productSkuId",cartItem.getProductId());

        omsCartItemMapper.updateByExampleSelective(cartItem,e);

        // 缓存同步
        flushCartCache(cartItem.getUserId());

    }
}
