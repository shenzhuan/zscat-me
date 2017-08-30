package com.zsCat.common.kafka.client;



/**
 * @author lixu
 * @version 1.0
 * @created 15-12-22
 */
public interface IConsumerProcessor {

    public void recvMessageWithParallel(Class clz, IMessageListener iMessageListener);

    public void close() throws Exception;
}
