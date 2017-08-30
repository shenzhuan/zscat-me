package com.zscat.storm.recommend.test.mongodb;
 
import com.mongodb.*;
import com.mongodb.util.JSON;
 
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
 
 
/**
 * Created by superman on 2014/6/30.
 */
 
public class MongoManager {
    private DB db;
    private static final Integer soTimeOut = 300000;
    private static final Integer connectionsPerHost = 500;
    private static final Integer threadsAllowedToBlockForConnectionMultiplier = 500;
 
    public MongoManager(String host, int port, String database) {
        MongoClient mongoClient = new MongoClient(new ServerAddress(host, port), new MongoClientOptions.Builder()
		        .socketTimeout(soTimeOut)
		        .connectionsPerHost(connectionsPerHost)
		        .threadsAllowedToBlockForConnectionMultiplier(threadsAllowedToBlockForConnectionMultiplier)
		        .socketKeepAlive(true)
		        .build()
		);
		db = mongoClient.getDB(database);
    }
 
    public void save(String collection, DBObject dbObject) {
        db.getCollection(collection).save(dbObject);
    }
 
    public void delete(String collection, DBObject query) {
        db.getCollection(collection).remove(query);
    }
 
    // 此处不使用toArray()方法直接转换为List,是因为toArray()会把结果集直接存放在内存中，
    // 如果查询的结果集很大，并且在查询过程中某一条记录被修改了，就不能够反应到结果集中，从而造成"不可重复读"
    // 而游标是惰性获取数据
    public List<DBObject> find(String collection, DBObject query, DBObject fields, int limit) {
        List<DBObject> list = new LinkedList<>();
        Cursor cursor = db.getCollection(collection).find(query, fields).limit(limit);
        while (cursor.hasNext()) {
            list.add(cursor.next());
        }
        return list.size() > 0 ? list : null;
    }
 
    public List<DBObject> find(String collection, DBObject query, DBObject fields, DBObject orderBy, int pageNum, int pageSize) {
        List<DBObject> list = new ArrayList<>();
        Cursor cursor = db.getCollection(collection).find(query, fields).skip((pageNum - 1) * pageSize).limit(pageSize).sort(orderBy);
        while (cursor.hasNext()) {
            list.add(cursor.next());
        }
        return list.size() > 0 ? list : null;
    }
 
    public DBObject findOne(String collection, DBObject query, DBObject fields) {
        return db.getCollection(collection).findOne(query, fields);
    }
 
    public void update(String collection, DBObject query, DBObject update, boolean upsert, boolean multi) {
        db.getCollection(collection).update(query, update, upsert, multi);
    }
 
    public long count(String collection, DBObject query) {
        return db.getCollection(collection).count(query);
    }
 
 
    //查询出key字段,去除重复，返回值是{_id:value}形式的list
    public List distinct(String collection, String key, DBObject query) {
        return db.getCollection(collection).distinct(key, query);
    }
 
    //适用于小数据量查询
    public void distinctWithHandle(String collection, String key, DBObject query, CursorHandle cursorHandle) {
        List<DBObject> pipeLine = new ArrayList<>();
        pipeLine.add(new BasicDBObject("$match", query));
        String groupStr = String.format("{$group:{_id:'$%s'}}", key);
        pipeLine.add((DBObject) JSON.parse(groupStr));
        Cursor cursor = db.getCollection(collection).aggregate(pipeLine, AggregationOptions.builder().build());
        cursorHandle.handle(cursor);
    }
 
    //适用于大数据量查询
    public void distinctWithHandle(String collection, String key, DBObject query, int pageNo, int pageSize, CursorHandle cursorHandle) {
        List<DBObject> pipeLine = new ArrayList<>();
        pipeLine.add(new BasicDBObject("$match", query));
        String groupStr = String.format("{$group:{_id:'$%s'}}", key);
        pipeLine.add((DBObject) JSON.parse(groupStr));
        pipeLine.add(new BasicDBObject("$skip", (pageNo - 1) * pageSize));
        pipeLine.add(new BasicDBObject("$limit", pageSize));
        Cursor cursor = db.getCollection(collection).aggregate(pipeLine, AggregationOptions.builder().build());
        cursorHandle.handle(cursor);
    }
 
		public static void main(String[] args) {

		}
}