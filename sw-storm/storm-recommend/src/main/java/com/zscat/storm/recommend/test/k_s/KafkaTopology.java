package com.zscat.storm.recommend.test.k_s;
  
import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.StormSubmitter;
import org.apache.storm.kafka.BrokerHosts;
import org.apache.storm.kafka.SpoutConfig;
import org.apache.storm.kafka.StringScheme;
import org.apache.storm.kafka.ZkHosts;
import org.apache.storm.spout.SchemeAsMultiScheme;
import org.apache.storm.topology.TopologyBuilder;

import java.util.Arrays;


  
public class KafkaTopology {  
    public static void main(String[] args) {  
        try {  
            //实例化topologyBuilder类。  
            TopologyBuilder topologyBuilder = new TopologyBuilder();
            //设置喷发节点并分配并发数，该并发数将会控制该对象在集群中的线程数。  
//            String zks = "h1:2181,h2:2181,h3:2181";  
//            String topic = "my-replicated-topic5";  
//            String zkRoot = "/storm"; // default zookeeper root configuration for storm  
//            String id = "word";  
//                
//            BrokerHosts brokerHosts = new ZkHosts(zks);  
//            SpoutConfig spoutConf = new SpoutConfig(brokerHosts, topic, zkRoot, id);  
//            spoutConf.scheme = new SchemeAsMultiScheme(new StringScheme());  
//            spoutConf.forceFromStart = true;  
//            spoutConf.zkServers = Arrays.asList(new String[] {"h1", "h2", "h3"});  
//            spoutConf.zkPort = 2181;  
   
            BrokerHosts brokerHosts = new ZkHosts("10.2.10.61:2181");
            String topic = "test3";  
            // 配置Kafka订阅的Topic，以及zookeeper中数据节点目录和名字  
            SpoutConfig spoutConfig = new SpoutConfig(brokerHosts, topic, "/storm", "redis-group5");
            spoutConfig.scheme = new SchemeAsMultiScheme(new StringScheme());
            spoutConfig.zkServers = Arrays.asList(new String[] {"10.2.10.61"});  
            spoutConfig.zkPort = 2181;  
            // -2 从kafka头开始  -1 是从最新的开始 0 =无 从ZK开始    
//          spoutConfig.startOffsetTime = Integer.valueOf(StormAppConfigUtil.get(CommonConstant.KAFKA_CONF_OFFSETTIME));  
//          spoutConfig.forceFromStart = Boolean.valueOf(StormAppConfigUtil.get(CommonConstant.KAFKA_CONF_FORCESTART));  
         //   spoutConfig.forceFromStart = false;//这个是是否每次从头读,false为不从头读  
            org.apache.storm.kafka.KafkaSpout receiver = new org.apache.storm.kafka.KafkaSpout(spoutConfig);
            topologyBuilder.setSpout("kafka-spout", receiver,5).setNumTasks(10);  
            // 设置数据处理节点，并分配并发数。指定该几点接收喷发节点的策略为随机方式。  
            topologyBuilder.setBolt("groupSnToredis-bolt", new GroupSnDatatoRedisBolt(),5).setNumTasks(10).shuffleGrouping("kafka-spout"); 
           //  topologyBuilder.setBolt("mysql-bolt", new AllDatatoMysqlBolt(),5).setNumTasks(10).shuffleGrouping("kafka-spout");  
            topologyBuilder.setBolt("redis-bolt", new AllDatatoRedisBolt(),5).setNumTasks(10).shuffleGrouping("kafka-spout");  
         //   topologyBuilder.setBolt("kafka-bolt", new SimpleBolt(),5).setNumTasks(10).shuffleGrouping("kafka-spout");  
          //  topologyBuilder.setBolt("kafka-hbase-bolt", new SimpleBolt2(),5).setNumTasks(10).shuffleGrouping("kafka-bolt");  
            Config config = new Config();
            config.setDebug(false);  
            if (args != null && args.length > 0) {  
                /*设置该topology在storm集群中要抢占的资源slot数，一个slot对应这supervisor节点上的以个worker进程 
                 如果你分配的spot数超过了你的物理节点所拥有的worker数目的话，有可能提交不成功，加入你的集群上面已经有了 
                 一些topology而现在还剩下2个worker资源，如果你在代码里分配4个给你的topology的话，那么这个topology可以提交 
                 但是提交以后你会发现并没有运行。 而当你kill掉一些topology后释放了一些slot后你的这个topology就会恢复正常运行。 
                */  
                config.setNumWorkers(1);  
                StormSubmitter.submitTopology(args[0], config, topologyBuilder.createTopology());
            } else {  
                //这里是本地模式下运行的启动代码。  
                config.setNumWorkers(2);  
                config.setMaxTaskParallelism(1);  
                LocalCluster cluster = new LocalCluster();
                cluster.submitTopology("simple", config,  
                        topologyBuilder.createTopology());  
//                Thread.sleep(5000);  
//                cluster.killTopology("simple");  
//                cluster.shutdown();  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.  
        }  
    }  
} 