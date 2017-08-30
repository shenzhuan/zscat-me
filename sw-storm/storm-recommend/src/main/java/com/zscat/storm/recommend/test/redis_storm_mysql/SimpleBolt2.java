package com.zscat.storm.recommend.test.redis_storm_mysql;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

import java.util.HashMap;
import java.util.Map;



public class SimpleBolt2 extends BaseBasicBolt {

    /**
     *
     */
    private static final long serialVersionUID = 2246728833921545676L;
    Integer id;
    String name;
    Map<String, Integer> counters;

    @SuppressWarnings("rawtypes")
    public void prepare(Map stormConf, TopologyContext context) {
        this.counters=new HashMap<String, Integer>();
        this.name=context.getThisComponentId();
        this.id=context.getThisTaskId();
        System.out.println(String.format("componentId:%s",this.name));
    }

    public void execute(Tuple input, BasicOutputCollector collector) {
        String word=input.getString(0);
        if(counters.containsKey(word)){
            Integer c=counters.get(word);
            counters.put(word, c+1);
        }
        else{
            counters.put(word, 1);
        }
        collector.emit(new Values(word,counters.get(word)));
        System.out.println(String.format("stats result is:%s:%s", word,counters.get(word)));
    }

    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("word","count"));
    }

}