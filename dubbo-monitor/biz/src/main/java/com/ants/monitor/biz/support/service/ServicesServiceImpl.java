package com.ants.monitor.biz.support.service;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.utils.ConcurrentHashSet;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.ants.monitor.bean.MonitorConstants;
import com.ants.monitor.bean.bizBean.HostBO;
import com.ants.monitor.bean.bizBean.ServiceBO;
import com.ants.monitor.biz.dubboService.DubboMonitorService;
import com.ants.monitor.biz.dubboService.RegistryContainer;
import com.ants.monitor.common.tools.TimeUtil;
import com.ants.monitor.common.tools.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by zxg on 15/11/11.
 */
@Service
public class ServicesServiceImpl implements ServicesService {


    @Autowired
    private RegistryContainer registryContainer;


    @Override
    public Set<String> getAllServicesString() {
        Set<String> resultServices = new ConcurrentHashSet<>();

        Map<String, Map<String, Set<URL>>> registry = registryContainer.getRegistryCache();

        Map<String, Set<URL>> providersServices = registry.get(Constants.PROVIDERS_CATEGORY);
        Map<String, Set<URL>> consumersServices = registry.get(Constants.CONSUMERS_CATEGORY);
        for (Map.Entry<String, Set<URL>> serviceEntry : providersServices.entrySet()) {
            String service = serviceEntry.getKey();
            resultServices.add(service);
        }
        for (Map.Entry<String, Set<URL>> serviceEntry : consumersServices.entrySet()) {
            String service = serviceEntry.getKey();
            resultServices.add(service);
        }

        return resultServices;
    }

    @Override
    public Map<String, Set<String>> getServiceProviders() {
        Map<String, Set<String>> ServiceProviders = new ConcurrentHashMap<>();

        Map<String, Map<String, Set<URL>>> registry = registryContainer.getRegistryCache();
        Map<String, Set<URL>> providerServices = registry.get(Constants.PROVIDERS_CATEGORY);

        for (Map.Entry<String, Set<URL>> serviceEntry : providerServices.entrySet()) {
            String service = serviceEntry.getKey();
            Set<String> applications = ServiceProviders.get(service);
            if(null == applications){
                applications = new ConcurrentHashSet<>();
                ServiceProviders.put(service,applications);
            }
            Set<URL> urls = serviceEntry.getValue();
            for(URL url : urls){
                String application = url.getParameter(Constants.APPLICATION_KEY);
                if(null != application) {
                    applications.add(application);
                }
            }
        }
        return ServiceProviders;
    }

    @Override
    public Map<String, Set<String>> getServiceConsumers() {
        Map<String, Set<String>> ServiceConsumers = new ConcurrentHashMap<>();

        Map<String, Map<String, Set<URL>>> registry = registryContainer.getRegistryCache();
        Map<String, Set<URL>> consumeServices = registry.get(Constants.CONSUMERS_CATEGORY);

        for (Map.Entry<String, Set<URL>> serviceEntry : consumeServices.entrySet()) {
            String service = serviceEntry.getKey();
            Set<String> applications = ServiceConsumers.get(service);
            if(null == applications){
                applications = new ConcurrentHashSet<>();
                ServiceConsumers.put(service,applications);
            }
            Set<URL> urls = serviceEntry.getValue();
            for(URL url : urls){
                String application = url.getParameter(Constants.APPLICATION_KEY);
                if(null != application) {
                    applications.add(application);
                }
            }
        }
        return ServiceConsumers;
    }

