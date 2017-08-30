package com.zscat.storm.recommend.bolt;

import com.zscat.storm.recommend.redis.RedisLink;
import com.zscat.storm.recommend.util.PHPSerializerUtil;
import com.zscat.storm.recommend.util.RedisLinkUtil;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.IRichBolt;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 查找共同好友
 * @author likaige
 * @date 2016-10-24
 */
public class SearchCommonFriendBolt implements IRichBolt {

    private static final Logger log = LoggerFactory.getLogger(SearchCommonFriendBolt.class);

    private OutputCollector outputCollector;
    private RedisLink userRedisLink;
    private RedisLink statRedisLink;
    private RedisLink relationRedisLink;

    @Override
    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
        this.outputCollector = outputCollector;
        userRedisLink = RedisLinkUtil.getUserRedisLink();
        statRedisLink = RedisLinkUtil.getStatRedisLink();
        relationRedisLink = RedisLinkUtil.getRelationRedisLink();
    }

    @Override
    public void execute(Tuple tuple) {
        try {
            String fromUid = tuple.getString(0);
            String toUid = tuple.getString(1);
            log.info("SearchCommonFriendBolt receive param fromUid={} toUid={}", fromUid, toUid);

            //设置性别触发
            if (StringUtils.isBlank(toUid)) {
                //获取我所有的好友
                Collection<String> myFriendUids = relationRedisLink.hkeys("relation_" + fromUid);
                //过滤掉小秘书uid
//                Set<String> miMembers = userRedisLink.smembers("app:xiaomishu:all");
//                myFriendUids = CollectionUtils.subtract(myFriendUids, miMembers);

                myFriendUids.remove("10");

                //遍历我的好友重新计算
                for (String myFriendUid : myFriendUids) {
                    //查找共同好友
                    boolean hasCommonFriend1 = this.searchCommonFriend(fromUid, myFriendUid);
                    boolean hasCommonFriend2 = this.searchCommonFriend(myFriendUid, fromUid);
                    if (hasCommonFriend1) {
                        outputCollector.emit(new Values(fromUid));
                    }
                    if (hasCommonFriend2) {
                        outputCollector.emit(new Values(myFriendUid));
                    }
                }
            }
            //加好友触发
            else {
                //检查好友推荐列表，如果在推荐列表中则移除
                this.removeFromRecommendList(fromUid, toUid);

                //查找共同好友
                boolean hasCommonFriend1 = this.searchCommonFriend(fromUid, toUid);
                boolean hasCommonFriend2 = this.searchCommonFriend(toUid, fromUid);
                if (hasCommonFriend1) {
                    outputCollector.emit(new Values(fromUid));
                }
                if (hasCommonFriend2) {
                    outputCollector.emit(new Values(toUid));
                }
            }
        } catch (Exception e) {
            log.error("SearchCommonFriendBolt error:", e);
        } finally {
            //outputCollector.ack(tuple);
        }
    }

    //检查好友推荐列表，如果在推荐列表中则移除
    private void removeFromRecommendList(String fromUid, String toUid) {
        try {
            log.info("removeFromRecommendList fromUid={} toUid={}", fromUid, toUid);
            statRedisLink.zrem("recommend:user:" + fromUid, toUid);
            statRedisLink.zrem("recommend:user:" + toUid, fromUid);
        } catch (Exception e) {
            log.error("removeFromRecommendList error.", e);
        }
    }

    /**
     * 为我(uid)查找有共同好友的二度人脉
     * @param uid 用户id
     * @param friendUid 一度好友id
     */
    private boolean searchCommonFriend(String uid, String friendUid) throws Exception {
        log.info("searchCommonFriend for uid={} by {} start......", uid, friendUid);
        boolean hasCommonFriend = false;

        //首先获取我所有的好友
        Collection<String> myFriendUids = relationRedisLink.hkeys("relation_" + uid);

        //过滤掉小秘书uid
//        Set<String> miMembers = userRedisLink.smembers("app:xiaomishu:all");
//        myFriendUids = CollectionUtils.subtract(myFriendUids, miMembers);
        myFriendUids.remove("10");
        //过滤掉单向关系的好友
        myFriendUids = this.filterOneWayUser(uid, myFriendUids);
        log.info("myFriendUids: {}", myFriendUids);
        if (myFriendUids.isEmpty()) {
            log.info("user {} is no filtered friends. so return!", uid);
            return false;
        }

        //获取我一度好友的所有好友，即二度好友
        Collection<String> secondFriendUids = relationRedisLink.hkeys("relation_" + friendUid);
        log.info("secondFriendUids 1: {}", secondFriendUids);
        if (secondFriendUids.isEmpty()) {
            log.info("user {} is no second friends. so return!", uid);
            return false;
        }

        //过滤掉单向关系的好友
        secondFriendUids = this.filterOneWayUser(friendUid, secondFriendUids);

        //过滤数据
        secondFriendUids = this.filterData(uid, secondFriendUids, myFriendUids);
        log.info("secondFriendUids filtered: {}", secondFriendUids);

        //遍历二度好友
        for (String secondFriendUid : secondFriendUids) {
            //获取二度好友的所有好友
            Collection<String> secondFriendFriendUids = relationRedisLink.hkeys("relation_" + secondFriendUid);
            log.info("secondFriendUid={} secondFriendFriendUids: {}", secondFriendUid, secondFriendFriendUids);

            //过滤掉单向关系的好友
            secondFriendFriendUids = this.filterOneWayUser(secondFriendUid, secondFriendFriendUids);
            log.info("filtered one way! secondFriendUid={} secondFriendFriendUids: {}", secondFriendUid, secondFriendFriendUids);

            //计算出我与二度好友的共同好友
            Collection<String> commonFriendUids = CollectionUtils.intersection(myFriendUids, secondFriendFriendUids);
            log.info("secondFriendUid={} commonFriendUids: {}", secondFriendUid, commonFriendUids);

            //记录到stat数据库
            if (!commonFriendUids.isEmpty()) {
                hasCommonFriend = true;
                statRedisLink.hset("common:user:count:"+uid, secondFriendUid, commonFriendUids.size()+"");
                statRedisLink.hset("common:user:detail:"+uid, secondFriendUid, StringUtils.join(commonFriendUids, ","));

                statRedisLink.hset("common:user:count:"+secondFriendUid, uid, commonFriendUids.size()+"");
                statRedisLink.hset("common:user:detail:"+secondFriendUid, uid, StringUtils.join(commonFriendUids, ","));
            } else {
                log.info("uid={} secondFriendUid={} is no common user. so ignore!!!", uid, secondFriendUid);
            }
        }
        return hasCommonFriend;
    }

    private Collection<String> filterOneWayUser(String uid, Collection<String> myFriendUids) {
        Set<String> oneWayUids = new HashSet<>();
        for (String fuid : myFriendUids) {
            Boolean exists = relationRedisLink.hexists("relation_" + fuid, uid);
            if (!exists) {
                oneWayUids.add(fuid);
            }
        }
        myFriendUids = CollectionUtils.subtract(myFriendUids, oneWayUids);
        return myFriendUids;
    }

    //过滤数据
    private Collection<String> filterData(String uid, Collection<String> secondFriendUids, Collection<String> myFriendUids) {
        //剔除掉已经是我的好友的uid
        secondFriendUids = CollectionUtils.subtract(secondFriendUids, myFriendUids);
        if (secondFriendUids.isEmpty()) {
            return secondFriendUids;
        }

        //小秘书uid
//        Set<String> miMembers = userRedisLink.smembers("app:xiaomishu:all");
//        secondFriendUids = CollectionUtils.subtract(secondFriendUids, miMembers);
        secondFriendUids.remove("10");
        if (secondFriendUids.isEmpty()) {
            return secondFriendUids;
        }

        //我自己的uid
        secondFriendUids.remove(uid);
        if (secondFriendUids.isEmpty()) {
            return secondFriendUids;
        }

        //过滤掉未设置性别的uid
        String[] suids = new String[secondFriendUids.size()];
        secondFriendUids.toArray(suids);
        Map<String, String> userSexMap = this.getUserSexMap(suids);
        for (String id : userSexMap.keySet()) {
            String sex = userSexMap.get(id);
            if (StringUtils.isBlank(sex)) {
                secondFriendUids.remove(id);
            }
        }

        return secondFriendUids;
    }

    private Map<String, String> getUserSexMap(String... uids) {
        Map<String, String> userSexMap = new HashMap<>();
        try {
            Map<String, String> userMap = userRedisLink.mgetToMap(uids);
            for (String uid : userMap.keySet()) {
                Map<String, Object> user = PHPSerializerUtil.unserializeToMap(userMap.get(uid).getBytes());
                if (user.containsKey("sex") && user.get("sex")!=null){
                    userSexMap.put(uid, user.get("sex").toString());
                }else {
                    userSexMap.put(uid, null);
                }
            }
        } catch (Exception e) {
            log.error("getUserSexMap error.", e);
        }
        return userSexMap;
    }


    @Override
    public void cleanup() {
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("uid"));
    }

    @Override
    public Map<String, Object> getComponentConfiguration() {
        return null;
    }
}
