package com.zscat.storm.recommend.redis;

import org.apache.commons.lang.StringUtils;
import redis.clients.jedis.GeoCoordinate;
import redis.clients.jedis.GeoRadiusResponse;
import redis.clients.jedis.GeoUnit;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Transaction;
import redis.clients.jedis.Tuple;
import redis.clients.jedis.exceptions.JedisConnectionException;
import redis.clients.jedis.params.geo.GeoRadiusParam;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by njh on 15/12/27.
 */
public class RedisLink {
    private JedisPool pool;
    private JedisPoolConfig config = new JedisPoolConfig();
    public  static int COUNTER  = 0;

    public RedisLink(String address, int port, String pwd) {
        config.setMaxTotal(100);
        config.setMaxIdle(80);
        config.setMaxWaitMillis(6000);
        config.setTestWhileIdle(true);
        if (StringUtils.isBlank(pwd)) {
            pool = new JedisPool(config, address, port, 6000);
        } else {
            pool = new JedisPool(config, address, port, 6000, pwd);
        }
    }

    public boolean exist(String key) {
        Jedis jedis = null;
        boolean ret = false;
        try {
            jedis = pool.getResource();
            ret = jedis.exists(key);
        } catch (JedisConnectionException e) {
            try {
                jedis = pool.getResource();
                ret = jedis.exists(key);
            } finally {
            }
        } finally {
            returnJedis(jedis);
        }
        return ret;
    }

    public long exist(String... keys) {
        Jedis jedis = null;
        long ret = 0;
        try {
            jedis = pool.getResource();
            ret = jedis.exists(keys);
        } catch (JedisConnectionException e) {
            try {
                jedis = pool.getResource();
                ret = jedis.exists(keys);
            } finally {
            }
        } finally {
            returnJedis(jedis);
        }
        return ret;
    }

