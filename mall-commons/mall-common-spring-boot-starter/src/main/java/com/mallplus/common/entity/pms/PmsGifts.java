package com.mallplus.common.entity.pms;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 帮助表
 * </p>
 *
 * @author zscat
 * @since 2019-07-07
 */
@Data
@TableName("pms_gifts")
public class PmsGifts implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 类别
     */
    @TableField("category_id")
    private Long categoryId;

    /**
     * 图片
     */
    private String icon;

    /**
     * 标题
     */
    private String title;

    /**
     * 状态
     */
    @TableField("show_status")
    private Integer showStatus;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 内容
     */
    private String content;

    private Integer stock;

    /**
     * 1 赠品 2 活动商品
     */
    private Integer type;

    private BigDecimal price;


}
