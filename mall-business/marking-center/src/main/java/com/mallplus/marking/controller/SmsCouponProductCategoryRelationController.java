package com.mallplus.marking.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mallplus.common.annotation.SysLog;
import com.mallplus.common.entity.sms.SmsCouponProductCategoryRelation;
import com.mallplus.marking.service.ISmsCouponProductCategoryRelationService;
import com.mallplus.common.utils.CommonResult;
import com.mallplus.common.utils.ValidatorUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 优惠券和产品分类关系表
 * </p>
 *
 * @author zscat
 * @since 2019-04-19
 */
@Slf4j
@RestController
@Api(tags = "SmsCouponProductCategoryRelationController", description = "优惠券和产品分类关系表管理")
@RequestMapping("/marking/SmsCouponProductCategoryRelation")
public class SmsCouponProductCategoryRelationController {
    @Resource
    private ISmsCouponProductCategoryRelationService ISmsCouponProductCategoryRelationService;

    @SysLog(MODULE = "sms", REMARK = "根据条件查询所有优惠券和产品分类关系表列表")
    @ApiOperation("根据条件查询所有优惠券和产品分类关系表列表")
    @GetMapping(value = "/list")
    @PreAuthorize("hasAuthority('marking:SmsCouponProductCategoryRelation:read')")
    public Object getSmsCouponProductCategoryRelationByPage(SmsCouponProductCategoryRelation entity,
                                                            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize
    ) {
        try {
            return new CommonResult().success(ISmsCouponProductCategoryRelationService.page(new Page<SmsCouponProductCategoryRelation>(pageNum, pageSize), new QueryWrapper<>(entity)));
        } catch (Exception e) {
            log.error("根据条件查询所有优惠券和产品分类关系表列表：%s", e.getMessage(), e);
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "sms", REMARK = "保存优惠券和产品分类关系表")
    @ApiOperation("保存优惠券和产品分类关系表")
    @PostMapping(value = "/create")
    @PreAuthorize("hasAuthority('marking:SmsCouponProductCategoryRelation:create')")
    public Object saveSmsCouponProductCategoryRelation(@RequestBody SmsCouponProductCategoryRelation entity) {
        try {
            if (ISmsCouponProductCategoryRelationService.save(entity)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("保存优惠券和产品分类关系表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "sms", REMARK = "更新优惠券和产品分类关系表")
    @ApiOperation("更新优惠券和产品分类关系表")
    @PostMapping(value = "/update/{id}")
    @PreAuthorize("hasAuthority('marking:SmsCouponProductCategoryRelation:update')")
    public Object updateSmsCouponProductCategoryRelation(@RequestBody SmsCouponProductCategoryRelation entity) {
        try {
            if (ISmsCouponProductCategoryRelationService.updateById(entity)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("更新优惠券和产品分类关系表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "sms", REMARK = "删除优惠券和产品分类关系表")
    @ApiOperation("删除优惠券和产品分类关系表")
    @GetMapping(value = "/delete/{id}")
    @PreAuthorize("hasAuthority('marking:SmsCouponProductCategoryRelation:delete')")
    public Object deleteSmsCouponProductCategoryRelation(@ApiParam("优惠券和产品分类关系表id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("优惠券和产品分类关系表id");
            }
            if (ISmsCouponProductCategoryRelationService.removeById(id)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("删除优惠券和产品分类关系表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "sms", REMARK = "给优惠券和产品分类关系表分配优惠券和产品分类关系表")
    @ApiOperation("查询优惠券和产品分类关系表明细")
    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('marking:SmsCouponProductCategoryRelation:read')")
    public Object getSmsCouponProductCategoryRelationById(@ApiParam("优惠券和产品分类关系表id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("优惠券和产品分类关系表id");
            }
            SmsCouponProductCategoryRelation coupon = ISmsCouponProductCategoryRelationService.getById(id);
            return new CommonResult().success(coupon);
        } catch (Exception e) {
            log.error("查询优惠券和产品分类关系表明细：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }

    }

    @ApiOperation(value = "批量删除优惠券和产品分类关系表")
    @RequestMapping(value = "/delete/batch", method = RequestMethod.POST)
    @ResponseBody
    @SysLog(MODULE = "pms", REMARK = "批量删除优惠券和产品分类关系表")
    @PreAuthorize("hasAuthority('marking:SmsCouponProductCategoryRelation:delete')")
    public Object deleteBatch(@RequestParam("ids") List<Long> ids) {
        boolean count = ISmsCouponProductCategoryRelationService.removeByIds(ids);
        if (count) {
            return new CommonResult().success(count);
        } else {
            return new CommonResult().failed();
        }
    }

}
