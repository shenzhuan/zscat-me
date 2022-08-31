package com.mallplus.goods.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mallplus.common.annotation.IgnoreAuth;
import com.mallplus.common.annotation.SysLog;
import com.mallplus.common.entity.pms.*;
import com.mallplus.common.entity.sms.SmsGroup;
import com.mallplus.common.redis.constant.RedisToolsConstant;
import com.mallplus.common.redis.template.RedisRepository;
import com.mallplus.common.redis.template.RedisUtil;
import com.mallplus.common.utils.CommonResult;
import com.mallplus.common.utils.ValidatorUtils;
import com.mallplus.common.vo.HomeContentResult;
import com.mallplus.common.vo.ProductTypeVo;
import com.mallplus.goods.mapper.PmsProductCategoryMapper;
import com.mallplus.goods.mapper.PmsProductMapper;
import com.mallplus.goods.mapper.PmsSkuStockMapper;
import com.mallplus.goods.service.*;
import com.mallplus.goods.vo.ConsultTypeCount;
import com.mallplus.goods.vo.PmsProductParam;
import com.mallplus.goods.vo.PromotionProduct;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: shenzhuan
 * @Date: 2019/4/2 15:02
 * @Description:
 */
@Slf4j
@RestController
@Api(tags = "NotAuthRpcPmsController", description = "商品关系管理")
public class NotAuthRpcPmsController {

    @Autowired
    private IPmsProductConsultService pmsProductConsultService;
    @Resource
    private IPmsProductService pmsProductService;
    @Resource
    private IPmsProductAttributeCategoryService productAttributeCategoryService;
    @Resource
    private IPmsProductCategoryService productCategoryService;
    @Resource
    private PmsProductMapper productMapper;
    @Resource
    private IPmsBrandService IPmsBrandService;
    @Resource
    private RedisRepository redisRepository;
    @Resource
    private  PmsProductCategoryMapper categoryMapper;
    @Resource
    private RedisUtil redisUtil;
    @Autowired
    private IPmsFavoriteService favoriteService;
    @Resource
    private PmsSkuStockMapper skuStockMapper;


    @GetMapping(value = "/notAuth/rpc/PmsProduct/id", params = "id")
    PmsProduct selectById(Long id){
        return  productMapper.selectById(id);
    }

    @GetMapping(value = "/notAuth/rpc/PmsSkuStock/id", params = "id")
    PmsSkuStock selectSkuById(Long id){
        return skuStockMapper.selectById(id);
    }

    @GetMapping(value = "/notAuth/rpc/getPromotionProductList")
    List<PromotionProduct> getPromotionProductList(List<Long> productIdList){
        return  null;
    }

    @PostMapping(value = "/notAuth/rpc/updateSkuById")
    void updateSkuById(PmsSkuStock skuStock){
        skuStockMapper.updateById(skuStock);
    }


}