    public void del(String key) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            jedis.del(key);
        } catch (JedisConnectionException e) {
            try {
                jedis = pool.getResource();
                jedis.del(key);
            } finally {
            }
        } finally {
            returnJedis(jedis);
        }
    }

    public void expire(String key, int time) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            jedis.expire(key, time);
        } catch (JedisConnectionException e) {
            try {
                jedis = pool.getResource();
                jedis.expire(key, time);
            } finally {
            }
        }finally {
            returnJedis(jedis);
        }
    }

    public byte[] get(String key) {
        byte[] value = null;
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            value = jedis.get(key.getBytes());
        } catch (JedisConnectionException e) {
            try {
                jedis = pool.getResource();
                value = jedis.get(key.getBytes());
            } finally {
            }
        } finally {
            returnJedis(jedis);
        }
        return value;
    }

    public String getString(String key) {
        String value = null;
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            value = jedis.get(key);
        } catch (JedisConnectionException e) {
            try {
                jedis = pool.getResource();
                value = jedis.get(key);
            } finally {
            }
        } finally {
            returnJedis(jedis);
        }
        return value;
    }

    public String set(String key, byte[] value) {
        String ret = "";
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            ret = jedis.set(key.getBytes(), value);
        }catch (JedisConnectionException e) {
            try {
                jedis = pool.getResource();
                ret = jedis.set(key.getBytes(), value);
            }catch (JedisConnectionException e1) {
            }
        } finally {
            returnJedis(jedis);
        }
        return ret;
    }

    public long setnx(String key, String value) {
        Jedis jedis = null;
        long ret = 0;
        try {
            jedis = pool.getResource();
            ret = jedis.setnx(key, value);
        }catch (JedisConnectionException e) {
            try {
                jedis = pool.getResource();
                ret = jedis.setnx(key, value);
            }catch (JedisConnectionException e1) {
            }
        } finally {
            returnJedis(jedis);
        }
        return ret;
    }


    public String setString(String key, String value) {
        String ret="";
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            ret = jedis.set(key,value);
        }catch (JedisConnectionException e) {
            try {
                jedis = pool.getResource();
                ret = jedis.set(key,value);
            }catch (JedisConnectionException e1) {
            }
        } finally {
            returnJedis(jedis);
        }
        return  ret;
    }




    public String setex(String key, int expire, byte[] value) {
        String ret = "";
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            ret = jedis.setex(key.getBytes(), expire, value);
        }  catch (JedisConnectionException e) {
            try {
                jedis = pool.getResource();
                ret = jedis.setex(key.getBytes(), expire, value);
            } finally {
            }
        }finally {
            returnJedis(jedis);
        }
        return ret;
    }


    public String setStringEx(String key, int expire, String value) {
        String ret = "";
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            ret = jedis.setex(key, expire, value);
        }  catch (JedisConnectionException e) {
            try {
                jedis = pool.getResource();
                ret = jedis.setex(key, expire, value);
            } finally {
            }
        }finally {
            returnJedis(jedis);
        }
        return ret;
    }

    public Long incr(String key) {
        Jedis jedis = null;
        Long ret = (long)0;
        try {
            jedis = pool.getResource();
            ret = jedis.incr(key);
        }catch (JedisConnectionException e) {
            try {
                jedis = pool.getResource();
                ret = jedis.incr(key);
            } finally {
            }
        } finally {
            returnJedis(jedis);
        }
        return ret;
    }

    public Long incrBy(String key, long by) {
        Jedis jedis = null;
        Long ret = (long)0;
        try {
            jedis = pool.getResource();
            ret = jedis.incrBy(key,by);
        }catch (JedisConnectionException e) {
            try {
                jedis = pool.getResource();
                ret = jedis.incrBy(key,by);
            } finally {
            }
        } finally {
            returnJedis(jedis);
        }
        return ret;
    }

    public Map<String, byte[]> mgetByteArr(String[] keys) {
        byte[][] b = new byte[keys.length][];
        for (int i=0;i<b.length;i++){
            b[i]=keys[i].getBytes();
        }
        Jedis jedis = null;
        Map<String, byte[]> ret = new HashMap<String, byte[]>();
        try {
            jedis = pool.getResource();
            List<byte[]> values = jedis.mget(b);
            for( int i=0; i<keys.length; i++ ) {
                if(values.get(i)!=null){
                    ret.put(keys[i], values.get(i));
                }
            }
        } catch (JedisConnectionException e) {
            try {
                jedis = pool.getResource();
                List<byte[]> values = jedis.mget(b);
                for( int i=0; i<keys.length; i++ ) {
                    if(values.get(i)!=null){
                        ret.put(keys[i], values.get(i));
                    }
                }
            } finally {
            }
        } finally {
            returnJedis(jedis);
        }
        return ret;
    }

    public Map<String, String> mgetToMap(String... keys) {
        Jedis jedis = null;
        Map<String, String> ret = new HashMap<String, String>();
        try {
            jedis = pool.getResource();
            List<String> values = jedis.mget(keys);
            for (int i = 0; i < keys.length; i++) {
                if (values.get(i) != null) {
                    ret.put(keys[i], values.get(i));
                }
            }
        } catch (JedisConnectionException e) {
            try {
                jedis = pool.getResource();
                List<String> values = jedis.mget(keys);
                for (int i = 0; i < keys.length; i++) {
                    if (values.get(i) != null) {
                        ret.put(keys[i], values.get(i));
                    }
                }
            } finally {
            }
        } finally {
            returnJedis(jedis);
        }
        return ret;
    }

    public List<String> mget(String... key) {
        Jedis jedis = null;
        List<String> value = null;

        try {
            jedis = pool.getResource();
            value = jedis.mget(key);
        } catch (JedisConnectionException e) {
            try {
                jedis = pool.getResource();
                value = jedis.mget(key);
            } finally {
            }
        }finally {
            returnJedis(jedis);
        }
        return value;
    }


    public void rpushMulti(String key, List<byte[]> values) {
        Jedis jedis = null;
        String[] strValues = new String[values.size()];

        for( int i=0; i<values.size(); i++ ) {
            strValues[i] = new String(values.get(i));
        }
        try {
            jedis = pool.getResource();
            jedis.rpush(key, strValues);
        }catch (JedisConnectionException e) {
            try {
                jedis = pool.getResource();
                jedis.rpush(key, strValues);
            } finally {
            }
        } finally {
            returnJedis(jedis);
        }
    }

    public void rpushMultiInString(String key, List<String> values) {
        Jedis jedis = null;
        String[] strValues = new String[values.size()];

        for( int i=0; i<values.size(); i++ ) {
            strValues[i] = values.get(i);
        }
        try {
            jedis = pool.getResource();
            jedis.rpush(key, strValues);
        } catch (JedisConnectionException e) {
            try {
                jedis = pool.getResource();
                jedis.rpush(key, strValues);
            } finally {
            }
        }finally {
            returnJedis(jedis);
        }
    }


    public void lpushMulti(String key, List<byte[]> values) {
        Jedis jedis = null;
        String[] strValues = new String[values.size()];

        for( int i=0; i<values.size(); i++ ) {
            strValues[i] = new String(values.get(i));
        }
        try {
            jedis = pool.getResource();
            jedis.lpush(key, strValues);
        } catch (JedisConnectionException e) {
            try {
                jedis = pool.getResource();
                jedis.lpush(key, strValues);
            } finally {
            }
        }finally {
            returnJedis(jedis);
        }
    }

    public void lpushMultiInString(String key, List<String> values) {
        Jedis jedis = null;
        String[] strValues = new String[values.size()];
        for( int i=0; i<values.size(); i++ ) {
            strValues[i] = values.get(i);
        }
        try {
            jedis = pool.getResource();
            jedis.lpush(key, strValues);
        } catch (JedisConnectionException e) {
            try {
                jedis = pool.getResource();
                jedis.lpush(key, strValues);
            } finally {
            }
        }finally {
            returnJedis(jedis);
        }
    }

    public List<String> lrange(String key, int offset ,int limit) {
        Jedis jedis = null;
        List<String> ret = null;
        try {
            jedis = pool.getResource();
            ret = jedis.lrange(key, offset, limit);
        }catch (JedisConnectionException e) {
            try {
                jedis = pool.getResource();
                ret = jedis.lrange(key, offset, limit);
            } finally {
            }
        } finally {
            returnJedis(jedis);
        }
        return ret;
    }

    public List<byte[]> lrangereturnbyte(String key, int offset ,int limit) {
        Jedis jedis = null;
        List<byte[]> ret = null;
        try {
            jedis = pool.getResource();
            ret = jedis.lrange(key.getBytes(), offset, limit);
        } catch (JedisConnectionException e) {
            try {
                jedis = pool.getResource();
                ret = jedis.lrange(key.getBytes(), offset, limit);
            } finally {
            }
        }finally {
            returnJedis(jedis);
        }
        return ret;
    }

    public String hget(String key, String field) {
        Jedis jedis = null;
        String value = null;

        try {
            jedis = pool.getResource();
            value = jedis.hget(key, field);
        } catch (JedisConnectionException e) {
            try {
                jedis = pool.getResource();
                value = jedis.hget(key, field);
            } finally {
            }
        }finally {
            returnJedis(jedis);
        }
        return value;
    }

    public Map<String, String> hgetall(String key) {
        Jedis jedis = null;
        Map<String, String> value = null;
        try {
            jedis = pool.getResource();
            value = jedis.hgetAll(key);
        } catch (JedisConnectionException e) {
            try {
                jedis = pool.getResource();
                value = jedis.hgetAll(key);
            } finally {
            }
        }finally {
            returnJedis(jedis);
        }
        return value;
    }

    public Map<String, String> hmgetToMap(String key, String... fields) {
        Jedis jedis = null;
        Map<String, String> ret = new HashMap<String, String>();
        if (fields != null && fields.length != 0) {
            try {
                jedis = pool.getResource();
                List<String> values = jedis.hmget(key, fields);
                for (int i = 0; i < fields.length; i++) {
                    if (values.get(i) != null) {
                        ret.put(fields[i], values.get(i));
                    }
                }
            } catch (JedisConnectionException e) {
                try {
                    jedis = pool.getResource();
                    List<String> values = jedis.hmget(key, fields);
                    for (int i = 0; i < fields.length; i++) {
                        if (values.get(i) != null) {
                            ret.put(fields[i], values.get(i));
                        }
                    }
                } finally {
                }
            }finally {
                returnJedis(jedis);
            }
        }
        return ret;
    }

    public long hset(String key, String field, String value) {
        Jedis jedis = null;
        long ret = 0;
        try {
            jedis = pool.getResource();
            ret = jedis.hset(key, field, value);
        } catch (JedisConnectionException e) {
            try {
                jedis = pool.getResource();
                ret = jedis.hset(key, field, value);
            } finally {
            }
        }finally {
            returnJedis(jedis);
        }
        return ret;
    }

    public long hsetnx(String key, String field, String value) {
        Jedis jedis = null;
        long ret = 0;
        try {
            jedis = pool.getResource();
            ret = jedis.hsetnx(key, field, value);
        } catch (JedisConnectionException e) {
            try {
                jedis = pool.getResource();
                ret = jedis.hsetnx(key, field, value);
            } finally {
            }
        }finally {
            returnJedis(jedis);
        }
        return ret;
    }


    public String hmset(String key, Map<String, String> hash) {
        Jedis jedis = null;
        String ret = "";
        try {
            jedis = pool.getResource();
            ret = jedis.hmset(key, hash);
        } catch (JedisConnectionException e) {
            jedis = pool.getResource();
            ret = jedis.hmset(key, hash);
        }finally {
            returnJedis(jedis);
        }
        return ret;
    }

    public long hlen(String key) {
        Jedis jedis = null;
        long value = 0L;
        try {
            jedis = pool.getResource();
            value = jedis.hlen(key);
        } catch (JedisConnectionException e) {
            jedis = pool.getResource();
            value = jedis.hlen(key);
        }finally {
            returnJedis(jedis);
        }
        return value;
    }

    public Boolean hexists(String key, String field) {
        Jedis jedis = null;
        Boolean value = null;
        try {
            jedis = pool.getResource();
            value = jedis.hexists(key, field);
        } catch (JedisConnectionException e) {
            jedis = pool.getResource();
            value = jedis.hexists(key, field);
        }finally {
            returnJedis(jedis);
        }
        return value;
    }

    public double zincrby(String key, double addValue, String member) {
        Jedis jedis = null;
        double newscore;
        try {
            jedis = pool.getResource();
            newscore = jedis.zincrby(key,addValue,member);
        } catch (JedisConnectionException e) {
            jedis = pool.getResource();
            newscore = jedis.zincrby(key,addValue,member);
        }finally {
            returnJedis(jedis);
        }
        return newscore;
    }
    public void hrem(String key, String field) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            jedis.hdel(key, field);
        }catch (JedisConnectionException e) {
            try {
                jedis = pool.getResource();
                jedis.hdel(key, field);
            } finally {
            }
        } finally {
            returnJedis(jedis);
        }
    }

    public Set<String> hkeys(String key) {
        Jedis jedis = null;
        Set<String> ret = null;
        try {
            jedis = pool.getResource();
            ret = jedis.hkeys(key);
        } catch (JedisConnectionException e) {
            try {
                jedis = pool.getResource();
                ret = jedis.hkeys(key);
            } finally {
            }
        }finally {
            returnJedis(jedis);
        }
        return ret;
    }

    public long lpush(String key,String...vaule) {
        Jedis jedis = null;
        long ret = 0L;
        try {
            jedis = pool.getResource();
            ret = jedis.lpush(key,vaule);
        } catch (JedisConnectionException e) {
            try {
                jedis = pool.getResource();
                ret = jedis.lpush(key,vaule);
            } finally {
            }
        }finally {
            returnJedis(jedis);
        }
        return ret;
    }

    public long lrem(String key,int count,String vaule) {
        Jedis jedis = null;
        long ret = 0L;
        try {
            jedis = pool.getResource();
            ret = jedis.lrem(key,count,vaule);
        } catch (JedisConnectionException e) {
            try {
                jedis = pool.getResource();
                ret = jedis.lrem(key,count,vaule);
            } finally {
            }
        }finally {
            returnJedis(jedis);
        }
        return ret;
    }

    public String lpop(String key) {
        Jedis jedis = null;
        String ret = null;
        try {
            jedis = pool.getResource();
            ret = jedis.lpop(key);
        } catch (JedisConnectionException e) {
            try {
                jedis = pool.getResource();
                ret = jedis.lpop(key);
            } finally {
            }
        }finally {
            returnJedis(jedis);
        }
        return ret;
    }

    public String rpop(String key) {
        Jedis jedis = null;
        String ret = null;
        try {
            jedis = pool.getResource();
            ret = jedis.rpop(key);
        } catch (JedisConnectionException e) {
            try {
                jedis = pool.getResource();
                ret = jedis.rpop(key);
            } finally {
            }
        }finally {
            returnJedis(jedis);
        }
        return ret;
    }

    public long rpush(String key,String... vaule) {
        Jedis jedis = null;
        long ret = 0L;
        try {
            jedis = pool.getResource();
            ret = jedis.rpush(key,vaule);
        } catch (JedisConnectionException e) {
            try {
                jedis = pool.getResource();
                ret = jedis.rpush(key,vaule);
            } finally {
            }
        }finally {
            returnJedis(jedis);
        }
        return ret;
    }

    public String lindex(String key,long index) {
        Jedis jedis = null;
        String ret = "";
        try {
            jedis = pool.getResource();
            ret = jedis.lindex(key,index);
        } catch (JedisConnectionException e) {
            try {
                jedis = pool.getResource();
                ret = jedis.lindex(key,index);
            } finally {
            }
        }finally {
            returnJedis(jedis);
        }
        return ret;
    }

    public long hdel(String key,String... files) {
        Jedis jedis = null;
        long ret = 0L;
        try {
            jedis = pool.getResource();
            ret = jedis.hdel(key,files);
        } catch (JedisConnectionException e) {
            try {
                jedis = pool.getResource();
                ret = jedis.hdel(key,files);
            } finally {
            }
        }finally {
            returnJedis(jedis);
        }
        return ret;
    }

    public long hincrBy(final String key, final String field, final long value) {
        Jedis jedis = null;
        long ret = 0L;
        try {
            jedis = pool.getResource();
            ret = jedis.hincrBy(key, field, value);
        } catch (JedisConnectionException e) {
            try {
                jedis = pool.getResource();
                ret = jedis.hincrBy(key, field, value);
            } finally {
            }
        }finally {
            returnJedis(jedis);
        }
        return ret;
    }

    public double hincrByFloat(final String key, final String field, final double value) {
        Jedis jedis = null;
        double ret;
        try {
            jedis = pool.getResource();
            ret = jedis.hincrByFloat(key, field, value);
        } catch (JedisConnectionException e) {
            try {
                jedis = pool.getResource();
                ret = jedis.hincrByFloat(key, field, value);
            } finally {
            }
        }finally {
            returnJedis(jedis);
        }
        return ret;
    }

    public void zadd(String key, double score, String value) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            jedis.zadd(key, score, value);
        } catch (JedisConnectionException e) {
            try {
                jedis = pool.getResource();
                jedis.zadd(key, score, value);
            } finally {
            }
        }finally {
            returnJedis(jedis);
        }
    }

    public void zadd(String key, long score, String value) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            jedis.zadd(key, score, value);
        } catch (JedisConnectionException e) {
            try {
                jedis = pool.getResource();
                jedis.zadd(key, score, value);
            } finally {
            }
        }finally {
            returnJedis(jedis);
        }
    }

    public long zrem(String key, String value) {
        Jedis jedis = null;
        long ret = 0;
        try {
            jedis = pool.getResource();
            ret = jedis.zrem(key.getBytes(), value.getBytes());
        } catch (JedisConnectionException e) {
            try {
                jedis = pool.getResource();
                ret = jedis.zrem(key.getBytes(), value.getBytes());
            } finally {
            }
        }finally {
            returnJedis(jedis);
        }
        return ret;
    }



    public long zremMulti(String key, String[] values) {
        Jedis jedis = null;
        long ret = 0;
        if( values == null || values.length == 0 )
            return 0;

        try {
            jedis = pool.getResource();
            ret = jedis.zrem(key, values);
        }catch (JedisConnectionException e) {
            try {
                jedis = pool.getResource();
                ret = jedis.zrem(key, values);
            } finally {
            }
        } finally {
            returnJedis(jedis);
        }
        return ret;
    }

    public long zremrangeByRank(String key, long start, long end) {
        Jedis jedis = null;
        long ret = 0;
        try {
            jedis = pool.getResource();
            ret = jedis.zremrangeByRank(key, start, end);
        }catch (JedisConnectionException e) {
            try {
                jedis = pool.getResource();
                ret = jedis.zremrangeByRank(key, start, end);
            } finally {
            }
        } finally {
            returnJedis(jedis);
        }
        return ret;
    }

    public Double zscore(String key, String field) {
        Jedis jedis = null;
        Double ret = (Double)0.0;
        try {
            jedis = pool.getResource();
            ret = jedis.zscore(key.getBytes(), field.getBytes());
        }catch (JedisConnectionException e) {
            try {
                jedis = pool.getResource();
                ret = jedis.zscore(key.getBytes(), field.getBytes());
            } finally {
            }
        } finally {
            returnJedis(jedis);
        }
        return ret;
    }

    public Set<String> zrangeByScore(String key, double start, double end) {
        Jedis jedis = null;
        Set<String> ret;
        try {
            jedis = pool.getResource();
            ret = jedis.zrangeByScore(key, start, end);
        }catch (JedisConnectionException e) {
            try {
                jedis = pool.getResource();
                ret = jedis.zrangeByScore(key, start, end);
            } finally {
            }
        } finally {
            returnJedis(jedis);
        }
        return ret;
    }


    public Set<byte[]> zrangeByScoreRetByte(String key, long start, long end) {
        Jedis jedis = null;
        Set<byte[]> byteRet = null;
        Set<byte[]> ret = new HashSet<byte[]>();

        try {
            jedis = pool.getResource();
            byteRet = jedis.zrangeByScore(key.getBytes(), start, end);
            if( byteRet != null ) {
                for( byte[] it: byteRet ) {
                    ret.add(it);
                }
            }
        }catch (JedisConnectionException e) {
            try {
                jedis = pool.getResource();
                byteRet = jedis.zrangeByScore(key.getBytes(), start, end);
                if( byteRet != null ) {
                    for( byte[] it: byteRet ) {
                        ret.add(it);
                    }
                }
            } finally {
            }
        } finally {
            returnJedis(jedis);
        }
        return ret;
    }

    public Set<byte[]> zrevrange(String key, long start, long end) {
        Jedis jedis = null;
        Set<byte[]> ret = null;
        try {
            jedis = pool.getResource();
            ret = jedis.zrevrange(key.getBytes(), start, end);
        }catch (JedisConnectionException e) {
            try {
                jedis = pool.getResource();
                ret = jedis.zrevrange(key.getBytes(), start, end);
            } finally {
            }
        } finally {
            returnJedis(jedis);
        }
        return ret;
    }

    public Set<byte[]> zrange(String key, long start, long end) {
        Jedis jedis = null;
        Set<byte[]> ret = null;
        try {
            jedis = pool.getResource();
            ret = jedis.zrange(key.getBytes(), start, end);
        }catch (JedisConnectionException e) {
            try {
                jedis = pool.getResource();
                ret = jedis.zrange(key.getBytes(), start, end);
            } finally {
            }
        } finally {
            returnJedis(jedis);
        }
        return ret;
    }


    public Set<String> zrangeRetStrSet(String key, long start, long end) {
        Jedis jedis = null;
        Set<String> ret = null;
        try {
            jedis = pool.getResource();
            ret = jedis.zrange(key, start, end);
        }catch (JedisConnectionException e) {
            try {
                jedis = pool.getResource();
                ret = jedis.zrange(key, start, end);
            } finally {
            }
        } finally {
            returnJedis(jedis);
        }
        return ret;
    }



    public Long zrank(String key, String member) {
        Jedis jedis = null;
        Long ret;
        try {
            jedis = pool.getResource();
            ret = jedis.zrank(key, member);
        }catch (JedisConnectionException e) {
            try {
                jedis = pool.getResource();
                ret = jedis.zrank(key, member);
            } finally {
            }
        } finally {
            returnJedis(jedis);
        }
        return ret;
    }
    public Long zrevrank(String key, String member) {
        Jedis jedis = null;
        Long ret;
        try {
            jedis = pool.getResource();
            ret = jedis.zrevrank(key, member);
        }catch (JedisConnectionException e) {
            try {
                jedis = pool.getResource();
                ret = jedis.zrevrank(key, member);
            } finally {
            }
        } finally {
            returnJedis(jedis);
        }
        return ret;
    }

    public Set<String> zrevrangeRetString(String key, long start, long end) {
        Jedis jedis = null;
        Set<String> ret = null;
        try {
            jedis = pool.getResource();
            ret = jedis.zrevrange(key, start, end);
        }catch (JedisConnectionException e) {
            try {
                jedis = pool.getResource();
                ret = jedis.zrevrange(key, start, end);
            } finally {
            }
        } finally {
            returnJedis(jedis);
        }
        return ret;
    }

    public Map<String, Double> zrevrangeWithScores(String key, long start, long end) {
        Jedis jedis = null;
        Map<String, Double> ret = new HashMap<String, Double>();
        try {
            jedis = pool.getResource();
            Set<Tuple> tuples = jedis.zrevrangeWithScores(key, start, end);
            for (Tuple tuple : tuples) {
                ret.put(tuple.getElement(), tuple.getScore());
            }
        }catch (JedisConnectionException e) {
            try {
                jedis = pool.getResource();
                Set<Tuple> tuples = jedis.zrevrangeWithScores(key, start, end);
                for (Tuple tuple : tuples) {
                    ret.put(tuple.getElement(), tuple.getScore());
                }
            } finally {
            }
        } finally {
            returnJedis(jedis);
        }
        return ret;
    }

    public Map<String, Double> zrangeWithScores(String key, long start, long end) {
        Jedis jedis = null;
        Map<String, Double> ret = new HashMap<String, Double>();
        try {
            jedis = pool.getResource();
            Set<Tuple> tuples = jedis.zrangeWithScores(key, start, end);
            for (Tuple tuple : tuples) {
                ret.put(tuple.getElement(), tuple.getScore());
            }
        }catch (JedisConnectionException e) {
            try {
                jedis = pool.getResource();
                Set<Tuple> tuples = jedis.zrangeWithScores(key, start, end);
                for (Tuple tuple : tuples) {
                    ret.put(tuple.getElement(), tuple.getScore());
                }
            } finally {
            }
        } finally {
            returnJedis(jedis);
        }
        return ret;
    }

    public List<String> zrevrangeByScoreString(String key, long start, long end, int offset, int limit) {
        Jedis jedis = null;
        Set<byte[]> byteRet = null;
        List<String> ret = new ArrayList<String>();

        try {
            jedis = pool.getResource();
            byteRet = jedis.zrevrangeByScore(key.getBytes(), start, end, offset, limit);
            if( byteRet != null ) {
                for( byte[] it: byteRet ) {
                    ret.add(new String(it));
                }
            }
        }catch (JedisConnectionException e) {
            try {
                jedis = pool.getResource();
                byteRet = jedis.zrevrangeByScore(key.getBytes(), start, end, offset, limit);
                if( byteRet != null ) {
                    for( byte[] it: byteRet ) {
                        ret.add(new String(it));
                    }
                }
            } finally {
            }
        } finally {
            returnJedis(jedis);
        }
        return ret;
    }

