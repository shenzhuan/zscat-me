package com.zscat.storm.recommend.test.ks_mysql;
import java.util.UUID;
import com.zscat.storm.recommend.test.redis_storm_mysql.RedisUtils;
import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.kafka.KafkaSpout;
import org.apache.storm.kafka.SpoutConfig;
import org.apache.storm.kafka.ZkHosts;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;
  
/** 
* Storm读取Kafka消息中间件数据 
* 
* @author shenfl 
* 
*/  
public class KafkaLogProcess {  
  
  
     private static final String BOLT_ID = LogFilterBolt.class.getName();  
     private static final String SPOUT_ID = KafkaSpout.class.getName();
  
     public static void main(String[] args) {  
           
          TopologyBuilder builder = new TopologyBuilder();
          //表示kafka使用的zookeeper的地址  
          String brokerZkStr = "192.168.2.20:2181";  
          ZkHosts zkHosts = new ZkHosts(brokerZkStr);
          //表示的是kafak中存储数据的主题名称  
          String topic = "mytopic";  
          //指定zookeeper中的一个根目录，里面存储kafkaspout读取数据的位置等信息  
          String zkRoot = "/kafkaspout";  
          String id = UUID.randomUUID().toString();  
          SpoutConfig spoutconf  = new SpoutConfig(zkHosts, topic, zkRoot, id);
          builder.setSpout(SPOUT_ID , new KafkaSpout(spoutconf));  
          builder.setBolt(BOLT_ID,new  LogFilterBolt()).shuffleGrouping(SPOUT_ID);  
           
          LocalCluster localCluster = new LocalCluster();
          localCluster.submitTopology(KafkaLogProcess.class.getSimpleName(), new Config(),builder.createTopology() );
     }  
}  