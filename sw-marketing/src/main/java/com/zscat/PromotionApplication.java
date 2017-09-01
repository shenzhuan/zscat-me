package com.zscat;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

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
/**
 * @author : zscat [951449465]
 * @version : 1.0
 * @created on  : 14/03/2017  4:28 PM
 */

//@EnableWebMvc
//@SpringBootApplication
//@MapperScan(basePackages = "com.zscat.*.mapper")
//public class PromotionApplication  extends WebMvcConfigurerAdapter {
//    private static final Logger LOG = LoggerFactory.getLogger(PromotionApplication.class.getName());
//
//    public static void main(String[] args) {
//        SpringApplication application = new SpringApplication(PromotionApplication.class);
//        application.run(args);
//    }

}

