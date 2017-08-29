package com.zscat;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author : zscat [951449465]
 * @version : 1.0
 * @created on  : 14/03/2017  4:28 PM
 */
@SpringBootApplication
@MapperScan(basePackages = "com.zscat.*.mapper")
public class ShopServiceApplication {


    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(ShopServiceApplication.class);
        application.run(args);
    }
}