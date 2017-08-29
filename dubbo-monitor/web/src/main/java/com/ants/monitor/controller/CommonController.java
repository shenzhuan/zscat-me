package com.ants.monitor.controller;

import com.ants.monitor.bean.ResultVO;
import com.ants.monitor.biz.dubboService.RegistryContainer;
import com.ants.monitor.common.tools.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * Created by zxg on 15/11/11.
 */
@Controller
@RequestMapping("/monitor/common")
public class CommonController {
    @Autowired
    private RegistryContainer registryContainer;

    //测试
    @RequestMapping(value = "/getFinalTime", method = RequestMethod.GET)
    public
    @ResponseBody
    ResultVO getFinalTime(){
        Date finalTime = registryContainer.getFinalUpdateTime();
        String timeString = TimeUtil.getTimeString(finalTime);

        return ResultVO.wrapSuccessfulResult(timeString);
    }
}
