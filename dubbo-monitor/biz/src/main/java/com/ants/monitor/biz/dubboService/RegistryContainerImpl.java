package com.ants.monitor.biz.dubboService;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.utils.ConcurrentHashSet;
import com.alibaba.dubbo.common.utils.NetUtils;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.registry.NotifyListener;
import com.alibaba.dubbo.registry.RegistryService;
import com.ants.monitor.bean.MonitorConstants;
import com.ants.monitor.bean.bizBean.ApplicationChangeBO;
import com.ants.monitor.biz.support.service.AppChangeService;
import com.ants.monitor.common.tools.SpringContextsUtil;
import com.ants.monitor.common.tools.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * RegistryContainerImpl
 * RegistryContainer 的实现类
 *
 * @author zxg 2015-11-03
 */
@Service
public class RegistryContainerImpl implements RegistryContainer {

    // 自身项目 dubbo注册名
    @Value(value = "${dubbo.application.name}")
    private String myDubboName;
    //存关系
    private final Map<String, Map<String, Set<URL>>> registryCache = new ConcurrentHashMap<>();
    //key:serviceInterface value:Set<service:version>－－用作provider 停止服务时 取消其内容于registryCache中
    private final Map<String, Set<String>> interfaceCache = new ConcurrentHashMap<>();

    //变更的app的变化 provider:list<bo> || consumer:list<bo>，每次启动时从redis读取
    private final Map<String, Set<ApplicationChangeBO>> changeAppCaChe = new ConcurrentHashMap<>();
    //上次启动时存在redis的数据
    private final Map<String, Set<ApplicationChangeBO>> redisChangeAppCaChe = new ConcurrentHashMap<>();

    /**
     * 判断是否开始监控数据变化
     * time:执行时间
     * startMonitor:是否开始执行
     */
    private final Map<String, Object> finalDataMap = new ConcurrentHashMap<>();
    // 最后时间戳
    private static final String NOW_TIME_KEY = "now";
    // 是否初始化完成，可以正常监控
    private static final String IS_START_MONITOR = "isStartMonitor";

    @Reference
    private RegistryService registry;
    @Autowired
    private AppChangeService appChangeService;


    public Map<String, Map<String, Set<URL>>> getRegistryCache() {
        if (!registryCache.containsKey(Constants.PROVIDERS_CATEGORY)) {
            registryCache.put(Constants.PROVIDERS_CATEGORY, new ConcurrentHashMap<String, Set<URL>>());
        }
        if (!registryCache.containsKey(Constants.CONSUMERS_CATEGORY)) {
            registryCache.put(Constants.CONSUMERS_CATEGORY, new ConcurrentHashMap<String, Set<URL>>());
        }
        if (!registryCache.containsKey(Constants.CONFIGURATORS_CATEGORY)) {
            registryCache.put(Constants.CONFIGURATORS_CATEGORY, new ConcurrentHashMap<String, Set<URL>>());
        }

        return Collections.unmodifiableMap(registryCache);
    }

    public Date getFinalUpdateTime() {
        Date now = (Date) finalDataMap.get(NOW_TIME_KEY);
        return now;
    }

    //初始化changeApp--redis取出,比较后，执行存储
    public void initRedisChangeAppCaChe() {
        Map<String, Set<ApplicationChangeBO>> map = appChangeService.getChangeAppCache();
        if (null != map) {
            redisChangeAppCaChe.putAll(map);
        }
        /**比较此次初始化跟上次的区别**/
        //insert新增
        for (Map.Entry<String, Set<ApplicationChangeBO>> nowEntry : changeAppCaChe.entrySet()) {
            String category = nowEntry.getKey();
            Set<ApplicationChangeBO> nowSet = nowEntry.getValue();
            Set<ApplicationChangeBO> redisSet = redisChangeAppCaChe.get(category);
            if (null == redisSet) redisSet = new ConcurrentHashSet<>();
            for (ApplicationChangeBO newChangeBO : nowSet) {
                if (!redisSet.contains(newChangeBO)) {
                    appChangeService.afterChangeInsertDo(newChangeBO);
                }
            }
        }
        //delete减少
        for (Map.Entry<String, Set<ApplicationChangeBO>> redisEntry : redisChangeAppCaChe.entrySet()) {
            String category = redisEntry.getKey();
            Set<ApplicationChangeBO> redisSet = redisEntry.getValue();
            Set<ApplicationChangeBO> nowSet = changeAppCaChe.get(category);
            if (null == nowSet) nowSet = new ConcurrentHashSet<>();
            for (ApplicationChangeBO redisBo : redisSet) {
                if (!nowSet.contains(redisBo)) {
                    appChangeService.afterChangeDeleteDo(redisBo);
                }
            }
        }

        finalDataMap.put(IS_START_MONITOR, true);
    }

