package com.ants.monitor.controller.task;

import com.ants.monitor.bean.MonitorConstants;
import com.ants.monitor.bean.bizBean.ApplicationChangeBO;
import com.ants.monitor.common.tools.TimeUtil;
import com.ants.monitor.dao.redisManager.AppStopRedisManager;
import com.ants.monitor.dao.redisManager.ApplicationBaseRedisManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zxg on 16/1/8.
 * 17:58
 * 查找规定时间未重启的应用，若未重启，则通知业务方
 */
@RestController
@RequestMapping("/monitor/appStopTask")
@Slf4j
public class AppStopTaskController {
    // 是否是线上
    @Value(value = "${is_online}")
    private String is_online;
    // 超时时间
    @Value(value = "${out_time}")
    private String outTime;

    @Autowired
    private AppStopRedisManager appStopRedisManager;
    @Autowired
    private ApplicationBaseRedisManager applicationBaseRedisManager;

    //每分钟触发一次检查,若超时还未连接，则做发短信、邮件等操作。
    @Scheduled(cron = "0 * * * * ?")
    public void smsStopApp(){
        Boolean online = Boolean.parseBoolean(is_online);
        if(online) {
            Date now = new Date();


            for (Map.Entry<ApplicationChangeBO, String> applicationChangeBOStringEntry : appStopRedisManager.getAllStopApp().entrySet()) {
                String value = applicationChangeBOStringEntry.getValue();
                String[] valueArray = value.split(",");
                String oldTime = valueArray[0];
                Integer num = Integer.valueOf(valueArray[1]);

                Date stopDate = TimeUtil.getDateByTimeString(oldTime);
                if(null != stopDate) {
                    Long diff = now.getTime() - stopDate.getTime();

                    Integer addNum = num*(10*60*1000);
                    Long out = Long.parseLong(outTime)+Long.parseLong(addNum.toString());
                    if (diff >= out) {
                        senStopMessageTo(applicationChangeBOStringEntry.getKey(),diff,oldTime,num);
                    }
                }

            }
        }
    }

    //发送警告短信给业务负责人,若非线上配置的host，则不会发送
    private void senStopMessageTo(ApplicationChangeBO applicationChangeBO,Long diffTime,String stopTime,Integer nowNum){
        // {$shop}{$day}单日发送会员短信超过{$limit}条！
        /*!!警告：服务于{$time}停止服务,它已停止{$stopTime}分钟了，(应用名：{$appName}，host地址：{$host}， 服务器：{$server})
        请及时排错重启，以免影响线上服务的使用。*/

        String appName = applicationChangeBO.getAppName();
        String mobile = applicationBaseRedisManager.getPhoneByAppName(appName);
        if(mobile == null){
            return;
        }

        Map<String, Object> map = new HashMap<>();
        map.put("time", stopTime);
        map.put("stopTime", diffTime/(1000*60));
        map.put("appName", appName);
        map.put("host", applicationChangeBO.getHostString());

        String host = applicationChangeBO.getHost();
        if(MonitorConstants.ecsMap.containsKey(host)){
            map.put("server", MonitorConstants.ecsMap.get(host));

            try {
//                smsServiceHelp.sendMsg(mobile, MonitorConstants.SMS_STOP_ACTION, map);
                //todo 发短信或者发邮件等任意提醒操作
                log.info("发短信给{},内容：{}", mobile, applicationChangeBO.toString());

                nowNum +=1;
                applicationChangeBO.setTime(stopTime);
                appStopRedisManager.saveStopApp(applicationChangeBO,nowNum);
            } catch (Exception e) {
                e.printStackTrace();
                log.error("send message to phone "+mobile+" wrong",e);
            }

        }
    }
}