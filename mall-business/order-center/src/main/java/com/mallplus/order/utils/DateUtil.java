package com.mallplus.order.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 * https://github.com/shenzhuan/mallplus on 2019/1/29.
 */
public class DateUtil {

    /**
     * 从Date类型的时间中提取日期部分
     */
    public static Date getDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * 从Date类型的时间中提取时间部分
     */
    public static Date getTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.YEAR, 1970);
        calendar.set(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }
    public static String dateToStr(Date date, String formatStr) {
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        String s = "";

        try {
            s = format.format(date);
        } catch (Exception var5) {
            var5.printStackTrace();
        }

        return s;
    }
    public static Date strToDate(String str, String formatStr) {
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        Date date = null;

        try {
            date = format.parse(str);
        } catch (ParseException var5) {
            var5.printStackTrace();
        }

        return date;
    }
}
