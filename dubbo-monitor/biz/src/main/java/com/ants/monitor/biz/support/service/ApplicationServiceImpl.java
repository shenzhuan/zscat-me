package com.ants.monitor.biz.support.service;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.utils.ConcurrentHashSet;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.ants.monitor.bean.MonitorConstants;
import com.ants.monitor.bean.bizBean.ApplicationBO;
import com.ants.monitor.bean.bizBean.HostBO;
import com.ants.monitor.bean.bizBean.ServiceBO;
import com.ants.monitor.biz.dubboService.DubboMonitorService;
import com.ants.monitor.biz.dubboService.RegistryContainer;
import com.ants.monitor.common.redis.RedisClientTemplate;
import com.ants.monitor.common.redis.RedisKeyBean;
import com.ants.monitor.common.tools.JsonUtil;
import com.ants.monitor.common.tools.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by zxg on 15/11/11.
 */
@Service
public class ApplicationServiceImpl implements ApplicationService {


    @Autowired
    private RegistryContainer registryContainer;

    @Autowired
    private RedisClientTemplate redisClientTemplate;

    @Override
    public Set<String> getAllApplications() {
        Set<String> resultApplications = new ConcurrentHashSet<>();

        Map<String, Map<String, Set<URL>>> registry = registryContainer.getRegistryCache();

        Map<String, Set<URL>> providersServices = registry.get(Constants.PROVIDERS_CATEGORY);
        Map<String, Set<URL>> consumersServices = registry.get(Constants.CONSUMERS_CATEGORY);
        for (Map.Entry<String, Set<URL>> serviceEntry : providersServices.entrySet()) {
            Set<URL> urls = serviceEntry.getValue();
            for (URL url : urls) {
                String application = url.getParameter(Constants.APPLICATION_KEY);
                if (null != application) {
                    resultApplications.add(application);
                }
            }
        }
        for (Map.Entry<String, Set<URL>> serviceEntry : consumersServices.entrySet()) {
            Set<URL> urls = serviceEntry.getValue();
            for (URL url : urls) {
                String application = url.getParameter(Constants.APPLICATION_KEY);
                if (null != application) {
                    resultApplications.add(application);
                }
            }
        }

        return resultApplications;
    }


    @Override
    public List<String> getAllApplicationsCache() {
        List<String> resultList = new ArrayList<>();
        String redisKey = RedisKeyBean.APP_LIST_KEY;
        // 从redis中取
        String redisResultString = redisClientTemplate.get(redisKey);
        if(redisResultString != null && redisClientTemplate.isNone(redisResultString)){
            //缓存里判定之前查找为空，因此此次不走数据库，直接空
            return resultList;
        }
        if(redisResultString != null){
            //返回redis 缓存结果集
            return JsonUtil.jsonStrToList(redisResultString, String.class);
        }
        //redis 中无数据，进行数据库操作
        Set<String> resultSet = getAllApplications();

        //缓存一份到数据库
        if(resultSet.isEmpty()){
            redisClientTemplate.setNone(redisKey);
        }else{
            resultList = new ArrayList<>(resultSet);
            redisClientTemplate.lazySet(redisKey,resultList,RedisKeyBean.RREDIS_EXP_HOURS);
        }

        return resultList;
    }

