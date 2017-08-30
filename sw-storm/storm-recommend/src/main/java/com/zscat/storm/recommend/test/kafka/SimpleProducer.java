package com.zscat.storm.recommend.test.kafka;
  
import java.util.Properties;  
  
import kafka.javaapi.producer.Producer;  
import kafka.producer.KeyedMessage;  
import kafka.producer.ProducerConfig;  
  
/******************************************************************************* 
 * BidPlanStructForm.java Created on 2014-7-8 
 * Author: <a href=mailto:wanghouda@126.com>houda</a> 
 * @Title: SimpleProducer.java 
 * @Package bonree.producer 
 * Description: 
 * Version: 1.0 
 ******************************************************************************/  
public class SimpleProducer {  
    private static Producer<Integer,String> producer;  
    private final Properties props=new Properties();  
    public SimpleProducer(){  
        //定义连接的broker list  
        props.put("metadata.broker.list", "10.2.10.61:9092");  
        //定义序列化类（Java对象传输前要序列化）  
        props.put("serializer.class", "kafka.serializer.StringEncoder");  
        producer = new Producer<Integer, String>(new ProducerConfig(props));  
    }  
    public static void main(String[] args) {  
        SimpleProducer sp=new SimpleProducer();  
        //定义topic  
        String topic="test";  
        //定义要发送给topic的消息  
        String messageStr = "send a message to broker ";  
        //构建消息对象  
        KeyedMessage<Integer, String> data = new KeyedMessage<Integer, String>(topic, messageStr);  
        //推送消息到broker  
        producer.send(data);  
        producer.close();  
    }  
} 