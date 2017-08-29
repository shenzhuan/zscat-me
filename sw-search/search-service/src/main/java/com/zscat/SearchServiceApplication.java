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
public class SearchServiceApplication {


    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(SearchServiceApplication.class);
        application.run(args);
    }
}