package com.mallplus.goods.controller;


import com.mallplus.common.annotation.IgnoreAuth;
import com.mallplus.common.annotation.SysLog;
import com.mallplus.common.entity.pms.PmsProduct;
import com.mallplus.common.redis.constant.RedisToolsConstant;
import com.mallplus.common.redis.template.RedisRepository;
import com.mallplus.common.redis.template.RedisUtil;
import com.mallplus.common.utils.CommonResult;
import com.mallplus.goods.mapper.PmsProductCategoryMapper;
import com.mallplus.goods.mapper.PmsProductMapper;
import com.mallplus.goods.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: shenzhuan
 * @Date: 2019/4/2 15:02
 * @Description:
 */
@RestController
@Api(tags = "AuthPmsController", description = "商品关系管理")
@RequestMapping("/auth")
public class AuthPmsController {

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
    private com.mallplus.goods.service.IPmsBrandService IPmsBrandService;
    @Resource
    private RedisRepository redisRepository;
    @Resource
    private PmsProductCategoryMapper categoryMaaddresspper;
    @Resource
    private RedisUtil redisUtil;

    @IgnoreAuth
    @ApiOperation("添加商品浏览记录")
    @SysLog(MODULE = "pms", REMARK = "添加商品浏览记录")
    @PostMapping(value = "/addView")
    public void addView(@RequestParam(value = "memberId", required = false) Long memberId,
                        @RequestParam(value = "goodsId", required = false) Long goodsId) {

        String key = String.format(RedisToolsConstant.GOODSHISTORY, memberId);

        //为了保证浏览商品的 唯一性,每次添加前,将list 中该 商品ID去掉,在加入,以保证其浏览的最新的商品在最前面
        redisUtil.lRemove(key, 1, goodsId.toString());
        //将value push 到该key下的list中
        redisUtil.lLeftPush(key,goodsId.toString());
        //使用ltrim将60个数据之后的数据剪切掉
        redisUtil.lTrim(key,0,59);
        //设置缓存时间为一个月
        redisUtil.expire(key,60*60*24*30, TimeUnit.SECONDS);
    }
    @SysLog(MODULE = "pms", REMARK = "查询用户浏览记录列表")
    @IgnoreAuth
    @ApiOperation(value = "查询用户浏览记录列表")
    @GetMapping(value = "/viewList")
    public Object viewList(@RequestParam Long memberId,
                           @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                           @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum) {
        String key = String.format(RedisToolsConstant.GOODSHISTORY, memberId);

        //获取用户的浏览的商品的总页数;
        long pageCount = redisUtil.lLen(key);
        //拼装返回
        Map<String,Object> map = new HashMap<>();
        //根据用户的ID分頁获取该用户最近浏览的50个商品信息
        List<String> result = redisUtil.lRange(key,(pageNum-1)*pageSize,pageNum*pageSize-1);
        if (result!=null && result.size()>0){
            List<PmsProduct> list = (List<PmsProduct>) pmsProductService.listByIds(result);

            map.put("result",list);
            map.put("pageCount",(pageCount%pageSize == 0 ? pageCount/pageSize : pageCount/pageSize+1));
        }

        return new CommonResult().success(map);
    }




}
