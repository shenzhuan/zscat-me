package com.zscat.marketing.service;

import com.zscat.base.BaseService;
import com.zscat.marketing.model.Income;
import com.zscat.marketing.model.IncomeData;

import java.util.List;

/**
 * @author : zscat
 * @version : 1.0
 * @created on  : 2017/6/27  下午5:43
 */
public interface IincomeService extends BaseService<Income> {

    List<IncomeData> getIncomeDataList(Long uid, String starttime, String endtime);

}
