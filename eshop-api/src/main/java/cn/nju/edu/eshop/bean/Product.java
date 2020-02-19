package cn.nju.edu.eshop.bean;

//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
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
    private int id;

    @Column
    private int userId;

    @Column
    private int catalog1Id;
    @Column
    private int catalog2Id;

    @Column
    private String title;

    @Column
    private double price;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCatalog1Id() {
        return catalog1Id;
    }

    public void setCatalog1Id(int catalog1Id) {
        this.catalog1Id = catalog1Id;
    }

    public int getCatalog2Id() {
        return catalog2Id;
    }

    public void setCatalog2Id(int catalog2Id) {
        this.catalog2Id = catalog2Id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
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
