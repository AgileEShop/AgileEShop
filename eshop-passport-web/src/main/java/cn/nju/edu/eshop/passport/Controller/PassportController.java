package cn.nju.edu.eshop.passport.Controller;

import cn.nju.edu.eshop.bean.User;
import cn.nju.edu.eshop.service.UserService;
import cn.nju.edu.eshop.util.JwtUtil;
import com.alibaba.dubbo.config.annotation.Reference;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class PassportController {
    @Reference
    UserService userService;

    @RequestMapping("verify")
    @ResponseBody
    public String verify(String token, String currentIp, HttpServletRequest request) {
        // 通过jwt校验token真假
        Map<String, String> map = new HashMap<>();
        Map<String, Object> decode = JwtUtil.decode(token, "agileeshop", currentIp);
        if (decode != null) {
            map.put("status", "success");
            map.put("userId", (String) decode.get("userId"));
            map.put("nickname", (String) decode.get("nickname"));
        } else {
            map.put("status", "fail");
        }
        return JSON.toJSONString(map);
    }

    @RequestMapping("login")
    @ResponseBody
    public String login(User user, HttpServletRequest request) {
        String token = "";
        User userLogin = userService.login(user);
        if (userLogin != null) {
            // 登录成功, 用jwt制作token ， 将token存入redis
            // 在web-util中有一个jwt加密算法，定义了一个map<String , Object>
            String userId = userLogin.getId();
            String nickname = userLogin.getNickname();
            Map<String, Object> userMap = new HashMap<>();
            userMap.put("userId", userId);
            userMap.put("nickname", nickname);
            String ip = request.getHeader("x-forwarded-for");// 通过nginx转发的客户端ip
            if (StringUtils.isBlank(ip)) {
                ip = request.getRemoteAddr();//从request中获取ip
                if (StringUtils.isBlank(ip)) {
                    ip = "127.0.0.1";
                }
            }
            // 按照设计的算法对参数进行加密后，生成token
            token = JwtUtil.encode("agileeshop", userMap, ip);
            // 将token存入redis一份
            userService.addUserToken(token, userId);
        } else {
            // 登录失败
            token = "fail";
        }
        return token;
    }

    @RequestMapping("index")
    public String index(String ReturnUrl, ModelMap map) {
        map.put("ReturnUrl", ReturnUrl);
        return "index";
    }
}
