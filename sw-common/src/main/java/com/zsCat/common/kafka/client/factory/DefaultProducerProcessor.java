package com.zsCat.common.kafka.client.factory;


import com.zsCat.common.kafka.client.IProducerProcessor;
import com.zsCat.common.kafka.client.exception.ProducerRuntimeException;
import com.zsCat.common.kafka.client.utils.FutureCallback;
import com.zsCat.common.kafka.client.utils.StackTraceUtil;
import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author lixu
 * @version 1.0
 * @created 15-12-22
 * @param <K> tag
 * @param <V> tag
 */
public class DefaultProducerProcessor<K, V> implements IProducerProcessor<K, V> {

    private static final Logger log = LoggerFactory.getLogger(DefaultProducerProcessor.class);

    private Producer<K, V> producer;

    private String topic;

    public DefaultProducerProcessor(Producer<K, V> producer, String topic) {
        this.producer = producer;
        this.topic = topic;
    }

    public Producer<K, V> getProducer() {
        return producer;
    }

    @Override
    public void sendAsyncMessage(V msg, K partationField, final FutureCallback futureCallback) throws Exception {

        if (msg == null) {
            log.error("xm-kafka-client send msg is null.");
            throw new ProducerRuntimeException("xm-kafka-client send msg is null.");
        }
        try {
            KeyedMessage<K, V> data = new KeyedMessage<K, V>(topic, null, partationField, msg);
            getProducer().send(data);
            futureCallback.onSuccess(msg);
        } catch (Exception e) {
            log.error("xm-kafka-client, send.async occur exception = {}.", StackTraceUtil.getStackTrace(e));
            futureCallback.onFailure(msg, e);
        }
    }

    @Override
    public void sendAsyncMessage(V msg, FutureCallback futureCallback) throws Exception {

        if (msg == null) {
            log.error("xm-kafka-client send msg is null.");
            throw new ProducerRuntimeException("xm-kafka-client send msg is null.");
        }
        try {
            KeyedMessage<K, V> data = new KeyedMessage<K, V>(topic, null, msg.toString(), msg);
            getProducer().send(data);
            futureCallback.onSuccess(msg);
        } catch (Exception e) {
            log.error("xm-kafka-client, send.async occur exception = {}.", StackTraceUtil.getStackTrace(e));
            futureCallback.onFailure(msg, e);
        }
    }

    @Override
    public void close() throws Exception {
        getProducer().close();
    }
}
