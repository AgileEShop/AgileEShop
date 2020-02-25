package cn.nju.edu.eshop.service;

import cn.nju.edu.eshop.bean.Product;
import cn.nju.edu.eshop.bean.ProductSearchParam;

import java.util.List;

public interface SearchService {
    List<Product> list(ProductSearchParam productSearchParam);
}
