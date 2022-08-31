package com.mallplus.marking.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mallplus.common.annotation.SysLog;
import com.mallplus.common.entity.sms.SmsDrawUser;
import com.mallplus.marking.service.ISmsDrawUserService;
import com.mallplus.common.utils.CommonResult;
import com.mallplus.common.utils.ValidatorUtils;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 抽奖与用户关联表 前端控制器
 * </p>
 *
 * @author zscat
 * @since 2019-10-17
 */
@Slf4j
@RestController
@RequestMapping("/sms/smsDrawUser")
public class SmsDrawUserController {
    @Resource
    private ISmsDrawUserService ISmsCouponService;

    @SysLog(MODULE = "sms", REMARK = "根据条件查询所有抽奖与用户关联表列表")
    @ApiOperation("根据条件查询所有抽奖与用户关联表列表")
    @GetMapping(value = "/list")
    public Object getSmsCouponByPage(SmsDrawUser entity,
                                     @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                     @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize
    ) {
        try {
            return new CommonResult().success(ISmsCouponService.page(new Page<SmsDrawUser>(pageNum, pageSize), new QueryWrapper<>(entity).orderByDesc("time")));
        } catch (Exception e) {
            log.error("根据条件查询所有抽奖与用户关联表列表：%s", e.getMessage(), e);
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "sms", REMARK = "保存抽奖与用户关联表")
    @ApiOperation("保存抽奖与用户关联表")
    @PostMapping(value = "/create")

    public Object saveSmsCoupon(@RequestBody SmsDrawUser entity) {
        try {
            if (ISmsCouponService.save(entity)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("保存抽奖与用户关联表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "sms", REMARK = "更新抽奖与用户关联表")
    @ApiOperation("更新抽奖与用户关联表")
    @PostMapping(value = "/update/{id}")
    public Object updateSmsCoupon(@RequestBody SmsDrawUser entity) {
        try {
            if (ISmsCouponService.updateById(entity)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("更新抽奖与用户关联表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "sms", REMARK = "删除抽奖与用户关联表")
    @ApiOperation("删除抽奖与用户关联表")
    @GetMapping(value = "/delete/{id}")
    public Object deleteSmsCoupon(@ApiParam("抽奖与用户关联表id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("抽奖与用户关联表id");
            }
            if (ISmsCouponService.removeById(id)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("删除抽奖与用户关联表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "sms", REMARK = "给抽奖与用户关联表分配抽奖与用户关联表")
    @ApiOperation("查询抽奖与用户关联表明细")
    @GetMapping(value = "/{id}")
    public Object getSmsCouponById(@ApiParam("抽奖与用户关联表id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("抽奖与用户关联表id");
            }
            SmsDrawUser coupon = ISmsCouponService.getById(id);
            return new CommonResult().success(coupon);
        } catch (Exception e) {
            log.error("查询抽奖与用户关联表明细：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }

    }
}

