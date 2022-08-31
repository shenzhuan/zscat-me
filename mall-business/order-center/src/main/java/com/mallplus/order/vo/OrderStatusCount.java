package com.mallplus.order.vo;

import com.mallplus.common.entity.BaseEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: shenzhuan
 * @Date: 2019/2/23 13:12
 * @Description:
 */
@Data
public class OrderStatusCount implements Serializable{
    // 0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单
    private  int status0;
    private  int status1;
    private  int status2;
    private  int status3;
    private  int status4;
    private  int status5;
}
