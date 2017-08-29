package com.zscat.shop.model;

import javax.persistence.Column;
import javax.persistence.Table;

import com.zsCat.common.base.BaseEntity;

@Table(name = "t_order_log")
public class OrderLog extends BaseEntity{
   

    /**
     * 订单id
     */
    @Column(name = "order_id")
    private Long orderId;

    /**
     * 订单状态信息
     */
    @Column(name = "order_state")
    private String orderState;

    /**
     * 下一步订单状态信息
     */
    @Column(name = "change_state")
    private String changeState;

    /**
     * 订单状态描述
     */
    @Column(name = "state_info")
    private String stateInfo;

    /**
     * 处理时间
     */
    @Column(name = "create_time")
    private Long createTime;

    /**
     * 操作人
     */
    private String operator;

   

    /**
     * 获取订单id
     *
     * @return order_id - 订单id
     */
    public Long getOrderId() {
        return orderId;
    }

    /**
     * 设置订单id
     *
     * @param orderId 订单id
     */
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    /**
     * 获取订单状态信息
     *
     * @return order_state - 订单状态信息
     */
    public String getOrderState() {
        return orderState;
    }

    /**
     * 设置订单状态信息
     *
     * @param orderState 订单状态信息
     */
    public void setOrderState(String orderState) {
        this.orderState = orderState;
    }

    /**
     * 获取下一步订单状态信息
     *
     * @return change_state - 下一步订单状态信息
     */
    public String getChangeState() {
        return changeState;
    }

    /**
     * 设置下一步订单状态信息
     *
     * @param changeState 下一步订单状态信息
     */
    public void setChangeState(String changeState) {
        this.changeState = changeState;
    }

    /**
     * 获取订单状态描述
     *
     * @return state_info - 订单状态描述
     */
    public String getStateInfo() {
        return stateInfo;
    }

    /**
     * 设置订单状态描述
     *
     * @param stateInfo 订单状态描述
     */
    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    /**
     * 获取处理时间
     *
     * @return create_time - 处理时间
     */
    public Long getCreateTime() {
        return createTime;
    }

    /**
     * 设置处理时间
     *
     * @param createTime 处理时间
     */
    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取操作人
     *
     * @return operator - 操作人
     */
    public String getOperator() {
        return operator;
    }

    /**
     * 设置操作人
     *
     * @param operator 操作人
     */
    public void setOperator(String operator) {
        this.operator = operator;
    }
}