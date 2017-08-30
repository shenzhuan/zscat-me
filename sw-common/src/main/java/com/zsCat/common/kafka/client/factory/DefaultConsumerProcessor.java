package com.zsCat.common.kafka.client.factory;

import com.zsCat.common.kafka.client.IConsumerProcessor;
import com.zsCat.common.kafka.client.IMessageListener;
import com.zsCat.common.kafka.client.KafkaClient;
        import com.zsCat.common.kafka.client.exception.ConsumerConfigException;
import com.zsCat.common.kafka.client.exception.ConsumerRuntimeException;
import com.zsCat.common.kafka.client.utils.KafkaConstants;
import com.zsCat.common.kafka.client.utils.NamedThreadFactory;
import com.zsCat.common.kafka.client.utils.StackTraceUtil;

import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import org.slf4j.Logger;
        import org.slf4j.LoggerFactory;



import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author lixu
 * @version 1.0
 * @created 15-12-22
 */
public class DefaultConsumerProcessor implements IConsumerProcessor {

    private static final Logger log = LoggerFactory.getLogger(DefaultConsumerProcessor.class);

    private String topic;

    private int numThreads;

    private ConsumerConnector consumer;

    private ExecutorService executor;

    public DefaultConsumerProcessor(ConsumerConnector consumer, String topic, int numThreads) {
        this.consumer = consumer;
        this.topic = topic;
        this.numThreads = numThreads;
    }

    @Override
    public void recvMessageWithParallel(Class clz, IMessageListener iMessageListener) {

        if ( numThreads <= 0 ) {
            log.error("xm-kafka-client, consumer.numThreads is not illegality, topic : {}", topic);
            throw new ConsumerRuntimeException("xm-kafka-client, consumer.numThreads is not illegality");
        }

        Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
        topicCountMap.put(topic, new Integer(numThreads));
        Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumer.createMessageStreams(topicCountMap);
        List<KafkaStream<byte[], byte[]>> streams = consumerMap.get(topic);
        executor = createConsumerExecutor(numThreads);
        for (final KafkaStream stream : streams) {
            executor.submit(new ConsumerMsgTask(stream, iMessageListener));
        }
        log.info("xm-kafka-client, consumer.recv start, topic : {}", topic);
    }

    @Override
    public void close() throws Exception {
        executor.shutdown();
        consumer.shutdown();
    }

    private ExecutorService createConsumerExecutor(int numThreads) {

        this.executor = new ThreadPoolExecutor(numThreads, numThreads, 60L,
                TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(100000), new NamedThreadFactory(
                "XM-KAFKA-CLIENT-CONSUMER-EXECUTOR", true), new ThreadPoolExecutor.AbortPolicy() {

            private Logger inlog = LoggerFactory.getLogger(getClass());

            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {

                String msg = String.format("Thread pool is EXHAUSTED!"
                        + " Thread Name: %s, Pool Size: %d (active: %d, core: %d, max: %d, largest: %d), Task: %d (completed: %d),"
                        + " Executor status:(isShutdown:%s, isTerminated:%s, isTerminating:%s) !",
                        "XM-KAFKA-CLIENT-CONSUMER-ABORD-REJECTD-PROCESSOR", e.getPoolSize(), e.getActiveCount(), e.getCorePoolSize(), e.getMaximumPoolSize(),
                        e.getLargestPoolSize(), e.getTaskCount(), e.getCompletedTaskCount(), e.isShutdown(),
                        e.isTerminated(), e.isTerminating());
                inlog.warn(msg);
                throw new RejectedExecutionException(msg);
            }
        });
        return this.executor;
    }
}
