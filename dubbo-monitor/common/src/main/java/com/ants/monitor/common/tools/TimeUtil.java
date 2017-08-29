package com.ants.monitor.common.tools;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by zxg on 15/11/17.
 */
public class TimeUtil {

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    // 获得当前日期与本周一相差的天数
    public static int getMondayPlus() {
        Calendar cd = Calendar.getInstance();
        // 获得今天是一周的第几天，星期日是第一天，星期二是第二天......
        int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeek == 1) {
            return -6;
        } else {
            return 2 - dayOfWeek;
        }
    }

    // 获得当前周- 周一的日期
    public static String getCurrentMonday() {
        int mondayPlus = getMondayPlus();
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus);
        Date monday = currentDate.getTime();
        DateFormat df = DateFormat.getDateInstance();
        String preMonday = df.format(monday);
        return preMonday;
    }

    // 获得当前周- 周日  的日期
    public static String getPreviousSunday() {
        int mondayPlus = getMondayPlus();
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus + 6);
        Date monday = currentDate.getTime();
        DateFormat df = DateFormat.getDateInstance();
        String preMonday = df.format(monday);
        return preMonday;
    }


    // 获得当前月--开始日期
    public static String getMinMonthDateString(String date) {
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(dateFormat.parse(date));
            calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
            return dateFormat.format(calendar.getTime());
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
    // 获得当前月--开始日期
    public static Date getMinMonthDate(String date) {
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(dateFormat.parse(date));
            calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
            return calendar.getTime();
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        return null;
    }


    // 获得当前月--结束日期
    public static String getMaxMonthDate(String date) {
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(dateFormat.parse(date));
            calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
            return dateFormat.format(calendar.getTime());
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        return null;
    }


    // date 的 后n天,若为负数 则为前n天
    public static String getBeforDateByNumber(Date date,Integer amount){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, amount);
        date = calendar.getTime();

        return getDateString(date);
    }

    // date 的 后n小时,若为负数 则为前n小时
    public static Date getBeforHourByNumber(Date date,Integer amount){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR, amount);
        date = calendar.getTime();

        return date;
    }



    public static String getDateString(Date date){
        String str2 = dateFormat.format(date);
        return str2;
    }


    public static String getTimeString(Date date){
        SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
        String str2 = formatter.format(date);
        return str2;
    }

    public static Date getDateByTimeString(String time){
        SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
        try {
            Date d1 = formatter.parse(time);
            return d1;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getMinuteString(Date date){
        SimpleDateFormat formatter = new SimpleDateFormat( "HH:mm");
        String str2 = formatter.format(date);
        return str2;
    }

    public static String getHourString(Date date){
        SimpleDateFormat formatter = new SimpleDateFormat( "HH");
        String str2 = formatter.format(date);
        return str2;
    }

    public static String getYearMonthString(Date date){
        SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM");
        String str2 = formatter.format(date);
        return str2;
    }

    // time1 > tim2 : true
    public static Boolean compareTime(String time1,String time2) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
        Date dt1 = formatter.parse(time1);
        Date dt2 = formatter.parse(time2);
        if (dt1.getTime() > dt2.getTime()) {
            return true;
        }

        return false;
    }



}
