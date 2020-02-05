package cn.nju.edu.eshop.manage.Controller;

import cn.nju.edu.eshop.bean.ProductCatalog1;
import cn.nju.edu.eshop.bean.ProductCatalog2;
import cn.nju.edu.eshop.bean.ProductCatalog3;
import cn.nju.edu.eshop.service.ProductCatalogService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@CrossOrigin
public class ProductCatalogController {
    @Reference
    ProductCatalogService productCatalogService;

    @RequestMapping("getProductCatalog3")
    @ResponseBody
    public List<ProductCatalog3> getProductCatalog3(String productCatalog2Id){
        return productCatalogService.getProductCatalog3(productCatalog2Id);
    }

    @RequestMapping("getProductCatalog2")
    @ResponseBody
    public List<ProductCatalog2> getProductCatalog2(String productCatalog1Id){
        return productCatalogService.getProductCatalog2(productCatalog1Id);
    }

    @RequestMapping("getProductCatalog1")
    @ResponseBody
    public List<ProductCatalog1> getProductCatalog1(){
        return productCatalogService.getProductCatalog1();
    }
}
