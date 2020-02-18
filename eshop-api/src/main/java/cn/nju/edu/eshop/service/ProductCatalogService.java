package cn.nju.edu.eshop.service;

import cn.nju.edu.eshop.bean.ProductCatalog1;
import cn.nju.edu.eshop.bean.ProductCatalog2;

import java.util.List;

public interface ProductCatalogService {
    List<ProductCatalog1> getProductCatalog1();
    List<ProductCatalog2> getProductCatalog2(String productCatalog1Id);
}
