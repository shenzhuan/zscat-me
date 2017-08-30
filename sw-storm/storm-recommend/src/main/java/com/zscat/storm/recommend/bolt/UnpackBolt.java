package com.zscat.storm.recommend.bolt;

import com.alibaba.fastjson.JSONObject;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.IRichBolt;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * 解包
 * @author likaige
 * @date 2016-10-24
 */
public class UnpackBolt implements IRichBolt {

    private static final Logger log = LoggerFactory.getLogger(UnpackBolt.class);

    private OutputCollector outputCollector;

    @Override
    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
        this.outputCollector = outputCollector;
    }

    @Override
    public void execute(Tuple tuple) {
        try {
            byte[] bytes = (byte[]) tuple.getValue(0);
            String msg = new String(bytes, "UTF-8");
            log.info("=============================UnpackBolt receive msg: {}", msg);
            JSONObject jsonObject = JSONObject.parseObject(msg);

            String fromUid = jsonObject.getString("fromuid");
            String toUid = jsonObject.getString("touid");

            outputCollector.emit(new Values(fromUid, toUid));
        } catch (Exception e) {
            log.error("UnpackBolt error:", e);
        } finally {
            outputCollector.ack(tuple);
        }
    }


    @Override
    public void cleanup() {
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("fromUid", "toUid"));
    }

    @Override
    public Map<String, Object> getComponentConfiguration() {
        return null;
    }
}
