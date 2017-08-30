package com.zscat.storm.recommend.test.mongodb;

import java.net.UnknownHostException;
import java.util.ArrayList;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoException;
import com.mongodb.WriteResult;

/**
 * 类名： MongoDBDaoImpl
 * 包名： com.newsTest.dao.impl
 * 作者： zhouyh
 * 时间： 2014-8-30 下午04:21:11
 * 描述： TODO(这里用一句话描述这个类的作用) 
 */
public class MongoDBDaoImpl implements MongoDBDao{
	
	/**
	 * MongoClient的实例代表数据库连接池，是线程安全的，可以被多线程共享，客户端在多线程条件下仅维持一个实例即可
	 * Mongo是非线程安全的，目前mongodb API中已经建议用MongoClient替代Mongo
	 */
	private MongoClient mongoClient = null;
	/**
	 * 
	 * 私有的构造函数
	 * 作者：zhouyh
	 */
	private MongoDBDaoImpl(){
		if(mongoClient == null){
			MongoClientOptions.Builder build = new MongoClientOptions.Builder();		
			build.connectionsPerHost(50);	//与目标数据库能够建立的最大connection数量为50

			build.threadsAllowedToBlockForConnectionMultiplier(50);	//如果当前所有的connection都在使用中，则每个connection上可以有50个线程排队等待
			/*
			 * 一个线程访问数据库的时候，在成功获取到一个可用数据库连接之前的最长等待时间为2分钟
			 * 这里比较危险，如果超过maxWaitTime都没有获取到这个连接的话，该线程就会抛出Exception
			 * 故这里设置的maxWaitTime应该足够大，以免由于排队线程过多造成的数据库访问失败
			 */
			build.maxWaitTime(1000*60*2);
			build.connectTimeout(1000*60*1);	//与数据库建立连接的timeout设置为1分钟
			
			MongoClientOptions myOptions = build.build();		
			try {
				//数据库连接实例
				mongoClient = new MongoClient("127.0.0.1", myOptions);			
			} catch (Exception e) {
				// TODO 这里写异常处理的代码
				e.printStackTrace();
			}
			
		}
	}
	
	/********单例模式声明开始，采用饿汉式方式生成，保证线程安全********************/
	
	//类初始化时，自行实例化，饿汉式单例模式
	private static final MongoDBDaoImpl mongoDBDaoImpl = new MongoDBDaoImpl();
	/**
	 * 
	 * 方法名：getMongoDBDaoImplInstance
	 * 作者：zhouyh
	 * 创建时间：2014-8-30 下午04:29:26
	 * 描述：单例的静态工厂方法
	 * @return
	 */
	public static MongoDBDaoImpl getMongoDBDaoImplInstance(){
		return mongoDBDaoImpl;
	}
	
	/************************单例模式声明结束*************************************/
	
	@Override
	public boolean delete(String dbName, String collectionName, String[] keys,
			Object[] values) {
		DB db = null;
		DBCollection dbCollection = null;
		if(keys!=null && values!=null){
			if(keys.length != values.length){	//如果keys和values不对等，直接返回false
				return false;
			}else{
				try {
					db = mongoClient.getDB(dbName);	//获取指定的数据库
					dbCollection = db.getCollection(collectionName);	//获取指定的collectionName集合
					
					BasicDBObject doc = new BasicDBObject();	//构建删除条件
					WriteResult result = null;	//删除返回结果
					String resultString = null;
					
					for(int i=0; i<keys.length; i++){
						doc.put(keys[i], values[i]);	//添加删除的条件
					}
					result = dbCollection.remove(doc);	//执行删除操作
					

					
					if(null != db){
						try {

							db = null;
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}
						
					}
					
					return (resultString!=null) ? false : true;	//根据删除执行结果进行判断后返回结果
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				} finally{
					if(null != db){

						db = null;
					}
				}
				
			}
		}
		return false;
	}

