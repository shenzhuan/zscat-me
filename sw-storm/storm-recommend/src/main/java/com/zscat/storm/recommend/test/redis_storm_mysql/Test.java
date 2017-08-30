package com.zscat.storm.recommend.test.redis_storm_mysql;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RedisUtils r=new RedisUtils(1);
		r.set("test1", "test1");
		System.out.println("test1:"+r.get("test1"));
		RedisUtils rr=new RedisUtils(2);
		System.out.println("test11:"+r.get("test1"));
		rr.set("test2", "test2");
		System.out.println("test2:"+r.get("test2"));
		
	}

}
