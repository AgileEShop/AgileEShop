package cn.nju.edu.eshop.user.service.impl;

import cn.nju.edu.eshop.bean.User;
import cn.nju.edu.eshop.bean.UserReceiveAddress;
import cn.nju.edu.eshop.service.UserService;
import cn.nju.edu.eshop.user.mapper.UserMapper;
import cn.nju.edu.eshop.user.mapper.UserReceiveAddressMapper;
import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import cn.nju.edu.eshop.util.RedisUtil;
import tk.mybatis.mapper.entity.Example;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    UserReceiveAddressMapper userReceiveAddressMapper;
    @Autowired
    RedisUtil redisUtil;

    @Override
    public List<User> getAllUser() {
        return userMapper.selectAll();
    }

    @Override
    public List<UserReceiveAddress> getReceiveAddressByUserId(String userId) {
        UserReceiveAddress userReceiveAddress = new UserReceiveAddress();
        System.err.println("userId:"+userId);
        userReceiveAddress.setUserId(userId);
        return userReceiveAddressMapper.select(userReceiveAddress);
    }

    @Override
    public User login(User user) {
        //passport认证中心
        System.err.println("login");
        try (Jedis jedis = redisUtil.getJedis()) {
            if (jedis != null) {
                String userStr = jedis.get("user:" + user.getPassword() + ":info");
                if (StringUtils.isNotBlank(userStr)) {
                    return JSON.parseObject(userStr, User.class);// 用户名，密码正确
                }
            }
            // 链接redis失败，访问数据库
            User userFromDb = loginFromDb(user);
            assert jedis != null;
            jedis.setex("user:" + user.getPassword() + ":info", 60 * 60 * 24, JSON.toJSONString(userFromDb));
            return userFromDb;
        }
    }

    @Override
    public void addUserToken(String token, String userId) {
        System.err.println("add user token");
        Jedis jedis = redisUtil.getJedis();
        jedis.setex("user:" + userId + ":token", 60 * 60 * 2, token);
        jedis.close();
    }

    @Override
    public User getOneUser() {
        Example example = new Example(User.class);
        Example.Criteria criteria= example.createCriteria();
        criteria.andEqualTo("id",1);
        User user = userMapper.selectOneByExample(example);
        System.out.println(user.toString());
        return user;
    }

    @Override
    public UserReceiveAddress getReceiveAddressById(String receiveAddress) {
        UserReceiveAddress userReceiveAddress = new UserReceiveAddress();
        userReceiveAddress.setId(receiveAddress);

        return userReceiveAddressMapper.selectOne(userReceiveAddress);
    }

    private User loginFromDb(User user) {
        System.err.println("login from db");
        List<User> userList = userMapper.select(user);
        if(userList!=null){
            return userList.get(0);
        }
        return null;
//        User newUser = new User();
//        newUser.setId("000001");
//        newUser.setPassword("123456");
//        newUser.setNickname("aaaaaa");
//        newUser.setPhone("111111");
//        newUser.setStatus(0);
//        newUser.setUsername("abcdef");
//        newUser.setCreateTime(new Date(2018, Calendar.FEBRUARY, 1));
//        newUser.setModifyTime(new Date(2019, Calendar.FEBRUARY, 1));
//        return newUser;
    }
}
