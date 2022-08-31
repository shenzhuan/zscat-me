package com.mallplus.common.entity.oms;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import com.mallplus.common.entity.BaseEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 订单操作历史记录
 * </p>
 *
 * @author zscat
 * @since 2019-04-17
 */
@Data
@TableName("oms_order_operate_history")
public class OmsOrderOperateHistory extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 订单id
     */
    @TableField("order_id")
    private Long orderId;

    /**
     * 操作人：用户；系统；后台管理员
     */
    @TableField("operate_man")
    private String operateMan;

    /**
     * 操作时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 订单状态：0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单
     */
    @TableField("order_status")
    private Integer orderStatus;
    /**
     * 原订单状态：0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单
     */
    @TableField("pre_status")
    private Integer preStatus;

    /**
     * 备注
     */
    private String note;



}