    //    @PostConstruct
    public void start() {
        URL subscribeUrl = new URL(Constants.ADMIN_PROTOCOL, NetUtils.getLocalHost(), 0, "",
                Constants.INTERFACE_KEY, Constants.ANY_VALUE,
                Constants.GROUP_KEY, Constants.ANY_VALUE,
                Constants.VERSION_KEY, Constants.ANY_VALUE,
                Constants.CLASSIFIER_KEY, Constants.ANY_VALUE,
                Constants.CATEGORY_KEY, Constants.PROVIDERS_CATEGORY + ","
                + Constants.CONSUMERS_CATEGORY + ","
                + Constants.CONFIGURATORS_CATEGORY,
                Constants.CHECK_KEY, String.valueOf(false));
        if (null == registry) {
            registry = (RegistryService) SpringContextsUtil.getBean("registryService");
        }
        finalDataMap.put(IS_START_MONITOR, false);


        // 订阅符合条件的已注册数据，当有注册数据变更时自动推送.
        registry.subscribe(subscribeUrl, new NotifyListener() {
            public void notify(List<URL> urls) {
                if (urls == null || urls.size() == 0) {
                    return;
                }

                // 组合新数据
                final Map<String, Map<String, Set<URL>>> categories = new ConcurrentHashMap<>();
                //此批的提供者 interface:Service
                final Map<String, Set<String>> interfaces = new ConcurrentHashMap<>();

                Date now = new Date();
                //实际逻辑
                for (URL url : urls) {
                    //逻辑处理
                    String application = url.getParameter(Constants.APPLICATION_KEY);
                    if (myDubboName.equals(application)) {
                        continue;
                    }
                    String category = url.getParameter(Constants.CATEGORY_KEY, Constants.DEFAULT_CATEGORY);
                    String protocol = url.getProtocol();

                    // 移除此数据:涉及provider、consumer的减少／进行数据通知+禁用数据的更改
                    if (Constants.EMPTY_PROTOCOL.equals(protocol)) {
                        UrlEmptyDo(category, url);
                        continue;
                    }

                    Map<String, Set<URL>> services = categories.get(category);
                    if (services == null) {
                        services = new ConcurrentHashMap<>();
                        categories.put(category, services);
                    }
                    String service = url.getServiceInterface();
                    if ("com.alibaba.dubbo.monitor.MonitorService".equals(service)) {
                        continue;
                    }
                    Set<URL> ids = services.get(service);
                    if (ids == null) {
                        ids = new ConcurrentHashSet<>();
                        services.put(service, ids);
                    }
                    ids.add(url);

                    // interface : service
                    if (Constants.PROVIDERS_CATEGORY.equals(category)) {
                        String serviceInterface = url.getServiceInterface();
                        Set<String> interfaceServices = interfaces.get(serviceInterface);
                        if (interfaceServices == null) {
                            interfaceServices = new ConcurrentHashSet<>();
                            interfaces.put(serviceInterface, interfaceServices);
                        }
                        interfaceServices.add(service);
                    }
                }
                if (interfaces.size() != 0) {
                    // 提供者，批量的interface，涉及provider的减少
                    IsProviderReduce(interfaces);
                }


                if (categories.size() != 0) {
                    //涉及consumer新增、减少；provider新增
                    categoryServiceChange(categories);
                }

                finalDataMap.put(NOW_TIME_KEY, now);

            }
        });
    }


