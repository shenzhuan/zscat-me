package com.mallplus.order.vo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * @Auther: shenzhuan
 * @Date: 2019/3/29 17:07
 * @Description:
 */
@Data
public class GroupAndOrderVo  {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("group_id")
    private Long groupId;

    @TableField("member_id")
    private Long memberId;

    @TableField("create_time")
    private Date createTime;

    @TableField("main_id")
    private Long mainId;

    private String name;

    @TableField("goods_id")
    private Long goodsId;

    /**
     * 状态
     */
    private Integer status;

    @TableField("order_id")
    private Long orderId;


    //收货地址id
    private Long addressId;
    //支付方式
    private Integer payType = 1; // 1 微信 2 支付宝

    private  Integer sourceType =1; // 1 小程序  2 h5  3 pc  4 android  5 ios

    private Integer orderType = 1; // 1 普通订单 2拼团订单 3秒杀订单

    private String page;
    private String formId;

    private String wxid;

    private String username;

    String platform = "2";

    //优惠券id
    private Long couponId;
    //使用的积分数
    private Integer useIntegration;

    private Integer offline;// 0 送货 1 自取
    private String content;
    private String cartId;
    private String cartIds;
    private String type; // 1 商品详情 2 勾选购物车 3全部购物车的商品
}
