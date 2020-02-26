package cn.nju.edu.eshop.service;

import cn.nju.edu.eshop.bean.Order;

public interface OrderService {
    String checkTradeCode(String userId, String tradeCode);

    String genTradeCode(String userId);

    void saveOrder(Order order);

    Order getOrderByOutTradeNo(String outTradeNo);

    void updateOrder(Order order);
}
