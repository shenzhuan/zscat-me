package com.zscat.marketing.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zscat.base.ServiceMybatis;
import com.zscat.marketing.config.BaseConfig;
import com.zscat.marketing.constant.RedisKey;
import com.zscat.marketing.mapper.PromotionUserMapper;
import com.zscat.marketing.mapper.WithdrawMapper;
import com.zscat.marketing.model.PromotionUser;
import com.zscat.marketing.model.Withdraw;
import com.zscat.marketing.service.IWithDrawService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author : zscat
 * @version : 1.0
 * @created on  : 2017/6/27  下午5:43
 */
@Service
public class WithDrawService extends ServiceMybatis<Withdraw> implements IWithDrawService {
    @Resource
    WithdrawMapper withdrawMapper;
    @Resource
    PromotionUserMapper promotionUserMapper;
    @Resource
    private BaseConfig baseConfig;
    @Override
    public PageInfo<Withdraw> selectPageByDate(Long uid,int pageNum, int pageSize, Date startDate,Date endDate, String orderStr) {
        Example example = new Example(Withdraw.class);
      //  example.createCriteria().andBetween("operationtime",startDate,endDate);
        example.createCriteria().andEqualTo("uid",uid);
        example.orderBy(orderStr);
     //   example.selectProperties("id", "operationTime", "hehe");
        PageHelper.startPage(pageNum, pageSize);
        List<Withdraw> list = withdrawMapper.selectByExample(example);
        return new PageInfo<Withdraw>(list);
    }

    @Override
    public void withdraw(Withdraw withdraw) {
        baseConfig.redisLink().hset(RedisKey.WITHDRARLIST,withdraw.getUid()+"","1");
        withdrawMapper.insert(withdraw);
        PromotionUser p =promotionUserMapper.selectByPrimaryKey(withdraw.getUid());
        p.setTotalIncome(p.getTotalIncome()-withdraw.getMoney());
        p.setUpdateTime(new Date());
        promotionUserMapper.updateByPrimaryKeySelective(p);

    }
}
