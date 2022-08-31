package com.mallplus.common.entity.pms;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mallplus.common.entity.BaseEntity;
import com.mallplus.common.vo.SamplePmsProduct;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 产品属性分类表
 * </p>
 *
 * @author zscat
 * @since 2019-04-19
 */
@Data
@TableName("pms_product_attribute_category")
public class PmsProductAttributeCategory extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableField(exist = false)
    List<PmsProduct> goodsList;
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private String name;
    private String pic;
    /**
     * 属性数量
     */
    @TableField("attribute_count")
    private Integer attributeCount;
    /**
     * 参数数量
     */
    @TableField("param_count")
    private Integer paramCount;

}
