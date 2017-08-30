package com.zsCat.common.kafka.client.factory;


import com.zsCat.common.kafka.client.IMessageListener;
import com.zsCat.common.kafka.client.utils.StackTraceUtil;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author lixu
 * @version 1.0
 * @created 15-12-24
 */
public class ConsumerMsgTask implements Runnable {

    private static final Logger log = LoggerFactory.getLogger(ConsumerMsgTask.class);

    private KafkaStream stream;

    private IMessageListener msgListener;

    public ConsumerMsgTask(KafkaStream stream, IMessageListener msgListener) {
        this.stream = stream;
        this.msgListener = msgListener;
    }

    public void run() {

        ConsumerIterator<byte[], byte[]> it = stream.iterator();
        try {
            while (it.hasNext()) {
                byte[] array = it.next().message();
                msgListener.recvMessage(array);
            }
        } catch (Exception e) {
            log.error("xm-kafka-client, recv.msg occur exception = {}.", StackTraceUtil.getStackTrace(e));
        }
    }
}

