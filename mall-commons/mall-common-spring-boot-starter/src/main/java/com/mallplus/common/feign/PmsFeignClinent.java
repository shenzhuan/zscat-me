package com.mallplus.common.feign;

import com.mallplus.common.constant.ServiceNameConstants;
import com.mallplus.common.entity.pms.PmsGifts;
import com.mallplus.common.entity.pms.PmsProduct;

import com.mallplus.common.entity.pms.PmsProductConsult;
import com.mallplus.common.entity.pms.PmsSkuStock;
import com.mallplus.common.entity.sms.SmsGroup;
import com.mallplus.common.feign.fallback.PmsFeignClientFallbackFactory;
import com.mallplus.common.vo.PromotionProduct;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author mall
 */
@FeignClient(name = ServiceNameConstants.GOODS_SERVICE, fallbackFactory = PmsFeignClientFallbackFactory.class, decode404 = true)
public interface PmsFeignClinent {
    /**
     * feign rpc访问远程/goods/{id}接口
     * 查询用户实体对象PmsProduct
     *
     * @param id
     * @return
     */


    @GetMapping(value = "/notAuth/rpc/PmsProduct/id", params = "id")
    PmsProduct selectById(@RequestParam("id") Long id);

    @GetMapping(value = "/notAuth/rpc/PmsSkuStock/id", params = "id")
    PmsSkuStock selectSkuById(@RequestParam("id") Long id);

    @GetMapping(value = "/notAuth/rpc/getPromotionProductList", params = "productIdList")
    List<PromotionProduct> getPromotionProductList(@RequestParam("productIdList") List<Long> productIdList);

    @PostMapping(value = "/notAuth/rpc/updateSkuById")
    void updateSkuById(@RequestBody PmsSkuStock skuStock);

    @PostMapping(value = "/notAuth/updateGoodsById")
    void updateGoodsById(@RequestBody PmsProduct goods);

    @GetMapping(value = "/notAuth/getGiftById")
    PmsGifts getGiftById(@RequestParam("goodsId") Long goodsId);

    @PostMapping(value = "/notAuth/rpc/updateGiftById")
    void updateGiftById(@RequestBody PmsGifts newGoods);

    @GetMapping(value = "/notAuth/listGoodsByIds")
    List<PmsProduct> listGoodsByIds(@RequestBody List<Long> ids);

    @PostMapping(value = "/notAuth/rpc/saveProductConsult")
    void saveProductConsult(@RequestBody PmsProductConsult productConsult);



}
