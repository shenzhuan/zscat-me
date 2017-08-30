package util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by zscat on 2016-05-26.
 */
public class DateUtil {

    /**
     * 获取当前时间，格式：2016-01-01 12:01:01
     *
     * @return 当前时间
     */
    public static String getCurrentDatetime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    }

    /**
     * @param time
     * @return
     */
    public static long getCurrentTime(String time) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.parse(time).getTime();
    }

    /**
     * 获取当前时间，格式：2016-01-01
     *
     * @return 当前时间
     */
    public static String getCurrentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(new Date());
    }

    /**
     * 获取当前时间，格式：2016-01-01
     *
     * @return 当前时间
     */
    public static String getYearMonth(Long second) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        return sdf.format(new Date(second * 1000));
    }


    /**
     * 判断是否发送时间
     *
     * @param millisecond
     * @return
     */
    public static boolean isSendtime(long millisecond) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millisecond);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        return hour > 8 && hour < 21;
    }

    /*public static void main(String[] args) {
        String d = "DROP TABLE IF EXISTS %s;";
        String s = "CREATE TABLE `%s` (\n" +
                "  `file_key` varchar(100) NOT NULL DEFAULT '',\n" +
                "  `grouplist` varchar(64) DEFAULT NULL,\n" +
                "  `uid` bigint(64) DEFAULT NULL,\n" +
                "  `filesize` int(20) DEFAULT NULL,\n" +
                "  `createtime` int(20) DEFAULT NULL,\n" +
                "  `isBAR` char(5) DEFAULT NULL,\n" +
                "  `videoType` char(1) DEFAULT NULL,\n" +
                "  `userlist` varchar(500) DEFAULT NULL,\n" +
                "  PRIMARY KEY (`file_key`)\n" +
                ") ENGINE=MyISAM DEFAULT CHARSET=utf8;";


        int i = 2016;

        while (i<2021){
            int j = 1;
            while (j<13){
                String j1 = "";
                if(j<10){
                    j1 = "0"+j;
                }else{
                    j1 = j+"";
                }
                System.out.println(String.format(d,"fileinfo"+i+j1));
                System.out.println(String.format(s,"fileinfo"+i+j1));
               j++;
            }
            i++;
        }
    }
*/
    public static long getTaskBeginTime(boolean flag) {
        long beginTime = 30 * 1000;
        // 定时时间为每日3点，当天执行为true，不执行为false,时间为毫秒
        if (flag) {
            return beginTime;
        } else {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(new Date().getTime());
            long t1 = calendar.getTimeInMillis();
            calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + 1);
            calendar.set(Calendar.HOUR_OF_DAY, 9);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            long t2 = calendar.getTimeInMillis();
            return t2 - t1;
        }
    }

    public static void main(String[] args) {
        System.out.println(isSendtime(System.currentTimeMillis()));
    }
}
