package com.ants.monitor.biz.support.processor;

import com.ants.monitor.bean.MonitorConstants;
import com.ants.monitor.biz.dubboService.RegistryContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * Created by zxg on 15/11/3.
 * 14:04
 */
public class RegistryContainerProcessor implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private RegistryContainer registryContainer;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        //需要执行的逻辑代码，当spring容器初始化完成后就会执行该方法。
        ApplicationContext applicationContext = event.getApplicationContext().getParent();
        if(applicationContext == null){//root application context 没有parent，他就是老大.
            //需要执行的逻辑代码，当spring容器初始化完成后就会执行该方法。
            System.out.println("1=====start]");
            registryContainer.start();
            // ip地址的初始化
            MonitorConstants.initEcsMap();
        }else{
            // dubbo的数据初始化后的操作
            System.out.println("2=====start]");
            registryContainer.initRedisChangeAppCaChe();
        }

    }

//    @Override
//    public void onApplicationEvent(ApplicationEvent arg0)
//    {
//        if(arg0 instanceof ContextStartedEvent)
//        {
//            ApplicationContext parent = ((ContextStartedEvent) arg0).getApplicationContext().getParent();
//            if(parent != null){
//                System.out.println("parent not null ");
//            }
//            System.out.println("容器开始");
//        }else if(arg0 instanceof ContextRefreshedEvent)
//        {
//            System.out.println("容器刷新");
//        }else if(arg0 instanceof ContextStoppedEvent)
//        {
//            System.out.println("容器暂停");
//        }else if(arg0 instanceof ContextClosedEvent)
//        {
//            System.out.println("容器关闭");
//        }
//    }

}
