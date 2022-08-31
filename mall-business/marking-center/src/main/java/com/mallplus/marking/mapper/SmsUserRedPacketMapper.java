package com.mallplus.marking.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mallplus.common.entity.sms.SmsUserRedPacket;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 用户红包 Mapper 接口
 * </p>
 *
 * @author zscat
 * @since 2019-04-19
 */
public interface SmsUserRedPacketMapper extends BaseMapper<SmsUserRedPacket> {

    int countOne(@Param("redPacketId") Integer redPacketId, @Param("userId") Long userId);

    SmsUserRedPacket listUserRedOne(Integer id);
}
