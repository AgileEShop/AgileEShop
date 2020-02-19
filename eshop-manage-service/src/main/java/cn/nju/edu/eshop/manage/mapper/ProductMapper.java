package cn.nju.edu.eshop.manage.mapper;

import cn.nju.edu.eshop.bean.Product;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ProductMapper extends Mapper<Product> {
    Product selectProductById(String productId);
    List<Product> selectProductByCatalog2Id(String productCatalog2Id);
}
