package com.mallplus.common.vo;



import com.mallplus.common.entity.pms.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 创建和修改商品时使用的参数
 * https://github.com/shenzhuan/mallplus on 2018/4/26.
 */
@Data
public class GoodsDetailResult implements Serializable {

    private List<PmsProductLadder> productLadderList;

    private List<PmsProductFullReduction> productFullReductionList;

    private List<PmsMemberPrice> memberPriceList;

    private List<PmsSkuStock> skuStockList;

    private List<PmsProductAttributeValue> productAttributeValueList;

    private List<CmsSubjectProductRelation> subjectProductRelationList;

    private List<CmsPrefrenceAreaProductRelation> prefrenceAreaProductRelationList;

    private List<PmsProductAttributeValue>  productCanShuValueList ;
    private List<PmsProduct> typeGoodsList;

    private  PmsProduct goods;
}
