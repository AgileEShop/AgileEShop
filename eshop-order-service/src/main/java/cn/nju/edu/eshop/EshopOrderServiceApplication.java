package cn.nju.edu.eshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "cn.nju.edu.eshop.order.mapper")
public class EshopOrderServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EshopOrderServiceApplication.class, args);
    }

}
