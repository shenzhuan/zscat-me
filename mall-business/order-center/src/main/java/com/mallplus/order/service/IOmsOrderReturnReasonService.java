package com.mallplus.order.service;

import com.mallplus.common.entity.oms.OmsOrderReturnReason;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 退货原因表 服务类
 * </p>
 *
 * @author zscat
 * @since 2019-04-17
 */
public interface IOmsOrderReturnReasonService extends IService<OmsOrderReturnReason> {
    /**
     * 批量修改退货原因状态
     */
    int updateStatus(List<Long> ids, Integer status);
}
