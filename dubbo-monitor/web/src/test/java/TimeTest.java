import com.ants.monitor.common.tools.TimeUtil;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zxg on 15/11/17.
 */
public class TimeTest {

    // 获得当前日期与本周一相差的天数
    @Test
    public void getMondayPlus() {
        System.out.println(TimeUtil.getMondayPlus());
    }

    // 获得当前周- 周一的日期
    @Test
    public void getCurrentMonday() {
        System.out.println(TimeUtil.getCurrentMonday());
    }

    // 获得当前周- 周日  的日期
    @Test
    public void getPreviousSunday() {
        System.out.println(TimeUtil.getPreviousSunday());
    }


    // 获得当前月--开始日期
    @Test
    public void getMinMonthDate() {
        String date = "2015-11-17";
        System.out.println(TimeUtil.getMinMonthDate(date));
    }


    // 获得当前月--结束日期
    @Test
    public void getMaxMonthDate() {
        String date = "2015-11-17";
        System.out.println(TimeUtil.getMinMonthDate(date));
    }

    @Test
    public void testLongTo() throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d1 = df.parse("2012-11-05 12:21:00");
//        long before = d1.getTime();
        long before = 1448435301542L;
        Date date = new Date(before);
        System.out.println(TimeUtil.getTimeString(date));

        long afterMinute = (before / 60000) * 60000;
        System.out.println("minute:" + TimeUtil.getTimeString(new Date(afterMinute)));
        long afterMinute1 = (before / 600000) * 600000;
        System.out.println("minute:" + TimeUtil.getTimeString(new Date(afterMinute1)));
        long afterHour = before / (60 * 1000 * 60) * (60 * 60 * 1000);
        System.out.println("hour:" + TimeUtil.getTimeString(new Date(afterHour)));

        System.out.println("hour:" + afterHour);
    }

    @Test
    public void testTime() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {

            Date d1 = df.parse("2012-11-05 12:00:00"); //后的时间
            Date d2 = df.parse("2012-11-04 11:10:00"); //前的时间
            Long diff = d1.getTime() - d2.getTime(); //两时间差，精确到毫秒

            Long day = diff / (1000 * 60 * 60 * 24); //以天数为单位取整
            Long hour = (diff / (60 * 60 * 1000) - day * 24); //以小时为单位取整
            Long min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60); //以分钟为单位取整
            Long secone = (diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);

            long truehour = diff / (60 * 60 * 1000);

            if (truehour > 10) {
                System.out.print("====1====");
            }
            if (truehour < 10) {
                System.out.print("====2====");
            }


            System.out.println("---diff的值---->" + diff);
            System.out.println("---days的值---->" + day);
            System.out.println("---hour的值---->" + hour);
            System.out.println("---min的值---->" + min);
            System.out.println("---secone的值---->" + secone);

            System.out.println("---两时间差---> " + day + "天" + hour + "小时" + min + "分" + secone + "秒");

            List<String> list = new ArrayList<>();
            list.contains("a");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testThreadLocal(){
        ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
        threadLocal.set(1);
        System.out.println("=====:" + threadLocal.get());
        threadLocal.set(2);
        System.out.println("=====:" + threadLocal.get());
    }
}

