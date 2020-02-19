package cn.nju.edu.eshop.item.Controller;

import cn.nju.edu.eshop.bean.Product;
import cn.nju.edu.eshop.service.ProductService;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@CrossOrigin
public class ItemController {
    @Reference
    ProductService productService;

    @RequestMapping("{productId}.html")
    @ResponseBody
    public Product item(@PathVariable String productId, ModelMap map, HttpServletRequest request){
//        String remoteAddr = request.getRemoteAddr(); //没有用nginx负载均衡的算法，获取用户的IP地址
//        String header = request.getHeader("");//使用nginx负载均衡的算法，获取用户的IP地址
        System.err.println(productId);
        Product product = productService.getProductById(productId);
//        System.out.println(product.toString());
//        map.put("product",product);
//        return "item";
        return product;
    }

    @RequestMapping("index")
    @ResponseBody
    public String index(ModelMap modelMap) {

//        List<String> list = new ArrayList<>();
//        for (int i = 0; i < 5; i++) {
//            list.add("循环数据" + i);
//        }
//        modelMap.put("list", list);
//        modelMap.put("chk", "0");
//        modelMap.put("hello", "hello!!");

        return "index";
    }

}
