package com.mallplus.cms.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mallplus.common.annotation.SysLog;
import com.mallplus.common.entity.cms.CmsPrefrenceArea;
import com.mallplus.cms.service.ICmsPrefrenceAreaService;
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
 * 优选专区
 * </p>
 *
 * @author zscat
 * @since 2019-04-19
 */
@Slf4j
@RestController
@Api(tags = "CmsPrefrenceAreaController", description = "优选专区管理")
@RequestMapping("/cms/CmsPrefrenceArea")
public class CmsPrefrenceAreaController {
    @Resource
    private ICmsPrefrenceAreaService ICmsPrefrenceAreaService;

    @SysLog(MODULE = "cms", REMARK = "根据条件查询所有优选专区列表")
    @ApiOperation("根据条件查询所有优选专区列表")
    @GetMapping(value = "/list")
    @PreAuthorize("hasAuthority('cms:CmsPrefrenceArea:read')")
    public Object getCmsPrefrenceAreaByPage(CmsPrefrenceArea entity,
                                            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize
    ) {
        try {
            return new CommonResult().success(ICmsPrefrenceAreaService.page(new Page<CmsPrefrenceArea>(pageNum, pageSize), new QueryWrapper<>(entity)));
        } catch (Exception e) {
            log.error("根据条件查询所有优选专区列表：%s", e.getMessage(), e);
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "cms", REMARK = "保存优选专区")
    @ApiOperation("保存优选专区")
    @PostMapping(value = "/create")
    @PreAuthorize("hasAuthority('cms:CmsPrefrenceArea:create')")
    public Object saveCmsPrefrenceArea(@RequestBody CmsPrefrenceArea entity) {
        try {
            if (ICmsPrefrenceAreaService.save(entity)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("保存优选专区：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "cms", REMARK = "更新优选专区")
    @ApiOperation("更新优选专区")
    @PostMapping(value = "/update/{id}")
    @PreAuthorize("hasAuthority('cms:CmsPrefrenceArea:update')")
    public Object updateCmsPrefrenceArea(@RequestBody CmsPrefrenceArea entity) {
        try {
            if (ICmsPrefrenceAreaService.updateById(entity)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("更新优选专区：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "cms", REMARK = "删除优选专区")
    @ApiOperation("删除优选专区")
    @GetMapping(value = "/delete/{id}")
    @PreAuthorize("hasAuthority('cms:CmsPrefrenceArea:delete')")
    public Object deleteCmsPrefrenceArea(@ApiParam("优选专区id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("优选专区id");
            }
            if (ICmsPrefrenceAreaService.removeById(id)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("删除优选专区：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "cms", REMARK = "给优选专区分配优选专区")
    @ApiOperation("查询优选专区明细")
    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('cms:CmsPrefrenceArea:read')")
    public Object getCmsPrefrenceAreaById(@ApiParam("优选专区id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("优选专区id");
            }
            CmsPrefrenceArea coupon = ICmsPrefrenceAreaService.getById(id);
            return new CommonResult().success(coupon);
        } catch (Exception e) {
            log.error("查询优选专区明细：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }

    }

    @ApiOperation(value = "批量删除优选专区")
    @RequestMapping(value = "/delete/batch", method = RequestMethod.POST)
    @ResponseBody
    @SysLog(MODULE = "pms", REMARK = "批量删除优选专区")
    @PreAuthorize("hasAuthority('cms:CmsPrefrenceArea:delete')")
    public Object deleteBatch(@RequestParam("ids") List<Long> ids) {
        boolean count = ICmsPrefrenceAreaService.removeByIds(ids);
        if (count) {
            return new CommonResult().success(count);
        } else {
            return new CommonResult().failed();
        }
    }

}
