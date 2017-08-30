package com.zscat.storm.recommend.test.ka2;
import java.util.Date;  
import java.util.Properties;  
import kafka.javaapi.producer.Producer;  
import kafka.producer.KeyedMessage;  
import kafka.producer.ProducerConfig;  
  
public class TestProducer {  
    public static void main(String[] args) {  
   
        // 设置配置属性  
        Properties props = new Properties();  
        props.put("metadata.broker.list","10.2.10.61:9092");  
        props.put("serializer.class", "kafka.serializer.StringEncoder");  
        // key.serializer.class默认为serializer.class  
        props.put("key.serializer.class", "kafka.serializer.StringEncoder");  
        // 可选配置，如果不配置，则使用默认的partitioner  
//        props.put("partitioner.class", "com.catt.kafka.demo.PartitionerDemo");  
        // 触发acknowledgement机制，否则是fire and forget，可能会引起数据丢失  
        // 值为0,1,-1,可以参考  
        // http://kafka.apache.org/08/configuration.html  
        props.put("request.required.acks", "1");  
        ProducerConfig config = new ProducerConfig(props);  
   
        // 创建producer  
        Producer<String, String> producer = new Producer<String, String>(config);  
        // 产生并发送消息  
        long start=System.currentTimeMillis();  
        long runtime = new Date().getTime();  
        String ip = "10.2.10.61" ;   //rnd.nextInt(255);  
        String msg = runtime + "top33" + ip;  
        //如果topic不存在，则会自动创建，默认replication-factor为1，partitions为0  
        KeyedMessage<String, String> data = new KeyedMessage<String, String>(  
                "test", ip, msg);  
        producer.send(data);  
        System.out.println("耗时:" + (System.currentTimeMillis() - start));  
        // 关闭producer  
        producer.close();  
    }  
}  