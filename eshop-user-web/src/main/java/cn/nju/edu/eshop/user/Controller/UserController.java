package cn.nju.edu.eshop.user.Controller;

import cn.nju.edu.eshop.bean.User;
import cn.nju.edu.eshop.bean.UserReceiveAddress;
import cn.nju.edu.eshop.service.UserService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UserController {

    @Reference
    UserService userService;

    @RequestMapping("getReceiveAddressByUserId")
    public List<UserReceiveAddress> getReceiveAddressByUserId(String userId){
        return userService.getReceiveAddressByUserId(userId);
    }

    @RequestMapping("getAllUser")
    @ResponseBody
    public List<User> getAllUser(){
        return userService.getAllUser();
    }

    @RequestMapping("getOneUser")
    @ResponseBody
    public User getOneUser(){
        return userService.getOneUser();
    }

    @RequestMapping("index")
    @ResponseBody
    public String index(){
        return "hello user";
    }


}
