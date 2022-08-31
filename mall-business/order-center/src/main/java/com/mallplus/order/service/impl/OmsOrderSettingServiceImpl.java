package com.mallplus.order.service.impl;

import com.mallplus.common.entity.oms.OmsOrderSetting;
import com.mallplus.order.mapper.OmsOrderSettingMapper;
import com.mallplus.order.service.IOmsOrderSettingService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单设置表 服务实现类
 * </p>
 *
 * @author zscat
 * @since 2019-04-17
 */
@Service
public class OmsOrderSettingServiceImpl extends ServiceImpl<OmsOrderSettingMapper, OmsOrderSetting> implements IOmsOrderSettingService {

}
