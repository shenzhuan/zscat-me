package com.mallplus.marking.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mallplus.common.entity.sms.SmsSignRecord;
import com.mallplus.marking.mapper.SmsSignRecordMapper;
import com.mallplus.marking.service.ISmsSignRecordService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 签到记录 服务实现类
 * </p>
 *
 * @author zscat
 * @since 2019-10-17
 */
@Service
public class SmsSignRecordServiceImpl extends ServiceImpl<SmsSignRecordMapper, SmsSignRecord> implements ISmsSignRecordService {

}
