package com.zscat.dubbo.cache.ehcache;

import com.alibaba.dubbo.cache.Cache;
import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.ExtensionLoader;
import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.alibaba.dubbo.common.serialize.ObjectInput;
import com.alibaba.dubbo.common.serialize.ObjectOutput;
import com.alibaba.dubbo.common.serialize.Serialization;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.zscat.dubbo.cache.AbstractCache;
import com.zscat.dubbo.cache.config.CacheConfig;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.config.Configuration;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author : zscat
 * @version : 1.0
 * @created on  : 2017/07/22  下午3:19
 */
public class EhCache implements Cache {
    protected byte[] cacheName;

    protected URL cachedUrl;

    protected int expireSecond;

    //默认缓存一个小时
    private static final int DEFAULT_EXPIRE_SECONDS=60*10;


    private static final Serialization serialization = ExtensionLoader.getExtensionLoader(Serialization.class).getAdaptiveExtension();

    protected static final Logger logger = LoggerFactory.getLogger(AbstractCache.class);



    protected byte[] objectToBytes(URL url,Object object){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            ObjectOutput objectOutput = serialization.serialize(url, byteArrayOutputStream);
            objectOutput.writeObject(object);
            objectOutput.flushBuffer();
        } catch (IOException e) {
            logger.error("Failed to serialize object ["+object.toString()+"] for url ["+url.toString()+"]",e);
            return null;
        }
        return byteArrayOutputStream.toByteArray();
    }

    protected Object bytesToObject(URL url,byte[] bytes){
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        try {
            ObjectInput objectInput = serialization.deserialize(url,byteArrayInputStream);
            return objectInput.readObject();
        } catch (IOException e) {
            logger.error("Failed to deserialize bytes to object for url [" + url.toString() + "]", e);
            return null;
        } catch (ClassNotFoundException e) {
            logger.error("Failed to deserialize bytes to  object for url [" + url.toString() + "] by class not found ",e);
            return null;
        }
    }


    protected int getExpireSecond(URL url){
        String prefix = "cache."+getTagName()+".";

        int expireSeconds = CacheConfig.getProperty(prefix+url.getParameter(Constants.INTERFACE_KEY)+
                        "."+url.getParameter(Constants.METHOD_KEY)+".expire",
                CacheConfig.getProperty("cache."+url.getParameter(Constants.INTERFACE_KEY)+
                                "."+url.getParameter(Constants.METHOD_KEY)+".expire",
                        CacheConfig.getProperty(prefix + url.getParameter(Constants.INTERFACE_KEY) + ".expire",
                                CacheConfig.getProperty("cache." + url.getParameter(Constants.INTERFACE_KEY) + ".expire",
                                        CacheConfig.getProperty(prefix+"default.expire",
                                                CacheConfig.getProperty("cache.default.expire",DEFAULT_EXPIRE_SECONDS))))));
        return expireSeconds;
    }

    protected byte[] generateCacheKey(Object key){
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byteArrayOutputStream.write(objectToBytes(cachedUrl,key));
            byteArrayOutputStream.write(cacheName);
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            logger.error("Failed to generate cache key for object ["+key.toString()+"]",e);
            return null;
        }
    }
    private static CacheManager cacheManager;
    
    private static final String CONFIGURATION_FILE="cache.ehcache.configuration";
    
    private Ehcache originCache;
    
    static {
        if(StringUtils.isEmpty(CacheConfig.getProperty(CONFIGURATION_FILE))){
            Configuration configuration = new Configuration();
            CacheConfig.appendProperties(configuration, Ehcache.class);

            CacheConfiguration cacheConfiguration = new CacheConfiguration();
            CacheConfig.appendProperties(cacheConfiguration,EhCache.class);
            if(cacheConfiguration.getMaxEntriesLocalHeap()==0){
                cacheConfiguration.setMaxEntriesLocalHeap(10000);
            }
            if(cacheConfiguration.getMaxEntriesLocalDisk()==0){
                cacheConfiguration.setMaxEntriesLocalDisk(10000000);
            }
            configuration.setDefaultCacheConfiguration(cacheConfiguration);
            cacheManager=CacheManager.create(configuration);
        }else{
            try {
                cacheManager=CacheManager.create(CacheConfig.getConfigurationInputStream(CacheConfig.getProperty(CONFIGURATION_FILE)));
            } catch (FileNotFoundException e) {
                logger.error("Failed to load configuration file",e);
                throw new IllegalArgumentException("Failed to load configuration file",e);
            }
        }
        logger.info("init ehcache");
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                cacheManager.shutdown();
            }
        }));
    }


    protected String getTagName() {
        return "ehcache";
    }

    public EhCache(String cacheName, URL url) {
        if(StringUtils.isEmpty(cacheName)){
            cacheName="noarguments";
        }
        this.cacheName =objectToBytes(url,cacheName);
        cachedUrl=url;
        expireSecond=getExpireSecond(url);
        originCache = cacheManager.addCacheIfAbsent(cacheName);
    }
    public EhCache( URL url) {

    }
    @Override
    public void put(Object key, Object value) {
        logger.info("dubbo cache put ehcache key="+key);
        if(key==null||value==null){
            return ;
        }
        Element element = new Element(key,value);
        element.setTimeToLive(getExpireSecond(cachedUrl));
        originCache.put(element);
    }

    @Override
    public Object get(Object key) {
        Element element = originCache.get(key);
        if(element==null){
            logger.info("not hit ehcache key="+key);

            return null;
        }
        logger.info("hit ehcache key="+key.toString());
        return element.getObjectValue();
    }
}
