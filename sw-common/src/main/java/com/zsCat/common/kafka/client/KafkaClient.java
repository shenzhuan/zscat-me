package com.zsCat.common.kafka.client;


import com.zsCat.common.kafka.client.exception.ProducerConfigException;
import com.zsCat.common.kafka.client.factory.DefaultConsumerProcessor;
import com.zsCat.common.kafka.client.factory.DefaultProducerProcessor;
import com.zsCat.common.kafka.client.utils.KafkaConstants;
import com.zsCat.common.kafka.client.utils.StackTraceUtil;
import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.javaapi.producer.Producer;
import kafka.producer.ProducerConfig;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * @author lixu
 * @version 1.0
 * @created 15-12-22
 */
public class KafkaClient {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(KafkaClient.class);

    public static IProducerProcessor buildProduceFactory(Properties initConfig) throws Exception {

        try {
            String topicName = initConfig.get(KafkaConstants.KAFKA_TOPIC).toString();
            if (topicName != null) {
                Producer producer = new Producer(new ProducerConfig(initConfig));
                return new DefaultProducerProcessor(producer, topicName);
            } else {
                log.error("xm-kafka-client, kafka.topic is null.");
            }
        } catch (Exception e) {
            log.error("xm-kafka-client, build producer occur exception : {}.", StackTraceUtil.getStackTrace(e));
            throw new ProducerConfigException(e);
        }
        return null;
    }

    public static IConsumerProcessor buildConsumerFactory(Properties initConfig) throws Exception {

        String topicName = initConfig.get(KafkaConstants.KAFKA_TOPIC).toString();

        try {
            int consumerThreadNum = Integer.parseInt(initConfig.get(KafkaConstants.CONSUMER_THREAD_NUM).toString());
            if (topicName != null && consumerThreadNum > 0) {
                ConsumerConnector consumer = Consumer.createJavaConsumerConnector(new ConsumerConfig(initConfig));
                return new DefaultConsumerProcessor(consumer, topicName, consumerThreadNum);
            } else {
                log.error("xm-kafka-client, kafka.topic || kafka.consumer.thread.num is null.");
            }
        } catch (Exception e) {
            log.error("xm-kafka-client, build consumer occur exception, topic={} ", topicName, e);
            throw new ProducerConfigException(e);
        }
        return null;
    }
}
