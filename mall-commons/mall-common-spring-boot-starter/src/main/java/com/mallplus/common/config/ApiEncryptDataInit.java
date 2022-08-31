package com.mallplus.common.config;


import com.mallplus.common.annotation.IgnoreAuth;
import com.mallplus.common.constant.HttpMethodTypePrefixConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ApiEncryptDataInit implements ApplicationContextAware {
	
	private Logger logger = LoggerFactory.getLogger(ApiEncryptDataInit.class);
	


	/**
	 * 需要对请求内容进行不需要解密的URI<br>
	 * 比如：/user/list<br>
	 * 不支持@PathVariable格式的URI
	 */
	public static List<String> notLoginUriList = new ArrayList<String>();
	private String contextPath;
	
    @Override
    public void setApplicationContext(ApplicationContext ctx) throws BeansException {
    	this.contextPath = ctx.getEnvironment().getProperty("server.servlet.context-path");
        Map<String, Object> beanMap = ctx.getBeansWithAnnotation(RestController.class);
        initData(beanMap);
        beanMap = ctx.getBeansWithAnnotation(Controller.class);
        initData(beanMap);
    }

	private void initData(Map<String, Object> beanMap) {
		if (beanMap != null) {
            for (Object bean : beanMap.values()) {
                Class<?> clz = bean.getClass();
                Method[] methods = clz.getMethods();
                for (Method method : methods) {

					if (method.isAnnotationPresent(IgnoreAuth.class)) {
						String uri = method.getAnnotation(IgnoreAuth.class).value();
						if (!StringUtils.hasText(uri)) {
							uri = getApiUri(clz, method);
						}
						logger.debug("NotNeedEncrypt URI: {}", uri);
						notLoginUriList.add(uri);
					}
                }
            }
        }
	}
    
    private String getApiUri(Class<?> clz, Method method) {
    	String methodType = "";
        StringBuilder uri = new StringBuilder();
        
        if (clz.isAnnotationPresent(RequestMapping.class)) {
        	uri.append(formatUri(clz.getAnnotation(RequestMapping.class).value()[0]));
		}
        
        if (method.isAnnotationPresent(GetMapping.class)) {
        	
        	methodType = HttpMethodTypePrefixConstant.GET;
            uri.append(formatUri(method.getAnnotation(GetMapping.class).value()[0]));
            
        } else if (method.isAnnotationPresent(PostMapping.class)) {
        	
        	methodType = HttpMethodTypePrefixConstant.POST;
            uri.append(formatUri(method.getAnnotation(PostMapping.class).value()[0]));
            
        } else if (method.isAnnotationPresent(RequestMapping.class)) {
        	
        	RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
        	RequestMethod m = requestMapping.method()[0];
        	methodType = m.name().toLowerCase() + ":";
            uri.append(formatUri(requestMapping.value()[0]));
            
        } else if (method.isAnnotationPresent(PutMapping.class)) {
        	
        	methodType = HttpMethodTypePrefixConstant.PUT;
            uri.append(formatUri(method.getAnnotation(PutMapping.class).value()[0]));
            
        } else if (method.isAnnotationPresent(GetMapping.class)) {
        	
        	methodType = HttpMethodTypePrefixConstant.DELETE;
            uri.append(formatUri(method.getAnnotation(GetMapping.class).value()[0]));
            
        }
        
        if (StringUtils.hasText(this.contextPath)) {
        	 return  this.contextPath + uri.toString();
		}
        return methodType + uri.toString();
    }
    
    private String formatUri(String uri) {
    	if (uri.startsWith("/")) {
			return uri;
		}
    	return "/" + uri;
    }
}
