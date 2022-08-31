package com.mallplus.common.entity.pms;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import com.mallplus.common.entity.BaseEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 产品咨询表
 * </p>
 *
 * @author zscat
 * @since 2019-04-19
 */
@Data
@TableName("pms_product_consult")
public class PmsProductConsult extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 咨询编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 商品编号
     */
    @TableField("goods_id")
    private Long goodsId;
    /**
     * 商品编号
     */
    @TableField("order_id")
    private Long orderId;
    /**
     * 商品名称
     */
    @TableField("goods_name")
    private String goodsName;

    /**
     * 咨询发布者会员编号(0：游客)
     */
    @TableField("member_id")
    private Long memberId;

    /**
     * 会员名称
     */
    @TableField("member_name")
    private String memberName;
    private String pic;


    /**
     * 咨询发布者邮箱
     */
    private String email;

    /**
     * 咨询内容
     */
    @TableField("consult_content")
    private String consultContent;

    /**
     * 咨询添加时间
     */
    @TableField("consult_addtime")
    private Date consultAddtime;

    /**
     * 咨询回复内容
     */
    @TableField("consult_reply")
    private String consultReply;

    /**
     * 咨询回复时间
     */
    @TableField("consult_reply_time")
    private Date consultReplyTime;

    /**
     * 0表示不匿名 1表示匿名
     */
    private Boolean isanonymous;

    // 1 差评 2 一般  3 好评
    private Integer stars;
    // 1 商品  2 订单
    private Integer type;


}
