package com.mallplus.marking.service;

import com.mallplus.common.entity.sms.SmsRedPacket;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 红包 服务类
 * </p>
 *
 * @author zscat
 * @since 2019-04-19
 */
public interface ISmsRedPacketService extends IService<SmsRedPacket> {
    int acceptRedPacket(Integer id,Long userId);

    int createRedPacket(SmsRedPacket redPacket);
}
