package com.ants.monitor.biz.bussiness;

import com.ants.monitor.bean.bizBean.HostBO;
import com.ants.monitor.bean.bizBean.MethodRankBO;
import com.ants.monitor.bean.entity.InvokeDO;
import com.ants.monitor.biz.support.service.HostService;
import com.ants.monitor.common.redis.RedisClientTemplate;
import com.ants.monitor.common.redis.RedisKeyBean;
import com.ants.monitor.common.tools.JsonUtil;
import com.ants.monitor.dao.mapper.InvokeDOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by zxg on 16/7/4.
 * 14:28
 * no bug,以后改代码的哥们，祝你好运~！！
 */
@Service
public class InvokeBizImpl implements InvokeBiz {

    @Autowired
    private HostService hostService;
    @Autowired
    private RedisClientTemplate redisClientTemplate;
    @Resource(name="invokeDOMapper")
    private InvokeDOMapper invokeDOMapper;

    //排行榜展示最大的数量
    private static final Integer maxRankNumber = 50;

    @Override
    public List<MethodRankBO> getMethodRankByAppName(String appName) {
        List<MethodRankBO> resultList = new ArrayList<>();
        if(StringUtils.isEmpty(appName)){
            return  resultList;
        }

        String redisKey = String.format(RedisKeyBean.invokeMethodRankKey, appName);
        // 从redis中取
        String redisResultString = redisClientTemplate.get(redisKey);
//        String redisResultString = null;
        if(redisResultString != null && redisClientTemplate.isNone(redisResultString)){
            //缓存里判定之前查找为空，因此此次不走数据库，直接空
            return resultList;
        }
        if(redisResultString != null){
            //返回redis 缓存结果集
            return JsonUtil.jsonStrToList(redisResultString,MethodRankBO.class);
        }
        //redis 中无数据，进行数据库操作
        resultList = findFromDataBase(appName);
        //缓存一份到数据库
        if(resultList.isEmpty()){
            redisClientTemplate.setNone(redisKey);
        }else{
            redisClientTemplate.lazySet(redisKey,resultList,RedisKeyBean.RREDIS_EXP_HOURS*23);
        }


        return resultList;
    }



    /*=============private=============*/
    private List<MethodRankBO> findFromDataBase(String appName){
        List<MethodRankBO> resultList = new ArrayList<>();
        Set<HostBO> hostBOSet = hostService.getHostPortByAppName(appName);
        if(hostBOSet.isEmpty()){
            return  resultList;
        }

        List<InvokeDO> invokeDOList = new ArrayList<>();
        for(HostBO hostBO : hostBOSet){
            //数据库拿出所有的数据，叠加到list
            String host = hostBO.getHost();
            String port = hostBO.getPort();

            InvokeDO searchDO = new InvokeDO();
            searchDO.setProviderHost(host);
            searchDO.setProviderPort(port);

            invokeDOList.addAll(invokeDOMapper.selectByInvokeDO(searchDO));
        }
        if(invokeDOList.isEmpty()){
            return resultList;
        }
        // 存在数据
        Map<MethodRankBO,Integer> rankMap = new HashMap<>();
        for(InvokeDO invokeDO : invokeDOList){
            String serviceName = invokeDO.getService();
            String methodName = invokeDO.getMethod();
            Integer usedNum = invokeDO.getSuccess();

            MethodRankBO rankBO = new MethodRankBO();
            rankBO.setServiceName(serviceName);
            rankBO.setMethodName(methodName);
            Integer nowNum = rankMap.get(rankBO);
            if(nowNum == null) nowNum = 0;
            nowNum += usedNum;
            rankMap.put(rankBO,nowNum);
        }
        //排序,从大到小
        List<Map.Entry<MethodRankBO,Integer>> sortedList = new ArrayList<>(rankMap.entrySet());
        Collections.sort(sortedList, new Comparator<Map.Entry<MethodRankBO, Integer>>() {
            @Override
            public int compare(Map.Entry<MethodRankBO, Integer> o1, Map.Entry<MethodRankBO, Integer> o2) {
                Integer result = o2.getValue() - o1.getValue();
                return result;
            }
        });
        int sortedListSize = sortedList.size();
        for(int i=0;i<sortedListSize;i++){
            Map.Entry<MethodRankBO, Integer> entry = sortedList.get(i);
            MethodRankBO rankBO =entry.getKey();
            rankBO.setUsedNum(entry.getValue());
            resultList.add(rankBO);
            if(resultList.size() > maxRankNumber-1){
                break;
            }
        }
        return resultList;
    }


}
