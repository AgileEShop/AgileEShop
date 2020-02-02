package cn.nju.edu.eshop.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "cn.nju.edu.eshop.user.mapper")
public class EshopUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(EshopUserApplication.class, args);
    }

}
