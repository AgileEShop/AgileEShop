package cn.nju.edu.eshop.manage.service.impl;

import cn.nju.edu.eshop.bean.ProductCatalog1;
import cn.nju.edu.eshop.bean.ProductCatalog2;
//import cn.nju.edu.eshop.bean.ProductCatalog3;
import cn.nju.edu.eshop.manage.mapper.ProductCatalog1Mapper;
import cn.nju.edu.eshop.manage.mapper.ProductCatalog2Mapper;
import cn.nju.edu.eshop.service.ProductCatalogService;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class ProductCatalogServiceImpl implements ProductCatalogService {
    @Autowired
    ProductCatalog1Mapper productCatalog1Mapper;
    @Autowired
    ProductCatalog2Mapper productCatalog2Mapper;
    @Override
    public List<ProductCatalog1> getProductCatalog1() {
        return productCatalog1Mapper.selectAll();
    }
    @Override
    public List<ProductCatalog2> getProductCatalog2(String productCatalog1Id) {
        ProductCatalog2 productCatalog2 = new ProductCatalog2();
//        productCatalog2.setProductCatalog1Id(productCatalog1Id);
        return productCatalog2Mapper.selectAll();
    }

}
