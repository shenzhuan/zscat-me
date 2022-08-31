package com.mallplus.cms.filter;

import com.mallplus.common.utils.ValidatorUtils;
import com.mallplus.common.vo.ApiContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
//顺序越小越先执行
@Order(1)
@WebFilter(filterName = "myfilter",urlPatterns = "/*")
/**
 * filter
 */
public class Storefilter implements Filter {
    @Autowired
    private ApiContext apiContext;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("filter初始化");

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        log.info(request.getRequestURI());
        String storeId = request.getParameter("storeid");
        if (ValidatorUtils.notEmpty(storeId)){
            apiContext.setCurrentProviderId(Long.valueOf(storeId));
        }else {
            storeId = request.getHeader("storeid");
            if (ValidatorUtils.notEmpty(storeId)){
                apiContext.setCurrentProviderId(Long.valueOf(storeId));
            }
        }
        System.out.println("goods=storeId = "+apiContext.getCurrentProviderId());

        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
