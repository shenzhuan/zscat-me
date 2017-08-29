package com.zscat;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
public class BlogWebApplication  extends WebMvcConfigurerAdapter{
    private static final Logger LOG = LoggerFactory.getLogger(BlogWebApplication.class.getName());

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(BlogWebApplication.class);
        application.run(args);
    }
}
