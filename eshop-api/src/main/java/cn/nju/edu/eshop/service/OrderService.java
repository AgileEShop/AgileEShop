package cn.nju.edu.eshop.service;

import cn.nju.edu.eshop.bean.Orders;

public interface OrderService {
    String checkTradeCode(String userId, String tradeCode);

    String genTradeCode(String userId);

    void saveOrder(Orders orders);

    Orders getOrderByOutTradeNo(String outTradeNo);

    void updateOrder(Orders orders);
}
