package cn.nju.edu.eshop.item.Controller;

import cn.nju.edu.eshop.bean.Product;
import cn.nju.edu.eshop.bean.ProductVO;
import cn.nju.edu.eshop.service.ProductService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class ItemController {
    @Reference
    ProductService productService;

    @RequestMapping("{productId}.html")
//    @ResponseBody
    public String item(@PathVariable String productId, ModelMap map, HttpServletRequest request){
//        String remoteAddr = request.getRemoteAddr(); //没有用nginx负载均衡的算法，获取用户的IP地址
//        String header = request.getHeader("");//使用nginx负载均衡的算法，获取用户的IP地址
        System.err.println("productId:"+productId);
        Product product = productService.getProductById(productId);
        ProductVO productVO = new ProductVO();
        productVO.setId(product.getId());
        productVO.setUserId(product.getUserId());
        productVO.setCatalog1Id(product.getCatalog1Id());
        productVO.setCatalog2Id(product.getCatalog2Id());
        productVO.setTitle(product.getTitle());
        productVO.setPrice(product.getPrice());
        productVO.setPlace(product.getPlace());
        productVO.setFee(product.getFee());
        String urls = product.getUrls();
        String[] imgUrls = urls.split(",");
        List<String>imageUrls = new ArrayList<>();
        productVO.setDefaultImageUrl(imgUrls[0]);
        imageUrls.addAll(Arrays.asList(imgUrls));
        productVO.setImageUrls(imageUrls);
        System.err.println("toString:"+product.toString());
        map.put("productVO",productVO);
        return "item";
//        return product;
    }

    @RequestMapping("index")
    public String index(ModelMap modelMap) {

        List<String> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add("循环数据" + i);
        }
        modelMap.put("list", list);
        modelMap.put("check", "0");
        modelMap.put("hello", "hello!!");

        return "index";
    }

}
