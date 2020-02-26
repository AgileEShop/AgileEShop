package cn.nju.edu.eshop.service;

import cn.nju.edu.eshop.bean.User;
import cn.nju.edu.eshop.bean.UserReceiveAddress;

import java.util.List;

public interface UserService {
    List<User> getAllUser();
    List<UserReceiveAddress> getReceiveAddressByUserId(String userId);
    User login(User user);
    void addUserToken(String token,String userid);
    User getOneUser();
    UserReceiveAddress getReceiveAddressById(String receiveAddress);
}
