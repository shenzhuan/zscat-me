package com.ants.monitor.controller.show;

import com.alibaba.dubbo.common.Constants;
import com.ants.monitor.bean.MonitorConstants;
import com.ants.monitor.bean.ResultVO;
import com.ants.monitor.bean.bizBean.HostBO;
import com.ants.monitor.bean.bizBean.ServiceBO;
import com.ants.monitor.bean.entity.InvokeDO;
import com.ants.monitor.dao.redisManager.InvokeRedisManager;
import com.ants.monitor.biz.support.service.HostService;
import com.ants.monitor.biz.support.service.ServicesService;
import com.ants.monitor.common.tools.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

/**
 * Created by zxg on 15/11/19.
 */
@Controller
@RequestMapping("/monitor/services")
public class ServicesController {
    @Autowired
    private ServicesService servicesService;
    @Autowired
    private InvokeRedisManager invokeRedisManager;
    @Autowired
    private HostService hostService;

    //主页
    @RequestMapping(value = "main")
    public ModelAndView main() {
        return new ModelAndView("monitorView/services/servicesIndex");
    }

    //所有service
    @RequestMapping(value = "/getAllService", method = RequestMethod.GET)
    public
    @ResponseBody
    ResultVO getAllService() {
        try {
            Map<String, Object> resultMap = new HashMap<>();

            Map<String, ServiceBO> allServicesMap = servicesService.getServiceBOMap();
            List<String> wrongMethodsList = new ArrayList<>();
            List<String> wrongAppList = new ArrayList<>();
            List<String> wrongHostServiceList = new ArrayList<>();

            for (Map.Entry<String, ServiceBO> serviceBOEntry : allServicesMap.entrySet()) {
                String serviceName = serviceBOEntry.getKey();
                ServiceBO serviceBO = serviceBOEntry.getValue();

                Set<String> ownerSet = serviceBO.getOwnerApp();
                Set<String> methodSet = serviceBO.getMethods();
                if(null != ownerSet && ownerSet.size() > 1){
                    wrongAppList.add(serviceName);
                }
                if(null != methodSet && methodSet.size() > 1){
                    wrongMethodsList.add(serviceName);
                }
                if(serviceBO.getIsHostWrong()){
                    wrongHostServiceList.add(serviceName);
                }

            }

            //测试环境url
            Set<String> testUrlSet = new HashSet<>();
            for(Map.Entry<String,String> entry : MonitorConstants.ecsTestMap.entrySet()){
                testUrlSet.add(entry.getKey());
                testUrlSet.add(entry.getValue());
            }

            resultMap.put("wrongAppList", wrongAppList);
            resultMap.put("wrongMethodsList", wrongMethodsList);
            resultMap.put("wrongHostServiceList", wrongHostServiceList);
            resultMap.put("allServicesMap", allServicesMap);
            resultMap.put("testUrlSet", testUrlSet);

            return ResultVO.wrapSuccessfulResult(resultMap);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVO.wrapErrorResult(e.getMessage());
        }

    }

    //获得此service下的 方法 当前时间的调用量
    @RequestMapping(value = "/getMethodSumOneDay", method = RequestMethod.GET)
    public
    @ResponseBody
    ResultVO getMethodSumOneDay(String serviceName,String methodName ,String type){
        List<String> recentDateList = getRecentDay(type);
        //判断 host 是否有这个 service
        List<String> haveList = new ArrayList<>();
        List<String> notHaveList = new ArrayList<>();

        Map<String,Object> resultMap = new HashMap<>();
        //消费的app
        Set<String> appList = new HashSet<>();

//        {hour:{success:xxx,elapsed:xxx}}
        Map<String,Object> dataMap = new HashMap<>();
        for(String date : recentDateList) {
            List<InvokeDO> methodList = invokeRedisManager.getInvokeByMethodDay(serviceName.split(":")[0],methodName,date);
            for(InvokeDO invokeDO : methodList){
                String providerHost = invokeDO.getProviderHost();
                String providerPort = invokeDO.getProviderPort();
                String providerKey = providerHost + "-" + providerPort;

                if(notHaveList.contains(providerKey)){
                    // 非此service
                    continue;
                }
                if(!haveList.contains(providerKey)) {
                    Set<String> serviceSet = hostService.getServiceByHost(new HostBO(providerHost, providerPort));
                    if (!serviceSet.contains(serviceName)) {
                        // 非此service
                        notHaveList.add(providerKey);
                        continue;
                    }
                    haveList.add(providerKey);
                }

                appList.add(invokeDO.getApplication());
                Integer successNum = invokeDO.getSuccess();
                Integer elapsedNum = invokeDO.getElapsed();

                String hourTime  = invokeDO.getInvokeHour();

                Map<String,Integer> hourMap = (Map<String, Integer>) dataMap.get(hourTime);
                if(hourMap == null){
                    hourMap = new HashMap<>();
                    dataMap.put(hourTime,hourMap);
                }
                Integer oldSuccessNum = hourMap.get("success") == null?0:hourMap.get("success");
                Integer oldElapsedNum = hourMap.get("elapsed") == null?0:hourMap.get("elapsed");

                hourMap.put("success",oldSuccessNum+successNum);
                hourMap.put("elapsed",oldElapsedNum+elapsedNum);
            }

        }

        resultMap.put("dataMap",dataMap);
        resultMap.put("appList",appList);

        return ResultVO.wrapSuccessfulResult(resultMap);
    }



    // ========private
    //=======private======
    private List<String> getRecentDay(String type) {
        Integer limit = 0;
        Date date = new Date(System.currentTimeMillis());

        List<String> recentDateList = new ArrayList<>();

        if (type.equals("Today")) {
            String nowDate = TimeUtil.getDateString(date);
            recentDateList.add(nowDate);
        } else if (type.equals("Month")) {
            String nowDate = TimeUtil.getDateString(date);
            Date firstDate = TimeUtil.getMinMonthDate(nowDate);
            String firstDateString = TimeUtil.getDateString(firstDate);
            while (!firstDateString.equals(nowDate)) {
                recentDateList.add(firstDateString);
                limit++;
                firstDateString = TimeUtil.getBeforDateByNumber(firstDate, limit);
            }
        } else {
            if (type.equals("Seven_DAY")) {
                limit = 7;
            } else if (type.equals("Fifteen_DAT")) {
                limit = 15;
            }else if (type.equals("Yesterday")) {
                limit = 1;
            }
            for (Integer amount = -limit; amount < 0; amount++) {
                recentDateList.add(TimeUtil.getBeforDateByNumber(date, amount));
            }
        }
        return recentDateList;
    }
}
