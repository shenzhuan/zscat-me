//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.zsCat.common.base;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.alibaba.dubbo.common.utils.StringUtils;
import org.springframework.util.CollectionUtils;
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

public class RedisLink {
    private JedisPool pool;
    private JedisPoolConfig config = new JedisPoolConfig();
    public static int COUNTER = 0;

    public RedisLink(String address, int port, String pwd) {
        this.config.setMaxTotal(100);
        this.config.setMaxIdle(80);
        this.config.setMaxWaitMillis(6000L);
        this.config.setTestWhileIdle(true);
        if(StringUtils.isBlank(pwd)) {
            this.pool = new JedisPool(this.config, address, port, 6000);
        } else {
            this.pool = new JedisPool(this.config, address, port, 6000, pwd);
        }

    }

    public boolean exist(String key) {
        Jedis jedis = null;
        boolean ret = false;

        try {
            jedis = this.pool.getResource();
            ret = jedis.exists(key).booleanValue();
        } catch (JedisConnectionException var8) {
            jedis = this.pool.getResource();
            ret = jedis.exists(key).booleanValue();
        } finally {
            this.returnJedis(jedis);
        }

        return ret;
    }

    public long exist(String... keys) {
        Jedis jedis = null;
        long ret = 0L;

        try {
            jedis = this.pool.getResource();
            ret = jedis.exists(keys).longValue();
        } catch (JedisConnectionException var9) {
            jedis = this.pool.getResource();
            ret = jedis.exists(keys).longValue();
        } finally {
            this.returnJedis(jedis);
        }

        return ret;
    }

    public void del(String key) {
        Jedis jedis = null;

        try {
            jedis = this.pool.getResource();
            jedis.del(key);
        } catch (JedisConnectionException var7) {
            jedis = this.pool.getResource();
            jedis.del(key);
        } finally {
            this.returnJedis(jedis);
        }

    }

    public void expire(String key, int time) {
        Jedis jedis = null;

        try {
            jedis = this.pool.getResource();
            jedis.expire(key, time);
        } catch (JedisConnectionException var8) {
            jedis = this.pool.getResource();
            jedis.expire(key, time);
        } finally {
            this.returnJedis(jedis);
        }

    }

    public byte[] get(String key) {
        byte[] value = null;
        Jedis jedis = null;

        try {
            jedis = this.pool.getResource();
            value = jedis.get(key.getBytes());
        } catch (JedisConnectionException var8) {
            jedis = this.pool.getResource();
            value = jedis.get(key.getBytes());
        } finally {
            this.returnJedis(jedis);
        }

        return value;
    }

    public String getString(String key) {
        String value = null;
        Jedis jedis = null;

        try {
            jedis = this.pool.getResource();
            value = jedis.get(key);
        } catch (JedisConnectionException var8) {
            jedis = this.pool.getResource();
            value = jedis.get(key);
        } finally {
            this.returnJedis(jedis);
        }

        return value;
    }

    public String set(String key, byte[] value) {
        String ret = "";
        Jedis jedis = null;

        try {
            jedis = this.pool.getResource();
            ret = jedis.set(key.getBytes(), value);
        } catch (JedisConnectionException var12) {
            try {
                jedis = this.pool.getResource();
                ret = jedis.set(key.getBytes(), value);
            } catch (JedisConnectionException var11) {
                ;
            }
        } finally {
            this.returnJedis(jedis);
        }

        return ret;
    }

    public long setnx(String key, String value) {
        Jedis jedis = null;
        long ret = 0L;

        try {
            jedis = this.pool.getResource();
            ret = jedis.setnx(key, value).longValue();
        } catch (JedisConnectionException var13) {
            try {
                jedis = this.pool.getResource();
                ret = jedis.setnx(key, value).longValue();
            } catch (JedisConnectionException var12) {
                ;
            }
        } finally {
            this.returnJedis(jedis);
        }

        return ret;
    }

    public String setString(String key, String value) {
        String ret = "";
        Jedis jedis = null;

        try {
            jedis = this.pool.getResource();
            ret = jedis.set(key, value);
        } catch (JedisConnectionException var12) {
            try {
                jedis = this.pool.getResource();
                ret = jedis.set(key, value);
            } catch (JedisConnectionException var11) {
                ;
            }
        } finally {
            this.returnJedis(jedis);
        }

        return ret;
    }

    public String setex(String key, int expire, byte[] value) {
        String ret = "";
        Jedis jedis = null;

        try {
            jedis = this.pool.getResource();
            ret = jedis.setex(key.getBytes(), expire, value);
        } catch (JedisConnectionException var10) {
            jedis = this.pool.getResource();
            ret = jedis.setex(key.getBytes(), expire, value);
        } finally {
            this.returnJedis(jedis);
        }

        return ret;
    }

    public String setStringEx(String key, int expire, String value) {
        String ret = "";
        Jedis jedis = null;

        try {
            jedis = this.pool.getResource();
            ret = jedis.setex(key, expire, value);
        } catch (JedisConnectionException var10) {
            jedis = this.pool.getResource();
            ret = jedis.setex(key, expire, value);
        } finally {
            this.returnJedis(jedis);
        }

        return ret;
    }

    public Long incr(String key) {
        Jedis jedis = null;
        Long ret = Long.valueOf(0L);

        try {
            jedis = this.pool.getResource();
            ret = jedis.incr(key);
        } catch (JedisConnectionException var8) {
            jedis = this.pool.getResource();
            ret = jedis.incr(key);
        } finally {
            this.returnJedis(jedis);
        }

        return ret;
    }

    public Long incrBy(String key, long by) {
        Jedis jedis = null;
        Long ret = Long.valueOf(0L);

        try {
            jedis = this.pool.getResource();
            ret = jedis.incrBy(key, by);
        } catch (JedisConnectionException var10) {
            jedis = this.pool.getResource();
            ret = jedis.incrBy(key, by);
        } finally {
            this.returnJedis(jedis);
        }

        return ret;
    }

