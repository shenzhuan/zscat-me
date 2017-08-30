package com.zscat.storm.recommend.test.order;
  
import java.util.UUID;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.StormSubmitter;
import org.apache.storm.generated.AlreadyAliveException;
import org.apache.storm.generated.InvalidTopologyException;
import org.apache.storm.kafka.*;
import org.apache.storm.spout.SchemeAsMultiScheme;
import org.apache.storm.topology.TopologyBuilder;

public class ordersAnalysisTopology {  
      
    private static String topicName = "ordersInfo";  
    private static String zkRoot = "/stormKafka/"+topicName;  
      
    public static void main(String[] args) {  
          
        BrokerHosts hosts = new ZkHosts("ymhHadoop:2181,ymhHadoop2:2181,ymhHadoop3:2181");
  
          
        SpoutConfig spoutConfig = new SpoutConfig(hosts,topicName,zkRoot,UUID.randomUUID().toString());
        spoutConfig.scheme = new SchemeAsMultiScheme(new StringScheme());
        KafkaSpout kafkaSpout = new KafkaSpout(spoutConfig);
          
        TopologyBuilder builder = new TopologyBuilder();
        builder.setSpout("kafkaSpout",kafkaSpout);          
        builder.setBolt("merchantsSalesBolt", new merchantsSalesAnalysisBolt(), 2).shuffleGrouping("kafkaSpout");  
  
        Config conf = new Config();
        conf.setDebug(true);  
          
        if(args != null && args.length > 0) {  
            conf.setNumWorkers(1);  
            try {  
                StormSubmitter.submitTopologyWithProgressBar(args[0], conf, builder.createTopology());
            } catch (Exception e) {
                // TODO Auto-generated catch block  
                e.printStackTrace();  
            }
              
        } else {  
              
            conf.setMaxSpoutPending(3);  
              
            LocalCluster cluster = new LocalCluster();
            cluster.submitTopology("ordersAnalysis", conf, builder.createTopology());  
              
              
        }  
  
    }  
}  