package com.zscat.filter;

import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * web 组件配置
 * 
 * @author sdcuike
 *         <p>
 *         Created on 2017-02-09
 *         <p>
 *         web组件如Filter等注解配置，支持依赖注入，但spring的@Order注解不支持排序;
 * @WebFilter has no element to define the order of filter of execution.
 */
@Configuration
@ServletComponentScan
public class WebComponentConfig {

}