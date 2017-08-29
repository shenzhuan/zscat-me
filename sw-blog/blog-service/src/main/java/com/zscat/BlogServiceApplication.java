package com.zscat;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author : zscat [951449465]
 * @version : 1.0
 * @created on  : 14/03/2017  4:28 PM
 */

@SpringBootApplication
@MapperScan(basePackages = "com.zscat.*.mapper")
public class BlogServiceApplication  {


    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(BlogServiceApplication.class);
        application.run(args);
    }
}
