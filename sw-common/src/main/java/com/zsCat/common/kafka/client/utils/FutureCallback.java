package com.zsCat.common.kafka.client.utils;

/**
 * @author lixu
 * @version 1.0
 * @created 15-12-23
 * @param <V> tag
 */
public interface FutureCallback<V> {

    void onSuccess(V msg);

    void onFailure(V msg, final Throwable t);

}
