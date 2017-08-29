/**
 * Project: dubbo.registry.server-1.1.0-SNAPSHOT
 * 
 * File Created at 2010-7-27
 * 
 * Copyright 1999-2010 Alibaba.com Croporation Limited.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Alibaba Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Alibaba.com.
 */
package com.ants.monitor.common.tools;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.URL;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.Set;

/**
 * Tool
 * 
 * @author william.liangf
 */
public class Tool {

    public static String getInterface(String service) {
    	if (service != null && service.length() > 0) {
    		int i = service.indexOf('/');
        	if (i >= 0) {
        		service = service.substring(i + 1);
        	}
        	i = service.lastIndexOf(':');
        	if (i >= 0) {
        		service = service.substring(0, i);
        	}
    	}
        return service;
    }

    public static String getGroup(String service) {
    	if (service != null && service.length() > 0) {
    		int i = service.indexOf('/');
        	if (i >= 0) {
        		return service.substring(0, i);
        	}
    	}
        return null;
    }
    
    public static String getVersion(String service) {
    	if (service != null && service.length() > 0) {
    		int i = service.lastIndexOf(':');
        	if (i >= 0) {
        		return service.substring(i + 1);
        	}
    	}
        return null;
    }
    
    public static String getIP(String address) {
    	if (address != null && address.length() > 0) {
	    	int i = address.indexOf("://");
	    	if (i >= 0) {
	    	    address = address.substring(i + 3);
	    	}
	    	i = address.indexOf('/');
            if (i >= 0) {
                address = address.substring(0, i);
            }
            i = address.indexOf('@');
            if (i >= 0) {
                address = address.substring(i + 1);
            }
            i = address.indexOf(':');
            if (i >= 0) {
                address = address.substring(0, i);
            }
            if (address.matches("[a-zA-Z]+")) {
                try {
                    address = InetAddress.getByName(address).getHostAddress();
                } catch (UnknownHostException e) {
                }
            }
    	}
    	return address;
    }
    
    public static String encode(String url) {
        try {
            return URLEncoder.encode(url, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return url;
        }
    }


    /**
     * 获取用户真实IP地址，不使用request.getRemoteAddr();的原因是有可能用户使用了代理软件方式避免真实IP地址,
     *
     * 可是，如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值，究竟哪个才是真正的用户端的真实IP呢？
     * 答案是取X-Forwarded-For中第一个非unknown的有效IP字符串。
     *
     * 如：X-Forwarded-For：192.168.1.110, 192.168.1.120, 192.168.1.130,
     * 192.168.1.100
     *
     * 用户真实IP为： 192.168.1.110
     *
     * @param request
     * @return
     */

    private static final String[] HEADERS_TO_TRY = {
            "X-Forwarded-For",
            "Proxy-Client-IP",
            "WL-Proxy-Client-IP",
            "HTTP_X_FORWARDED_FOR",
            "HTTP_X_FORWARDED",
            "HTTP_X_CLUSTER_CLIENT_IP",
            "HTTP_CLIENT_IP",
            "HTTP_FORWARDED_FOR",
            "HTTP_FORWARDED",
            "HTTP_VIA",
            "REMOTE_ADDR",
            "X-Real-IP"};
    public static String getIpAddress(HttpServletRequest request) {
        for (String header : HEADERS_TO_TRY) {
            String ip = request.getHeader(header);
            if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
                return ip;
            }
        }
        return request.getRemoteAddr();
    }



    //判断是否是禁止的url
    public static Boolean compareIsOverride(URL url,Set<URL> forbitUrlSet){
        if(null == forbitUrlSet || forbitUrlSet.isEmpty()){
            return false;
        }
        String host = url.getHost();
        Integer port = url.getPort();
        String path = url.getPath();
        String version = url.getParameter(Constants.VERSION_KEY);

        Boolean result = false;
        for(URL compareUrl : forbitUrlSet){
            String compareHost = compareUrl.getHost();
            Integer comparePort = compareUrl.getPort();
            String comparePath = compareUrl.getPath();
            String compareVersion = compareUrl.getParameter(Constants.VERSION_KEY);
         //   if(host.equals(compareHost) && port.equals(comparePort) && path.equals(comparePath) && version.equals(compareVersion)){
            if(host.equals(compareHost) && port.equals(comparePort) && path.equals(comparePath)){
                result = true;
                break;
            }
        }
        return result;
    }
}
