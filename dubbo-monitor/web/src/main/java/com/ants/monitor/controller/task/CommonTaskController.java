package com.ants.monitor.controller.task;

import com.ants.monitor.common.tools.TimeUtil;
import com.ants.monitor.dao.mapper.InvokeDOMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Created by zxg on 16/3/24.
 * 19:50
 * no bug,以后改代码的哥们，祝你好运~！！
 * 普通的任务
 */
@RestController
@RequestMapping("/monitor/invokeReportTask")
@Slf4j
public class CommonTaskController {


    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;
    @Autowired
    private InvokeDOMapper invokeDOMapper;

    //每天凌晨 00：30分执行
    @Scheduled(cron = "0 30 0 * * ?")
    public void everyDayDo() {

        //每天更新
        AppConsumerOnDayProcess appConsumerOnDayProcess = new AppConsumerOnDayProcess();
        taskExecutor.execute(appConsumerOnDayProcess);
    }


    //每天
    private class AppConsumerOnDayProcess implements Runnable {
        @Override
        public void run() {
            try {
                //每天删除 大于15天的日期的原始数据
                String minDate = TimeUtil.getBeforDateByNumber(new Date(), -15);
                invokeDOMapper.deleteByDate(minDate);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
