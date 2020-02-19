package cn.nju.edu.eshop.service;

import cn.nju.edu.eshop.bean.Product;

import java.util.List;

public interface ProductService {
    List<Product> productList(String productCatalog2Id);
    void saveProduct(Product product);
    Product getProductById(String productId);


}
