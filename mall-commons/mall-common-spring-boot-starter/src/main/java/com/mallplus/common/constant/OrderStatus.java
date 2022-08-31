package com.mallplus.common.constant;

/**
 * @Auther: Tiger
 * @Date: 2019-04-26 16:04
 * @Description:
 */
public enum OrderStatus {


    //   订单状态：12->待付款；2->待发货；3->已发货；4->已完成；5->售后订单 6->已关闭；
    INIT(12),//待付款
    PayNotNotice(1),//支付成功，没有回掉
    TO_DELIVER(2),//待发货
    DELIVERED(3),  // 待收货
    TO_COMMENT(4),//待评价
    TRADE_SUCCESS(5), // 已完成

    RIGHT_APPLY(6), // 维权中
    RIGHT_APPLYF_SUCCESS(7), // 维权已完成
    TO_SHARE(8),  // 待分享
    REFUNDING(13),  // 申请退款
    REFUND(14),  // 已退款
    //    CANCELED(7),
    CLOSED(15), // 已关闭 // 已取消 统一
    INVALID(16),//无效订单
    DELETED(17);//已删除



    private int value;

    private OrderStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

}
