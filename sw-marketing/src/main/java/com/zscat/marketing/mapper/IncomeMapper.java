package com.zscat.marketing.mapper;

import com.zscat.base.MyMapper;
import com.zscat.marketing.model.Income;
import com.zscat.marketing.model.IncomeData;

import java.util.List;
import java.util.Map;

public interface IncomeMapper extends MyMapper<Income> {

    public List<Income> getIncomeDetails(Long uid, String startTime, String endTime);

    public List<IncomeData> getIncomeDatas(Map params);

}