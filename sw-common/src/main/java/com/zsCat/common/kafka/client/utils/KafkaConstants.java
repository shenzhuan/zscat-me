package com.zsCat.common.kafka.client.utils;

/**
 * @author lixu
 * @version 1.0
 * @created 15-12-23
 */
public abstract class KafkaConstants {

    public static final String KAFKA_TOPIC                      = "kafka.topic";
    public static final String ZOOKEEPER_CONNECT                = "zookeeper.connect";
    public static final String KAFKA_PARTITIONER                = "partitioner.class";
    public static final String BROKER_LIST                      = "metadata.broker.list";
    public static final String TOPIC_SERIALIZER                 = "serializer.class";
    public static final String KAFKA_PROD_TYPE                  = "producer.type";
    public static final String GROUP_ID                         = "group.id";
    public static final String ZOOKEEPER_SESSION_TIMEOUT        = "zookeeper.session.timeout.ms";
    public static final String ZOOKEEPER_SYNC_TIME              = "zookeeper.sync.time.ms";
    public static final String AUTO_COMMIT_INTERVAL             = "auto.commit.interval.ms";
    public static final String REQUEST_REQUIRED_ACKS            = "request.required.acks";
    public static final String CONSUMER_THREAD_NUM              = "consumer.thread.num";
    public static final String SEND_BUFFER_SZIE                  = "queue.buffering.max.messages";
    public static final String SEND_BUFFER_TIME                  = "queue.buffering.max.ms";
    public static final String SEND_BATH_NUM                     = "batch.num.messages";

}
