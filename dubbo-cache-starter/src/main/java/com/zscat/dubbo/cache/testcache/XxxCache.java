package com.zscat.dubbo.cache.testcache;

/**
 * Created by Administrator on 2017/7/25 0025.
 */


import com.alibaba.dubbo.cache.Cache;

import java.net.URL;

public class XxxCache implements Cache {
    public XxxCache(URL url, String name) {

    }
    public void put(Object key, Object value) {
        // ...
    }
    public Object get(Object key) {
       return key;
    }
}