    public Map<String, byte[]> mgetByteArr(String[] keys) {
        byte[][] b = new byte[keys.length][];

        for(int i = 0; i < b.length; ++i) {
            b[i] = keys[i].getBytes();
        }

        Jedis jedis = null;
        HashMap ret = new HashMap();

        try {
            jedis = this.pool.getResource();
            List<byte[]> values = jedis.mget(b);

            for(int i = 0; i < keys.length; ++i) {
                if(values.get(i) != null) {
                    ret.put(keys[i], values.get(i));
                }
            }
        } catch (JedisConnectionException var11) {
            jedis = this.pool.getResource();
            List<byte[]> values = jedis.mget(b);

            for(int i = 0; i < keys.length; ++i) {
                if(values.get(i) != null) {
                    ret.put(keys[i], values.get(i));
                }
            }
        } finally {
            this.returnJedis(jedis);
        }

        return ret;
    }

    public Map<String, String> mgetToMap(String... keys) {
        Jedis jedis = null;
        HashMap ret = new HashMap();

        try {
            jedis = this.pool.getResource();
            List<String> values = jedis.mget(keys);

            for(int i = 0; i < keys.length; ++i) {
                if(values.get(i) != null) {
                    ret.put(keys[i], values.get(i));
                }
            }
        } catch (JedisConnectionException var10) {
            jedis = this.pool.getResource();
            List<String> values = jedis.mget(keys);

            for(int i = 0; i < keys.length; ++i) {
                if(values.get(i) != null) {
                    ret.put(keys[i], values.get(i));
                }
            }
        } finally {
            this.returnJedis(jedis);
        }

        return ret;
    }

    public List<String> mget(String... key) {
        Jedis jedis = null;
        List value = null;

        try {
            jedis = this.pool.getResource();
            value = jedis.mget(key);
        } catch (JedisConnectionException var8) {
            jedis = this.pool.getResource();
            value = jedis.mget(key);
        } finally {
            this.returnJedis(jedis);
        }

        return value;
    }

    public void rpushMulti(String key, List<byte[]> values) {
        Jedis jedis = null;
        String[] strValues = new String[values.size()];

        for(int i = 0; i < values.size(); ++i) {
            strValues[i] = new String((byte[])values.get(i));
        }

        try {
            jedis = this.pool.getResource();
            jedis.rpush(key, strValues);
        } catch (JedisConnectionException var9) {
            jedis = this.pool.getResource();
            jedis.rpush(key, strValues);
        } finally {
            this.returnJedis(jedis);
        }

    }

    public void rpushMultiInString(String key, List<String> values) {
        Jedis jedis = null;
        String[] strValues = new String[values.size()];

        for(int i = 0; i < values.size(); ++i) {
            strValues[i] = (String)values.get(i);
        }

        try {
            jedis = this.pool.getResource();
            jedis.rpush(key, strValues);
        } catch (JedisConnectionException var9) {
            jedis = this.pool.getResource();
            jedis.rpush(key, strValues);
        } finally {
            this.returnJedis(jedis);
        }

    }

    public void lpushMulti(String key, List<byte[]> values) {
        Jedis jedis = null;
        String[] strValues = new String[values.size()];

        for(int i = 0; i < values.size(); ++i) {
            strValues[i] = new String((byte[])values.get(i));
        }

        try {
            jedis = this.pool.getResource();
            jedis.lpush(key, strValues);
        } catch (JedisConnectionException var9) {
            jedis = this.pool.getResource();
            jedis.lpush(key, strValues);
        } finally {
            this.returnJedis(jedis);
        }

    }

    public void lpushMultiInString(String key, List<String> values) {
        Jedis jedis = null;
        String[] strValues = new String[values.size()];

        for(int i = 0; i < values.size(); ++i) {
            strValues[i] = (String)values.get(i);
        }

        try {
            jedis = this.pool.getResource();
            jedis.lpush(key, strValues);
        } catch (JedisConnectionException var9) {
            jedis = this.pool.getResource();
            jedis.lpush(key, strValues);
        } finally {
            this.returnJedis(jedis);
        }

    }

    public List<String> lrange(String key, int offset, int limit) {
        Jedis jedis = null;
        List ret = null;

        try {
            jedis = this.pool.getResource();
            ret = jedis.lrange(key, (long)offset, (long)limit);
        } catch (JedisConnectionException var10) {
            jedis = this.pool.getResource();
            ret = jedis.lrange(key, (long)offset, (long)limit);
        } finally {
            this.returnJedis(jedis);
        }

        return ret;
    }

    public List<byte[]> lrangereturnbyte(String key, int offset, int limit) {
        Jedis jedis = null;
        List ret = null;

        try {
            jedis = this.pool.getResource();
            ret = jedis.lrange(key.getBytes(), (long)offset, (long)limit);
        } catch (JedisConnectionException var10) {
            jedis = this.pool.getResource();
            ret = jedis.lrange(key.getBytes(), (long)offset, (long)limit);
        } finally {
            this.returnJedis(jedis);
        }

        return ret;
    }

    public String hget(String key, String field) {
        Jedis jedis = null;
        String value = null;

        try {
            jedis = this.pool.getResource();
            value = jedis.hget(key, field);
        } catch (JedisConnectionException var9) {
            jedis = this.pool.getResource();
            value = jedis.hget(key, field);
        } finally {
            this.returnJedis(jedis);
        }

        return value;
    }

    public Map<String, String> hgetall(String key) {
        Jedis jedis = null;
        Map value = null;

        try {
            jedis = this.pool.getResource();
            value = jedis.hgetAll(key);
        } catch (JedisConnectionException var8) {
            jedis = this.pool.getResource();
            value = jedis.hgetAll(key);
        } finally {
            this.returnJedis(jedis);
        }

        return value;
    }