//    public Set<Long> zrevrangeByScoreLong(String key, long start, long end, int offset, int limit) {
//        Jedis jedis = null;
//        Set<byte[]> byteRet = null;
//        Set<Long> ret = new HashSet<Long>();
//
//        try {
//            jedis = pool.getResource();
//            byteRet = jedis.zrevrangeByScore(key.getBytes(), start, end, offset, limit);
//            if( byteRet != null ) {
//                for( byte[] it: byteRet ) {
//                    ret.add(Long.parseLong(new String(it)));
//                }
//            }
//        }catch (JedisConnectionException e) {
//            try {
//                jedis = pool.getResource();
//                byteRet = jedis.zrevrangeByScore(key.getBytes(), start, end, offset, limit);
//                if( byteRet != null ) {
//                    for( byte[] it: byteRet ) {
//                        ret.add(Long.parseLong(new String(it)));
//                    }
//                }
//            } finally {
//            }
//        } finally {
//            returnJedis(jedis);
//        }
//        return ret;
//    }
    public List<Long> zrevrangeByScoreLong(String key, long start, long end, int offset, int limit) {
        Jedis jedis = null;
        Set<byte[]> byteRet = null;
        List<Long> ret = new ArrayList<Long>();

        try {
            jedis = pool.getResource();
            byteRet = jedis.zrevrangeByScore(key.getBytes(), start, end, offset, limit);
            if( byteRet != null ) {
                for( byte[] it: byteRet ) {
                    ret.add(Long.parseLong(new String(it)));
                }
            }
        }catch (JedisConnectionException e) {
            try {
                jedis = pool.getResource();
                byteRet = jedis.zrevrangeByScore(key.getBytes(), start, end, offset, limit);
                if( byteRet != null ) {
                    for( byte[] it: byteRet ) {
                        ret.add(Long.parseLong(new String(it)));
                    }
                }
            } finally {
            }
        } finally {
            returnJedis(jedis);
        }
        return ret;
    }


    public List<Map<String,Double>> zrevrangeByScoreWithScore(String key, long start, long end, int offset, int limit) {
        Jedis jedis = null;
        List<Map<String,Double>> ret = new ArrayList<Map<String, Double>>();
        try {
            jedis = pool.getResource();
            Set<Tuple> tuples = jedis.zrevrangeByScoreWithScores(key.getBytes(), start, end, offset, limit);
            if( tuples != null ) {
                for( Tuple it: tuples ) {
                    Map<String,Double> map = new HashMap<String, Double>();
                    map.put(it.getElement(),it.getScore());
                    ret.add(map);
                }
            }
        }catch (JedisConnectionException e) {
            try {
                jedis = pool.getResource();
                Set<Tuple> tuples = jedis.zrevrangeByScoreWithScores(key.getBytes(), start, end, offset, limit);
                if( tuples != null ) {
                    for( Tuple it: tuples ) {
                        Map<String,Double> map = new HashMap<String, Double>();
                        map.put(it.getElement(),it.getScore());
                        ret.add(map);
                    }
                }
            } finally {
            }
        } finally {
            returnJedis(jedis);
        }
        return ret;
    }

    public Map<String,Object> zrevrangeByScoreWithScoreReturnMap(String key, long start, long end, int offset, int limit) {
        Jedis jedis = null;
        Map<String,Object> ret = new HashMap<String, Object>();
        try {
            jedis = pool.getResource();
            Set<Tuple> tuples = jedis.zrevrangeByScoreWithScores(key.getBytes(), start, end, offset, limit);
            if( tuples != null ) {
                List<Long> values = new ArrayList<Long>();
                List<Long> scores = new ArrayList<Long>();
                for( Tuple it: tuples ) {
                    values.add(Long.parseLong(it.getElement()));
                    scores.add((long)it.getScore());
                }
                ret.put("values",values);
                ret.put("scores",scores);
            }
        }catch (JedisConnectionException e) {
            try {
                jedis = pool.getResource();
                Set<Tuple> tuples = jedis.zrevrangeByScoreWithScores(key.getBytes(), start, end, offset, limit);
                if( tuples != null ) {
                    List<Long> values = new ArrayList<Long>();
                    List<Long> scores = new ArrayList<Long>();
                    for( Tuple it: tuples ) {
                        values.add(Long.parseLong(it.getElement()));
                        scores.add((long)it.getScore());
                    }
                    ret.put("values",values);
                    ret.put("scores",scores);
                }
            } finally {
            }
        } finally {
            returnJedis(jedis);
        }
        return ret;
    }





    public Long zunionstore(String dstkey, String... sets) {
        Jedis jedis = null;
        Long ret;
        try {
            jedis = pool.getResource();
            ret = jedis.zunionstore(dstkey, sets);
        }catch (JedisConnectionException e) {
            try {
                jedis = pool.getResource();
                ret = jedis.zunionstore(dstkey, sets);
            } finally {
            }
        } finally {
            returnJedis(jedis);
        }
        return ret;
    }



    public long lLen(String key){
        Jedis jedis = null;
        long ret = 0;
        try {
            jedis = pool.getResource();
            ret = jedis.llen(key);
        } catch (JedisConnectionException e) {
            try {
                jedis = pool.getResource();
                ret = jedis.llen(key);
            } finally {
            }
        }finally {
            returnJedis(jedis);
        }
        return ret;
    }

    public Long zcard(String key) {
        Jedis jedis = null;
        Long ret = (long)0;
        try {
            jedis = pool.getResource();
            ret = jedis.zcard(key.getBytes());
        } catch (JedisConnectionException e) {
            try {
                jedis = pool.getResource();
                ret = jedis.zcard(key.getBytes());
            } finally {
            }
        }finally {
            returnJedis(jedis);
        }
        return ret;
    }

    public long zcount(String key, long min, long max) {
        Jedis jedis = null;
        long ret = 0;
        try {
            jedis = pool.getResource();
            ret = jedis.zcount(key, min, max);
        }catch (JedisConnectionException e) {
            try {
                jedis = pool.getResource();
                ret = jedis.zcount(key, min, max);
            } finally {
            }
        } finally {
            returnJedis(jedis);
        }
        return ret;
    }

    public void sadd(String key, String... value) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            jedis.sadd(key, value);
        } catch (JedisConnectionException e) {
            try {
                jedis = pool.getResource();
                jedis.sadd(key, value);
            } finally {
            }
        }finally {
            returnJedis(jedis);
        }
    }


    public long scard(String key) {
        Jedis jedis = null;
        long ret = 0;
        try {
            jedis = pool.getResource();
            ret = jedis.scard(key);
        } catch (JedisConnectionException e) {
            try {
                jedis = pool.getResource();
                ret = jedis.scard(key);
            } finally {
            }
        }finally {
            returnJedis(jedis);
        }
        return ret;
    }

    public boolean sismember(String key, String field) {
        Jedis jedis = null;
        boolean ret = false;
        try {
            jedis = pool.getResource();
            ret = jedis.sismember(key.getBytes(), field.getBytes());
        } catch (JedisConnectionException e) {
            try {
                jedis = pool.getResource();
                ret = jedis.sismember(key.getBytes(), field.getBytes());
            } finally {
            }
        }finally {
            returnJedis(jedis);
        }
        return ret;
    }

    public String srandmember(String key) {
        Jedis jedis = null;
        String result;
        try {
            jedis = pool.getResource();
            result = jedis.srandmember(key);
        } catch (JedisConnectionException e) {
            jedis = pool.getResource();
            result = jedis.srandmember(key);
        }finally {
            returnJedis(jedis);
        }
        return result;
    }

    public Set<String> smembers(String key) {
        Jedis jedis = null;
        Set<String> result;
        try {
            jedis = pool.getResource();
            result = jedis.smembers(key);
        } catch (JedisConnectionException e) {
            jedis = pool.getResource();
            result = jedis.smembers(key);
        }finally {
            returnJedis(jedis);
        }
        return result;
    }

    public Long srem(String key, String... members) {
        Jedis jedis = null;
        Long result;
        try {
            jedis = pool.getResource();
            result = jedis.srem(key, members);
        } catch (JedisConnectionException e) {
            jedis = pool.getResource();
            result = jedis.srem(key, members);
        }finally {
            returnJedis(jedis);
        }
        return result;
    }


    public Set<String> keys(String pattern) {
        Jedis jedis = null;
        Set<String> ret;
        try {
            jedis = pool.getResource();
            ret = jedis.keys(pattern);
        } catch (JedisConnectionException e) {
            jedis = pool.getResource();
            ret = jedis.keys(pattern);
        }finally {
            returnJedis(jedis);
        }
        return ret;
    }

    public Long renamenx(String oldkey, String newkey) {
        Jedis jedis = null;
        Long ret;
        try {
            jedis = pool.getResource();
            ret = jedis.renamenx(oldkey, newkey);
        } catch (JedisConnectionException e) {
            jedis = pool.getResource();
            ret = jedis.renamenx(oldkey, newkey);
        }finally {
            returnJedis(jedis);
        }
        return ret;
    }


    public Long ttl(String key) {
        Jedis jedis = null;
        Long ret;
        try {
            jedis = pool.getResource();
            ret = jedis.ttl(key);
        } catch (JedisConnectionException e) {
            jedis = pool.getResource();
            ret = jedis.ttl(key);
        }finally {
            returnJedis(jedis);
        }
        return ret;
    }

    public String watch(String... keys) {
        Jedis jedis = null;
        String ret;
        try {
            jedis = pool.getResource();
            ret = jedis.watch(keys);
        } catch (JedisConnectionException e) {
            jedis = pool.getResource();
            ret = jedis.watch(keys);
        }finally {
            returnJedis(jedis);
        }
        return ret;
    }


    public List<String> existsKeys(List<String> keys){
        String script =
                "local result={}" +
                        "local j = 0 " +
                        "for i = 0,#(KEYS) do " +
                        "if redis.call('EXISTS',KEYS[i]) == 1  " +
                        "then j=j+1 result[j]= KEYS[i] " +
                        "end  " +
                        "end  " +
                        "return result";
        Jedis jedis = null;
        List<String> ret;
        try {
            jedis = pool.getResource();
            String[] strings = keys.toArray(new String[keys.size()]);
            ret = (List<String>)jedis.eval(script, keys.size(), strings);
        } catch (JedisConnectionException e) {
            jedis = pool.getResource();
            String[] strings = keys.toArray(new String[keys.size()]);
            ret = (List<String>)jedis.eval(script, keys.size(), strings);
        }finally {
            returnJedis(jedis);
        }
        return ret;

    }





    public Jedis getJedis(){
        Jedis jedis = null;
        boolean ret = false;
        try {
            jedis = pool.getResource();
            return jedis;
        } catch (JedisConnectionException e) {
            try {
                jedis = pool.getResource();
                return jedis;
            } catch (JedisConnectionException e1) {
                try {
                    jedis = pool.getResource();
                    return jedis;
                } finally {
                }
            }
        }
    }


    public void close() {
        pool.close();
    }

    public void returnJedis(Jedis jedis) {
        if (jedis != null) {
            //pool.returnResource(jedis);
            jedis.close();
        }
    }

    public static void closePipeline(Pipeline pipeline) {
        if (pipeline != null) {
            try {
                pipeline.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void closeTransaction(Transaction transaction) {
        if (transaction != null) {
            try {
                transaction.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public int getNumActive() {
        return pool.getNumActive();
    }

    /**
     * 添加geo
     * @author jd.wang
     * @param key
     * @param longitude 经度
     * @param latitude 纬度
     * @param member 位置名称
     * @return
     */
    public Long geoADD(String key, double longitude, double latitude, String member){
        Jedis jedis = null;
        long ret;
        try {
            jedis = pool.getResource();
            ret = jedis.geoadd(key,longitude,latitude,member);
        } catch (JedisConnectionException e) {
            jedis = pool.getResource();
            ret = jedis.geoadd(key,longitude,latitude,member);
        }finally {
            returnJedis(jedis);
        }
        return ret;
    }

    /**
     * 查询附近坐标地址
     * @author jd.wang
     * @param key
     * @param longitude
     * @param latitude
     * @param unit
     * @param asc
     * @return
     */
    public Map<String,Double> geoRadius(String key,double longitude,double latitude,int radius,String unit,boolean asc) {
        Jedis jedis = null;
        Map<String,Double> data=new HashMap<String, Double>();
        try  {
            try {
                jedis = pool.getResource();
                final List<GeoRadiusResponse> userList = jedis.georadius(key, longitude, latitude, radius, GeoUnit.valueOf(unit=unit.toUpperCase()), GeoRadiusParam.geoRadiusParam().withDist());
                for (GeoRadiusResponse georadiu : userList) {
                    data.put(new String(georadiu.getMember(),"utf-8"),georadiu.getDistance());
                }
            } catch (JedisConnectionException e) {
                jedis = pool.getResource();
                final List<GeoRadiusResponse> userList = jedis.georadius(key, longitude, latitude, radius, GeoUnit.valueOf(unit=unit.toUpperCase()), GeoRadiusParam.geoRadiusParam().withDist());
                for (GeoRadiusResponse georadiu : userList) {
                    data.put(new String(georadiu.getMember(),"utf-8"),georadiu.getDistance());
                }
            } finally {
                returnJedis(jedis);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return data;
    }

    public List<GeoRadiusResponse> geoRadiusResponse(String key, double longitude, double latitude, int radius, String unit, boolean asc) {
        Jedis jedis = null;
        List<GeoRadiusResponse> userList;
        try {
            jedis = pool.getResource();
            userList = jedis.georadius(key, longitude, latitude, radius, GeoUnit.valueOf(unit = unit.toUpperCase()), GeoRadiusParam.geoRadiusParam().withDist());
        } catch (JedisConnectionException e) {
            jedis = pool.getResource();
            userList = jedis.georadius(key, longitude, latitude, radius, GeoUnit.valueOf(unit = unit.toUpperCase()), GeoRadiusParam.geoRadiusParam().withDist());
        } finally {
            returnJedis(jedis);
        }
        return userList;
    }


    /**
     * 删除geo
     * @author jd.wang
     * @param key
     * @param member
     * @return
     */
    public long geoDel(String key,String member){
        return this.zrem(key,member);
    }

    /**
     * 查询2位置距离
     * @param key
     * @param member1 位置1
     * @param member2 位置2
     * @param unit 单位
     * @return
     */
    public Double geoDist(String key,String member1,String member2,String unit){
        Jedis jedis = null;
        Double distance = null;
        try {
            jedis = pool.getResource();
            distance = jedis.geodist(key,member1,member2,GeoUnit.valueOf(unit=unit.toUpperCase()));
        } catch (JedisConnectionException e) {
            jedis = pool.getResource();
            distance = jedis.geodist(key,member1,member2,GeoUnit.valueOf(unit=unit.toUpperCase()));
        }finally {
            returnJedis(jedis);
        }
        return distance;
    }

    /**
     * 查询位置坐标
     * @param key
     * @param member 位置
     * @return
     */
    public Map<String,Double> geoPos(String key,String member){
        Jedis jedis = null;
        Map<String,Double> geoMap = new HashMap<String, Double>();
        try {
            jedis = pool.getResource();
            List<GeoCoordinate> geoCoordinateList= jedis.geopos(key,member);
            if(geoCoordinateList==null || geoCoordinateList.size()<1){
                return null;
            }
            GeoCoordinate geoCoordinate = geoCoordinateList.get(0);
            if(geoCoordinate==null){
                return null;
            }
            geoMap.put("longitude",geoCoordinate.getLongitude());
            geoMap.put("latitude",geoCoordinate.getLatitude());
        } catch (JedisConnectionException e) {
            jedis = pool.getResource();
            List<GeoCoordinate> geoCoordinateList= jedis.geopos(key,member);
            if(geoCoordinateList==null || geoCoordinateList.size()<1){
                return null;
            }
            GeoCoordinate geoCoordinate = geoCoordinateList.get(0);
            if(geoCoordinate==null){
                return null;
            }
            geoMap.put("longitude",geoCoordinate.getLongitude());
            geoMap.put("latitude",geoCoordinate.getLatitude());
        }finally {
            returnJedis(jedis);
        }
        return geoMap;
    }


    public static void main(String[] args)  {

        //final RedisLink redisLink = new RedisLink("073a15b4bd5f4072.m.cnbja.kvstore.aliyuncs.com",6379,"073a15b4bd5f4072:Dfis5D92kS");
        final RedisLink redisLink = new RedisLink("192.168.1.246",6379,"");


        //geo test.
//        System.out.println(redisLink.geoADD("beijing", 116.312014, 39.963019,"haidian"));
//        System.out.println(redisLink.geoADD("beijing", 116.227501, 39.90858,"shijingshan"));
//        System.out.println(redisLink.geoADD("beijing", 116.297641, 39.861631,"fengtai"));
//        System.out.println(redisLink.geoADD("beijing", 116.428146, 39.9316,"dongcheng"));
//        System.out.println(redisLink.geoADD("beijing", 116.375829, 39.920091,"xicheng"));
//        System.out.println(redisLink.geoADD("beijing", 116.110793, 39.943992,"mentougou"));
//        System.out.println(redisLink.geoADD("beijing", 116.480464, 39.95948,"caoyang"));
//        System.out.println(redisLink.geoADD("beijing", 116.663862, 39.916107,"tongzhou"));
//        System.out.println(redisLink.geoADD("beijing", 116.349383, 39.729911,"daxing"));
//        System.out.println(redisLink.geoADD("beijing", 116.157361, 39.748109,"fangshan"));
//        System.out.println(redisLink.geoADD("beijing", 116.662137, 40.134017,"sunyi"));
//        System.out.println(redisLink.geoADD("beijing", 116.2367, 40.224862,"changping"));
//        System.out.println(redisLink.geoADD("beijing", 117.141617, 40.14196,"pinggu"));
//        System.out.println(redisLink.geoADD("beijing", 116.64144, 40.316466,"huairou"));
//
//        System.out.println(redisLink.geoRadius("beijing",116.421822, 39.906809,5,"km",false).toString());
//        System.out.println(redisLink.geoDist("beijing","daxing","haidian","km"));

//        redisLink.geoDel("beijing","caoyang");

/*
Set<String> keys = redisLink.getJedis().keys("phonematchlist:*");
System.out.println(keys.size());
System.out.println(keys);

String[] kkkk = new String[keys.size()];
keys.toArray(kkkk);

redisLink.getJedis().del(kkkk);
for (String key: keys ) {
redisLink.getJedis().del(key);
}
ExecutorService servicePool = Executors.newFixedThreadPool(100);
for (;;){
servicePool.execute(new Runnable() {
@Override
public void run() {
COUNTER ++;
redisLink.set("testthread","111111".getBytes());
//System.out.println(COUNTER);
}
});
}
Set<String> buddyList = redisLink.zrevrangeByScoreString("xm:cache:msg:im:chatlist:buddy:103:1:401",System.currentTimeMillis() ,1,  0, 100);
System.out.println(buddyList);

String messageHashKey = "xm:cache:msg:im:chatlist:buddy:103:1:401";

byte[][] chatList = new byte[buddyList.size()][];
int messageIndex = 0;
for (String buddyStr : buddyList) {
System.out.println(buddyStr);
String messageId = redisLink.hget(messageHashKey, buddyStr);

System.out.println(messageId);

}
// fetch from cache first
String messageKey = genMessageBodyKey(messageId);
//byte[] messageBody = medisClient.getByteArr(messageKey);
byte[] messageBody = redis.get(messageKey);
if (messageBody == null) { // fetch from DB
CacheLogger.readCacheMiss("MessageBody", messageKey);
Message message = messageDAO.getOneMessage(messageId);
if (message != null && message.getMessageBody() != null) {
messageBody = getMessageBodyAsByteArr(message);
} else {
messageBody = EMPTY_BYTE_ARR;
MetricCollection.messageMiss.inc();
logger.error("ChatList: Failed to get message from DB, buddyListKey={}, mid={}", buddyListKey, messageId);
}
}
chatList[messageIndex++] = messageBody;
}messageBody
byte[] bytes = redisLink.get("ps:74-1");

System.out.println(new String(bytes));
*/



/*
        //m_body
         Set<String> zrevrange = redisLink.zrevrangeRetString("xm:msg:im:status:1:1:401:1:1", 0, 57);
        //Set<byte[]> zrevrange = redisLink.zrevrange("xm:msg:im:status:1:1:401:1:1", 0, 57);
        List<String> list = new ArrayList<String>(zrevrange);

        //List<byte[]> list = new ArrayList<byte[]>(zrevrange);

        Collections.reverse(list);

        redisLink.del("tesdteeee");

        //redisLink.lpushMulti("tesdteeee",list);

        redisLink.lpushMultiInString("tesdteeee",list);

        List<String> tesdteeee = redisLink.lrange("tesdteeee", 0, 57);

       // List<byte[]> tesdteeee = redisLink.lrangereturnbyte("tesdteeee", 0, 57);

        String[] keys=new String[tesdteeee.size()];
        for (int i=0;i<keys.length;i++){
            keys[i]="xm:cache:msg:im:m_body:"+tesdteeee.get(i);
        }

        Map<String, byte[]> stringMap = redisLink.mgetByteArr(keys);

        System.out.println(stringMap);


//        for (Map.Entry<String, byte[]> entry:stringMap.entrySet()){
//            PIMSendGroupMsg pimSendGroupMsg=new PIMSendGroupMsg();
//            pimSendGroupMsg.unmarshall(entry.getValue());
//            System.out.println(pimSendGroupMsg);
//            System.out.println(pimSendGroupMsg.getUri());
//            System.out.println(pimSendGroupMsg.getAppid());
//        }

//        for (Map.Entry<String, byte[]> entry:stringMap.entrySet()){
//            PIMSendMsg pimSendMsg=new PIMSendMsg();
//            pimSendMsg.unmarshall(entry.getValue());
//            System.out.println(pimSendMsg);
//            System.out.println(pimSendMsg.getUri());
//            System.out.println(pimSendMsg.getAppid());
//        }



        String b="AAADmwDKAAsAAAAAAAAAAAACAAYAmgAAAO+/vQHvv70AAQABAQAkODY5QzkzNkItNjdERi00NkFFLTk3RUQtNjgyQjVDQ0VGRkZGAAAAAAAAAAEAAAAAAAAAAgAAAAEAKQAAACkB77+9AAkAAQAU6Zi/5b63MDU1NTDllabllabllaYAAAAAAAAAAAAAAAAAAVLvv70677+9BXguCO+/vQAQAAABAAAAAAAAAAAAAAAAkQAAAO+/vQHvv70AAQABAQAkMzAxNUFFRUItQThENy00NzY0LTgwRDMtQzVEMjI4OTNCRjY2AAAAAAAAAAEAAAAAAAAAAgAAAAEAHgAAAB4B77+9AAkAAQAJ5ZWm5ZWm5ZWmAAAAAAAAAAAAAAAAAAFS77+9Qe+/vQV4Lgrvv73vv70QAAABAAAAAAAAAAAAAAAAjwAAAO+/vQHvv70AAQABAQAkNjE1RTQzQUEtNDA2MC00MzFELUFBRjUtOTMyMUIwODkzQ0Y2AAAAAAAAAAEAAAAAAAAAAgAAAAEAHgAAAB4B77+9AAkAAQAJ5ZWm5ZWm5ZWmAAAAAAAAAAAAAAAAAAFS77+9R++/vQV4Lgwi77+9EAAAAQAAAAAAAAAAAAAAAJAAAADvv70B77+9AAEAAQEAJDM3ODI4MjNFLTg0MzMtNEVGRC05ODQwLTFCRTBGNUNCMTAxNgAAAAAAAAABAAAAAAAAAAIAAAABAB8AAAAfAe+/vQAJAAEACiDlk6blk6blk6YAAAAAAAAAAAAAAAAAAVLvv71XbAV4Lg/vv73vv70QAAABAAAAAAAAAAAAAAAAkwAAAO+/vQHvv70AAQABAQAkMUU2RjE5MTUtNDhEQy00RUY1LUJENTEtMkZEMzI5QkNBRkM5AAAAAAAAAAEAAAAAAAAAAgAAAAEAHgAAAB4B77+9AAkAAQAJ5aC15aC15aC1AAAAAAAAAAAAAAAAAAFS77+9d++/vQV4Lu+/ve+/ve+/vRAAAAEAAAAAAAAAAAAAAACOAAAA77+9Ae+/vQABAAEBACQ0QkYwNDdDRS0wQjlFLTQwMEYtOTQ4Qy0xQzdGMkNBOEJDMzcAAAAAAAAAAQAAAAAAAAACAAAAAQAbAAAAGwHvv70ACQABAAblip/lpKsAAAAAAAAAAAAAAAAAAVLvv71+YAV4Lu+/ve+/ve+/vRAAAAEAAAAAAAAAAAAAAP////8AAAAAAAAAAAAAAAA=";


        byte[] bytes = Base64.decodeBase64(b);

        ProtoPacket protoPacket = new ProtoPacket();

        protoPacket.unmarshall(bytes);

        System.out.println(protoPacket.getUri());
        System.out.println(protoPacket.getAppid());
        */


//        byte[] bytes = redisLink.get("xm:cache:msg:im:m_body:394329288955924480");
//
//
//        PIMSendMsg p=new PIMSendMsg();
//        p.unmarshall(bytes);
//
//        System.out.println(p);
//        System.out.println(p.getUri());
//        PIMTextInfo pi=new PIMTextInfo();
//        pi.unmarshall(p.getMessage());
//        System.out.println(pi);



//        String b  = "AAACXgDKAAsAAAAAAAAAAAACAAQAiAAAAH4B77+9AAEAAQEAJDEyQTcwOENDLUEwN0UtNEFCRS1CMDdELUVGRkREODYzMkY4QwAAAAAAAAABAAAAAAAAAAIAAAABABgAAAAYAe+/vQAJAAEAA+aIkQAAAAAAAAAAAAAAAAABUu+/ve+/vQV477+9aO+/vQAQAAABAAAAAAAAAAAAAAAAjgAAAO+/vQHvv70AAQABAQAkRTlCOENCRTctM0JDMC00OEFFLUIyREEtMTg3NTdBNzI3RDgxAAAAAAAAAAEAAAAAAAAAAgAAAAEAHgAAAB4B77+9AAkAAQAJ5ZOm5ZOm5ZOmAAAAAAAAAAAAAAAAAAFS8pqwgAV477+9Zijvv70QAAABAAAAAAAAAAAAAAAAjgAAAO+/vQHvv70AAQABAQAkRDQ4NkM5RTMtMjM3Mi00M0FFLUE1NTgtM0ExMjE0QTc3NERFAAAAAAAAAAEAAAAAAAAAAgAAAAEAHgAAAB4B77+9AAkAAQAJ5ZGD5ZGD5ZGDAAAAAAAAAAAAAAAAAAFS77+9QwV477+9Y0jvv70QAAABAAAAAAAAAAAAAAAAjgAAAO+/vQHvv70AAQABAQAkMjI4RTBERTktQjU2NS00Mjg4LUIyQ0YtQkE1MzkwRjVDMTJEAAAAAAAAAAEAAAAAAAAAAgAAAAEAHgAAAB4B77+9AAkAAQAJ5ZWm5ZWm5ZWmAAAAAAAAAAAAAAAAAAFS77+9bwV477+9X++/vUAQAAABAAAAAAAAAAAAAAD/////AAAAAAAAAAAAAAAA";
//
//        byte[] bytes = Base64.decodeBase64(b);
//        ProtoPacket protoPacket = new PMsgHistoryRes();
//        protoPacket.unmarshall(bytes);
//
//        PMsgHistoryRes p =(PMsgHistoryRes)protoPacket;
//
//        System.out.print(p);
//
//        byte[][] msgs = p.getMsgs();


        double l = 12.12;
        System.out.println((long)l);


    }


}
