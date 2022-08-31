package com.mallplus.common.entity.pms;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by Administrator on 2019/9/15.
 */
@Data
public class ProductConsultParam implements Serializable{
    private  Long goodsId;
    private  Integer score;

    private  String []images;
    private  String textarea;
}
