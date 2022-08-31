package com.mallplus.common.feign;

import com.mallplus.common.constant.ServiceNameConstants;
import com.mallplus.common.entity.pms.PmsGifts;
import com.mallplus.common.entity.pms.PmsProduct;
import com.mallplus.common.entity.pms.PmsProductConsult;
import com.mallplus.common.entity.pms.PmsSkuStock;
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
public interface OmsFeignClinent {
    /**
     * feign rpc访问远程/goods/{id}接口
     * 查询用户实体对象PmsProduct
     *
     * @param id
     * @return
     */


    @GetMapping(value = "/notAuth/rpc/PmsProduct/id", params = "id")
    PmsProduct selectById(@RequestParam("id") Long id);


    @PostMapping(value = "/notAuth/updateGoodsById")
    void updateGoodsById(@RequestBody PmsProduct goods);


}