    public void restart() {
        registryCache.clear();
        start();
    }


    @PreDestroy
    public void stop() {
    }

    /**
     * =========================private=============================================
     **/
    //涉及consumer新增、减少；provider新增
    private void categoryServiceChange(final Map<String, Map<String, Set<URL>>> categories) {
        boolean appCacheChange = false;
        for (Map.Entry<String, Map<String, Set<URL>>> categoryEntry : categories.entrySet()) {
            String category = categoryEntry.getKey();
            Map<String, Set<URL>> services = registryCache.get(category);
            if (services == null) {
                services = new ConcurrentHashMap<>();
                registryCache.put(category, services);
            }
            Map<String, Set<URL>> NewServices = categoryEntry.getValue();
            if (compareOldAndNewServices(services, NewServices)) {
                appCacheChange = true;
            }
            services.putAll(NewServices);
        }
        //初始化完成方可对比处理数据
        Boolean startMonitor = (Boolean) finalDataMap.get(IS_START_MONITOR);

        //appCache发生变化
        if (appCacheChange && startMonitor) {
            saveChangeAppCaChe();
        }
    }

    //涉及consumer新增、减少；provider新增-- 更改保存到列表和 redis中
    private boolean compareOldAndNewServices(Map<String, Set<URL>> oldServices, Map<String, Set<URL>> newServices) {
        boolean appCacheChange = false;
        for (Map.Entry<String, Set<URL>> oldEntry : oldServices.entrySet()) {
            String service = oldEntry.getKey();
            Set<URL> oldUrlSet = oldEntry.getValue();
            if (!newServices.containsKey(service)) {
                continue;
            }

            Set<URL> newUrlSet = newServices.get(service);

            //减少
            for (URL url : oldUrlSet) {
                if (!newUrlSet.contains(url)) {
                    removeFromChangeAppCaChe(url);
                    appCacheChange = true;
                }
            }

            // 新增
            for (URL url : newUrlSet) {
                if (!oldUrlSet.contains(url)) {
                    addToChangeAppCaChe(url);
                    appCacheChange = true;
                }
            }
        }
        return appCacheChange;
    }

    // url以empty开头的url处理，移除此数据:涉及provider、consumer的减少
    private void UrlEmptyDo(String category, URL url) {
        boolean appCacheChange = false;

        Map<String, Set<URL>> services = registryCache.get(category);
        if (services != null) {
            String group = url.getParameter(Constants.GROUP_KEY);
            String version = url.getParameter(Constants.VERSION_KEY);
            // 注意：empty协议的group和version为*
            if (!Constants.ANY_VALUE.equals(group) && !Constants.ANY_VALUE.equals(version)) {
                String service = url.getServiceInterface();

                Set<URL> urlSet = services.get(service);
                for (URL removeUrl : urlSet) {
                    // 从 changeAppCache 中移除数据
                    appCacheChange = true;
                    removeFromChangeAppCaChe(removeUrl);
                }
                services.remove(service);

            } else {
                String urlService = url.getServiceInterface();
                for (Map.Entry<String, Set<URL>> serviceEntry : services.entrySet()) {
                    String service = serviceEntry.getKey();
                    if (Tool.getInterface(service).equals(urlService)
                            && (Constants.ANY_VALUE.equals(group) || StringUtils.isEquals(group, Tool.getGroup(service)))
                            && (Constants.ANY_VALUE.equals(version) || StringUtils.isEquals(version, Tool.getVersion(service)))) {

                        Set<URL> urlSet = serviceEntry.getValue();
                        for (URL removeUrl : urlSet) {
                            appCacheChange = true;
                            removeFromChangeAppCaChe(removeUrl);
                        }
                        services.remove(service);
                    }
                }
            }


            //初始化完成方可对比处理数据
            Boolean startMonitor = (Boolean) finalDataMap.get(IS_START_MONITOR);

            //appCache发生变化
            if (appCacheChange && startMonitor) {
                saveChangeAppCaChe();
            }

        }
    }


