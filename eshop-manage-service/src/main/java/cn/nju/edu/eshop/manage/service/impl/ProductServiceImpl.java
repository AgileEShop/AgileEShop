package cn.nju.edu.eshop.manage.service.impl;

import cn.nju.edu.eshop.bean.Product;
import cn.nju.edu.eshop.manage.mapper.ProductMapper;
import cn.nju.edu.eshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductMapper productMapper;

    @Override
    public List<Product> productList(String productCatalog2Id) {
        return productMapper.selectProductByCatalog2Id(productCatalog2Id);
    }

    @Override
    public void saveProduct(Product product) {

    }

    @Override
    public Product getProductById(String id) {
        Example example = new Example(Product.class);
        Example.Criteria criteria = example.createCriteria();
        System.err.println("id="+id);
        criteria.andEqualTo("id",id);
        Product product = productMapper.selectOneByExample(example);
        System.err.println(product.toString());
        return product;
    }
}