	@Override
	public ArrayList<DBObject> find(String dbName, String collectionName,
			String[] keys, Object[] values, int num) {
		ArrayList<DBObject> resultList = new ArrayList<DBObject>();	//创建返回的结果集
		DB db = null;
		DBCollection dbCollection = null;
		DBCursor cursor = null;
		if(keys!=null && values!=null){
			if(keys.length != values.length){
				return resultList;	//如果传来的查询参数对不对，直接返回空的结果集
			}else{
				try {
					db = mongoClient.getDB(dbName);	//获取数据库实例
					dbCollection = db.getCollection(collectionName);	//获取数据库中指定的collection集合
					
					BasicDBObject queryObj = new BasicDBObject();	//构建查询条件
					
					for(int i=0; i<keys.length; i++){	//填充查询条件
						queryObj.put(keys[i], values[i]);
					}				
					cursor = dbCollection.find(queryObj);	//查询获取数据
					int count = 0;
					if(num != -1){	//判断是否是返回全部数据，num=-1返回查询全部数据，num!=-1则返回指定的num数据
						while(count<num && cursor.hasNext()){
							resultList.add(cursor.next());
							count++;
						}
						return resultList;
					}else{
						while(cursor.hasNext()){
							resultList.add(cursor.next());
						}
						return resultList;
					}
				} catch (Exception e) {
					// TODO: handle exception
				} finally{				
					if(null != cursor){
						cursor.close();
					}
					if(null != db){

					}
				}
			}
		}

		return resultList;
	}

	@Override
	public DBCollection getCollection(String dbName, String collectionName) {
		// TODO Auto-generated method stub
		return mongoClient.getDB(dbName).getCollection(collectionName);
	}

	@Override
	public DB getDb(String dbName) {
		// TODO Auto-generated method stub
		return mongoClient.getDB(dbName);
	}

	@Override
	public boolean inSert(String dbName, String collectionName, String[] keys,
			Object[] values) {
		DB db = null;
		DBCollection dbCollection = null;
		WriteResult result = null;
		String resultString = null;
		if(keys!=null && values!=null){
			if(keys.length != values.length){
				return false;
			}else{
				db = mongoClient.getDB(dbName);	//获取数据库实例
				dbCollection = db.getCollection(collectionName);	//获取数据库中指定的collection集合
				BasicDBObject insertObj = new BasicDBObject();
				for(int i=0; i<keys.length; i++){	//构建添加条件
					insertObj.put(keys[i], values[i]);
				}
				
				try {
					result = dbCollection.insert(insertObj);

				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}finally{
					if(null != db){

					}
				}				
				return (resultString != null) ? false : true;
			}
		}
		return false;
	}

	@Override
	public boolean isExit(String dbName, String collectionName, String key,
			Object value) {
		// TODO Auto-generated method stub
		DB db = null;
		DBCollection dbCollection = null;
		if(key!=null && value!=null){
			try {
				db = mongoClient.getDB(dbName);	//获取数据库实例
				dbCollection = db.getCollection(collectionName);	//获取数据库中指定的collection集合
				BasicDBObject obj = new BasicDBObject();	//构建查询条件
				obj.put(key, value);
				
				if(dbCollection.count(obj) > 0) {
					return true;
				}else{
					return false;
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			} finally{
				if(null != db){

					db = null;
				}
			}
			
		}
		return false;
	}

	@Override
	public boolean update(String dbName, String collectionName,
			DBObject oldValue, DBObject newValue) {
		DB db = null;
		DBCollection dbCollection = null;
		WriteResult result = null;
		String resultString = null;
		
		if(oldValue.equals(newValue)){
			return true;
		}else{
			try {
				db = mongoClient.getDB(dbName);	//获取数据库实例
				dbCollection = db.getCollection(collectionName);	//获取数据库中指定的collection集合
				
				result = dbCollection.update(oldValue, newValue);

				
				return (resultString!=null) ? false : true;
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			} finally{
				if(null != db){

					db = null;
				}
			}
			
		}
		
		return false;
	}
	
	/**
	 * 方法名：main
	 * 作者：zhouyh
	 * 创建时间：2014-8-30 下午04:21:11
	 * 描述：TODO(这里用一句话描述这个方法的作用)
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}
	
}
