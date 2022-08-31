package com.mallplus.common.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2019/7/7.
 */
@Data
public class AmountAndCount {
    BigDecimal singleAmount = new BigDecimal("0");//实付金额
    int singleCount = 0;
}
