package com.ants.monitor.controller.task;

import com.ants.monitor.bean.ResultVO;
import com.ants.monitor.common.tools.TimeUtil;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Created by zxg on 15/11/18.
 */

@RestController
@RequestMapping("/monitor/testTask")
public class TestTaskController {

//    @Scheduled(cron = "*/5 * * * * ?")
    @RequestMapping(value = "/testA", method = RequestMethod.GET)
    public void test(){
        String result = TimeUtil.getTimeString(new Date());
        System.out.println(result);
    }
}
