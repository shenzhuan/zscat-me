package com.mallplus.common.entity.oms;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>
 * 支付方式表
 * </p>
 *
 * @author zscat
 * @since 2019-09-14
 */
@Setter
@Getter
@TableName("oms_payments")
public class OmsPayments implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 支付类型编码
     */
    private String code;

    /**
     * 支付类型名称
     */
    private String name;

    /**
     * 是否线上支付 1=线上支付 2=线下支付
     */
    @TableField("is_online")
    private Integer isOnline;

    /**
     * 参数
     */
    private String params;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 支付方式描述
     */
    private String memo;

    /**
     * 启用状态 1=启用 2=停用
     */
    private Integer status;


}
