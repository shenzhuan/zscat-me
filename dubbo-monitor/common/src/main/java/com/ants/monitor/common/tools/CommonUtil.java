package com.ants.monitor.common.tools;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zxg on 15/11/6.
 */
public class CommonUtil {


    private static final SimpleDateFormat sdfday= new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat sdfmonth= new SimpleDateFormat("yyyy-MM");
    private static final SimpleDateFormat sdftrueMonth= new SimpleDateFormat("MM");
    private static final SimpleDateFormat sdftrueDay= new SimpleDateFormat("dd");

    // 获得当前天yyyy-MM-dd
    public static String getNowDay(){
        long now = System.currentTimeMillis();
        return sdfday.format(now);
    }
    // 获得当前天yyyy-MM-dd
    public static String getTrueDay(){
        long now = System.currentTimeMillis();
        return sdftrueDay.format(now);
    }
    // 获得当前月份yyyy-MM
    public static String getNowMonth(){
        long now = System.currentTimeMillis();
        return sdfmonth.format(now);
    }
    // 获得实际月份MM
    public static String getTrueMonth(){
        long now = System.currentTimeMillis();
        return sdftrueMonth.format(now);
    }


}