    public Map<String, String> hmgetToMap(String key, String... fields) {
        Jedis jedis = null;
        Map<String, String> ret = new HashMap();
        if(fields != null && fields.length != 0) {
            try {
                jedis = this.pool.getResource();
                List<String> values = jedis.hmget(key, fields);

                for(int i = 0; i < fields.length; ++i) {
                    if(values.get(i) != null) {
                        ret.put(fields[i], values.get(i));
                    }
                }
            } catch (JedisConnectionException var11) {
                jedis = this.pool.getResource();
                List<String> values = jedis.hmget(key, fields);

                for(int i = 0; i < fields.length; ++i) {
                    if(values.get(i) != null) {
                        ret.put(fields[i], values.get(i));
                    }
                }
            } finally {
                this.returnJedis(jedis);
            }
        }

        return ret;
    }

    public long hset(String key, String field, String value) {
        Jedis jedis = null;
        long ret = 0L;

        try {
            jedis = this.pool.getResource();
            ret = jedis.hset(key, field, value).longValue();
        } catch (JedisConnectionException var11) {
            jedis = this.pool.getResource();
            ret = jedis.hset(key, field, value).longValue();
        } finally {
            this.returnJedis(jedis);
        }

        return ret;
    }

    public long hsetnx(String key, String field, String value) {
        Jedis jedis = null;
        long ret = 0L;

        try {
            jedis = this.pool.getResource();
            ret = jedis.hsetnx(key, field, value).longValue();
        } catch (JedisConnectionException var11) {
            jedis = this.pool.getResource();
            ret = jedis.hsetnx(key, field, value).longValue();
        } finally {
            this.returnJedis(jedis);
        }

        return ret;
    }

    public String hmset(String key, Map<String, String> hash) {
        Jedis jedis = null;
        String ret = "";

        try {
            jedis = this.pool.getResource();
            ret = jedis.hmset(key, hash);
        } catch (JedisConnectionException var9) {
            jedis = this.pool.getResource();
            ret = jedis.hmset(key, hash);
        } finally {
            this.returnJedis(jedis);
        }

        return ret;
    }

    public long hlen(String key) {
        Jedis jedis = null;
        long value = 0L;

        try {
            jedis = this.pool.getResource();
            value = jedis.hlen(key).longValue();
        } catch (JedisConnectionException var9) {
            jedis = this.pool.getResource();
            value = jedis.hlen(key).longValue();
        } finally {
            this.returnJedis(jedis);
        }

        return value;
    }

    public Boolean hexists(String key, String field) {
        Jedis jedis = null;
        Boolean value = null;

        try {
            jedis = this.pool.getResource();
            value = jedis.hexists(key, field);
        } catch (JedisConnectionException var9) {
            jedis = this.pool.getResource();
            value = jedis.hexists(key, field);
        } finally {
            this.returnJedis(jedis);
        }

        return value;
    }

    public double zincrby(String key, double addValue, String member) {
        Jedis jedis = null;

        double newscore;
        try {
            jedis = this.pool.getResource();
            newscore = jedis.zincrby(key, addValue, member).doubleValue();
        } catch (JedisConnectionException var12) {
            jedis = this.pool.getResource();
            newscore = jedis.zincrby(key, addValue, member).doubleValue();
        } finally {
            this.returnJedis(jedis);
        }

        return newscore;
    }

    public void hrem(String key, String field) {
        Jedis jedis = null;

        try {
            jedis = this.pool.getResource();
            jedis.hdel(key, new String[]{field});
        } catch (JedisConnectionException var8) {
            jedis = this.pool.getResource();
            jedis.hdel(key, new String[]{field});
        } finally {
            this.returnJedis(jedis);
        }

    }

    public Set<String> hkeys(String key) {
        Jedis jedis = null;
        Set ret = null;

        try {
            jedis = this.pool.getResource();
            ret = jedis.hkeys(key);
        } catch (JedisConnectionException var8) {
            jedis = this.pool.getResource();
            ret = jedis.hkeys(key);
        } finally {
            this.returnJedis(jedis);
        }

        return ret;
    }

    public long lpush(String key, String... vaule) {
        Jedis jedis = null;
        long ret = 0L;

        try {
            jedis = this.pool.getResource();
            ret = jedis.lpush(key, vaule).longValue();
        } catch (JedisConnectionException var10) {
            jedis = this.pool.getResource();
            ret = jedis.lpush(key, vaule).longValue();
        } finally {
            this.returnJedis(jedis);
        }

        return ret;
    }

    public long lrem(String key, int count, String vaule) {
        Jedis jedis = null;
        long ret = 0L;

        try {
            jedis = this.pool.getResource();
            ret = jedis.lrem(key, (long)count, vaule).longValue();
        } catch (JedisConnectionException var11) {
            jedis = this.pool.getResource();
            ret = jedis.lrem(key, (long)count, vaule).longValue();
        } finally {
            this.returnJedis(jedis);
        }

        return ret;
    }

    public String lpop(String key) {
        Jedis jedis = null;
        String ret = null;

        try {
            jedis = this.pool.getResource();
            ret = jedis.lpop(key);
        } catch (JedisConnectionException var8) {
            jedis = this.pool.getResource();
            ret = jedis.lpop(key);
        } finally {
            this.returnJedis(jedis);
        }

        return ret;
    }

    public String rpop(String key) {
        Jedis jedis = null;
        String ret = null;

        try {
            jedis = this.pool.getResource();
            ret = jedis.rpop(key);
        } catch (JedisConnectionException var8) {
            jedis = this.pool.getResource();
            ret = jedis.rpop(key);
        } finally {
            this.returnJedis(jedis);
        }

        return ret;
    }

    public long rpush(String key, String... vaule) {
        Jedis jedis = null;
        long ret = 0L;

        try {
            jedis = this.pool.getResource();
            ret = jedis.rpush(key, vaule).longValue();
        } catch (JedisConnectionException var10) {
            jedis = this.pool.getResource();
            ret = jedis.rpush(key, vaule).longValue();
        } finally {
            this.returnJedis(jedis);
        }

        return ret;
    }

