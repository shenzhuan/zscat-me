package com.mallplus.common.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Administrator on 2019/7/7.
 */
@Data
public class BasicRuls implements Serializable {
    private BigDecimal fullPrice;
    private BigDecimal reducePrice;

}
