package com.zscat.storm.recommend.test.redis_storm_mysql;


import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;

public class SimpleBolt3 extends BaseBasicBolt {

    /**
     *
     */
    private static final long serialVersionUID = 9140971206523366543L;

    public void execute(Tuple input, BasicOutputCollector collector) {
        String word=input.getString(0);
        StoreDatabase.insertRow(word);
    }

    public void declareOutputFields(OutputFieldsDeclarer declarer) {
            declarer.declare(new Fields("word"));
    }

}