    @Override
    public Map<String, ApplicationBO> getApplicationsBOMap() {
        Map<String, ApplicationBO> appMap = new HashMap<>();

//        Map<String, ServiceBO> serviceMap = new HashMap<>();

        Map<String, Map<String, Set<URL>>> registry = registryContainer.getRegistryCache();
        //service对应的所有提供者
        Map<String, Set<String>> serviceProviders = new HashMap<>();

        Map<String, Set<URL>> providersServices = registry.get(Constants.PROVIDERS_CATEGORY);
        Map<String, Set<URL>> consumersServices = registry.get(Constants.CONSUMERS_CATEGORY);
        Map<String, Set<URL>> forbidServices = registry.get(Constants.CONFIGURATORS_CATEGORY);

        //提供者处理
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

        // 提供者---拼基本信息
        for (Map.Entry<String, Set<URL>> serviceEntry : providersServices.entrySet()) {
            String serviceName = serviceEntry.getKey();
            //是否被禁止,禁止则不出现
            Set<URL> forbidSet = forbidServices.get(serviceName);

            //拼这个service对应的提供者
            Set<String> appSet = serviceProviders.get(serviceName);
            if(appSet == null){
                appSet = new HashSet<>();
                serviceProviders.put(serviceName,appSet);
            }

            Set<URL> urls = serviceEntry.getValue();
            for (URL url : urls) {
                //是否被禁止,禁止则不出现
                if(Tool.compareIsOverride(url, forbidSet)){
                    continue;
                }
                Set<HostBO> hostList = new HashSet<>();

                String application = url.getParameter(Constants.APPLICATION_KEY);
                //service的所有提供者
                appSet.add(application);
                //开始拼接BO
                ApplicationBO applicationBO = appMap.get(application);
                if (null == applicationBO) {
                    String organization = url.getParameter(MonitorConstants.ORGANICATION);

                    applicationBO = new ApplicationBO();
                    applicationBO.setApplicationName(application);
                    if(StringUtils.isEmpty(applicationBO.getOrganization())) applicationBO.setOrganization(organization == null ? "" : organization);
//                    if(StringUtils.isEmpty(applicationBO.getOwner())) applicationBO.setOwner(owners == null ? "" : owners);
                    appMap.put(application, applicationBO);
                } else {
                    hostList = applicationBO.getHostList();
                }
                String host = url.getHost();
                String port = String.valueOf(url.getPort());
                HostBO hostBO = new HostBO(host,port);
                hostList.add(hostBO);
                applicationBO.setHostList(hostList);
                applicationBO.setIsProvider(true);

                //==============start========================service的处理
                providerService(url,applicationBO,testUrlSet,onlineUrlSet);

            }

        }

        // 消费者--拼 依赖和消费的app
        for (Map.Entry<String, Set<URL>> serviceEntry : consumersServices.entrySet()) {
            String serviceName = serviceEntry.getKey();
            //其service的 提供方
            Set<String> providers = serviceProviders.get(serviceName);
            if (providers == null) {
                //无提供者者 暂时此处不处理
                providers = new HashSet<>();
            }
            Set<URL> urls = serviceEntry.getValue();
            for (URL url : urls) {
                Set<HostBO> hostList = new HashSet<>();

                String applicationName = url.getParameter(Constants.APPLICATION_KEY);

                String owners = url.getParameter(MonitorConstants.OWNER);
                String organization = url.getParameter(MonitorConstants.ORGANICATION);

                ApplicationBO consumerApplicationBO = appMap.get(applicationName);
                if (null == consumerApplicationBO) {
                    // 此app 未提供服务
                    consumerApplicationBO = new ApplicationBO();
                    consumerApplicationBO.setApplicationName(applicationName);
                    if(StringUtils.isEmpty(consumerApplicationBO.getOrganization())) consumerApplicationBO.setOrganization(organization == null ? "" : organization);

                    hostList.add(new HostBO(url.getHost(),null));
                    consumerApplicationBO.setHostList(hostList);
                    appMap.put(applicationName, consumerApplicationBO);
                }else if (!consumerApplicationBO.getIsProvider()) {
                    // 未提供app服务的host
                    hostList = consumerApplicationBO.getHostList();
                    hostList.add(new HostBO(url.getHost(),null));
                    consumerApplicationBO.setHostList(hostList);
                }

                Set<String> providersSet = consumerApplicationBO.getProvidersSet();
                if (null == providersSet) {providersSet = new HashSet<>();}

                for (String provider : providers) {
                    //提供者set
                    providersSet.add(provider);
                    //反向补充每个提供者的数据
                    ApplicationBO providerApplicationBO = appMap.get(provider);
                    if (null != providerApplicationBO) {
                        Set<String> consumersSet = providerApplicationBO.getConsumersSet();
                        if (null == consumersSet) consumersSet = new HashSet<>();
                        // 提供了这些app
                        consumersSet.add(applicationName);
                        providerApplicationBO.setConsumersSet(consumersSet);

                        Map<String,Set<ServiceBO>> thisServiceMap = providerApplicationBO.getServiceMap();
                        for(Map.Entry<String,Set<ServiceBO>> entry : thisServiceMap.entrySet()){
                            Set<ServiceBO> serviceBOSet = entry.getValue();
                            for(ServiceBO serviceBO : serviceBOSet){
                                if(serviceBO.getServiceName().equals(serviceName)){
                                    // 此方法存在消费者
                                    serviceBO.setIsConsumer(true);
                                }
                            }
                        }
                    }
                }
                consumerApplicationBO.setProvidersSet(providersSet);
                consumerApplicationBO.setIsConsumer(true);
                if(owners != null && consumerApplicationBO.getOwner().equals("")){
                    consumerApplicationBO.setOwner(owners);
                }
                if(organization != null && consumerApplicationBO.getOrganization().equals("")){
                    consumerApplicationBO.setOrganization(organization);
                }

            }
        }

        return appMap;
    }


