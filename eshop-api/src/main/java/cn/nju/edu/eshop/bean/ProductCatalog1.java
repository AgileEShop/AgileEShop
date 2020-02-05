package cn.nju.edu.eshop.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

public class ProductCatalog1 implements Serializable {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @Column
    private String name;
    @Transient
    private List<ProductCatalog2> productCatalog2List;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ProductCatalog2> getProductCatalog2List() {
        return productCatalog2List;
    }

    public void setProductCatalog2List(List<ProductCatalog2> productCatalog2List) {
        this.productCatalog2List = productCatalog2List;
    }
}
