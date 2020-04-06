package cn.nju.edu.eshop.order.controller;

import cn.nju.edu.eshop.annotations.LoginRequired;
import cn.nju.edu.eshop.bean.CartItem;
import cn.nju.edu.eshop.bean.Order;
import cn.nju.edu.eshop.bean.OrderItem;
import cn.nju.edu.eshop.bean.UserReceiveAddress;
import cn.nju.edu.eshop.service.CartService;
import cn.nju.edu.eshop.service.OrderService;
import cn.nju.edu.eshop.service.ProductService;
import cn.nju.edu.eshop.service.UserService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
public class OrderController {
    @Reference
    CartService cartService;

    @Reference
    UserService userService;
//
    @Reference
OrderService orderService;

    @Reference
    ProductService productService;

    @RequestMapping("submitOrder")
    @LoginRequired(loginSuccess = true)
    public ModelAndView submitOrder(String receiveAddressId, BigDecimal totalAmount, String tradeCode, HttpServletRequest request, HttpServletResponse response, HttpSession session, ModelMap modelMap) {


        String userId = (String) request.getAttribute("userId");
        String nickname = (String) request.getAttribute("nickname");

        // 检查交易码
        String success = orderService.checkTradeCode(userId, tradeCode);

        if (success.equals("success")) {
            List<OrderItem> orderItems = new ArrayList<>();
            // 订单对象
            Order order = new Order();
            order.setAutoConfirmDay(7);
            order.setCreateTime(new Date());
            //order.setFreightAmount(); 运费，支付后，在生成物流信息时
            order.setUserId(userId);
            order.setUsername(nickname);
            order.setNote("快点发货");
            String outTradeNo = "agileeshop";
            outTradeNo = outTradeNo + System.currentTimeMillis();// 将毫秒时间戳拼接到外部订单号
            SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMDDHHmmss");
            outTradeNo = outTradeNo + sdf.format(new Date());// 将时间字符串拼接到外部订单号

            order.setOrderSn(outTradeNo);//外部订单号
            order.setPayAmount(totalAmount);
            order.setOrderType(1);
            UserReceiveAddress userReceiveAddress = userService.getReceiveAddressById(receiveAddressId);
            order.setReceiverCity(userReceiveAddress.getCity());
            order.setReceiverDetailAddress(userReceiveAddress.getDetailAddress());
            order.setReceiverName(userReceiveAddress.getName());
            order.setReceiverPhone(userReceiveAddress.getPhoneNumber());
            order.setReceiverPostCode(userReceiveAddress.getPostCode());
            order.setReceiverProvince(userReceiveAddress.getProvince());
            order.setReceiverRegion(userReceiveAddress.getRegion());
            // 当前日期加一天，一天后配送
            Calendar c = Calendar.getInstance();
            c.add(Calendar.DATE,1);
            Date time = c.getTime();
            order.setReceiveTime(time);
            order.setStatus("0");
            order.setOrderType(0);
            order.setTotalAmount(totalAmount);

            // 根据用户id获得要购买的商品列表(购物车)，和总价格
            List<CartItem> cartItems = cartService.cartList(userId);

            for (CartItem cartItem : cartItems) {
                if (cartItem.getIsChecked().equals("1")) {
                    // 获得订单详情列表
                    OrderItem orderItem = new OrderItem();
                    // 检价
//                    boolean b = skuService.checkPrice(omsCartItem.getProductSkuId(),omsCartItem.getPrice());
//                    if (b == false) {
//                        ModelAndView mv = new ModelAndView("tradeFail");
//                        return mv;
//                    }
                    // 验库存,远程调用库存系统
                    orderItem.setProductPic(cartItem.getProductPic());
                    orderItem.setProductTitle(cartItem.getProductTitle());

                    orderItem.setOrderSn(outTradeNo);// 外部订单号，用来和其他系统进行交互，防止重复
                    orderItem.setProductCatalog2Id(cartItem.getProductCatalog2Id());
                    orderItem.setProductPrice(cartItem.getPrice());
                    orderItem.setRealAmount(cartItem.getTotalPrice());
                    orderItem.setProductQuantity(cartItem.getQuantity());
                    orderItem.setProductId(cartItem.getProductId());

                    orderItems.add(orderItem);
                }
            }
            order.setOrderItemList(orderItems);

            // 将订单和订单详情写入数据库
            // 删除购物车的对应商品
            System.err.println("order:"+order.toString());
            orderService.saveOrder(order);


            // 重定向到支付系统
            ModelAndView mv = new ModelAndView("redirect:http://localhost:8087/index");
            mv.addObject("outTradeNo",outTradeNo);
            mv.addObject("totalAmount",totalAmount);
            return mv;
        } else {
            ModelAndView mv = new ModelAndView("tradeFail");
            return mv;
        }

    }


    @RequestMapping("toTrade")
    @LoginRequired(loginSuccess = true)
    public String toTrade(HttpServletRequest request, HttpServletResponse response, HttpSession session, ModelMap modelMap) {

        String userId = (String) request.getAttribute("userId");
        String nickname = (String) request.getAttribute("nickname");

        // 收件人地址列表
        List<UserReceiveAddress> userReceiveAddresses = userService.getReceiveAddressByUserId(userId);

        // 将购物车集合转化为页面计算清单集合
        List<CartItem> cartItems = cartService.cartList(userId);
        System.err.println("cartitems:"+cartItems.size());
        List<OrderItem> orderItems = new ArrayList<>();

        for (CartItem cartItem : cartItems) {
            // 每循环一个购物车对象，就封装一个商品的详情到orderItem
            System.err.println("cartitem:"+cartItem.toString());
            if (cartItem.getIsChecked().equals("1")) {
                OrderItem orderItem = new OrderItem();
                orderItem.setProductTitle(cartItem.getProductTitle());
                orderItem.setProductPic(cartItem.getProductPic());
                orderItems.add(orderItem);
            }
        }

        modelMap.put("orderItems", orderItems);
        modelMap.put("userAddressList", userReceiveAddresses);
        modelMap.put("totalAmount", getTotalAmount(cartItems));

        // 生成交易码，为了在提交订单时做交易码的校验
        String tradeCode = orderService.genTradeCode(userId);
        modelMap.put("tradeCode", tradeCode);
        return "trade";
    }


    private BigDecimal getTotalAmount(List<CartItem> cartItems) {
        BigDecimal totalAmount = new BigDecimal("0");

        for (CartItem cartItem : cartItems) {
            BigDecimal totalPrice = cartItem.getTotalPrice();

            if (cartItem.getIsChecked().equals("1")) {
                totalAmount = totalAmount.add(totalPrice);
            }
        }

        return totalAmount;
    }


}
