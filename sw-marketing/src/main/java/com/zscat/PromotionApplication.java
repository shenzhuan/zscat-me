package com.zscat;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.WebApplicationInitializer;

/**
 * @author : zscat
 * @version : 1.0
 * @created on  : 14/03/2017  4:28 PM
 */
@SpringBootApplication
@MapperScan(basePackages = "com.zscat.*.mapper")
public class PromotionApplication  extends SpringBootServletInitializer implements WebApplicationInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {

        return application.sources(PromotionApplication.class);
    }
    public static void main(String[] args) {
        SpringApplication.run(PromotionApplication.class, args);
    }
}