    // 提供者，批量的interface，涉及providers的减少+禁用数据的更改
    private void IsProviderReduce(final Map<String, Set<String>> interfaces) {
        for (Map.Entry<String, Set<String>> interfaceServices : interfaces.entrySet()) {
            String interfaceName = interfaceServices.getKey();
            Set<String> interfaceServicesCache = interfaceCache.get(interfaceName);
            if (null == interfaceServicesCache) {
                interfaceServicesCache = new ConcurrentHashSet<>();
                interfaceCache.put(interfaceName, interfaceServicesCache);
            } else {
                Set<String> interfaceServicesNow = interfaceServices.getValue();
                // 减少的剔除掉，新增的增加
                for (String service : interfaceServicesCache) {
                    if (!interfaceServicesNow.contains(service)) {
                        Map<String, Set<URL>> services = registryCache.get(Constants.PROVIDERS_CATEGORY);

                        services.remove(service);
                    }
                }
                interfaceCache.put(interfaceName, interfaceServicesNow);
            }
        }
    }

    // 从appChange 中移除
    private void removeFromChangeAppCaChe(URL url) {
        String host = url.getHost();
        Integer portInt = url.getPort();
        String category = url.getParameter(Constants.CATEGORY_KEY, Constants.DEFAULT_CATEGORY);
        String port = String.valueOf(portInt);

        String application = url.getParameter(Constants.APPLICATION_KEY);
        String organization = url.getParameter(MonitorConstants.ORGANICATION);

        if (organization == null) organization = "";

        ApplicationChangeBO applicationChangeBO = new ApplicationChangeBO(host, port, application, category, organization);

        Set<ApplicationChangeBO> oldChangeSet = changeAppCaChe.get(category);
        if (oldChangeSet != null && oldChangeSet.contains(applicationChangeBO)) {
            oldChangeSet.remove(applicationChangeBO);
            //初始化完成方可对比处理数据
            Boolean startMonitor = (Boolean) finalDataMap.get(IS_START_MONITOR);

            if (startMonitor != null && startMonitor) {
                appChangeService.afterChangeDeleteDo(applicationChangeBO);
            }
        }


    }

    // 从appChange 中新增
    private void addToChangeAppCaChe(URL url) {
        String host = url.getHost();
        Integer portInt = url.getPort();
        String category = url.getParameter(Constants.CATEGORY_KEY, Constants.DEFAULT_CATEGORY);
        String port = String.valueOf(portInt);

        String application = url.getParameter(Constants.APPLICATION_KEY);
        String organization = url.getParameter(MonitorConstants.ORGANICATION);

        if (organization == null) organization = "";

        ApplicationChangeBO applicationChangeBO = new ApplicationChangeBO(host, port, application, category, organization);

        Set<ApplicationChangeBO> oldChangeSet = changeAppCaChe.get(category);
        if (null == oldChangeSet) {
            oldChangeSet = new ConcurrentHashSet<>();
            changeAppCaChe.put(category, oldChangeSet);
        }

        //老中没有的添加
        if (!oldChangeSet.contains(applicationChangeBO)) {

            oldChangeSet.add(applicationChangeBO);

            //初始化完成方可对比处理数据
            Boolean startMonitor = (Boolean) finalDataMap.get(IS_START_MONITOR);
            if (startMonitor != null && startMonitor) {
                appChangeService.afterChangeInsertDo(applicationChangeBO);
            }
        }

    }


    //changeApp--redis存入
    private void saveChangeAppCaChe() {
        Map<String, Set<ApplicationChangeBO>> map = new ConcurrentHashMap<>();
        map.putAll(changeAppCaChe);
        appChangeService.saveChangeAppCache(map);
    }
}