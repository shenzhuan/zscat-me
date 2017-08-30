package com.zsCat.common.kafka.client.factory;


import com.zsCat.common.kafka.client.IConsumerProcessor;
import com.zsCat.common.kafka.client.KafkaClient;
import com.zsCat.common.kafka.client.exception.ConsumerConfigException;
import com.zsCat.common.kafka.client.utils.KafkaConstants;
import com.zsCat.common.kafka.client.utils.StackTraceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * @author lixu
 * @version 1.0
 * @created 15-12-24
 */
public class KafkaConsumerBuildFactory extends KafkaBuildFactory {

    private static final Logger log = LoggerFactory.getLogger(KafkaConsumerBuildFactory.class);

    public static IConsumerProcessor init() throws Exception {

        IConsumerProcessor consumer;

        Properties properties = KafkaBuildFactory.init0(KafkaBuildFactory.CONSUMER_CONFIG);

        String topic = (String) properties.get(KafkaConstants.KAFKA_TOPIC);
        if (topic == null) {
            log.error("xm-kafka-client, kafka.topic is null.");
            throw new ConsumerConfigException("kafka Topic is null, can't init kafka client.");
        }

        String zookeeperConnect = (String) properties.get(KafkaConstants.ZOOKEEPER_CONNECT);
        if (zookeeperConnect == null) {
            log.error("xm-kafka-client, zookeeper.connect is null.");
            throw new ConsumerConfigException("kafka Zookeeper path is null, can't init kafka client.");
        }

        String groupId = (String) properties.get(KafkaConstants.GROUP_ID);
        if (groupId == null) {
            log.error("xm-kafka-client, kafka.group.id is null.");
            throw new ConsumerConfigException("kafka.group.id is null, can't init kafka client.");
        }

        String threadCount = (String) properties.get(KafkaConstants.CONSUMER_THREAD_NUM);
        if (threadCount == null) {
            log.error("xm-kafka-client, consumer.thread.num is null.");
            throw new ConsumerConfigException("consumer.thread.num is null, can't init kafka client.");
        }

        Properties initProperties = new Properties();
        initProperties.put(KafkaConstants.KAFKA_TOPIC, topic);
        initProperties.put(KafkaConstants.ZOOKEEPER_CONNECT, zookeeperConnect);
        initProperties.put(KafkaConstants.GROUP_ID, groupId);
        initProperties.put(KafkaConstants.CONSUMER_THREAD_NUM, threadCount);
        initProperties.put(KafkaConstants.ZOOKEEPER_SESSION_TIMEOUT, "5000");
        initProperties.put(KafkaConstants.ZOOKEEPER_SYNC_TIME, "200");
        initProperties.put(KafkaConstants.AUTO_COMMIT_INTERVAL, "1000");
        //initProperties.put("queued.max.message.chunks", "100");
        //initProperties.put("fetch.min.bytes", "6553600");
        //initProperties.put("fetch.wait.max.ms", "10");

        try {
            consumer = KafkaClient.buildConsumerFactory(initProperties);
        } catch (Exception e) {
            log.error("xm-kafka-client, Kafka Consumer build Exception. exception : {}", StackTraceUtil.getStackTrace(e));
            throw new ConsumerConfigException(e);
        }
        return consumer;
    }

    public static IConsumerProcessor init(String topicKey, String groupIdKey, String threadNumKey,
                                          String zookeeperConnectKey) throws Exception {
        IConsumerProcessor consumer;
        Properties properties = KafkaBuildFactory.init0(KafkaBuildFactory.CONSUMER_CONFIG);

        String topic = (String) properties.get(topicKey);
        if (topic == null) {
            log.error("xm-kafka-client, {} is null.", topicKey);
            throw new ConsumerConfigException("kafka Topic is null, can't init kafka client.");
        }

        String zookeeperConnect = (String) properties.get(zookeeperConnectKey);
        if (zookeeperConnect == null) {
            log.error("xm-kafka-client, {} is null.", zookeeperConnectKey);
            throw new ConsumerConfigException("kafka Zookeeper path is null, can't init kafka client.");
        }

        String groupId = (String) properties.get(groupIdKey);
        if (groupId == null) {
            log.error("xm-kafka-client, {} is null.", groupIdKey);
            throw new ConsumerConfigException("kafka.group.id is null, can't init kafka client.");
        }

        String threadCount = (String) properties.get(threadNumKey);
        if (threadCount == null) {
            log.error("xm-kafka-client, {} is null.", threadNumKey);
            throw new ConsumerConfigException("consumer.thread.num is null, can't init kafka client.");
        }

        Properties initProperties = new Properties();
        initProperties.put(KafkaConstants.KAFKA_TOPIC, topic);
        initProperties.put(KafkaConstants.ZOOKEEPER_CONNECT, zookeeperConnect);
        initProperties.put(KafkaConstants.GROUP_ID, groupId);
        initProperties.put(KafkaConstants.CONSUMER_THREAD_NUM, threadCount);
        initProperties.put(KafkaConstants.ZOOKEEPER_SESSION_TIMEOUT, "5000");
        initProperties.put(KafkaConstants.ZOOKEEPER_SYNC_TIME, "200");
        initProperties.put(KafkaConstants.AUTO_COMMIT_INTERVAL, "1000");
        //initProperties.put("queued.max.message.chunks", "100");
        //initProperties.put("fetch.min.bytes", "6553600");
        //initProperties.put("fetch.wait.max.ms", "10");

        try {
            consumer = KafkaClient.buildConsumerFactory(initProperties);
        } catch (Exception e) {
            log.error("xm-kafka-client, Kafka Consumer build Exception. exception : {}", StackTraceUtil.getStackTrace(e));
            throw new ConsumerConfigException(e);
        }
        return consumer;
    }
}
