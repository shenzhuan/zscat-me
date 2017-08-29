package com.huluuu.dubbo.cache.remote;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;

/**
 * @author : Hui.Wang [huzi.wh@gmail.com]
 * @version : 1.0
 * @created on  : 2017/07/22  下午3:19
 */
public abstract class RemoteClient {
    
    protected static final Logger logger = LoggerFactory.getLogger(RemoteClient.class);
    
    public abstract void cacheValue(byte[] key,byte[] bytes,int expireSecond);
    
    public abstract byte[] getValue(byte[] key);
    

}
