package cn.nju.edu.eshop.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
}
