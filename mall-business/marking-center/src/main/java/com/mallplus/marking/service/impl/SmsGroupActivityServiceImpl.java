package com.mallplus.marking.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.mallplus.common.entity.sms.SmsGroupActivity;
import com.mallplus.marking.mapper.SmsGroupActivityMapper;
import com.mallplus.marking.service.ISmsGroupActivityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zscat
 * @since 2019-10-12
 */
@Service
public class SmsGroupActivityServiceImpl extends ServiceImpl<SmsGroupActivityMapper, SmsGroupActivity> implements ISmsGroupActivityService {

    @Resource
    SmsGroupActivityMapper activityMapper;

    @Override
    public int updateShowStatus(Long ids, Integer status) {
        SmsGroupActivity record = new SmsGroupActivity();
        record.setStatus(status);
        return activityMapper.update(record, new QueryWrapper<SmsGroupActivity>().eq("id", ids));
    }
}
