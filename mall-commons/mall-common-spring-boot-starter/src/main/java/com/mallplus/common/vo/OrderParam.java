package com.mallplus.common.vo;

import lombok.Data;

/**
 * 生成订单时传入的参数
 * https://github.com/shenzhuan/mallplus on 2018/8/30.
 */
@Data
public class OrderParam {
    String page;
    String formId;
    private Integer total;
    String platform = "2";
    //收货地址id
    private Long addressId;
    //优惠券id
    private Long couponId;
    private Long  memberCouponId;
    private Long memberId;
    //使用的积分数
    private Integer useIntegration;
    //支付方式
    private Integer payType =1;
    private Integer offline;// 0 送货 1 自取
    private String content;
    private String cartId;
    private String cartIds;
    private String type; // 1 商品详情 2 勾选购物车 3全部购物车的商品
    private Integer source =1; ////订单来源：0->PC订单；5->app订单 2 h5 3微信小程序 4 支付宝小程序
    private Integer orderType=1; // 1 普通订单 2 秒杀订单 3 团购订单 4 拼团订单 5 积分订单
    private Long skuId;
    private Long goodsId;
    private Long groupId;
    private Long groupActivityId;
    // 1 发起拼团 2 参与拼团
    private Integer groupType;
    private Long mgId =0l;
    private Long skillId =0l; // 秒杀ID
    String basicGiftsVar;
    Long orderId;
    String memberIcon;
}
