package cn.nju.edu.eshop.bean;

import javax.persistence.Id;
import java.io.Serializable;

public class ProductCatalog3 implements Serializable {
    @Id
    private String id;
    private String name;
    private String productCatalog2Id;

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

    public String getProductCatalog2Id() {
        return productCatalog2Id;
    }

    public void setProductCatalog2Id(String productCatalog2Id) {
        this.productCatalog2Id = productCatalog2Id;
    }
}
