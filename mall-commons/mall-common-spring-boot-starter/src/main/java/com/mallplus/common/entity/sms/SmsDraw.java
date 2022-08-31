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
 * @since 2019-10-17
 */
@Data
@TableName("sms_draw")
public class SmsDraw implements Serializable {

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
     * 商品名称
     */
    @TableField("goods_name")
    private String goodsName;
    /**
     * 该抽奖所参与商品ID
     */
    @TableField("good_id")
    private Long goodId;

    /**
     * 创建时间
     */
    @TableField("found_time")
    private Date foundTime;

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
     * 每个团所需人数
     */
    private Integer num;

    /**
     * 可抽中奖次数（默认为1）
     */
    @TableField("spelling_number")
    private Integer spellingNumber;

    /**
     * 最少开奖团数（默认为1）
     */
    @TableField("collage_number")
    private Integer collageNumber;

    /**
     * 该团的状态（默认为0：未开始 ，1：进行中 ， 2：已结束）
     */
    private Integer state;

    /**
     * 抽奖金额
     */
    private BigDecimal price;

    /**
     * 次数
     */
    private Integer cishu;

    /**
     * 备注
     */
    private String type;


}
