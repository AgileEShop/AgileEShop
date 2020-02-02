package cn.nju.edu.eshop.user.controller;

import cn.nju.edu.eshop.bean.User;
import cn.nju.edu.eshop.bean.UserReceiveAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import cn.nju.edu.eshop.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UserController {
    @Autowired
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

    @RequestMapping("index")
    @ResponseBody
    public String index(){
        return "hello user";
    }


}
