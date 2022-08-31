package com.mallplus.common.entity.sms;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 一分钱抽奖
 * </p>
 *
 * @author zscat
 * @since 2019-10-19
 */
@Data
@TableName("sms_paimai")
public class SmsPaimai implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 活动名称
     */
    private String name;

    /**
     * 该抽奖所参与商品ID
     */
    @TableField("good_id")
    private Long goodId;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 开始时间
     */
    @TableField("start_time")
    private Date startTime;

    /**
     * 结束时间
     */
    @TableField("end_time")
    private Date endTime;

    /**
     * 该团的状态（默认为0：未开始 ，1：进行中 ， 2：已结束）
     */
    private Integer state;

    /**
     * 起拍价
     */
    private BigDecimal price;

    /**
     * 加价的倍数
     */
    private BigDecimal addprice;
    /**
     * 保证金
     */
    private BigDecimal proprice;

    @TableField("store_id")
    private Integer storeId;

    @TableField("goods_name")
    private String goodsName;

    private Integer status;


}
