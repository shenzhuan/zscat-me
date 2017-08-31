package com.zscat.dubbo.cache;

import com.alibaba.dubbo.common.URL;
import com.zscat.dubbo.cache.remote.RemoteClient;

/**
 * @author : zscat
 * @version : 1.0
 * @created on  : 2017/07/22  下午3:19
 */
public abstract class RemoteCache extends AbstractCache {
    
    protected RemoteClient remoteClient;
    
    public RemoteCache(String cacheName, URL url,RemoteClient remoteClient) {
        super(cacheName, url);
        this.remoteClient = remoteClient;
    }

    @Override
    public void put(Object key, Object value) {
        if(value==null){
            return ;
        }
        byte[] bytes = generateCacheKey(key);
        if(bytes!=null){
            remoteClient.cacheValue(generateCacheKey(key),objectToBytes(cachedUrl,value),expireSecond);
        }
    }

    @Override
    public Object get(Object key) {
        byte[] bytes = generateCacheKey(key);
        if(bytes!=null){
            byte[] value=remoteClient.getValue(bytes);
            if(value==null){
                return null;
            }
            return bytesToObject(cachedUrl,value);
        }
        return null;
    }
}
