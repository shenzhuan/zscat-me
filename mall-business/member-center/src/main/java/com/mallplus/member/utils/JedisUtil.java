//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.mallplus.member.utils;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;

public class JedisUtil {
    static Logger log = LoggerFactory.getLogger(JedisUtil.class);

    @Resource
    private static JedisPool jedisPool;

    public static Jedis getResource() {
        Jedis client = jedisPool.getResource();
        return client;
    }

    public static void returnResource(Jedis resource) {
        resource.close();
    }

    public static void shutdown() {
        jedisPool.destroy();
    }

    public static String hget(String key, String field) {
        if (StringUtils.isBlank(key)) {
            return "";
        } else {
            Jedis jedis = getResource();

            String var4;
            try {
                String var3 = jedis.hget(key, field);
                return var3;
            } catch (Exception var8) {
                log.error("Error hget, key:" + key + ", field:" + field, var8);
                if (jedis != null && jedis.isConnected()) {
                    returnResource(jedis);
                }

                var4 = "";
            } finally {
                if (null != jedis && jedis.isConnected()) {
                    returnResource(jedis);
                }

            }

            return var4;
        }
    }

    public static void setex(String key, int seconds, String value) {
        if (!StringUtils.isBlank(key)) {
            Jedis jedis = getResource();

            try {
                jedis.setex(key, seconds, value);
                return;
            } catch (Exception var8) {
                log.error("Error setex, key:" + key + ", value:" + value, var8);
                if (jedis != null && jedis.isConnected()) {
                    returnResource(jedis);
                }
            } finally {
                if (null != jedis && jedis.isConnected()) {
                    returnResource(jedis);
                }

            }

        }
    }

    public static boolean keyExists(String key) {
        if (StringUtils.isBlank(key)) {
            return false;
        } else {
            Jedis jedis = getResource();

            boolean var3;
            try {
                boolean var2 = jedis.exists(key);
                return var2;
            } catch (Exception var7) {
                log.error("Error keyExists, key:" + key, var7);
                if (jedis != null && jedis.isConnected()) {
                    returnResource(jedis);
                }

                var3 = false;
            } finally {
                if (null != jedis && jedis.isConnected()) {
                    returnResource(jedis);
                }

            }

            return var3;
        }
    }
}
