package com.mallplus.common.entity.sms;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import com.mallplus.common.entity.BaseEntity;
import com.mallplus.common.vo.BasicRuls;
import com.mallplus.common.vo.BeanKv;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 赠品营销
 * </p>
 *
 * @author zscat
 * @since 2019-07-07
 */
@Data
@TableName("sms_basic_gifts")
public class SmsBasicGifts extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String name;

    /**
     * 1 有效0 无效
     */
    private Integer status;

    /**
     * 活动对象1全部用户2 会员级别
     */
    @TableField("activi_user")
    private Integer activiUser;

    /**
     * 活动商品
     */
    @TableField("activi_goods")
    private Integer activiGoods;

    /**
     * 1 首购礼 2 满 购礼 3 单品礼赠
     */
    @TableField("big_type")
    private Integer bigType;

    /**
     * 首购礼 1第一单获取 2所有订单获取 ；
     * 满购礼1选赠礼 获取规则 2满赠礼；
     * 单品礼赠 1 仅送一件  2 按购买件数送  3 指定件数送
     *
     */
    @TableField("small_type")
    private Integer smallType;

    /**
     * 规则
     */
    private String rules;

    /**
     * 部分商品列表
     */
    @TableField("goods_ids")
    private String goodsIds;

    /**
     * 会员级别
     */
    @TableField("user_level")
    private String userLevel;

    @TableField("start_time")
    private Date startTime;

    @TableField("end_time")
    private Date endTime;

    @TableField("create_time")
    private Date createTime;
    private String note;
    /**
     * 赠品
     */
    @TableField("gift_ids")
    private String giftIds;

    /**
     * 消费金额
     */
    @TableField(exist = false)
    private List<BasicRuls> actrule;
    /**
     * 购买件数
     */
    @TableField(exist = false)
    private List<BasicRuls> actrule1;
    @TableField(exist = false)
    private List<BeanKv> productCategoryRelationList;
    @TableField(exist = false)
    private List<BeanKv> productRelationList;

    @TableField(exist = false)
    private List<BeanKv> memberLevelList;
    @TableField(exist = false)
    private List<BeanKv> giftsList;

    @TableField(exist = false)
    private BasicRuls rule;
}
