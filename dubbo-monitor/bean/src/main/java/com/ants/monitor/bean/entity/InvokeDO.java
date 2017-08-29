package com.ants.monitor.bean.entity;

import lombok.Data;

import java.util.Date;

@Data
public class InvokeDO {
    private String uuId;

    private String application;

    private String appType;

    private String service;

    private String method;

    private String consumerHost;

    private String consumerPort;

    private String providerHost;

    private String providerPort;

    private Integer success;

    private Integer failure;

    private Integer elapsed;

    private Integer concurrent;

    private Integer maxElapsed;

    private Integer maxConcurrent;

    private String invokeDate;

    private String invokeHour;

    private Long invokeTime;

    private Date gmtCreate;
}