    public String lindex(String key, long index) {
        Jedis jedis = null;
        String ret = "";

        try {
            jedis = this.pool.getResource();
            ret = jedis.lindex(key, index);
        } catch (JedisConnectionException var10) {
            jedis = this.pool.getResource();
            ret = jedis.lindex(key, index);
        } finally {
            this.returnJedis(jedis);
        }

        return ret;
    }

    public long hdel(String key, String... files) {
        Jedis jedis = null;
        long ret = 0L;

        try {
            jedis = this.pool.getResource();
            ret = jedis.hdel(key, files).longValue();
        } catch (JedisConnectionException var10) {
            jedis = this.pool.getResource();
            ret = jedis.hdel(key, files).longValue();
        } finally {
            this.returnJedis(jedis);
        }

        return ret;
    }

    public long hincrBy(String key, String field, long value) {
        Jedis jedis = null;
        long ret = 0L;

        try {
            jedis = this.pool.getResource();
            ret = jedis.hincrBy(key, field, value).longValue();
        } catch (JedisConnectionException var12) {
            jedis = this.pool.getResource();
            ret = jedis.hincrBy(key, field, value).longValue();
        } finally {
            this.returnJedis(jedis);
        }

        return ret;
    }

    public double hincrByFloat(String key, String field, double value) {
        Jedis jedis = null;

        double ret;
        try {
            jedis = this.pool.getResource();
            ret = jedis.hincrByFloat(key, field, value).doubleValue();
        } catch (JedisConnectionException var12) {
            jedis = this.pool.getResource();
            ret = jedis.hincrByFloat(key, field, value).doubleValue();
        } finally {
            this.returnJedis(jedis);
        }

        return ret;
    }

    public void zadd(String key, double score, String value) {
        Jedis jedis = null;

        try {
            jedis = this.pool.getResource();
            jedis.zadd(key, score, value);
        } catch (JedisConnectionException var10) {
            jedis = this.pool.getResource();
            jedis.zadd(key, score, value);
        } finally {
            this.returnJedis(jedis);
        }

    }

    public void zadd(String key, long score, String value) {
        Jedis jedis = null;

        try {
            jedis = this.pool.getResource();
            jedis.zadd(key, (double)score, value);
        } catch (JedisConnectionException var10) {
            jedis = this.pool.getResource();
            jedis.zadd(key, (double)score, value);
        } finally {
            this.returnJedis(jedis);
        }

    }

    public long zrem(String key, String value) {
        Jedis jedis = null;
        long ret = 0L;

        try {
            jedis = this.pool.getResource();
            ret = jedis.zrem(key.getBytes(), new byte[][]{value.getBytes()}).longValue();
        } catch (JedisConnectionException var10) {
            jedis = this.pool.getResource();
            ret = jedis.zrem(key.getBytes(), new byte[][]{value.getBytes()}).longValue();
        } finally {
            this.returnJedis(jedis);
        }

        return ret;
    }

    public long zremMulti(String key, String[] values) {
        Jedis jedis = null;
        long ret = 0L;
        if(values != null && values.length != 0) {
            try {
                jedis = this.pool.getResource();
                ret = jedis.zrem(key, values).longValue();
            } catch (JedisConnectionException var10) {
                jedis = this.pool.getResource();
                ret = jedis.zrem(key, values).longValue();
            } finally {
                this.returnJedis(jedis);
            }

            return ret;
        } else {
            return 0L;
        }
    }

    public long zremrangeByRank(String key, long start, long end) {
        Jedis jedis = null;
        long ret = 0L;

        try {
            jedis = this.pool.getResource();
            ret = jedis.zremrangeByRank(key, start, end).longValue();
        } catch (JedisConnectionException var13) {
            jedis = this.pool.getResource();
            ret = jedis.zremrangeByRank(key, start, end).longValue();
        } finally {
            this.returnJedis(jedis);
        }

        return ret;
    }

    public Double zscore(String key, String field) {
        Jedis jedis = null;
        Double ret = Double.valueOf(0.0D);

        try {
            jedis = this.pool.getResource();
            ret = jedis.zscore(key.getBytes(), field.getBytes());
        } catch (JedisConnectionException var9) {
            jedis = this.pool.getResource();
            ret = jedis.zscore(key.getBytes(), field.getBytes());
        } finally {
            this.returnJedis(jedis);
        }

        return ret;
    }

    public Set<String> zrangeByScore(String key, double start, double end) {
        Jedis jedis = null;

        Set ret;
        try {
            jedis = this.pool.getResource();
            ret = jedis.zrangeByScore(key, start, end);
        } catch (JedisConnectionException var12) {
            jedis = this.pool.getResource();
            ret = jedis.zrangeByScore(key, start, end);
        } finally {
            this.returnJedis(jedis);
        }

        return ret;
    }

    public Set<byte[]> zrangeByScoreRetByte(String key, long start, long end) {
        Jedis jedis = null;
        Set<byte[]> byteRet = null;
        HashSet ret = new HashSet();

        try {
            jedis = this.pool.getResource();
            byteRet = jedis.zrangeByScore(key.getBytes(), (double)start, (double)end);
            if(byteRet != null) {
                Iterator var9 = byteRet.iterator();

                while(var9.hasNext()) {
                    byte[] it = (byte[])var9.next();
                    ret.add(it);
                }
            }
        } catch (JedisConnectionException var15) {
            jedis = this.pool.getResource();
            byteRet = jedis.zrangeByScore(key.getBytes(), (double)start, (double)end);
            if(byteRet != null) {
                Iterator var10 = byteRet.iterator();

                while(var10.hasNext()) {
                    byte[] it = (byte[])var10.next();
                    ret.add(it);
                }
            }
        } finally {
            this.returnJedis(jedis);
        }

        return ret;
    }

