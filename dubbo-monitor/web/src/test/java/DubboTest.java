import com.alibaba.dubbo.common.utils.ConcurrentHashSet;
import com.ants.monitor.common.tools.JsonUtil;
import com.ants.monitor.common.tools.TimeUtil;
import com.ants.monitor.common.tools.Tool;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by zxg on 15/11/3.
 */
public class DubboTest {

    @Test
    public void testTime() throws ParseException {
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        Long before = 1447064683860L;
        long after = before/60000*60000;

        java.util.Date dt = new Date(before);
        String beforeTime = sdf.format(dt);
        System.out.println(beforeTime);
        Long time = 1447064000904L;
        System.out.println(sdf.format(new Date(time)));
        Long min = (before - time)/(60*1000);

        System.out.println(min);

        Date dt2 = sdf.parse("2015/11/05 09:38:00");
        //继续转换得到ms的long型
        long lTime = dt2.getTime();
        System.out.println(lTime);

        double result = before/lTime;
        System.out.println(before);
        System.out.println(after);
        System.out.println(result);




        java.util.Date afterdt = new Date(after);
        String afterTime = sdf.format(afterdt);
        System.out.println(afterTime);

        long now = System.currentTimeMillis();
        Integer dayFormat = 1000*60*60;
        Date nowData =  new Date(now/dayFormat);
        System.out.println(sdf.format(nowData));
    }

    @Test
    public void testNUllString(){
        String a = null;

        String b = a+"1221";
        System.out.println(b);
    }
    @Test
    public void testNUllMapKey(){
        Map<String,String> map = new ConcurrentHashMap();

        String a = map.get("1");

        System.out.println(a);
    }

    @Test
    public void testunmodifiableMap(){
        Map<String, Map<String, Set<String>>> registryCache = new ConcurrentHashMap<>();
        Set<String> a = new ConcurrentHashSet<>();
        Set<String> b = new ConcurrentHashSet<>();
        a.add("12");
//        a = Collections.unmodifiableSet(a);

        a.add("34");
        b.add("aw");b.add("se");

        Map<String, Set<String>> amap = new ConcurrentHashMap<>();
        Map<String, Set<String>> bmap = new ConcurrentHashMap<>();
        amap.put("1",a);
//        amap = Collections.unmodifiableMap(amap);

        amap.put("2", b);
        bmap.put("2", b);

        registryCache.put("first",amap);
        registryCache = Collections.unmodifiableMap(registryCache);

        registryCache.put("second", bmap);
        System.out.println("1");


    }


    @Test
    public void testRecentDay(){
        Calendar calendar = Calendar.getInstance();
        Date date = new Date(System.currentTimeMillis());
        calendar.setTime(date);
//        calendar.add(Calendar.WEEK_OF_YEAR, -1);
        calendar.add(Calendar.DATE, -7);
        date = calendar.getTime();


        System.out.println(TimeUtil.getTimeString(date));
    }

    @Test
    public void testSwitch(){
        Integer a = 10;
        for(int i = 0 ;i<5;i++) {
            switch (a) {
                case 10:
                    System.out.println(a);
                    break;
                case 11:
                    System.out.println(a+"sada");
                    break;

            }
        }
    }

    @Test
    public void test(){
        List<String> list = new ArrayList<>();
        List<String> zerolist = new ArrayList<>();
        for(int i = 0;i<24;i++){
            for(int j = 0;j<60;j++){
                String a = "";
                if(i<10){
                    a += "0"+i;
                }else{
                    a += i;
                }
                a += ":";
                if(j<10){
                    a += "0"+j;
                }else{
                    a += j;
                }
                list.add(a);
                zerolist.add(String.valueOf(Math.random()*10));
            }
        }

        System.out.println(JsonUtil.objectToJsonStr(list));
        System.out.println(JsonUtil.objectToJsonStr(zerolist));
    }
}
