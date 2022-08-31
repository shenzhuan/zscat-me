package com.mallplus.common.entity.ums;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mallplus.common.entity.BaseEntity;
import com.mallplus.common.entity.pms.PmsProduct;
import com.mallplus.common.vo.SamplePmsProduct;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author zscat
 * @since 2019-04-14
 */
@Data
@TableName("sys_school")
public class SysSchool extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 精度
     */
    private String longitude;

    /**
     * 纬度
     */
    private String latitude;

    /**
     * 名称
     */
    private String name;

    /**
     * 省
     */
    private String province;

    /**
     * 市
     */
    private String city;

    /**
     * 区
     */
    private String region;

    /**
     * 地址
     */
    private String address;

    private String pic;
    /**
     * 1热门
     */
    private Integer hot;

    /**
     * 211
     */
    private Integer is211;

    /**
     * 985
     */
    private Integer is985;


    @TableField(exist = false)
    private List<PmsProduct> goodsList;

    @TableField(exist = false)
    private Integer  goodsCount;
}
