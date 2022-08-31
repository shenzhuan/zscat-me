package com.mallplus.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mallplus.common.entity.oms.OmsOrder;
import com.mallplus.common.utils.CommonResult;
import com.mallplus.common.vo.CartParam;
import com.mallplus.common.vo.OrderParam;
import com.mallplus.order.vo.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 订单表 服务类
 * </p>
 *
 * @author zscat
 * @since 2019-04-17
 */
public interface IOmsOrderService extends IService<OmsOrder> {
    /**
     * 修改订单收货人信息
     */
    @Transactional
    int updateReceiverInfo(OmsReceiverInfoParam receiverInfoParam);

    /**
     * 修改订单费用信息
     */
    @Transactional
    int updateMoneyInfo(OmsMoneyInfoParam moneyInfoParam);

    /**
     * 修改订单备注
     */
    @Transactional
    int updateNote(Long id, String note, Integer status);


    /**
     * 批量发货
     */
    @Transactional
    int delivery(List<OmsOrderDeliveryParam> deliveryParamList);

    /**
     * 批量关闭订单
     */
    @Transactional
    int close(List<Long> ids, String note);


    Object addCart(CartParam cartParam);


    ConfirmOrderResult addGroup(OrderParam orderParam);

    Object acceptGroup(OrderParam orderParam);

    Object jifenPay(OrderParam payParam);

    boolean closeOrder(OmsOrder newE);
    void releaseStock(OmsOrder newE);

    /**
     * 根据提交信息生成订单
     */
    @Transactional
    CommonResult generateOrder(OrderParam orderParam);

    ConfirmOrderResult submitPreview(OrderParam orderParam);
    /**
     * 确认收货
     * @param id
     * @return
     */
    Object confimDelivery(Long id);

    /**
     * 余额支付
     * @param order
     * @return
     */
    Object blancePay(OmsOrder order);

    /**
     * 团购商品订单预览
     * @param orderParam
     * @return
     */
    Object preGroupActivityOrder(OrderParam orderParam);

    /**
     * 申请退款
     * @param id
     * @return
     */
    Object applyRefund(Long id);

    Object orderComment(Long orderId, String items);
}