    public Set<byte[]> zrevrange(String key, long start, long end) {
        Jedis jedis = null;
        Set ret = null;

        try {
            jedis = this.pool.getResource();
            ret = jedis.zrevrange(key.getBytes(), start, end);
        } catch (JedisConnectionException var12) {
            jedis = this.pool.getResource();
            ret = jedis.zrevrange(key.getBytes(), start, end);
        } finally {
            this.returnJedis(jedis);
        }

        return ret;
    }

    public Set<byte[]> zrange(String key, long start, long end) {
        Jedis jedis = null;
        Set ret = null;

        try {
            jedis = this.pool.getResource();
            ret = jedis.zrange(key.getBytes(), start, end);
        } catch (JedisConnectionException var12) {
            jedis = this.pool.getResource();
            ret = jedis.zrange(key.getBytes(), start, end);
        } finally {
            this.returnJedis(jedis);
        }

        return ret;
    }

    public Set<String> zrangeRetStrSet(String key, long start, long end) {
        Jedis jedis = null;
        Set ret = null;

        try {
            jedis = this.pool.getResource();
            ret = jedis.zrange(key, start, end);
        } catch (JedisConnectionException var12) {
            jedis = this.pool.getResource();
            ret = jedis.zrange(key, start, end);
        } finally {
            this.returnJedis(jedis);
        }

        return ret;
    }

    public Long zrank(String key, String member) {
        Jedis jedis = null;

        Long ret;
        try {
            jedis = this.pool.getResource();
            ret = jedis.zrank(key, member);
        } catch (JedisConnectionException var9) {
            jedis = this.pool.getResource();
            ret = jedis.zrank(key, member);
        } finally {
            this.returnJedis(jedis);
        }

        return ret;
    }

    public Long zrevrank(String key, String member) {
        Jedis jedis = null;

        Long ret;
        try {
            jedis = this.pool.getResource();
            ret = jedis.zrevrank(key, member);
        } catch (JedisConnectionException var9) {
            jedis = this.pool.getResource();
            ret = jedis.zrevrank(key, member);
        } finally {
            this.returnJedis(jedis);
        }

        return ret;
    }

    public Set<String> zrevrangeRetString(String key, long start, long end) {
        Jedis jedis = null;
        Set ret = null;

        try {
            jedis = this.pool.getResource();
            ret = jedis.zrevrange(key, start, end);
        } catch (JedisConnectionException var12) {
            jedis = this.pool.getResource();
            ret = jedis.zrevrange(key, start, end);
        } finally {
            this.returnJedis(jedis);
        }

        return ret;
    }

    public Map<String, Double> zrevrangeWithScores(String key, long start, long end) {
        Jedis jedis = null;
        HashMap ret = new HashMap();

        try {
            jedis = this.pool.getResource();
            Set<Tuple> tuples = jedis.zrevrangeWithScores(key, start, end);
            Iterator var18 = tuples.iterator();

            while(var18.hasNext()) {
                Tuple tuple = (Tuple)var18.next();
                ret.put(tuple.getElement(), Double.valueOf(tuple.getScore()));
            }
        } catch (JedisConnectionException var15) {
            jedis = this.pool.getResource();
            Set<Tuple> tuples = jedis.zrevrangeWithScores(key, start, end);
            Iterator var10 = tuples.iterator();

            while(var10.hasNext()) {
                Tuple tuple = (Tuple)var10.next();
                ret.put(tuple.getElement(), Double.valueOf(tuple.getScore()));
            }
        } finally {
            this.returnJedis(jedis);
        }

        return ret;
    }

    public Map<String, Double> zrangeWithScores(String key, long start, long end) {
        Jedis jedis = null;
        HashMap ret = new HashMap();

        try {
            jedis = this.pool.getResource();
            Set<Tuple> tuples = jedis.zrangeWithScores(key, start, end);
            Iterator var18 = tuples.iterator();

            while(var18.hasNext()) {
                Tuple tuple = (Tuple)var18.next();
                ret.put(tuple.getElement(), Double.valueOf(tuple.getScore()));
            }
        } catch (JedisConnectionException var15) {
            jedis = this.pool.getResource();
            Set<Tuple> tuples = jedis.zrangeWithScores(key, start, end);
            Iterator var10 = tuples.iterator();

            while(var10.hasNext()) {
                Tuple tuple = (Tuple)var10.next();
                ret.put(tuple.getElement(), Double.valueOf(tuple.getScore()));
            }
        } finally {
            this.returnJedis(jedis);
        }

        return ret;
    }

    public List<String> zrevrangeByScoreString(String key, long start, long end, int offset, int limit) {
        Jedis jedis = null;
        Set<byte[]> byteRet = null;
        ArrayList ret = new ArrayList();

        try {
            jedis = this.pool.getResource();
            byteRet = jedis.zrevrangeByScore(key.getBytes(), (double)start, (double)end, offset, limit);
            if(byteRet != null) {
                Iterator var11 = byteRet.iterator();

                while(var11.hasNext()) {
                    byte[] it = (byte[])var11.next();
                    ret.add(new String(it));
                }
            }
        } catch (JedisConnectionException var17) {
            jedis = this.pool.getResource();
            byteRet = jedis.zrevrangeByScore(key.getBytes(), (double)start, (double)end, offset, limit);
            if(byteRet != null) {
                Iterator var12 = byteRet.iterator();

                while(var12.hasNext()) {
                    byte[] it = (byte[])var12.next();
                    ret.add(new String(it));
                }
            }
        } finally {
            this.returnJedis(jedis);
        }

        return ret;
    }

