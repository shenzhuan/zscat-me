package com.zscat;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author : zscat [951449465]
 * @version : 1.0
 * @created on  : 14/03/2017  4:28 PM
 */
@Controller
@EnableWebMvc
@ServletComponentScan
@SpringBootApplication
public class ShopWebApplication extends WebMvcConfigurerAdapter {
    private static final Logger LOG = LoggerFactory.getLogger(ShopWebApplication.class.getName());

    public static void main(String[] args) {
        SpringApplication.run(ShopWebApplication.class, args);
    }
}
