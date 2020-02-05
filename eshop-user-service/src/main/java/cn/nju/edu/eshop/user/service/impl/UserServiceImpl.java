package cn.nju.edu.eshop.user.service.impl;

import cn.nju.edu.eshop.bean.User;
import cn.nju.edu.eshop.bean.UserReceiveAddress;
import cn.nju.edu.eshop.service.UserService;
import cn.nju.edu.eshop.user.mapper.UserMapper;
import cn.nju.edu.eshop.user.mapper.UserReceiveAddressMapper;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    UserReceiveAddressMapper userReceiveAddressMapper;
    @Override
    public List<User> getAllUser() {
        return userMapper.selectAll();
    }

    @Override
    public List<UserReceiveAddress> getReceiveAddressByUserId(String userId) {
        UserReceiveAddress userReceiveAddress = new UserReceiveAddress();
        userReceiveAddress.setUserId(userId);
        return userReceiveAddressMapper.select(userReceiveAddress);
    }
}
