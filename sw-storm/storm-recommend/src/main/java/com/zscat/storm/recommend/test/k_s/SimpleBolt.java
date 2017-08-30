package com.zscat.storm.recommend.test.k_s;
  
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

import java.util.Map;
  

  
/** 
 * Created by IntelliJ IDEA. User: comaple.zhang Date: 12-8-28 Time: 下午2:11 To 
 * change this template use File | Settings | File Templates. 
 */  
@SuppressWarnings("serial")  
public class SimpleBolt extends BaseRichBolt {
      
    private OutputCollector collector;
  
    @Override  
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("id","sn","order","type","datetime","address","user","event","params"));
    }  
  
    @SuppressWarnings("rawtypes")  
    @Override  
    public void prepare(Map stormConf, TopologyContext context,
            OutputCollector collector) {  
        this.collector = collector;  
    }  
  
    @Override  
    public void execute(Tuple input) {
        try {  
            String mesg = input.getString(0);  
        //    System.out.println("msg:"+mesg);
            String [] msgs=mesg.split(" - ");
            if (mesg != null && msgs.length == 9) {  
                 collector.emit(new Values(mesg.split(" - ")[0],mesg.split(" - ")[1],mesg.split(" - ")[2],mesg.split(" - ")[3],
                 mesg.split(" - ")[4],mesg.split(" - ")[5],mesg.split(" - ")[6],mesg.split(" - ")[7],mesg.split(" - ")[8]));  
            System.out.println(mesg);  
            }  else{
            	System.out.println(mesg);
            }
        } catch (Exception e) {  
            e.printStackTrace(); // To change body of catch statement use File |  
            collector.fail(input);                      // Settings | File Templates.  
        }  
        collector.ack(input);  
    }  
}  