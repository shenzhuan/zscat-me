package com.alibaba.boot.dubbo;

import org.springframework.boot.context.properties.ConfigurationProperties;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;

@ConfigurationProperties(prefix = "spring.dubbo")
public class DubboProperties {

    private String            scan;

    private ApplicationConfig application;

    private RegistryConfig    registry;

    private ProtocolConfig    protocol;

    public String getScan() {
        return scan;
    }

    public ApplicationConfig getApplication() {
        return application;
    }

    public void setApplication(ApplicationConfig application) {
        this.application = application;
    }

    public RegistryConfig getRegistry() {
        return registry;
    }

    public void setRegistry(RegistryConfig registry) {
        this.registry = registry;
    }

    public ProtocolConfig getProtocol() {
        return protocol;
    }

    public void setProtocol(ProtocolConfig protocol) {
        this.protocol = protocol;
    }

    public void setScan(String scan) {
        this.scan = scan;
    }

}
