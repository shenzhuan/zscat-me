package com.ants.monitor.controller.show;

import com.ants.monitor.bean.ResultVO;
import com.ants.monitor.bean.bizBean.HostBO;
import com.ants.monitor.biz.support.service.HostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

/**
 * Created by zxg on 15/12/3.
 * 16:39
 */
@Controller
@RequestMapping("/monitor/hosts")
public class HostController {

    @Autowired
    private HostService hostService;

    //主页
    @RequestMapping(value = "main")
    public ModelAndView main() {
        return new ModelAndView("monitorView/host/hostIndex");
    }

    @RequestMapping(value = "/getAllHostPage",method = RequestMethod.GET)
    public
    @ResponseBody
    ResultVO getAllHostPage() {
        try {
            Map<String,Object> map = new HashMap<>();

            Map<String,HostBO> hostMap = hostService.getHostBOMap();

            List<String> hostList = new ArrayList<>(hostMap.keySet());
            Collections.sort(hostList);


            map.put("sum", hostList.size());
            map.put("hostMap", hostMap);
            map.put("hostList", hostList);
            return ResultVO.wrapSuccessfulResult(map);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVO.wrapErrorResult(e.getMessage());
        }
    }

}
