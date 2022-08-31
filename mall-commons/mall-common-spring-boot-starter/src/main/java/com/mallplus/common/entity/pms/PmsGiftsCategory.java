package com.mallplus.common.entity.pms;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 帮助分类表
 * </p>
 *
 * @author zscat
 * @since 2019-07-07
 */
@Data
@TableName("pms_gifts_category")
public class PmsGiftsCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 标题
     */
    private String name;

    /**
     * 分类图标
     */
    private String icon;

    /**
     * 状态
     */
    @TableField("show_status")
    private Integer showStatus;

    /**
     * 排序
     */
    private Integer sort;


}
