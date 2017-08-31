package com.zscat.dubbo.cache.testcache;

/**
 * Created by Administrator on 2017/7/25 0025.
 */



import com.alibaba.dubbo.cache.Cache;
import com.alibaba.dubbo.cache.CacheFactory;

import java.net.URL;

public class XxxCacheFactory implements CacheFactory {
    public XxxCache getCache(URL url, String name) {
        return new XxxCache(url, name);
    }

    @Override
    public Cache getCache(com.alibaba.dubbo.common.URL url) {
        return null;
    }
}