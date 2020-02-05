package cn.nju.edu.eshop.bean;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

public class ProductInfo implements Serializable {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @Column
    private String shopId;
    @Column
    private String productCatalog3Id;
    @Column
    private String productTitle;
    @Column
    private String place;//发货地
    @Column
    private double fee;//快递费
    @Column
    private int saleNumber;//销量
    @Column
    private String urls;//图片列表
}
