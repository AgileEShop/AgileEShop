package cn.nju.edu.eshop.bean;

//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//@Entity
//@Table(name = "product")
public class Product implements Serializable {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column
    private String userId;

    @Column
    private String catalog1Id;
    @Column
    private String catalog2Id;

    @Column
    private String title;

    @Column
    private BigDecimal price;
    @Column
    private String place;//发货地
    @Column
    private double fee;//快递费

    @Column
    private String urls;//图片列表

    @Column
    private Timestamp createTime;
    @Column
    private Timestamp modifyTime;

//    public Product(int userId, int catalog1Id, int catalog2Id, String title, double price, String place, double fee, String urls, Timestamp createTime, Timestamp modifyTime) {
//        this.userId = userId;
//        this.catalog1Id = catalog1Id;
//        this.catalog2Id = catalog2Id;
//        this.title = title;
//        this.price = price;
//        this.place = place;
//        this.fee = fee;
//        this.urls = urls;
//        this.createTime = createTime;
//        this.modifyTime = modifyTime;
//    }

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

    public String getCatalog1Id() {
        return catalog1Id;
    }

    public void setCatalog1Id(String catalog1Id) {
        this.catalog1Id = catalog1Id;
    }

    public String getCatalog2Id() {
        return catalog2Id;
    }

    public void setCatalog2Id(String catalog2Id) {
        this.catalog2Id = catalog2Id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public String getUrls() {
        return urls;
    }

    public void setUrls(String urls) {
        this.urls = urls;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Timestamp modifyTime) {
        this.modifyTime = modifyTime;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", userId=" + userId +
                ", catalog1Id=" + catalog1Id +
                ", catalog2Id=" + catalog2Id +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", place='" + place + '\'' +
                ", fee=" + fee +
                ", urls='" + urls + '\'' +
                ", createTime=" + createTime +
                ", modifyTime=" + modifyTime +
                '}';
    }
}
