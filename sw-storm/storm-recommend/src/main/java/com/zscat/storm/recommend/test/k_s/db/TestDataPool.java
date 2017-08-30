package com.zscat.storm.recommend.test.k_s.db;
 
import java.sql.Connection;
 
public class TestDataPool {
    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        int j=0;
        System.out.println("连接池开始：" + start);
        for (int i = 0; i <20; i++) {
            Connection con = JDBCUtil.getConnection();
            System.out.println(con);
            if(i<14) {
                JDBCUtil.freeConnection(con);
            } 
        }
        long end = System.currentTimeMillis();
        System.out.println("连接池结束：" + end);
        System.out.println("连接池耗时：" + (end - start));
    }
}