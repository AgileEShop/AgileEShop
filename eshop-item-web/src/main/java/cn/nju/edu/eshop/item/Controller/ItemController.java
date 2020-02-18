package cn.nju.edu.eshop.item.Controller;

import cn.nju.edu.eshop.service.ProductService;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@CrossOrigin
public class ItemController {
    @Reference
    ProductService productService;

    @RequestMapping("{productId}.html")
    public String item(@PathVariable String productId, ModelMap map, HttpServletRequest request){
        String remoteAddr = request.getRemoteAddr(); //没有用nginx负载均衡的算法，获取用户的IP地址
        String header = request.getHeader("");//使用nginx负载均衡的算法，获取用户的IP地址
        return "item";
    }

}
