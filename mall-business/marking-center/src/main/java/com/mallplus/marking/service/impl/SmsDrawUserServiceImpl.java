package com.mallplus.marking.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mallplus.common.entity.sms.SmsDrawUser;
import com.mallplus.marking.mapper.SmsDrawUserMapper;
import com.mallplus.marking.service.ISmsDrawUserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 抽奖与用户关联表 服务实现类
 * </p>
 *
 * @author zscat
 * @since 2019-10-17
 */
@Service
public class SmsDrawUserServiceImpl extends ServiceImpl<SmsDrawUserMapper, SmsDrawUser> implements ISmsDrawUserService {

}
