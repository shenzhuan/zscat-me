package com.ants.monitor.bean.bizBean;

import lombok.Data;
import lombok.ToString;

import java.util.Set;

/**
 * Created by zxg on 15/11/17.
 */
@ToString
@Data
public class HostBO {
    private String host;
    private String port;

    public HostBO(){}
    public HostBO(String host, String port) {
        this.host = host;
        this.port = port;
    }

    private String hostString;

    public String getHostString(){
        if(port == null){
            return host;
        }
        return host+":"+port;
    }


    //======host 页面展示所用====
    Set<String> providers;
    Set<String> consumers;

    //服务名--即dba定义的该ip地址的名称
    String hostName;
    //对应的另外一个ip
    String anotherIp;
}
