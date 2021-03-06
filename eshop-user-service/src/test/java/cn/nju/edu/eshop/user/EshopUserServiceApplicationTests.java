package cn.nju.edu.eshop.user;

import cn.nju.edu.eshop.EshopUserServiceApplication;
import cn.nju.edu.eshop.bean.User;
import cn.nju.edu.eshop.user.service.impl.UserServiceImpl;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = EshopUserServiceApplication.class)
@WebAppConfiguration
class EshopUserServiceApplicationTests {

    private UserServiceImpl userService;

    @Before
    public void setup() {
        this.userService = new UserServiceImpl();// 获取mockMvc实例
    }

    @Test
    void contextLoads() {
    }

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
