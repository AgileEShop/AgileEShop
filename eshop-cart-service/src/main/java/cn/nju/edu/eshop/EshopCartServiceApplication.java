package cn.nju.edu.eshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "cn.nju.edu.eshop.cart.mapper")
public class EshopCartServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EshopCartServiceApplication.class, args);
	}

}
