package com.zsCat.common.kafka.client;


import com.zsCat.common.kafka.client.utils.FutureCallback;

/**
 * @author lixu
 * @version 1.0
 * @created 15-12-22
 * @param <K> tag
 * @param <V> tag
 */
public interface IProducerProcessor <K, V>  {

    public void sendAsyncMessage(V msg, K partationField, final FutureCallback futureCallback) throws Exception;

    public void sendAsyncMessage(V message, final FutureCallback futureCallback) throws Exception;

    public void close() throws Exception;
}
