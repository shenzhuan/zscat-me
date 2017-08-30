package com.zsCat.common.kafka;

import com.alibaba.fastjson.JSONObject;


import com.zsCat.common.kafka.client.IProducerProcessor;
import com.zsCat.common.kafka.client.factory.KafkaProducerBuildFactory;
import com.zsCat.common.kafka.client.utils.FutureCallback;
import com.zsCat.common.utils.JSONSerializerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 用于处理实时性要求不高的功能的Producer
 * @author zscat
 * @date 2016-12-16
 */
@Component
public class SlowProducer {

    private static final Logger log = LoggerFactory.getLogger(SlowProducer.class);

    private static AtomicLong errorTimes = new AtomicLong(0L);

    private IProducerProcessor producer;

    @PostConstruct
    public void init() {
        try {
            producer = KafkaProducerBuildFactory.init("slow.kafka.topic", "slow.metadata.broker.list");
        } catch (Exception e) {
            log.warn("build kafka SlowProducer failed.{}", e.getMessage());
        }
    }


    public void sendMessageAsync(final JSONObject json, final long id) throws Exception {
        json.put("logId", MDC.get("logId"));
        producer.sendAsyncMessage(JSONSerializerUtil.serializeToBytes(json), String.valueOf(id), new FutureCallback() {
            @Override
            public void onSuccess(Object asyncProducerResult) {
                log.info("SlowProducer send to kafka success.");
            }

            @Override
            public void onFailure(Object msg, Throwable throwable) {
                errorTimes.incrementAndGet();
                log.error("SlowProducer send to kafka error, msg={} errorTimes={}", json, errorTimes.get());
            }
        });
    }

}
