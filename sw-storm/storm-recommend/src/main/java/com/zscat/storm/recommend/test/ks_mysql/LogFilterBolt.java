package com.zscat.storm.recommend.test.ks_mysql;
  
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Tuple;

import java.util.Map;
import java.util.regex.Matcher;  
import java.util.regex.Pattern;  
  

  
/** 
* 处理来自KafkaSpout的tuple，并保存到数据库中 
* 
* @author shenfl 
* 
*/  
public class LogFilterBolt extends BaseRichBolt {
  
     private OutputCollector collector;
     /** 
     * 
     */  
     private static final long serialVersionUID = 1L;  
  
     Pattern p = Pattern.compile("省公司鉴权接口url\\[(.*)]\\,响应时间\\[([0-9]+)\\],当前时间\\[([0-9]+)\\]");  
  
     /** 
     * 每个LogFilterBolt实例仅初始化一次 
     */  
     @Override  
     public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
          this.collector = collector;  
     }  
  
     @Override  
     public void execute(Tuple input) {
          try {  
               // 接收KafkaSpout的数据  
               byte[] bytes = input.getBinaryByField("bytes");  
               String value = new String(bytes).replaceAll("[\n\r]", "");  
               // 解析数据并入库  
               Matcher m = p.matcher(value);  
               if (m.find()) {  
                    String url = m.group(1);  
                    String usetime = m.group(2);  
                    String currentTime = m.group(3);  
                    System.out.println(url + "->" + usetime + "->" + currentTime);  
               }  
               this.collector.ack(input);  
          } catch (Exception e) {  
               this.collector.fail(input);  
          }  
     }  
  
     @Override  
     public void declareOutputFields(OutputFieldsDeclarer declarer) {
     }  
} 