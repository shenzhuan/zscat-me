package com.zscat.storm.recommend.test.redis_storm_mysql;


import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

public class SimpleBolt1 extends BaseBasicBolt {

    /**
     *
     */
    private static final long serialVersionUID = -5266922733759958473L;

    public void execute(Tuple input, BasicOutputCollector collector) {
        String message=input.getString(0);
        if(null!=message.trim()){
            collector.emit(new Values(message));
        }
    }

    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("info"));
    }

}