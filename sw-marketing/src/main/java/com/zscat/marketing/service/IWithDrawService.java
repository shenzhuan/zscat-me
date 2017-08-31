package com.zscat.marketing.service;

import com.github.pagehelper.PageInfo;
import com.zscat.base.BaseService;
import com.zscat.marketing.model.Withdraw;

import java.util.Date;

/**
 * @author : zscat
 * @version : 1.0
 * @created on  : 2017/6/27  下午5:43
 */
public interface IWithDrawService extends BaseService<Withdraw> {
     PageInfo<Withdraw> selectPageByDate(Long  uid,int pageNum, int pageSize, Date startDate, Date endDate, String orderStr);

    void withdraw(Withdraw withdraw);
}
