  
  package com.zscat.storm.recommend.test.k_s;
import com.zscat.storm.recommend.test.redis_storm_mysql.RedisUtils;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Tuple;

import java.util.Date;
import java.util.Map;
import java.util.Random;

  
/** 
 * Created by IntelliJ IDEA. User: comaple.zhang Date: 12-8-28 Time: 下午2:11 To 
 * change this template use File | Settings | File Templates. 
 */  
@SuppressWarnings("serial")  
public class SimpleBolt2 extends BaseRichBolt {
  
    private OutputCollector collector;
    RedisUtils r;
    Random rr;
    @Override  
    public void prepare(Map stormConf, TopologyContext context,
            OutputCollector collector) {  
        this.collector = collector;  
        r=new RedisUtils();
        rr=new Random();
    }  
  
    @Override  
    public void execute(Tuple input) {
        try {  
            String id = input.getStringByField("id");  
            String sn = input.getStringByField("sn");  
            	System.out.println("id:"+id+",sn:"+sn);
  
        } catch (Exception e) {  
            e.printStackTrace(); // To change body of catch statement use File |  
            collector.fail(input);                  // Settings | File Templates.  
        }  
        collector.ack(input);  
    }  
  
    @Override  
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
  
    }  
}  