package com.mallplus.order.vo;


import com.mallplus.common.entity.oms.OmsCartItem;
import com.mallplus.common.entity.oms.UmsMemberReceiveAddress;
import com.mallplus.common.entity.pms.PmsProduct;
import com.mallplus.common.entity.sms.SmsBasicGifts;
import com.mallplus.common.entity.sms.SmsGroupActivity;
import com.mallplus.common.entity.ums.UmsIntegrationConsumeSetting;
import com.mallplus.common.vo.CartPromotionItem;
import com.mallplus.common.vo.SmsCouponHistoryDetail;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 确认单信息封装
 * https://github.com/shenzhuan/mallplus on 2018/8/30.
 */
@Data
public class ConfirmOrderResult {


    private UmsMemberReceiveAddress address;
    //包含优惠信息的购物车信息
    private List<OmsCartItem> cartPromotionItemList;
    //用户收货地址列表
    private List<UmsMemberReceiveAddress> memberReceiveAddressList;
    //用户可用优惠券列表
    private List<SmsCouponHistoryDetail> couponHistoryDetailList;
    //积分使用规则
    private UmsIntegrationConsumeSetting integrationConsumeSetting;
    //会员持有的积分
    private Integer memberIntegration;
    //计算的金额
    private CalcAmount calcAmount;
    private PmsProduct goods;
    private GroupAndOrderVo groupAndOrderVo;
    // 赠品营销
    List<SmsBasicGifts> basicGiftsList;

    SmsGroupActivity groupActivity;

    public static class CalcAmount {
        //订单商品总金额
        private BigDecimal totalAmount;
        //运费
        private BigDecimal freightAmount;
        //活动优惠
        private BigDecimal promotionAmount;
        //应付金额
        private BigDecimal payAmount;

        public BigDecimal getTotalAmount() {
            return totalAmount;
        }

        public void setTotalAmount(BigDecimal totalAmount) {
            this.totalAmount = totalAmount;
        }

        public BigDecimal getFreightAmount() {
            return freightAmount;
        }

        public void setFreightAmount(BigDecimal freightAmount) {
            this.freightAmount = freightAmount;
        }

        public BigDecimal getPromotionAmount() {
            return promotionAmount;
        }

        public void setPromotionAmount(BigDecimal promotionAmount) {
            this.promotionAmount = promotionAmount;
        }

        public BigDecimal getPayAmount() {
            return payAmount;
        }

        public void setPayAmount(BigDecimal payAmount) {
            this.payAmount = payAmount;
        }

    }
}
