package com.zscat.storm.recommend;

import com.zscat.storm.recommend.bolt.RecommendBolt;
import com.zscat.storm.recommend.bolt.SearchCommonFriendBolt;
import com.zscat.storm.recommend.bolt.UnpackBolt;
import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.StormSubmitter;
import org.apache.storm.kafka.BrokerHosts;
import org.apache.storm.kafka.KafkaSpout;
import org.apache.storm.kafka.SpoutConfig;
import org.apache.storm.kafka.ZkHosts;
import org.apache.storm.topology.TopologyBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Arrays;

/**
 * Created by likaige on 2016-10-24.
 */
public class RecommendTopology {

    private static final Logger log = LoggerFactory.getLogger(RecommendTopology.class);
    public static void main(String[] args) {

        try {
            com.zscat.storm.recommend.redis.Config.reload();
        } catch (IOException e) {
            log.error("load config.properties error:", e);
            System.exit(-1);
        }

        String zks = com.zscat.storm.recommend.redis.Config.get("brokerZkStr");
        String topic = com.zscat.storm.recommend.redis.Config.get("topic");
        String zkServers = com.zscat.storm.recommend.redis.Config.get("zkServers");
        String zkRoot = "/stormKafka";
        String offsetZkId = "offset-recommend";
        BrokerHosts brokerHosts = new ZkHosts(zks);
        SpoutConfig spoutConf = new SpoutConfig(brokerHosts, topic, zkRoot, offsetZkId);
        spoutConf.zkServers = Arrays.asList(zkServers.split(","));
        spoutConf.zkPort = 2181;
        spoutConf.startOffsetTime = kafka.api.OffsetRequest.LatestTime();
        spoutConf.useStartOffsetTimeIfOffsetOutOfRange = true;


        Config config = new Config();
        config.setDebug(false);

        TopologyBuilder builder = new TopologyBuilder();
        builder.setSpout("KafkaSpout", new KafkaSpout(spoutConf), 5);
        builder.setBolt("UnpackBolt", new UnpackBolt(), 5).shuffleGrouping("KafkaSpout");
        builder.setBolt("SearchCommonFriendBolt", new SearchCommonFriendBolt(), 10).shuffleGrouping("UnpackBolt");
        builder.setBolt("RecommendBolt", new RecommendBolt(), 20).shuffleGrouping("SearchCommonFriendBolt");

        //通过是否有参数来控制是否启动集群，或者本地模式执行
        if (args != null && args.length > 0) {
            try {
                config.setNumWorkers(2);
                StormSubmitter.submitTopology(args[0], config, builder.createTopology());
            } catch (Exception e) {
                log.error("StormSubmitter.submitTopology error:", e);
            }
        } else {
            config.setMaxTaskParallelism(5);
            LocalCluster cluster = new LocalCluster();
            cluster.submitTopology("RecommendTopology", config, builder.createTopology());
        }

    }
}
