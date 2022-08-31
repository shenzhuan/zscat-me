/*
package com.mallplus.gateway.filter.pre;
 
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
 

@Component
public class LoggingFilter implements GlobalFilter, Ordered {
 
    private static final Log logger = LogFactory.getLog(LoggingFilter.class);
    private static final String START_TIME = "startTime";
 
    public LoggingFilter() {
        logger.info("Loaded GlobalFilter [Logging]");
    }
 
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String info = String.format("Method:{%s} Host:{%s} Path:{%s} Query:{%s}",
                exchange.getRequest().getMethod().name(),
                exchange.getRequest().getURI().getHost(),
                exchange.getRequest().getURI().getPath(),
                exchange.getRequest().getQueryParams());
 
        logger.info(info);
 
        exchange.getAttributes().put(START_TIME, System.currentTimeMillis());
        return chain.filter(exchange).then( Mono.fromRunnable(() -> {
            Long startTime = exchange.getAttribute(START_TIME);
            if (startTime != null) {
                Long executeTime = (System.currentTimeMillis() - startTime);
                logger.info(exchange.getRequest().getURI().getRawPath() + " : " + executeTime + "ms");
            }
        }));
    }
 
    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}*/
