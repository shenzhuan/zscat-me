package com.zscat.storm.recommend.test.k_s;
  
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.serializer.StringDecoder;
import kafka.utils.VerifiableProperties;
import org.apache.storm.kafka.SpoutConfig;
import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;


public class KafkaSpout extends BaseRichSpout {
  
    private SpoutOutputCollector collector;
  
    private static ConsumerConnector consumer;



    /** 
     * 这里初始化collector 
     *  
     * @param conf 
     * @param context 
     * @param collector 
     */  
    @Override  
    public void open(Map conf, TopologyContext context,
            SpoutOutputCollector collector) {  
        this.collector = collector;  
        Properties props = new Properties();  
        // zookeeper 配置  
        props.put("zookeeper.connect", "10.2.10.61:2181");  
  
        // group 代表一个消费组  
        props.put("group.id", "shen-group5");  
  
        // zk连接超时  
        props.put("zookeeper.session.timeout.ms", "4000");  
        props.put("zookeeper.sync.time.ms", "200");  
        props.put("auto.commit.interval.ms", "1000");  
        props.put("auto.offset.reset", "smallest");  
        // 序列化类  
        props.put("serializer.class", "kafka.serializer.StringEncoder");  
  
        ConsumerConfig config = new ConsumerConfig(props);  
  
        consumer = kafka.consumer.Consumer.createJavaConsumerConnector(config);  
    }  
  
    /** 
     * 该方法会在SpoutTracker类中被调用每调用一次就可以向storm集群中发射一条数据（一个tuple元组） 该方法会被不停的调用 
     */  
    @Override  
    public void nextTuple() {  
        try {  
            // String msg = info[rd.nextInt(10)];  
            // // 调用发射方法  
            // collector.emit(new Values(msg),rd.nextInt(100));  
             Map<String, Integer> topicCountMap = new HashMap<String, Integer>();  
                topicCountMap.put("zhuan", new Integer(1));  
           
                StringDecoder keyDecoder = new StringDecoder(new VerifiableProperties());  
                StringDecoder valueDecoder = new StringDecoder(new VerifiableProperties());  
           
                Map<String, List<KafkaStream<String, String>>> consumerMap =  
                        consumer.createMessageStreams(topicCountMap,keyDecoder,valueDecoder);  
                KafkaStream<String, String> stream = consumerMap.get("zhuan").get(0);  
                ConsumerIterator<String, String> it = stream.iterator();  
                System.out.println(123456);  
                while (it.hasNext()){  
                    String message = it.next().message();  
                    collector.emit(new Values(message));
                }  
            // 模拟等待100ms  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
  
    /** 
     * 这里定义字段id，该id在简单模式下没有用处，但在按照字段分组的模式下有很大的用处。 该declarer变量有很大作用，我们还可以调用 
     * declarer.declareStream(); 来定义stramId，该id可以用来定义 更加复杂的流拓扑结构 
     *  
     * @param declarer 
     */  
    @Override  
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("source"));
    }  
  
    @Override  
    public void ack(Object msgId) {  
        System.out.println("任务执行完了:" + msgId);  
    }  
  
    @Override  
    public void fail(Object msgId) {  
        System.out.println("任务执行失败了:" + msgId);  
    }  
} 