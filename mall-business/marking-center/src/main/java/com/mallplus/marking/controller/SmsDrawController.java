package com.mallplus.marking.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mallplus.common.utils.CommonResult;
import com.mallplus.common.utils.ValidatorUtils;
import com.mallplus.marking.service.ISmsDrawService;
import com.mallplus.common.annotation.SysLog;
import com.mallplus.common.entity.sms.SmsDraw;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

/**
 * <p>
 * 一分钱抽奖 前端控制器
 * </p>
 *
 * @author zscat
 * @since 2019-10-17
 */
@Slf4j
@RestController
@RequestMapping("/sms/smsDraw")
public class SmsDrawController {
    @Resource
    private ISmsDrawService ISmsCouponService;

    @SysLog(MODULE = "sms", REMARK = "根据条件查询所有一分钱抽奖表列表")
    @ApiOperation("根据条件查询所有一分钱抽奖表列表")
    @GetMapping(value = "/list")
    public Object getSmsCouponByPage(SmsDraw entity,
                                     @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                     @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize
    ) {
        try {
            return new CommonResult().success(ISmsCouponService.page(new Page<SmsDraw>(pageNum, pageSize), new QueryWrapper<>(entity).orderByDesc("found_time")));
        } catch (Exception e) {
            log.error("根据条件查询所有一分钱抽奖表列表：%s", e.getMessage(), e);
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "sms", REMARK = "保存一分钱抽奖表")
    @ApiOperation("保存一分钱抽奖表")
    @PostMapping(value = "/create")

    public Object saveSmsCoupon(@RequestBody SmsDraw entity) {
        try {
            entity.setFoundTime(new Date());
            if (ISmsCouponService.save(entity)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("保存一分钱抽奖表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "sms", REMARK = "更新一分钱抽奖表")
    @ApiOperation("更新一分钱抽奖表")
    @PostMapping(value = "/update/{id}")
    public Object updateSmsCoupon(@RequestBody SmsDraw entity) {
        try {
            if (ISmsCouponService.updateById(entity)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("更新一分钱抽奖表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "sms", REMARK = "删除一分钱抽奖表")
    @ApiOperation("删除一分钱抽奖表")
    @GetMapping(value = "/delete/{id}")
    public Object deleteSmsCoupon(@ApiParam("一分钱抽奖表id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("一分钱抽奖表id");
            }
            if (ISmsCouponService.removeById(id)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("删除一分钱抽奖表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "sms", REMARK = "给一分钱抽奖表分配一分钱抽奖表")
    @ApiOperation("查询一分钱抽奖表明细")
    @GetMapping(value = "/{id}")
    public Object getSmsCouponById(@ApiParam("一分钱抽奖表id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("一分钱抽奖表id");
            }
            SmsDraw coupon = ISmsCouponService.getById(id);
            return new CommonResult().success(coupon);
        } catch (Exception e) {
            log.error("查询一分钱抽奖表明细：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }

    }
}

