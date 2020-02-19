package cn.nju.edu.eshop.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
//
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//@Entity
//@Table(name = "product_catalog2")
public class ProductCatalog2 implements Serializable {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    @Column
    private String catalog1Id;

//    public ProductCatalog2(String name, String catalog1Id) {
//        this.name = name;
//        this.catalog1Id = catalog1Id;
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCatalog1Id() {
        return catalog1Id;
    }

    public void setCatalog1Id(String catalog1Id) {
        this.catalog1Id = catalog1Id;
    }
}
