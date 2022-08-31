package com.mallplus.common.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Auther: shenzhuan
 * @Date: 2019/6/2 12:36
 * @Description:
 */
@Data
public class OrderStstic {
    private Long memberId;
    private int totalCount;
    private BigDecimal totalPayAmount;
    private int memberCount;
    private Long objId;
}
