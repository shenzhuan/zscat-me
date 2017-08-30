package com.zscat.mq;

import com.zscat.mq.kafka.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * @author zscat
 * @date 2016-12-17
 */
@Named
public class SlowServer {

    private static Logger log = LoggerFactory.getLogger(SlowServer.class);

    @Inject
    private KafkaConsumer kafkaConsumer;

    @PostConstruct
    public void start() throws Exception {
        try {
            kafkaConsumer.start();

            log.warn("SlowServer start success.");
        } catch (Exception e) {
            log.error("SlowServer start error!", e);
            System.exit(-1);
        }
    }

}
