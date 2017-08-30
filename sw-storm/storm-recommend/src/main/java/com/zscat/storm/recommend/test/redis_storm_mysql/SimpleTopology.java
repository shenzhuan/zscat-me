package com.zscat.storm.recommend.test.redis_storm_mysql;
import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.StormSubmitter;
import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

/**
 * mvn clean assembly:assembly -Dmaven.test.skip=true
提交作业并运行：

本地模式运行：storm jar target/app-storm-1.0-jar-with-dependencies.jar  com.haozu.app.SimpleTopology 

集群模式运行：storm jar target/app-storm-1.0-jar-with-dependencies.jar  com.haozu.app.SimpleTopology "test"
 *
 */
public class SimpleTopology
{
    public static void main( String[] args ) throws Exception
    {
        TopologyBuilder topologyBuilder=new TopologyBuilder();
        topologyBuilder.setSpout("simple-spout", new SimpleSpout(),1);
        topologyBuilder.setBolt("simple-bilt",new SimpleBolt1(), 3).shuffleGrouping("simple-spout");
        topologyBuilder.setBolt("wordcounter", new SimpleBolt2(), 3).fieldsGrouping("simple-bilt", new Fields("info"));
        topologyBuilder.setBolt("word-to-upper", new SimpleBolt4(),5).shuffleGrouping("simple-spout");
        topologyBuilder.setBolt("store", new SimpleBolt3(),10).shuffleGrouping("word-to-upper");
        Config config=new Config();
        config.setDebug(true);
        if(null!=args&&args.length>0){
            //使用集群模式运行
            config.setNumWorkers(1);
            StormSubmitter.submitTopology(args[0], config, topologyBuilder.createTopology());
        }
        else{
            //使用本地模式运行
            config.setMaxTaskParallelism(1);
            LocalCluster cluster=new LocalCluster();
            cluster.submitTopology("simple", config, topologyBuilder.createTopology());
        }
    }
}