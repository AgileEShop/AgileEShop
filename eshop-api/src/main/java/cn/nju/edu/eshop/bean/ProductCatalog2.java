package cn.nju.edu.eshop.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

public class ProductCatalog2 implements Serializable {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @Column
    private String name;
    @Column
    private String productCatalog1Id;
    @Transient
    private List<ProductCatalog3> productCatalog3List;

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

    public String getProductCatalog1Id() {
        return productCatalog1Id;
    }

    public void setProductCatalog1Id(String productCatalog1Id) {
        this.productCatalog1Id = productCatalog1Id;
    }

    public List<ProductCatalog3> getProductCatalog3List() {
        return productCatalog3List;
    }

    public void setProductCatalog3List(List<ProductCatalog3> productCatalog3List) {
        this.productCatalog3List = productCatalog3List;
    }
}
