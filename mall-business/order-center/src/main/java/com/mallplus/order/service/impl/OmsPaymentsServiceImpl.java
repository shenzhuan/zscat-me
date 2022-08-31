package com.mallplus.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.mallplus.common.entity.oms.OmsPayments;
import com.mallplus.order.mapper.OmsPaymentsMapper;
import com.mallplus.order.service.IOmsPaymentsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author mallplus
 * @date 2019-12-07
 */
@Service
public class OmsPaymentsServiceImpl extends ServiceImpl<OmsPaymentsMapper, OmsPayments> implements IOmsPaymentsService {

    @Resource
    private OmsPaymentsMapper omsPaymentsMapper;


}
