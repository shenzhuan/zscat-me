package com.mallplus.common.vo;



import com.mallplus.common.entity.oms.OmsCartItem;
import com.mallplus.common.entity.oms.OmsOrderItem;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @Auther: shenzhuan
 * @Date: 2019/6/1 13:54
 * @Description:
 */
@Data
public class CartMarkingVo implements Serializable{
    private List<OmsCartItem> cartList ;
    private List<OmsOrderItem> shopOrderGoodsList ;
    /**
     * 首购礼 1第一单获取 2所有订单获取 ；
     * 满购礼1选赠礼 获取规则 2满赠礼；
     * 单品礼赠 1 仅送一件  2 按购买件数送  3 指定件数送
     */
    private int type ;
    /**
     * 新人券 1首次进入 2首次下单 3 首次支付
     * 满额发券 1 订单完成 2 支付完成
     * g购物发券  1 订单完成 2 支付完成
     * 手工改发券  1 订单完成 2 支付完成
     */
    private int scope ;
    private Long  ruleId ;
    private String  ruleIds ;
    private Long  memberId ;
    private String  code ;
    private String  marketingId ;
    private Long  memberCouponId;
    private BigDecimal payAmount;
    private String  openId ;
}
