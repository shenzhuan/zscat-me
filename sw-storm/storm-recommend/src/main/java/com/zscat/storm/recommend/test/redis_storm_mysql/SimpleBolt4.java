package com.zscat.storm.recommend.test.redis_storm_mysql;

import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

public class SimpleBolt4  extends BaseBasicBolt{

    /**
     *
     */
    private static final long serialVersionUID = -8025390241512976224L;

    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("word"));
    }

    public void execute(Tuple input, BasicOutputCollector collector) {
        String word=input.getString(0);
        if(null!=word&&word.trim()!=""){
            String upper=word.trim().toUpperCase();
            System.out.println(String.format("upper word is:%s", upper));
            collector.emit(new Values(upper));
        }
    }

}