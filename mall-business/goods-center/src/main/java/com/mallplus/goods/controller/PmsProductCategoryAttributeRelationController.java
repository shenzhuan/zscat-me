package com.mallplus.goods.controller;

import com.mallplus.common.utils.CommonResult;



import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import com.mallplus.common.entity.pms.PmsProductCategoryAttributeRelation;
import com.mallplus.goods.service.IPmsProductCategoryAttributeRelationService;
import com.mallplus.common.utils.ValidatorUtils;
import com.mallplus.common.annotation.SysLog;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 产品的分类和属性的关系表，用于设置分类筛选条件（只支持一级分类）
 * </p>
 *
 * @author zscat
 * @since 2019-04-19
 */
@Slf4j
@RestController
@Api(tags = "PmsProductCategoryAttributeRelationController", description = "产品的分类和属性的关系表，用于设置分类筛选条件（只支持一级分类）管理")
@RequestMapping("/pms/PmsProductCategoryAttributeRelation")
public class PmsProductCategoryAttributeRelationController {
    @Resource
    private IPmsProductCategoryAttributeRelationService IPmsProductCategoryAttributeRelationService;

    @SysLog(MODULE = "pms", REMARK = "根据条件查询所有产品的分类和属性的关系表，用于设置分类筛选条件（只支持一级分类）列表")
    @ApiOperation("根据条件查询所有产品的分类和属性的关系表，用于设置分类筛选条件（只支持一级分类）列表")
    @GetMapping(value = "/list")
    @PreAuthorize("hasAuthority('pms:PmsProductCategoryAttributeRelation:read')")
    public Object getPmsProductCategoryAttributeRelationByPage(PmsProductCategoryAttributeRelation entity,
                                                               @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                               @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize
    ) {
        try {
            return new CommonResult().success(IPmsProductCategoryAttributeRelationService.page(new Page<PmsProductCategoryAttributeRelation>(pageNum, pageSize), new QueryWrapper<>(entity)));
        } catch (Exception e) {
            log.error("根据条件查询所有产品的分类和属性的关系表，用于设置分类筛选条件（只支持一级分类）列表：%s", e.getMessage(), e);
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "pms", REMARK = "保存产品的分类和属性的关系表，用于设置分类筛选条件（只支持一级分类）")
    @ApiOperation("保存产品的分类和属性的关系表，用于设置分类筛选条件（只支持一级分类）")
    @PostMapping(value = "/create")
    @PreAuthorize("hasAuthority('pms:PmsProductCategoryAttributeRelation:create')")
    public Object savePmsProductCategoryAttributeRelation(@RequestBody PmsProductCategoryAttributeRelation entity) {
        try {
            if (IPmsProductCategoryAttributeRelationService.save(entity)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("保存产品的分类和属性的关系表，用于设置分类筛选条件（只支持一级分类）：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "pms", REMARK = "更新产品的分类和属性的关系表，用于设置分类筛选条件（只支持一级分类）")
    @ApiOperation("更新产品的分类和属性的关系表，用于设置分类筛选条件（只支持一级分类）")
    @PostMapping(value = "/update/{id}")
    @PreAuthorize("hasAuthority('pms:PmsProductCategoryAttributeRelation:update')")
    public Object updatePmsProductCategoryAttributeRelation(@RequestBody PmsProductCategoryAttributeRelation entity) {
        try {
            if (IPmsProductCategoryAttributeRelationService.updateById(entity)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("更新产品的分类和属性的关系表，用于设置分类筛选条件（只支持一级分类）：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "pms", REMARK = "删除产品的分类和属性的关系表，用于设置分类筛选条件（只支持一级分类）")
    @ApiOperation("删除产品的分类和属性的关系表，用于设置分类筛选条件（只支持一级分类）")
    @GetMapping(value = "/delete/{id}")
    @PreAuthorize("hasAuthority('pms:PmsProductCategoryAttributeRelation:delete')")
    public Object deletePmsProductCategoryAttributeRelation(@ApiParam("产品的分类和属性的关系表，用于设置分类筛选条件（只支持一级分类）id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("产品的分类和属性的关系表，用于设置分类筛选条件（只支持一级分类）id");
            }
            if (IPmsProductCategoryAttributeRelationService.removeById(id)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("删除产品的分类和属性的关系表，用于设置分类筛选条件（只支持一级分类）：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "pms", REMARK = "给产品的分类和属性的关系表，用于设置分类筛选条件（只支持一级分类）分配产品的分类和属性的关系表，用于设置分类筛选条件（只支持一级分类）")
    @ApiOperation("查询产品的分类和属性的关系表，用于设置分类筛选条件（只支持一级分类）明细")
    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('pms:PmsProductCategoryAttributeRelation:read')")
    public Object getPmsProductCategoryAttributeRelationById(@ApiParam("产品的分类和属性的关系表，用于设置分类筛选条件（只支持一级分类）id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("产品的分类和属性的关系表，用于设置分类筛选条件（只支持一级分类）id");
            }
            PmsProductCategoryAttributeRelation coupon = IPmsProductCategoryAttributeRelationService.getById(id);
            return new CommonResult().success(coupon);
        } catch (Exception e) {
            log.error("查询产品的分类和属性的关系表，用于设置分类筛选条件（只支持一级分类）明细：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }

    }

    @ApiOperation(value = "批量删除产品的分类和属性的关系表，用于设置分类筛选条件（只支持一级分类）")
    @RequestMapping(value = "/delete/batch", method = RequestMethod.POST)
    @ResponseBody
    @SysLog(MODULE = "pms", REMARK = "批量删除产品的分类和属性的关系表，用于设置分类筛选条件（只支持一级分类）")
    @PreAuthorize("hasAuthority('pms:PmsProductCategoryAttributeRelation:delete')")
    public Object deleteBatch(@RequestParam("ids") List<Long> ids) {
        boolean count = IPmsProductCategoryAttributeRelationService.removeByIds(ids);
        if (count) {
            return new CommonResult().success(count);
        } else {
            return new CommonResult().failed();
        }
    }

}
