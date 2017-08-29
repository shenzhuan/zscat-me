package com.alibaba.boot.dubbo.endpoint;

import java.util.List;

public class ProviderBean {

    private String       target;
    private String       serviceInterface;
    private String       serviceVersion;
    private String       serviceGroup;
    private Integer      clientTimeout;
    private List<String> methodNames;

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getServiceInterface() {
        return serviceInterface;
    }

    public void setServiceInterface(String serviceInterface) {
        this.serviceInterface = serviceInterface;
    }

    public String getServiceVersion() {
        return serviceVersion;
    }

    public void setServiceVersion(String serviceVersion) {
        this.serviceVersion = serviceVersion;
    }

    public String getServiceGroup() {
        return serviceGroup;
    }

    public void setServiceGroup(String serviceGroup) {
        this.serviceGroup = serviceGroup;
    }

    public Integer getClientTimeout() {
        return clientTimeout;
    }

    public void setClientTimeout(Integer clientTimeout) {
        this.clientTimeout = clientTimeout;
    }

    public List<String> getMethodNames() {
        return methodNames;
    }

    public void setMethodNames(List<String> methodNames) {
        this.methodNames = methodNames;
    }
}
