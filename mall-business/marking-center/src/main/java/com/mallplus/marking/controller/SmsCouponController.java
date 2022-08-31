package com.mallplus.marking.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mallplus.common.annotation.SysLog;
import com.mallplus.common.entity.sms.SmsCoupon;
import com.mallplus.marking.service.ISmsCouponService;
import com.mallplus.marking.vo.SmsCouponParam;
import com.mallplus.common.utils.CommonResult;
import com.mallplus.common.utils.ValidatorUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 优惠卷表
 * </p>
 *
 * @author zscat
 * @since 2019-04-19
 */
@Slf4j
@RestController
@Api(tags = "SmsCouponController", description = "优惠卷表管理")
@RequestMapping("/marking/SmsCoupon")
public class SmsCouponController {
    @Resource
    private ISmsCouponService ISmsCouponService;

    @SysLog(MODULE = "sms", REMARK = "根据条件查询所有优惠卷表列表")
    @ApiOperation("根据条件查询所有优惠卷表列表")
    @GetMapping(value = "/list")
    @PreAuthorize("hasAuthority('marking:SmsCoupon:read')")
    public Object getSmsCouponByPage(SmsCoupon entity,
                                     @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                     @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize
    ) {
        try {
            return new CommonResult().success(ISmsCouponService.page(new Page<SmsCoupon>(pageNum, pageSize), new QueryWrapper<>(entity).orderByDesc("id")));
        } catch (Exception e) {
            log.error("根据条件查询所有优惠卷表列表：%s", e.getMessage(), e);
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "sms", REMARK = "保存优惠卷表")
    @ApiOperation("保存优惠卷表")
    @PostMapping(value = "/create")
    @PreAuthorize("hasAuthority('marking:SmsCoupon:create')")
    public Object saveSmsCoupon(@RequestBody SmsCouponParam entity) {
        try {
            if (ISmsCouponService.save(entity)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("保存优惠卷表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "sms", REMARK = "更新优惠卷表")
    @ApiOperation("更新优惠卷表")
    @PostMapping(value = "/update/{id}")
    @PreAuthorize("hasAuthority('marking:SmsCoupon:update')")
    public Object updateSmsCoupon(@RequestBody SmsCouponParam entity) {
        try {
            if (ISmsCouponService.updateById(entity)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("更新优惠卷表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "sms", REMARK = "删除优惠卷表")
    @ApiOperation("删除优惠卷表")
    @GetMapping(value = "/delete/{id}")
    @PreAuthorize("hasAuthority('marking:SmsCoupon:delete')")
    public Object deleteSmsCoupon(@ApiParam("优惠卷表id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("优惠卷表id");
            }
            if (ISmsCouponService.removeById(id)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("删除优惠卷表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

   /* @SysLog(MODULE = "sms", REMARK = "给优惠卷表分配优惠卷表")
    @ApiOperation("查询优惠卷表明细")
    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('marking:SmsCoupon:read')")
    public Object getSmsCouponById(@ApiParam("优惠卷表id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("优惠卷表id");
            }
            SmsCouponParam coupon = ISmsCouponService.s(id);
            return new CommonResult().success(coupon);
        } catch (Exception e) {
            log.error("查询优惠卷表明细：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }

    }*/



}
