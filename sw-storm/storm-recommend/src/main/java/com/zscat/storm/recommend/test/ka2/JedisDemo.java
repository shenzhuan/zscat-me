package com.zscat.storm.recommend.test.ka2;

import java.util.Map;

import redis.clients.jedis.Jedis;

public class JedisDemo {

    public static void main(String[] args) {
        // 构造jedis对象
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        // 向redis中添加数据
       // jedis.set("mytest", "123");
       // jedis.flushAll();jedis.flushDB();
        // 从redis中读取数据
        String value = jedis.get("log9706");
      //  for(int i=0;i<11120;i++){
        	// jedis.hset("jd3", "log", "val");
       // }
        Map<String, String> map =jedis.hgetAll("jd2");
		for (Map.Entry<String, String> entry : map.entrySet()) { 
			System.out.println("key:"+entry.getKey()+",value:"+entry.getValue());
			
		} 
       
        System.out.println(value);
        // 关闭连接
        jedis.close();

    }

}
