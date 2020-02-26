package cn.nju.edu.eshop.service;

import cn.nju.edu.eshop.bean.PaymentInfo;

import java.util.Map;

public interface PaymentService {
    void savePaymentInfo(PaymentInfo paymentInfo);

    void updatePayment(PaymentInfo paymentInfo);

    void sendDelayPaymentResultCheckQueue(String outTradeNo, int count);

    Map<String,Object> checkAlipayPayment(String out_trade_no);
}
