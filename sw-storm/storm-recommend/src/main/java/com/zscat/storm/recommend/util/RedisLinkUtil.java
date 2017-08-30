package com.zscat.storm.recommend.util;

import com.zscat.storm.recommend.redis.RedisLink;
import com.zscat.storm.recommend.redis.Config;


import java.io.IOException;

/**
 * Created by likaige on 2016-04-14.
 */
public class RedisLinkUtil {

    private static RedisLink userRedisLink;
    private static RedisLink statRedisLink;
    private static RedisLink relationRedisLink;

    static {
        try {
            Config.reload();
        } catch (IOException e) {
            e.printStackTrace();
        }

        userRedisLink = new RedisLink(Config.get("user_redis"), Config.getInt("user_redis_port", 6379),
                Config.get("user_redis_pwd"));
        statRedisLink = new RedisLink(Config.get("stat_redis"), Config.getInt("stat_redis_port", 6379),
                Config.get("stat_redis_pwd"));

        relationRedisLink = new RedisLink(Config.get("relation_redis"), Config.getInt("relation_redis_port", 6379),
                Config.get("relation_redis_pwd"));
    }

    public static RedisLink getUserRedisLink() {
        return userRedisLink;
    }
    public static RedisLink getStatRedisLink() {
        return statRedisLink;
    }
    public static RedisLink getRelationRedisLink() {
        return relationRedisLink;
    }
}
