package cn.nju.edu.eshop.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Order implements Serializable {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @Column
    private String userId;
    @Column
    private String orderSn;//订单编号（外部订单号）
    @Column
    private Date createTime;
    @Column
    private String username;//会员账号
    @Column
    private BigDecimal totalAmount;//订单总金额
    @Column
    private BigDecimal payAmount;//应付金额（实际支付金额）
    @Column
    private BigDecimal freightAmount;//应付金额（实际支付金额）
    @Column
    private int payType;//支付方式：0->未支付；1->支付宝；2->微信
    @Column
    private String status;//订单状态：0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单
    @Column
    private int orderType;//订单类型：0->正常订单；1->秒杀订单
    @Column
    private String deliveryCompany;//物流公司(配送方式)
    @Column
    private String deliverySn;//物流单号
    @Column
    private int autoConfirmDay;//自动确认时间（天）
    @Column
    private int billType;//发票类型：0->不开发票；1->电子发票；2->纸质发票
    @Column
    private String billHeader;//发票抬头
    @Column
    private String billContent;//发票内容
    @Column
    private String billReceiverPhone;//收票人电话
    @Column
    private String billReceiverEmail;//收票人邮箱
    @Column
    private String receiverName;//收货人姓名
    @Column
    private String receiverPhone;//收货人电话
    @Column
    private String receiverPostCode;//收货人邮编
    @Column
    private String receiverProvince;//省份/直辖市
    @Column
    private String receiverCity;//城市
    @Column
    private String receiverRegion;//区
    @Column
    private String receiverDetailAddress;//详细地址
    @Column
    private String note;//订单备注
    @Column
    private int confirmStatus;//确认收货状态：0->未确认；1->已确认
    @Column
    private int deleteStatus;//删除状态：0->未删除；1->已删除
    @Column
    private Date paymentTime;//支付时间
    @Column
    private Date deliveryTime;//发货时间
    @Column
    private Date receiveTime;//确认收货时间
    @Column
    private Date commentTime;//评价时间
    @Column
    private Date modifyTime;//修改时间
    @Transient
    List<OrderItem> orderItemList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    public BigDecimal getFreightAmount() {
        return freightAmount;
    }

    public void setFreightAmount(BigDecimal freightAmount) {
        this.freightAmount = freightAmount;
    }

    public int getPayType() {
        return payType;
    }

    public void setPayType(int payType) {
        this.payType = payType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getOrderType() {
        return orderType;
    }

    public void setOrderType(int orderType) {
        this.orderType = orderType;
    }

    public String getDeliveryCompany() {
        return deliveryCompany;
    }

    public void setDeliveryCompany(String deliveryCompany) {
        this.deliveryCompany = deliveryCompany;
    }

    public String getDeliverySn() {
        return deliverySn;
    }

    public void setDeliverySn(String deliverySn) {
        this.deliverySn = deliverySn;
    }

    public int getAutoConfirmDay() {
        return autoConfirmDay;
    }

    public void setAutoConfirmDay(int autoConfirmDay) {
        this.autoConfirmDay = autoConfirmDay;
    }

    public int getBillType() {
        return billType;
    }

    public void setBillType(int billType) {
        this.billType = billType;
    }

    public String getBillHeader() {
        return billHeader;
    }

    public void setBillHeader(String billHeader) {
        this.billHeader = billHeader;
    }

    public String getBillContent() {
        return billContent;
    }

    public void setBillContent(String billContent) {
        this.billContent = billContent;
    }

    public String getBillReceiverPhone() {
        return billReceiverPhone;
    }

    public void setBillReceiverPhone(String billReceiverPhone) {
        this.billReceiverPhone = billReceiverPhone;
    }

    public String getBillReceiverEmail() {
        return billReceiverEmail;
    }

    public void setBillReceiverEmail(String billReceiverEmail) {
        this.billReceiverEmail = billReceiverEmail;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public String getReceiverPostCode() {
        return receiverPostCode;
    }

    public void setReceiverPostCode(String receiverPostCode) {
        this.receiverPostCode = receiverPostCode;
    }

    public String getReceiverProvince() {
        return receiverProvince;
    }

    public void setReceiverProvince(String receiverProvince) {
        this.receiverProvince = receiverProvince;
    }

    public String getReceiverCity() {
        return receiverCity;
    }

    public void setReceiverCity(String receiverCity) {
        this.receiverCity = receiverCity;
    }

    public String getReceiverRegion() {
        return receiverRegion;
    }

    public void setReceiverRegion(String receiverRegion) {
        this.receiverRegion = receiverRegion;
    }

    public String getReceiverDetailAddress() {
        return receiverDetailAddress;
    }

    public void setReceiverDetailAddress(String receiverDetailAddress) {
        this.receiverDetailAddress = receiverDetailAddress;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getConfirmStatus() {
        return confirmStatus;
    }

    public void setConfirmStatus(int confirmStatus) {
        this.confirmStatus = confirmStatus;
    }

    public int getDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(int deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    public Date getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(Date paymentTime) {
        this.paymentTime = paymentTime;
    }

    public Date getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Date deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public Date getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
    }

    public Date getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public List<OrderItem> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", orderSn='" + orderSn + '\'' +
                ", createTime=" + createTime +
                ", username='" + username + '\'' +
                ", totalAmount=" + totalAmount +
                ", payAmount=" + payAmount +
                ", freightAmount=" + freightAmount +
                ", payType=" + payType +
                ", status='" + status + '\'' +
                ", orderType=" + orderType +
                ", deliveryCompany='" + deliveryCompany + '\'' +
                ", deliverySn='" + deliverySn + '\'' +
                ", autoConfirmDay=" + autoConfirmDay +
                ", billType=" + billType +
                ", billHeader='" + billHeader + '\'' +
                ", billContent='" + billContent + '\'' +
                ", billReceiverPhone='" + billReceiverPhone + '\'' +
                ", billReceiverEmail='" + billReceiverEmail + '\'' +
                ", receiverName='" + receiverName + '\'' +
                ", receiverPhone='" + receiverPhone + '\'' +
                ", receiverPostCode='" + receiverPostCode + '\'' +
                ", receiverProvince='" + receiverProvince + '\'' +
                ", receiverCity='" + receiverCity + '\'' +
                ", receiverRegion='" + receiverRegion + '\'' +
                ", receiverDetailAddress='" + receiverDetailAddress + '\'' +
                ", note='" + note + '\'' +
                ", confirmStatus=" + confirmStatus +
                ", deleteStatus=" + deleteStatus +
                ", paymentTime=" + paymentTime +
                ", deliveryTime=" + deliveryTime +
                ", receiveTime=" + receiveTime +
                ", commentTime=" + commentTime +
                ", modifyTime=" + modifyTime +
                ", orderItemList=" + orderItemList +
                '}';
    }
}
