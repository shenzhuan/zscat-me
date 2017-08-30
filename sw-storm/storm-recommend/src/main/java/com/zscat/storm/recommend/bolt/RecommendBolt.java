package com.zscat.storm.recommend.bolt;

import com.alibaba.fastjson.JSONObject;
import com.zscat.storm.recommend.redis.RedisLink;
import com.zscat.storm.recommend.util.PHPSerializerUtil;
import com.zscat.storm.recommend.util.RedisLinkUtil;
import com.zscat.storm.recommend.util.ScoreUtil;

import org.apache.commons.lang.StringUtils;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.IRichBolt;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Tuple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by likaige on 2016-10-24.
 */
public class RecommendBolt implements IRichBolt {

    private static final Logger log = LoggerFactory.getLogger(RecommendBolt.class);

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
            String uid = tuple.getString(0);
            log.info("RecommendBolt receive param uid={}", uid);
            this.recommendFriend(uid);
        } catch (Exception e) {
            log.error("RecommendBolt error:", e);
        } finally {
            //outputCollector.ack(tuple);
        }
    }

    /**
     * 推荐好友
     * @param uid 用户id
     */
    private void recommendFriend(String uid) {
        log.info("recommend friend for {}. start......", uid);

        //判断我的性别是否设置了
        String mySex = this.getUserSex(uid);
        if (StringUtils.isBlank(mySex)) {
            log.warn("user {} is not set sex. so return!", uid);
            return;
        }

        //判断我是否设置了头像
        boolean isSetHeadUrl = this.isSetHeadUrl(uid);

        //获取共同好友数据
        Map<String, String> commonUserCountMap = statRedisLink.hgetall("common:user:count:" + uid);
        log.info("uid={} commonUserCountMap={}", uid, commonUserCountMap);

        for (String secondUid : commonUserCountMap.keySet()) {
            int commonUserCount = Integer.parseInt(commonUserCountMap.get(secondUid));

            //共同好友小于2
            if (commonUserCount < 2) {
                log.info("uid={} secondFriendUid={} common user count is < 2. so ignore!!!", uid, secondUid);
                continue;
            }

            //按性别过滤
            String secondUserSex = this.getUserSex(secondUid);
            if (secondUserSex==null || mySex.equals(secondUserSex)){
                log.info("uid={} secondFriendUid={} is not set sex or same to me. so ignore!!!", uid, secondUid);
                continue;
            }

            //判断对方是否设置了头像
            boolean secondUserIsSetHeadUrl = this.isSetHeadUrl(secondUid);
            if (secondUserIsSetHeadUrl) {
                //共同好友权重
                double commonFriendScore = ScoreUtil.calCommonFriendScore(commonUserCount);

                //单人活跃度
                double singleVitalityScore = this.getSingleVitalityScore(secondUid);

                //群互动
                double groupInteractionScore = this.getGroupInteractionScore(uid, secondUid);

                //单向好友数量
                double friendCountScore = this.getFriendCountScore(secondUid);

                //总分
                double totalScore = commonFriendScore + singleVitalityScore + groupInteractionScore + friendCountScore;
                log.info("uid={} secondUid={} totalScore={} commonFriendScore={} singleVitalityScore={} " +
                                "groupInteractionScore={} friendCountScore={}", uid, secondUid, totalScore, commonFriendScore,
                        singleVitalityScore, groupInteractionScore, friendCountScore);

                //保存到数据库
                statRedisLink.zadd("recommend:user:"+uid, (long) totalScore, secondUid+"");
            } else {
                log.info("uid={} secondFriendUid={} is not set head url. so ignore!!!", uid, secondUid);
            }

            //如果我已经设置了头像，再把我推荐给二度好友
            if (isSetHeadUrl) {
                this.recommendMeToSecondFriend(uid, secondUid);
            } else {
                log.warn("I {} is not set head url. so can't recommend me to second friend!!!", uid);
            }
        }

        //更新业务库中的推荐列表，只查询top5
        this.updateRecommendTop5(uid);
    }

    //再把我推荐给二度好友
    private void recommendMeToSecondFriend(String myUid, String secondUid) {
        //获取共同好友数据
        String commonUserCountStr = statRedisLink.hget("common:user:count:" + myUid, secondUid);
        int commonUserCount = Integer.parseInt(commonUserCountStr);

        //共同好友权重
        double commonFriendScore = ScoreUtil.calCommonFriendScore(commonUserCount);

        //单人活跃度
        double singleVitalityScore = this.getSingleVitalityScore(myUid);

        //群互动
        double groupInteractionScore = this.getGroupInteractionScore(secondUid, myUid);

        //单向好友数量
        double friendCountScore = this.getFriendCountScore(myUid);

        //总分
        double totalScore = commonFriendScore + singleVitalityScore + groupInteractionScore + friendCountScore;
        log.info("recommendMeToSecondFriend myUid={} secondUid={} totalScore={} commonFriendScore={} " +
                        "singleVitalityScore={} groupInteractionScore={} friendCountScore={}", myUid, secondUid,
                totalScore, commonFriendScore, singleVitalityScore, groupInteractionScore, friendCountScore);

        //保存到数据库
        statRedisLink.zadd("recommend:user:"+secondUid, (long) totalScore, myUid+"");

        //更新业务库中的推荐列表，只查询top5
        this.updateRecommendTop5(secondUid);
    }

    //更新业务的推荐好友列表
    private void updateRecommendTop5(String uid) {
        log.info("updateRecommendTop5 uid============================={}", uid);
        Set<byte[]> zrevrange = statRedisLink.zrevrange("recommend:user:" + uid, 0, -1);
        userRedisLink.del("recommend:top5:user:" + uid);

        int count = 0;
        for (byte[] idByte : zrevrange) {
            String recommendUserId = new String(idByte);

            //判断该用户是否在取消列表中
            boolean isCancelFriend = statRedisLink.exist("recommend:cancel:" + uid + ":" + recommendUserId);
            if (isCancelFriend) {
                log.info("uid={} recommendUserId={} isCancelFriend. so ignore....", uid, recommendUserId);
                continue;
            }

            //获取共同好友数
            String sameFriendNum = statRedisLink.hget("common:user:count:" + uid, recommendUserId);
            if (StringUtils.isBlank(sameFriendNum)) {
                sameFriendNum = statRedisLink.hget("common:user:count:" + recommendUserId, uid);
            }
            JSONObject json = new JSONObject();
            json.put("uid", recommendUserId);
            json.put("sameFriendNum", sameFriendNum);

            //塞到业务redis库中
            userRedisLink.rpush("recommend:top5:user:" + uid, json.toJSONString());

            count++;

            //注意：为了容错，这个地方是故意多放一些推荐数据。update by likaige
            if (count >= 10) {
                break;
            }
        }
    }

    private String getUserSex(String uid) {
        Map<String, String> userSexMap = this.getUserSexMap(uid);
        return userSexMap.get(uid);
    }

    private boolean isSetHeadUrl(String uid) {
        boolean isSetHeadUrl = false;
        byte[] bytes = userRedisLink.get(uid);
        try {
            Map<String, Object> userMap = PHPSerializerUtil.unserializeToMap(bytes);
            String headurl = (String) userMap.get("headurl");
            if (!headurl.startsWith("defaultheadurl")) {
                isSetHeadUrl = true;
            }
        } catch (Exception e) {
            log.error("isSetHeadUrl error.", e);
        }
        return isSetHeadUrl;
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

    private double getFriendCountScore(String secondUid) {
        long friendCount = relationRedisLink.hlen("relation_" + secondUid);
        return ScoreUtil.calFriendCountScore(friendCount);
    }

    private double getGroupInteractionScore(String uid, String secondUid) {
        //转发
        long relayCount = 0;
        String relay1 = statRedisLink.hget("relay:" + uid, secondUid);
        if (relay1 != null){
            relayCount += Long.parseLong(relay1);
        }
        String relay2 = statRedisLink.hget("relay:" + secondUid, uid);
        if (relay2 != null){
            relayCount += Long.parseLong(relay2);
        }

        //@
        long atCount = 0;
        String at1 = statRedisLink.hget("at:" + uid, secondUid);
        if (at1 != null){
            atCount += Long.parseLong(at1);
        }
        String at2 = statRedisLink.hget("at:" + secondUid, uid);
        if (at2 != null){
            atCount += Long.parseLong(at2);
        }

        double relayScore = ScoreUtil.calRelayScore(relayCount);
        double atScore = ScoreUtil.calAtScore(atCount);

        return relayScore + atScore;
    }

    private double getSingleVitalityScore(String secondUid) {
        //获取活跃度数据
        long ptpCount = 0;
        long fcCount = 0;
        Map<String, String> vitalityData = statRedisLink.hgetall("user:vitality:" + secondUid);
        for (String type : vitalityData.keySet()){
            if ("fc".equals(type)){
                fcCount = Long.parseLong(vitalityData.get(type));
            } else {
                ptpCount += Long.parseLong(vitalityData.get(type));
            }
        }

        //点对点活跃度
        double ptpVitalityScore = ScoreUtil.calPtpVitalityScore(ptpCount);

        //朋友圈活跃度
        double fcVitalityScore = ScoreUtil.calFcVitalityScore(fcCount);

        return ptpVitalityScore + fcVitalityScore;
    }

    @Override
    public void cleanup() {
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
    }

    @Override
    public Map<String, Object> getComponentConfiguration() {
        return null;
    }
}
