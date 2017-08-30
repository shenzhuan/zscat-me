package com.zscat.storm.recommend.test.order;
  
import java.util.Map;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Tuple;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

  
public class merchantsSalesAnalysisBolt extends BaseRichBolt {
      
    private OutputCollector _collector;
    logInfoHandler loginfohandler;  
    JedisPool pool;  
  
    public void execute(Tuple tuple) {
        String orderInfo = tuple.getString(0);  
        ordersBean order = loginfohandler.getOrdersBean(orderInfo);  
          
        //store the salesByMerchant infomation into Redis  
        Jedis jedis = pool.getResource();  
        jedis.zincrby("orderAna:topSalesByMerchant", order.getTotalPrice(), order.getMerchantName());  
    }  
   
    public void prepare(Map arg0, TopologyContext arg1, OutputCollector collector) {
        this._collector = collector;  
        this.loginfohandler = new logInfoHandler();  
        this.pool = new JedisPool(new JedisPoolConfig(), "ymhHadoop",6379,2 * 60000,"12345");  
          
    }  
  
    public void declareOutputFields(OutputFieldsDeclarer arg0) {
        // TODO Auto-generated method stub  
          
    }  
  
}  