package com.mallplus.gateway.config;

import com.didispace.swagger.butler.EnableSwaggerButler;
import org.springframework.context.annotation.Configuration;

/**
 * swagger 聚合文档配置
 * zuul routers 映射具体服务的/v2/api-docs swagger
 *
 * @author 作者 mallplus E-mail: 951449465@qq.com
 */
@Configuration
@EnableSwaggerButler
public class SwaggerConfig {

}