package com.zscat.marketing.service.impl;

import com.zscat.base.ServiceMybatis;
import com.zscat.marketing.mapper.IncomeMapper;
import com.zscat.marketing.model.Income;
import com.zscat.marketing.model.IncomeData;
import com.zscat.marketing.service.IincomeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : zscat
 * @version : 1.0
 * @created on  : 2017/6/27  下午5:43
 */
@Service
public class IncomeService extends ServiceMybatis<Income> implements IincomeService {

    @Resource
    private IncomeMapper incomeMapper;

    @Override
    public List<IncomeData> getIncomeDataList(Long uid, String starttime, String endtime) {
        List<IncomeData> list = new ArrayList<>();
        Map params = new HashMap();
        params.put("uid", uid);
        params.put("starttime", starttime);
        params.put("endtime", endtime);

        return incomeMapper.getIncomeDatas(params);
    }

}
