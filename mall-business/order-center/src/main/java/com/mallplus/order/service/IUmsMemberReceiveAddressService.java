package com.mallplus.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mallplus.common.entity.oms.UmsMemberReceiveAddress;


/**
 * <p>
 * 会员收货地址表 服务类
 * </p>
 *
 * @author zscat
 * @since 2019-04-19
 */
public interface IUmsMemberReceiveAddressService extends IService<UmsMemberReceiveAddress> {

    UmsMemberReceiveAddress getDefaultItem(Long userId);

    int setDefault(Long id,Long userId);
}
