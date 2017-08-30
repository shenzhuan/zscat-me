package com.zscat.storm.recommend.test.ka2;
public class KafkaConsumerDemo {  
    public static void main(String[] args) {  
         Consumer consumerThread1 = new Consumer("Consumer1","test");  
  
         consumerThread1.start();  
    }  
} 