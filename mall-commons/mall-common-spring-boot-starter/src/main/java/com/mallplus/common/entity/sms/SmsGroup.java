package com.mallplus.common.entity.sms;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mallplus.common.entity.BaseEntity;
import com.mallplus.common.entity.pms.PmsProduct;
import com.mallplus.common.vo.SamplePmsProduct;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author zscat
 * @since 2019-04-19
 */
@Data
@TableName("sms_group")
public class SmsGroup extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("goods_id")
    private Long goodsId;

    /**
     * 商品
     */
    @TableField("goods_name")
    private String goodsName;

    /**
     * 商品价格
     */
    @TableField("origin_price")
    private BigDecimal originPrice;

    /**
     * 拼团价格
     */
    @TableField("group_price")
    private BigDecimal groupPrice;

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
     * 拼团小时
     */
    private Integer hours;

    /**
     * 成团人数
     */
    private Integer peoples;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 拼团总人数
     */
    @TableField("max_people")
    private Integer maxPeople;

    /**
     * 团购次数
     */
    @TableField("limit_goods")
    private Integer limitGoods;
    @TableField(exist = false)
    private PmsProduct goods;


}