    public List<Long> zrevrangeByScoreLong(String key, long start, long end, int offset, int limit) {
        Jedis jedis = null;
        Set<byte[]> byteRet = null;
        ArrayList ret = new ArrayList();

        try {
            jedis = this.pool.getResource();
            byteRet = jedis.zrevrangeByScore(key.getBytes(), (double)start, (double)end, offset, limit);
            if(byteRet != null) {
                Iterator var11 = byteRet.iterator();

                while(var11.hasNext()) {
                    byte[] it = (byte[])var11.next();
                    ret.add(Long.valueOf(Long.parseLong(new String(it))));
                }
            }
        } catch (JedisConnectionException var17) {
            jedis = this.pool.getResource();
            byteRet = jedis.zrevrangeByScore(key.getBytes(), (double)start, (double)end, offset, limit);
            if(byteRet != null) {
                Iterator var12 = byteRet.iterator();

                while(var12.hasNext()) {
                    byte[] it = (byte[])var12.next();
                    ret.add(Long.valueOf(Long.parseLong(new String(it))));
                }
            }
        } finally {
            this.returnJedis(jedis);
        }

        return ret;
    }

    public List<Map<String, Double>> zrevrangeByScoreWithScore(String key, long start, long end, int offset, int limit) {
        Jedis jedis = null;
        ArrayList ret = new ArrayList();

        try {
            jedis = this.pool.getResource();
            Set<Tuple> tuples = jedis.zrevrangeByScoreWithScores(key.getBytes(), (double)start, (double)end, offset, limit);
            if(tuples != null) {
                Iterator var20 = tuples.iterator();

                while(var20.hasNext()) {
                    Tuple it = (Tuple)var20.next();
                    Map<String, Double> map = new HashMap();
                    map.put(it.getElement(), Double.valueOf(it.getScore()));
                    ret.add(map);
                }
            }
        } catch (JedisConnectionException var18) {
            jedis = this.pool.getResource();
            Set<Tuple> tuples = jedis.zrevrangeByScoreWithScores(key.getBytes(), (double)start, (double)end, offset, limit);
            if(tuples != null) {
                Iterator var12 = tuples.iterator();

                while(var12.hasNext()) {
                    Tuple it = (Tuple)var12.next();
                    Map<String, Double> map = new HashMap();
                    map.put(it.getElement(), Double.valueOf(it.getScore()));
                    ret.add(map);
                }
            }
        } finally {
            this.returnJedis(jedis);
        }

        return ret;
    }

    public Map<String, Object> zrevrangeByScoreWithScoreReturnMap(String key, long start, long end, int offset, int limit) {
        Jedis jedis = null;
        HashMap ret = new HashMap();

        try {
            ArrayList values;
            try {
                jedis = this.pool.getResource();
                Set<Tuple> tuples = jedis.zrevrangeByScoreWithScores(key.getBytes(), (double)start, (double)end, offset, limit);
                if(tuples != null) {
                     values = new ArrayList();
                    values = new ArrayList();
                    Iterator var22 = tuples.iterator();

                    while(var22.hasNext()) {
                        Tuple it = (Tuple)var22.next();
                        values.add(Long.valueOf(Long.parseLong(it.getElement())));
                        values.add(Long.valueOf((long)it.getScore()));
                    }

                    ret.put("values", values);
                    ret.put("scores", values);
                }
            } catch (JedisConnectionException var19) {
                jedis = this.pool.getResource();
                Set<Tuple> tuples = jedis.zrevrangeByScoreWithScores(key.getBytes(), (double)start, (double)end, offset, limit);
                if(tuples != null) {
                    values = new ArrayList();
                    List<Long> scores = new ArrayList();
                    Iterator var14 = tuples.iterator();

                    while(var14.hasNext()) {
                        Tuple it = (Tuple)var14.next();
                        values.add(Long.valueOf(Long.parseLong(it.getElement())));
                        scores.add(Long.valueOf((long)it.getScore()));
                    }

                    ret.put("values", values);
                    ret.put("scores", scores);
                }
            }
        } finally {
            this.returnJedis(jedis);
        }

        return ret;
    }

    public Long zunionstore(String dstkey, String... sets) {
        Jedis jedis = null;

        Long ret;
        try {
            jedis = this.pool.getResource();
            ret = jedis.zunionstore(dstkey, sets);
        } catch (JedisConnectionException var9) {
            jedis = this.pool.getResource();
            ret = jedis.zunionstore(dstkey, sets);
        } finally {
            this.returnJedis(jedis);
        }

        return ret;
    }

    public Long zinterstore(String dstkey, String... sets) {
        Jedis jedis = null;

        Long ret;
        try {
            jedis = this.pool.getResource();
            ret = jedis.zinterstore(dstkey, sets);
        } catch (JedisConnectionException var9) {
            jedis = this.pool.getResource();
            ret = jedis.zinterstore(dstkey, sets);
        } finally {
            this.returnJedis(jedis);
        }

        return ret;
    }

    public long lLen(String key) {
        Jedis jedis = null;
        long ret = 0L;

        try {
            jedis = this.pool.getResource();
            ret = jedis.llen(key).longValue();
        } catch (JedisConnectionException var9) {
            jedis = this.pool.getResource();
            ret = jedis.llen(key).longValue();
        } finally {
            this.returnJedis(jedis);
        }

        return ret;
    }

    public Long zcard(String key) {
        Jedis jedis = null;
        Long ret = Long.valueOf(0L);

        try {
            jedis = this.pool.getResource();
            ret = jedis.zcard(key.getBytes());
        } catch (JedisConnectionException var8) {
            jedis = this.pool.getResource();
            ret = jedis.zcard(key.getBytes());
        } finally {
            this.returnJedis(jedis);
        }

        return ret;
    }

    public long zcount(String key, long min, long max) {
        Jedis jedis = null;
        long ret = 0L;

        try {
            jedis = this.pool.getResource();
            ret = jedis.zcount(key, (double)min, (double)max).longValue();
        } catch (JedisConnectionException var13) {
            jedis = this.pool.getResource();
            ret = jedis.zcount(key, (double)min, (double)max).longValue();
        } finally {
            this.returnJedis(jedis);
        }

        return ret;
    }

