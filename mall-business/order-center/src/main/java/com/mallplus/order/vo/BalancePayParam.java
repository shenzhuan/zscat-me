package com.mallplus.order.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Auther: shenzhuan
 * @Date: 2019/6/19 09:08
 * @Description:
 */
@Data
public class BalancePayParam {

    private Long orderId;
    private Long memberId;
    private BigDecimal payAmount;
    private BigDecimal balance;
}
