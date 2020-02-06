package cn.nju.edu.eshop.bean;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class ProductAttr {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @Column
    private String productCatalog3Id;
    @Column
    private String property;
    @Column
    private double price;
    @Column
    private String url;
    @Column
    private int inventory;
}
