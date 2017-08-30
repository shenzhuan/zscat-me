package com.zsCat.common.kafka.client.factory;


import com.zsCat.common.kafka.client.IProducerProcessor;
import com.zsCat.common.kafka.client.KafkaClient;
import com.zsCat.common.kafka.client.exception.ProducerConfigException;
import com.zsCat.common.kafka.client.utils.KafkaConstants;
import com.zsCat.common.kafka.client.utils.StackTraceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * @author lixu
 * @version 1.0
 * @created 15-12-22
 */
public class KafkaProducerBuildFactory extends KafkaBuildFactory {

    private static final Logger log = LoggerFactory.getLogger(KafkaProducerBuildFactory.class);

    public static IProducerProcessor init() throws Exception {

        IProducerProcessor producer;

        Properties properties = KafkaBuildFactory.init0(KafkaBuildFactory.PRODUCER_CONFIG);

        String topic = (String) properties.get(KafkaConstants.KAFKA_TOPIC);
        if (topic == null) {
            log.error("xm-kafka-client, kafka.topic is null.");
            throw new ProducerConfigException("kafka Topic is null, can't init kafka client.");
        }

        String partitioner = (String) properties.get(KafkaConstants.KAFKA_PARTITIONER);
        if (partitioner == null) {
            log.error("xm-kafka-client, kafka.partitioner.class is null.");
            throw new ProducerConfigException("kafka Partitioner class is null, can't init kafka client.");
        }

        String brokerlist = (String) properties.get(KafkaConstants.BROKER_LIST);
        if (brokerlist == null) {
            log.error("xm-kafka-client, metadata.broker.list is null.");
            throw new ProducerConfigException("kafka Broker list is null, can't init kafka client.");
        }

        Properties initProperties = new Properties();
        initProperties.put(KafkaConstants.KAFKA_TOPIC, topic);
        initProperties.put(KafkaConstants.KAFKA_PARTITIONER, partitioner);
        initProperties.put(KafkaConstants.BROKER_LIST,brokerlist);
        initProperties.put(KafkaConstants.KAFKA_PROD_TYPE, "async");
        //initProperties.put(KafkaConstants.KAFKA_PROD_TYPE, "sync");
        initProperties.put(KafkaConstants.REQUEST_REQUIRED_ACKS, "1");
        initProperties.put(KafkaConstants.SEND_BUFFER_SZIE, "10000");
        initProperties.put(KafkaConstants.SEND_BUFFER_TIME, "1");
        initProperties.put(KafkaConstants.SEND_BATH_NUM, "1000");

        try {
            producer = KafkaClient.buildProduceFactory(initProperties);
        } catch (Exception e) {
            log.error("xm-kafka-client, Kafka Producer build Exception. exception : {}", StackTraceUtil.getStackTrace(e));
            throw new ProducerConfigException(e);
        }
        return producer;
    }


    public static IProducerProcessor init(String topicKey, String brokerKey) throws Exception {
        IProducerProcessor producer;
        Properties properties = KafkaBuildFactory.init0(KafkaBuildFactory.PRODUCER_CONFIG);

        String topic = (String) properties.get(topicKey);
        if (topic == null) {
            log.error("xm-kafka-client, kafka.topic is null.");
            throw new ProducerConfigException("kafka Topic is null, can't init kafka client.");
        }

        String partitioner = (String) properties.get(KafkaConstants.KAFKA_PARTITIONER);
        if (partitioner == null) {
            log.error("xm-kafka-client, kafka.partitioner.class is null.");
            throw new ProducerConfigException("kafka Partitioner class is null, can't init kafka client.");
        }

        String brokerlist = (String) properties.get(brokerKey);
        if (brokerlist == null) {
            log.error("xm-kafka-client, metadata.broker.list is null.");
            throw new ProducerConfigException("kafka Broker list is null, can't init kafka client.");
        }

        Properties initProperties = new Properties();
        initProperties.put(KafkaConstants.KAFKA_TOPIC, topic);
        initProperties.put(KafkaConstants.KAFKA_PARTITIONER, partitioner);
        initProperties.put(KafkaConstants.BROKER_LIST,brokerlist);
        initProperties.put(KafkaConstants.KAFKA_PROD_TYPE, "async");
        //initProperties.put(KafkaConstants.KAFKA_PROD_TYPE, "sync");
        initProperties.put(KafkaConstants.REQUEST_REQUIRED_ACKS, "1");
        initProperties.put(KafkaConstants.SEND_BUFFER_SZIE, "10000");
        initProperties.put(KafkaConstants.SEND_BUFFER_TIME, "1");
        initProperties.put(KafkaConstants.SEND_BATH_NUM, "1000");

        try {
            producer = KafkaClient.buildProduceFactory(initProperties);
        } catch (Exception e) {
            log.error("xm-kafka-client, Kafka Producer build Exception. exception : {}", StackTraceUtil.getStackTrace(e));
            throw new ProducerConfigException(e);
        }
        return producer;
    }
}