    @Override
    public Map<String, ServiceBO> getServiceBOMap() {
        Map<String, ServiceBO> serviceBOMap = new HashMap<>();

        Map<String, Map<String, Set<URL>>> registry = registryContainer.getRegistryCache();

        Map<String, Set<URL>> providersServices = registry.get(Constants.PROVIDERS_CATEGORY);
        Map<String, Set<URL>> consumersServices = registry.get(Constants.CONSUMERS_CATEGORY);
        Map<String, Set<URL>> forbidServices = registry.get(Constants.CONFIGURATORS_CATEGORY);

        //测试环境url
        Set<String> testUrlSet = new HashSet<>();
        for(Map.Entry<String,String> entry : MonitorConstants.ecsTestMap.entrySet()){
            testUrlSet.add(entry.getKey());
            testUrlSet.add(entry.getValue());
        }
        //所有服务器url,除测试环境外
        Set<String> onlineUrlSet = new HashSet<>();
        for(Map.Entry<String,String> entry : MonitorConstants.ecsMap.entrySet()){
            String url = entry.getKey();
            if(!testUrlSet.contains(url)) {
                onlineUrlSet.add(url);
            }
        }

        for (Map.Entry<String, Set<URL>> serviceEntry : providersServices.entrySet()) {
            String service = serviceEntry.getKey();
            Set<URL> urlSet = serviceEntry.getValue();

            ServiceBO serviceBO = serviceBOMap.get(service);
            if(null == serviceBO){
                serviceBO = new ServiceBO();
                serviceBO.setServiceName(service);
            }
            String finalTime = "";
            for(URL url : urlSet){
                //是否被禁止,禁止则不出现
                Set<URL> forbidSet = forbidServices.get(url.getServiceInterface());
                if(null != forbidSet && !forbidSet.isEmpty()) {
                    if (Tool.compareIsOverride(url, forbidSet)) {
                        continue;
                    }
                }
                String application = url.getParameter(Constants.APPLICATION_KEY);
                String organization = url.getParameter(MonitorConstants.ORGANICATION);
                String owner = url.getParameter(MonitorConstants.OWNER);
                if(StringUtils.isEmpty(serviceBO.getOrganization())) serviceBO.setOrganization(organization == null ? "" : organization);
                if(StringUtils.isEmpty(serviceBO.getOwner())) serviceBO.setOwner(owner == null ? "" : owner);

                //method set
                ServiceBOSetMethods(serviceBO,url);
                //owner app set
                Set<String> ownerApp = serviceBO.getOwnerApp();
                if(null == ownerApp) ownerApp = new HashSet<>();
                ownerApp.add(application);
                serviceBO.setOwnerApp(ownerApp);
                //本地起了测试或线上，测试起了线上
                String host = url.getHost();
                String hostTime = DubboMonitorService.getServiceConsumerTime(service,host);
                if(hostTime != null) {
                    //时间处理
                    if (finalTime.equals("")) {
                        finalTime = hostTime;
                    } else {
                        try {
                            //比较时间先后顺序，取靠后的时间
                            Boolean compareResult = TimeUtil.compareTime(hostTime, finalTime);
                            if (compareResult) finalTime = hostTime;
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                }

                if(service.endsWith("1.0.0") && !onlineUrlSet.contains(host)){
                    serviceBO.setIsHostWrong(true);
                }
                if(service.endsWith("1.0.0.daily") && !testUrlSet.contains(host)){
                    serviceBO.setIsHostWrong(true);
                }
            }

            serviceBO.setFinalConsumerTime(finalTime);
            serviceBOMap.put(service,serviceBO);
        }

        for (Map.Entry<String, Set<URL>> serviceEntry : consumersServices.entrySet()) {
            String service = serviceEntry.getKey();
            Set<URL> urlSet = serviceEntry.getValue();
            ServiceBO serviceBO = serviceBOMap.get(service);
            if(null == serviceBO){
                serviceBO = new ServiceBO();
                serviceBO.setServiceName(service);
            }
            for(URL url : urlSet){
                String applicationName = url.getParameter(Constants.APPLICATION_KEY);
                Set<String> usedSet = serviceBO.getUsedApp();
                if(usedSet == null){
                    usedSet = new HashSet<>();
                }
                usedSet.add(applicationName);
                serviceBO.setUsedApp(usedSet);
            }
            serviceBOMap.put(service,serviceBO);
        }

        return serviceBOMap;
    }



    // 保存method，若method不一致，则保存多条----保存methods所在的host
    private void ServiceBOSetMethods(ServiceBO serviceBO,URL url){

        String methods = url.getParameter(Constants.METHODS_KEY);


        Set<String> methodSet = serviceBO.getMethods();
        if(null == methodSet){
            methodSet = new HashSet<>();
            methodSet.add(methods);
            serviceBO.setMethods(methodSet);
        }else{
            String oldMethods = methodSet.iterator().next();
            List<String> old_list = Arrays.asList(oldMethods.split(","));
            List<String> now_list = Arrays.asList(methods.split(","));
            Boolean isWrong = false;
            if(old_list.size() != now_list.size()) isWrong = true;
            for(String one_method : now_list){
                if(!old_list.contains(one_method)){
                    // 存在不同方法
                    isWrong = true;
                    break;
                }
            }
            if(isWrong){
                serviceBO.setIsWrong(true);
                methodSet.add(methods);
                serviceBO.setMethods(methodSet);
            }else {
                methods = oldMethods;
            }
        }



        // 添加host 到同一个method上
        Map<String,Set<HostBO>> methodsHost = serviceBO.getMethodsHost();
        if(null == methodsHost) methodsHost = new HashMap<>();

        Set<HostBO> hostSet = methodsHost.get(methods);
        if(null == hostSet) hostSet = new HashSet<>();

        hostSet.add(new HostBO(url.getHost(), String.valueOf(url.getPort())));
        methodsHost.put(methods,hostSet);
        serviceBO.setMethodsHost(methodsHost);
    }
}
