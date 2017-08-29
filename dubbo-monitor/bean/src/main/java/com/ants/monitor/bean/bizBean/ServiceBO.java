package com.ants.monitor.bean.bizBean;

import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * services 的biz bean类
 * Created by zxg on 15/11/16.
 */
@Data
public class ServiceBO {

    private String serviceName;

    private Set<String> methods;

    private String owner;

    private Boolean isConsumer = false;

    // 若同个service 存在的方法不一样，则此service 出错
    private Boolean isWrong = false;

    //错误原因
    private String wrongReason;

    // ==================services.ftl 使用====================

    // 所属团队
    private String  organization ;

    // 所属的application
    private Set<String> ownerApp;

    //使用的app
    private Set<String> usedApp;

    //本地起了测试或线上，测试起了线上
    private Boolean isHostWrong = false;

    //每个method提供的host地址
    private Map<String,Set<HostBO>> methodsHost;

    //最后消费时间
    private String finalConsumerTime = "1997-01-01 00:00:00";

}