    //提供者的service处理
    private void providerService(URL url,ApplicationBO applicationBO,Set<String> testUrlSet,Set<String> onlineUrlSet){
        String serviceName = url.getServiceInterface();
        String host = url.getHost();
        String port = String.valueOf(url.getPort());
        HostBO hostBO = new HostBO(host,port);
        String finalTime = DubboMonitorService.getServiceConsumerTime(serviceName,host);

        Map<String,Set<ServiceBO>> thisServiceMap = applicationBO.getServiceMap();
        if(null == thisServiceMap) thisServiceMap = new HashMap<>();
        Set<ServiceBO> onlineSet = thisServiceMap.get("online");
        Set<ServiceBO> testSet = thisServiceMap.get("test");
        Set<ServiceBO> localSet = thisServiceMap.get("local");
        Set<ServiceBO> wrongSet = thisServiceMap.get("wrong");

        if(onlineSet == null) onlineSet = new HashSet<>();
        if(testSet == null) testSet = new HashSet<>();
        if(localSet == null) localSet = new HashSet<>();
        if(wrongSet == null) wrongSet = new HashSet<>();

        String methods = url.getParameter(Constants.METHODS_KEY);
        String owners = url.getParameter(MonitorConstants.OWNER);
        //设置service对象
        ServiceBO serviceBO = new ServiceBO();
        serviceBO.setServiceName(serviceName);
        serviceBO.setOwner(owners == null ? "" : owners);
        Set<String> methodSet = serviceBO.getMethods() ;
        if(null == methodSet) methodSet = new HashSet<>();
        methodSet.add(methods);


        Map<String,Set<HostBO>> methodsHost = serviceBO.getMethodsHost();
        if(null == methodsHost) methodsHost = new HashMap<>();
        Set<HostBO> hostBOSet = methodsHost.get(methods);
        if(null == hostBOSet) hostBOSet = new HashSet<>();
        hostBOSet.add(hostBO);
        methodsHost.put(methods,hostBOSet);

        serviceBO.setMethodsHost(methodsHost);
        serviceBO.setMethods(methodSet);
        serviceBO.setFinalConsumerTime(finalTime);

        //正式环境判断
        if(serviceName.endsWith("1.0.0")){
            if(!onlineUrlSet.contains(host)){
                //非线上
                String wrongReason = "非线上环境";
                serviceBO.setWrongReason(wrongReason);
                wrongSet.add(serviceBO);
            }else{
                onlineSet.add(serviceBO);
            }
        }else if(serviceName.endsWith("1.0.0.daily")){
            //测试环境判断
            if (!testUrlSet.contains(host)) {
                //非测试环境
                String wrongReason = "非测试环境";
                serviceBO.setWrongReason(wrongReason);
                wrongSet.add(serviceBO);
            } else {
                testSet.add(serviceBO);
            }
        }else{
            //正常的
            localSet.add(serviceBO);
        }

        if(!wrongSet.isEmpty()) thisServiceMap.put("wrong",wrongSet);
        if(!onlineSet.isEmpty()) thisServiceMap.put("online", onlineSet);
        if(!testSet.isEmpty()) thisServiceMap.put("test", testSet);
        if(!localSet.isEmpty()) thisServiceMap.put("local",localSet);

        applicationBO.setServiceMap(thisServiceMap);
    }

}
