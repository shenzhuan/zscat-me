package com.zscat.marketing.config;

import org.springframework.context.annotation.Configuration;

import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;



/**
 * @author Jonsy
 *
 */
@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/signup").setViewName("signup");
        registry.addViewController("/error").setViewName("error");
        registry.addViewController("/403").setViewName("error/403");
        registry.addViewController("/401").setViewName("error/401");
        registry.addViewController("/404").setViewName("error/404");

    }
//    @Bean
//    public FilterRegistrationBean WxAuthFilter(){
//        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WxAuthFilter());
//        //添加过滤规则.
//        filterRegistrationBean.addUrlPatterns("/*");
//        //添加不需要忽略的格式信息.
//        filterRegistrationBean.addInitParameter(
//                "exclusions","/static/*,*.js,*.gif,*.jpg,*.png,*.css,*.ico");
//
//        return filterRegistrationBean;
//    }

}
