package com.mallplus.common.entity.sms;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import com.mallplus.common.vo.SamplePmsProduct;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author zscat
 * @since 2019-10-12
 */

@Data
@TableName("sms_group_activity")
public class SmsGroupActivity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 活动名称
     */
    private String name;
    private String pic;

    /**
     * 活动价格
     */
    private BigDecimal price;
    /**
     * 原格
     */
    private BigDecimal originprice;

    /**
     * 运费
     */
    private BigDecimal transfee;

    /**
     * 活动状态 1 开启 2 关闭
     */
    private Integer status;

    /**
     * 1 买家承担 2 卖家承担
     */
    private Integer feestatus;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;


    @TableField("goods_ids")
    private String goodsIds;


    @TableField(exist = false)
    List<SamplePmsProduct> productList;


}
