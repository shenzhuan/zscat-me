package com.mallplus.cms.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mallplus.common.annotation.SysLog;
import com.mallplus.common.entity.cms.CmsSubjectCategory;
import com.mallplus.cms.service.ICmsSubjectCategoryService;
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
 * 专题分类表
 * </p>
 *
 * @author zscat
 * @since 2019-04-19
 */
@Slf4j
@RestController
@Api(tags = "CmsSubjectCategoryController", description = "专题分类表管理")
@RequestMapping("/cms/CmsSubjectCategory")
public class CmsSubjectCategoryController {
    @Resource
    private ICmsSubjectCategoryService ICmsSubjectCategoryService;

    @SysLog(MODULE = "cms", REMARK = "根据条件查询所有专题分类表列表")
    @ApiOperation("根据条件查询所有专题分类表列表")
    @GetMapping(value = "/list")
    @PreAuthorize("hasAuthority('cms:CmsSubjectCategory:read')")
    public Object getCmsSubjectCategoryByPage(CmsSubjectCategory entity,
                                              @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                              @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize
    ) {
        try {
            return new CommonResult().success(ICmsSubjectCategoryService.page(new Page<CmsSubjectCategory>(pageNum, pageSize), new QueryWrapper<>(entity)));
        } catch (Exception e) {
            log.error("根据条件查询所有专题分类表列表：%s", e.getMessage(), e);
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "cms", REMARK = "保存专题分类表")
    @ApiOperation("保存专题分类表")
    @PostMapping(value = "/create")
    @PreAuthorize("hasAuthority('cms:CmsSubjectCategory:create')")
    public Object saveCmsSubjectCategory(@RequestBody CmsSubjectCategory entity) {
        try {
            if (ICmsSubjectCategoryService.save(entity)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("保存专题分类表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "cms", REMARK = "更新专题分类表")
    @ApiOperation("更新专题分类表")
    @PostMapping(value = "/update/{id}")
    @PreAuthorize("hasAuthority('cms:CmsSubjectCategory:update')")
    public Object updateCmsSubjectCategory(@RequestBody CmsSubjectCategory entity) {
        try {
            if (ICmsSubjectCategoryService.updateById(entity)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("更新专题分类表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "cms", REMARK = "删除专题分类表")
    @ApiOperation("删除专题分类表")
    @GetMapping(value = "/delete/{id}")
    @PreAuthorize("hasAuthority('cms:CmsSubjectCategory:delete')")
    public Object deleteCmsSubjectCategory(@ApiParam("专题分类表id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("专题分类表id");
            }
            if (ICmsSubjectCategoryService.removeById(id)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("删除专题分类表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "cms", REMARK = "给专题分类表分配专题分类表")
    @ApiOperation("查询专题分类表明细")
    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('cms:CmsSubjectCategory:read')")
    public Object getCmsSubjectCategoryById(@ApiParam("专题分类表id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("专题分类表id");
            }
            CmsSubjectCategory coupon = ICmsSubjectCategoryService.getById(id);
            return new CommonResult().success(coupon);
        } catch (Exception e) {
            log.error("查询专题分类表明细：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }

    }

    @ApiOperation(value = "批量删除专题分类表")
    @RequestMapping(value = "/delete/batch", method = RequestMethod.POST)
    @ResponseBody
    @SysLog(MODULE = "pms", REMARK = "批量删除专题分类表")
    @PreAuthorize("hasAuthority('cms:CmsSubjectCategory:delete')")
    public Object deleteBatch(@RequestParam("ids") List<Long> ids) {
        boolean count = ICmsSubjectCategoryService.removeByIds(ids);
        if (count) {
            return new CommonResult().success(count);
        } else {
            return new CommonResult().failed();
        }
    }

}