    public long sadd(String key, String... value) {
        Jedis jedis = null;
        long ret = 0L;

        try {
            jedis = this.pool.getResource();
            ret = jedis.sadd(key, value).longValue();
        } catch (JedisConnectionException var10) {
            jedis = this.pool.getResource();
            ret = jedis.sadd(key, value).longValue();
        } finally {
            this.returnJedis(jedis);
        }

        return ret;
    }

    public long scard(String key) {
        Jedis jedis = null;
        long ret = 0L;

        try {
            jedis = this.pool.getResource();
            ret = jedis.scard(key).longValue();
        } catch (JedisConnectionException var9) {
            jedis = this.pool.getResource();
            ret = jedis.scard(key).longValue();
        } finally {
            this.returnJedis(jedis);
        }

        return ret;
    }

    public boolean sismember(String key, String field) {
        Jedis jedis = null;
        boolean ret = false;

        try {
            jedis = this.pool.getResource();
            ret = jedis.sismember(key.getBytes(), field.getBytes()).booleanValue();
        } catch (JedisConnectionException var9) {
            jedis = this.pool.getResource();
            ret = jedis.sismember(key.getBytes(), field.getBytes()).booleanValue();
        } finally {
            this.returnJedis(jedis);
        }

        return ret;
    }

    public String srandmember(String key) {
        Jedis jedis = null;

        String result;
        try {
            jedis = this.pool.getResource();
            result = jedis.srandmember(key);
        } catch (JedisConnectionException var8) {
            jedis = this.pool.getResource();
            result = jedis.srandmember(key);
        } finally {
            this.returnJedis(jedis);
        }

        return result;
    }

    public Set<String> smembers(String key) {
        Jedis jedis = null;

        Set result;
        try {
            jedis = this.pool.getResource();
            result = jedis.smembers(key);
        } catch (JedisConnectionException var8) {
            jedis = this.pool.getResource();
            result = jedis.smembers(key);
        } finally {
            this.returnJedis(jedis);
        }

        return result;
    }

    public Long srem(String key, String... members) {
        Jedis jedis = null;

        Long result;
        try {
            jedis = this.pool.getResource();
            result = jedis.srem(key, members);
        } catch (JedisConnectionException var9) {
            jedis = this.pool.getResource();
            result = jedis.srem(key, members);
        } finally {
            this.returnJedis(jedis);
        }

        return result;
    }

    public Set<String> keys(String pattern) {
        Jedis jedis = null;

        Set ret;
        try {
            jedis = this.pool.getResource();
            ret = jedis.keys(pattern);
        } catch (JedisConnectionException var8) {
            jedis = this.pool.getResource();
            ret = jedis.keys(pattern);
        } finally {
            this.returnJedis(jedis);
        }

        return ret;
    }

    public Long renamenx(String oldkey, String newkey) {
        Jedis jedis = null;

        Long ret;
        try {
            jedis = this.pool.getResource();
            ret = jedis.renamenx(oldkey, newkey);
        } catch (JedisConnectionException var9) {
            jedis = this.pool.getResource();
            ret = jedis.renamenx(oldkey, newkey);
        } finally {
            this.returnJedis(jedis);
        }

        return ret;
    }

    public Long ttl(String key) {
        Jedis jedis = null;

        Long ret;
        try {
            jedis = this.pool.getResource();
            ret = jedis.ttl(key);
        } catch (JedisConnectionException var8) {
            jedis = this.pool.getResource();
            ret = jedis.ttl(key);
        } finally {
            this.returnJedis(jedis);
        }

        return ret;
    }

    public String watch(String... keys) {
        Jedis jedis = null;

        String ret;
        try {
            jedis = this.pool.getResource();
            ret = jedis.watch(keys);
        } catch (JedisConnectionException var8) {
            jedis = this.pool.getResource();
            ret = jedis.watch(keys);
        } finally {
            this.returnJedis(jedis);
        }

        return ret;
    }

    public List<String> existsKeys(List<String> keys) {
        String script = "local result={}local j = 0 for i = 0,#(KEYS) do if redis.call('EXISTS',KEYS[i]) == 1  then j=j+1 result[j]= KEYS[i] end  end  return result";
        Jedis jedis = null;

        List ret;
        try {
            jedis = this.pool.getResource();
            String[] strings = (String[])keys.toArray(new String[keys.size()]);
            ret = (List)jedis.eval(script, keys.size(), strings);
        } catch (JedisConnectionException var10) {
            jedis = this.pool.getResource();
            String[] strings = (String[])keys.toArray(new String[keys.size()]);
            ret = (List)jedis.eval(script, keys.size(), strings);
        } finally {
            this.returnJedis(jedis);
        }

        return ret;
    }

    public Jedis getJedis() {
        Jedis jedis = null;
        boolean var2 = false;

        try {
            jedis = this.pool.getResource();
            return jedis;
        } catch (JedisConnectionException var6) {
            try {
                jedis = this.pool.getResource();
                return jedis;
            } catch (JedisConnectionException var5) {
                jedis = this.pool.getResource();
                return jedis;
            }
        }
    }

    public void close() {
        this.pool.close();
    }

    public void returnJedis(Jedis jedis) {
        if(jedis != null) {
            jedis.close();
        }

    }

    public static void closePipeline(Pipeline pipeline) {
        if(pipeline != null) {
            try {
                pipeline.close();
            } catch (IOException var2) {
                var2.printStackTrace();
            }
        }

    }

    public static void closeTransaction(Transaction transaction) {
        if(transaction != null) {
            try {
                transaction.close();
            } catch (IOException var2) {
                var2.printStackTrace();
            }
        }

    }

    public int getNumActive() {
        return this.pool.getNumActive();
    }

    public Long geoADD(String key, double longitude, double latitude, String member) {
        Jedis jedis = null;

        long ret;
        try {
            jedis = this.pool.getResource();
            ret = jedis.geoadd(key, longitude, latitude, member).longValue();
        } catch (JedisConnectionException var14) {
            jedis = this.pool.getResource();
            ret = jedis.geoadd(key, longitude, latitude, member).longValue();
        } finally {
            this.returnJedis(jedis);
        }

        return Long.valueOf(ret);
    }

