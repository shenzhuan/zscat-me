package com.zscat.storm.recommend.test.redis_storm_mysql;
import java.util.List;
import java.util.Map;

import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.base.BaseRichSpout;
import redis.clients.jedis.Jedis;
import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

public class SimpleSpout extends BaseRichSpout {

    /**
     *
     */
    private static final long serialVersionUID = -6335251364034714629L;
    private SpoutOutputCollector collector;
    private Jedis jedis;
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("source"));
    }

    @SuppressWarnings("rawtypes")
    public void open(Map conf, TopologyContext context,
            SpoutOutputCollector collector) {
            this.collector=collector;
            jedis=new Jedis("127.0.0.1", 6379);
    }

    public void nextTuple() {
          List<String> messages=jedis.brpop(3600,"msg_queue");
          if(!messages.isEmpty()){
              for (String msg : messages) {
                  collector.emit(new Values(msg));
            }
          }
    }

    public static void main(String[] args) {
    	Jedis jedis1=new Jedis("127.0.0.1", 6379);
    	jedis1.lpush("msg_queue", "msg_queue1");
    	jedis1.lpush("msg_queue", "msg_queue2");
    	System.out.println(jedis1.brpop(3600,"msg_queue"));
	}
}