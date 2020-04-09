package cn.nju.edu.eshop.service;

import cn.nju.edu.eshop.bean.User;
import cn.nju.edu.eshop.order.controller.OrderController;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {
    @Autowired
    UserService userService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getAllUser() {
        userService.getAllUser();
    }

    @Test
    void getReceiveAddressByUserId() {
        userService.getReceiveAddressByUserId("feng");
    }

    @Test
    void login() {
        User user = new User();
        user.setUsername("fengfeng");
        user.setPassword("123456");
        userService.login(user);
    }

    @Test
    void addUserToken() {
        userService.addUserToken("IMDIU9VapP9RCkUX/MyhJa6HMErYdwAgicnXfv4UfRw=", "feng");
    }

    @Test
    void getOneUser() {
        userService.getOneUser();
    }

    @Test
    void getReceiveAddressById() {
        userService.getReceiveAddressByUserId("feng");
    }

}