    public Map<String, Double> geoRadius(String key, double longitude, double latitude, int radius, String unit, boolean asc) {
        Jedis jedis = null;
        LinkedHashMap data = new LinkedHashMap();

        try {
            try {
                jedis = this.pool.getResource();
                List<GeoRadiusResponse> userList = jedis.georadius(key, longitude, latitude, (double)radius, GeoUnit.valueOf(unit.toUpperCase()), GeoRadiusParam.geoRadiusParam().withDist());
                if(asc && !CollectionUtils.isEmpty(userList)) {
                    Collections.sort(userList, new Comparator<GeoRadiusResponse>() {
                        public int compare(GeoRadiusResponse arg0, GeoRadiusResponse arg1) {
                            return Double.valueOf(arg0.getDistance()).compareTo(Double.valueOf(arg1.getDistance()));
                        }
                    });
                }

                Iterator var22 = userList.iterator();

                while(var22.hasNext()) {
                    GeoRadiusResponse georadiu = (GeoRadiusResponse)var22.next();
                    data.put(new String(georadiu.getMember(), "utf-8"), Double.valueOf(georadiu.getDistance()));
                }
            } catch (JedisConnectionException var19) {
                jedis = this.pool.getResource();
                List<GeoRadiusResponse> userList = jedis.georadius(key, longitude, latitude, (double)radius, GeoUnit.valueOf(unit.toUpperCase()), GeoRadiusParam.geoRadiusParam().withDist());
                if(asc && !CollectionUtils.isEmpty(userList)) {
                    Collections.sort(userList, new Comparator<GeoRadiusResponse>() {
                        public int compare(GeoRadiusResponse arg0, GeoRadiusResponse arg1) {
                            return Double.valueOf(arg0.getDistance()).compareTo(Double.valueOf(arg1.getDistance()));
                        }
                    });
                }

                Iterator var13 = userList.iterator();

                while(var13.hasNext()) {
                    GeoRadiusResponse georadiu = (GeoRadiusResponse)var13.next();
                    data.put(new String(georadiu.getMember(), "utf-8"), Double.valueOf(georadiu.getDistance()));
                }
            } finally {
                this.returnJedis(jedis);
            }
        } catch (UnsupportedEncodingException var21) {
            var21.printStackTrace();
        }

        return data;
    }

    public List<GeoRadiusResponse> geoRadiusResponse(String key, double longitude, double latitude, int radius, String unit, boolean asc) {
        Jedis jedis = null;

        List userList;
        try {
            jedis = this.pool.getResource();
            userList = jedis.georadius(key, longitude, latitude, (double)radius, GeoUnit.valueOf(unit.toUpperCase()), GeoRadiusParam.geoRadiusParam().withDist());
        } catch (JedisConnectionException var15) {
            jedis = this.pool.getResource();
            userList = jedis.georadius(key, longitude, latitude, (double)radius, GeoUnit.valueOf(unit.toUpperCase()), GeoRadiusParam.geoRadiusParam().withDist());
        } finally {
            this.returnJedis(jedis);
        }

        if(asc && !CollectionUtils.isEmpty(userList)) {
            Collections.sort(userList, new Comparator<GeoRadiusResponse>() {
                public int compare(GeoRadiusResponse arg0, GeoRadiusResponse arg1) {
                    return Double.valueOf(arg0.getDistance()).compareTo(Double.valueOf(arg1.getDistance()));
                }
            });
        }

        return userList;
    }

    public long geoDel(String key, String member) {
        return this.zrem(key, member);
    }

    public Double geoDist(String key, String member1, String member2, String unit) {
        Jedis jedis = null;
        Double distance = null;

        try {
            jedis = this.pool.getResource();
            distance = jedis.geodist(key, member1, member2, GeoUnit.valueOf(unit.toUpperCase()));
        } catch (JedisConnectionException var11) {
            jedis = this.pool.getResource();
            distance = jedis.geodist(key, member1, member2, GeoUnit.valueOf(unit.toUpperCase()));
        } finally {
            this.returnJedis(jedis);
        }

        return distance;
    }

    public Map<String, Double> geoPos(String key, String member) {
        Jedis jedis = null;
        HashMap geoMap = new HashMap();

        try {
            List<GeoCoordinate> geoCoordinateList;
            GeoCoordinate geoCoordinate;
            try {
                jedis = this.pool.getResource();
                 geoCoordinateList = jedis.geopos(key, new String[]{member});
                if(geoCoordinateList == null || geoCoordinateList.size() < 1) {
                    geoCoordinateList = null;
                    return null;
                }

                 geoCoordinate = (GeoCoordinate)geoCoordinateList.get(0);
                if(geoCoordinate == null) {
                    geoCoordinate = null;
                    return null;
                }

                geoMap.put("longitude", Double.valueOf(geoCoordinate.getLongitude()));
                geoMap.put("latitude", Double.valueOf(geoCoordinate.getLatitude()));
            } catch (JedisConnectionException var12) {
                jedis = this.pool.getResource();
                geoCoordinateList = jedis.geopos(key, new String[]{member});
                if(geoCoordinateList == null || geoCoordinateList.size() < 1) {
                    geoCoordinate = null;
                    return null;
                }

                geoCoordinate = (GeoCoordinate)geoCoordinateList.get(0);
                if(geoCoordinate == null) {
                    Object var8 = null;
                    return (Map)var8;
                }

                geoMap.put("longitude", Double.valueOf(geoCoordinate.getLongitude()));
                geoMap.put("latitude", Double.valueOf(geoCoordinate.getLatitude()));
            }
        } finally {
            this.returnJedis(jedis);
        }

        return geoMap;
    }

    public static void main(String[] args) {
        double l = 12.12D;
        System.out.println((long)l);
    }
}
