package com.zscat;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author : zscat [951449465]
 * @version : 1.0
 * @created on  : 14/03/2017  4:28 PM
 */
@SpringBootApplication
@MapperScan(basePackages = "com.zscat.*.mapper")
public class CmsServiceApplication  {


    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(CmsServiceApplication.class);
        application.run(args);
    }
}