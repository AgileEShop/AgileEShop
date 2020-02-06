package cn.nju.edu.eshop.manage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "cn.nju.edu.eshop.manage.mapper")
public class EshopManageServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EshopManageServiceApplication.class, args);
    }

}
