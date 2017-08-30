package com.zscat.storm.recommend.util;

/**
 * Created by likg on 2016-10-25.
 */
public class ScoreUtil {

    private static double log(double value, double base) {
        return Math.log(value) / Math.log(base);
    }

    //共同好友数量
    public static double calCommonFriendScore(int commonUserCount) {
        if (commonUserCount == 0){
            return 0;
        }
        return 10 * (commonUserCount - 2) * commonUserCount * log(commonUserCount, 2);
    }

    //点对点活跃度
    public static double calPtpVitalityScore(long ptpCount) {
        if (ptpCount == 0) {
            return 0;
        }
        return 6 * log(ptpCount, 2);
    }

    //朋友圈活跃度
    public static double calFcVitalityScore(long fcCount) {
        if (fcCount == 0) {
            return 0;
        }
        return 4 * log(fcCount, 2);
    }

    //转发
    public static double calRelayScore(long relayCount) {
        return 0.4 * log(relayCount + 1, 2);
    }

    //@
    public static double calAtScore(long atCount) {
        return 0.6 * log(atCount + 1, 2);
    }

    //好友数量
    public static double calFriendCountScore(long friendCount) {
        if (friendCount == 0) {
            return 0;
        }
        return log(friendCount, 5);
    }


    public static void main(String[] args) {
        System.out.println(calCommonFriendScore(3));
        System.out.println((int)1.9233);
    }
}
