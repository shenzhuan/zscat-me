package com.mallplus.marking.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mallplus.common.annotation.SysLog;
import com.mallplus.common.entity.sms.SmsConfigure;
import com.mallplus.marking.service.ISmsConfigureService;
import com.mallplus.common.utils.CommonResult;
import com.mallplus.common.utils.ValidatorUtils;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 商品配置表 前端控制器
 * </p>
 *
 * @author zscat
 * @since 2019-10-18
 */
@Slf4j
@RestController
@RequestMapping("/sms/smsConfigure")
public class SmsConfigureController {
    @Resource
    private ISmsConfigureService ISmsCouponService;

    @SysLog(MODULE = "sms", REMARK = "根据条件查询所有商品配置表表列表")
    @ApiOperation("根据条件查询所有商品配置表表列表")
    @GetMapping(value = "/list")
    public Object getSmsCouponByPage(SmsConfigure entity,
                                     @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                     @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize
    ) {
        try {
            return new CommonResult().success(ISmsCouponService.page(new Page<SmsConfigure>(pageNum, pageSize), new QueryWrapper<>(entity)));
        } catch (Exception e) {
            log.error("根据条件查询所有商品配置表表列表：%s", e.getMessage(), e);
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "sms", REMARK = "保存商品配置表表")
    @ApiOperation("保存商品配置表表")
    @PostMapping(value = "/create")
    public Object saveSmsCoupon(@RequestBody SmsConfigure entity) {
        try {
            if (ISmsCouponService.save(entity)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("保存商品配置表表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "sms", REMARK = "更新商品配置表表")
    @ApiOperation("更新商品配置表表")
    @PostMapping(value = "/update/{id}")
    public Object updateSmsCoupon(@RequestBody SmsConfigure entity) {
        try {
            if (ISmsCouponService.updateById(entity)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("更新商品配置表表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "sms", REMARK = "删除商品配置表表")
    @ApiOperation("删除商品配置表表")
    @GetMapping(value = "/delete/{id}")
    public Object deleteSmsCoupon(@ApiParam("商品配置表表id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("商品配置表表id");
            }
            if (ISmsCouponService.removeById(id)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("删除商品配置表表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "sms", REMARK = "给商品配置表表分配商品配置表表")
    @ApiOperation("查询商品配置表表明细")
    @GetMapping(value = "/{id}")
    public Object getSmsCouponById(@ApiParam("商品配置表表id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("商品配置表表id");
            }
            SmsConfigure coupon = ISmsCouponService.getById(id);
            return new CommonResult().success(coupon);
        } catch (Exception e) {
            log.error("查询商品配置表表明细：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }

    }
}

