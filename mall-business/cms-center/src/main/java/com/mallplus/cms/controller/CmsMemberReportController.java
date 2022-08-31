package com.mallplus.cms.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mallplus.common.annotation.SysLog;
import com.mallplus.common.entity.cms.CmsMemberReport;
import com.mallplus.cms.service.ICmsMemberReportService;
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
 * 用户举报表
 * </p>
 *
 * @author zscat
 * @since 2019-04-19
 */
@Slf4j
@RestController
@Api(tags = "CmsMemberReportController", description = "用户举报表管理")
@RequestMapping("/cms/CmsMemberReport")
public class CmsMemberReportController {
    @Resource
    private ICmsMemberReportService ICmsMemberReportService;

    @SysLog(MODULE = "cms", REMARK = "根据条件查询所有用户举报表列表")
    @ApiOperation("根据条件查询所有用户举报表列表")
    @GetMapping(value = "/list")
    @PreAuthorize("hasAuthority('cms:CmsMemberReport:read')")
    public Object getCmsMemberReportByPage(CmsMemberReport entity,
                                           @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                           @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize
    ) {
        try {
            return new CommonResult().success(ICmsMemberReportService.page(new Page<CmsMemberReport>(pageNum, pageSize), new QueryWrapper<>(entity)));
        } catch (Exception e) {
            log.error("根据条件查询所有用户举报表列表：%s", e.getMessage(), e);
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "cms", REMARK = "保存用户举报表")
    @ApiOperation("保存用户举报表")
    @PostMapping(value = "/create")
    @PreAuthorize("hasAuthority('cms:CmsMemberReport:create')")
    public Object saveCmsMemberReport(@RequestBody CmsMemberReport entity) {
        try {
            if (ICmsMemberReportService.save(entity)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("保存用户举报表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "cms", REMARK = "更新用户举报表")
    @ApiOperation("更新用户举报表")
    @PostMapping(value = "/update/{id}")
    @PreAuthorize("hasAuthority('cms:CmsMemberReport:update')")
    public Object updateCmsMemberReport(@RequestBody CmsMemberReport entity) {
        try {
            if (ICmsMemberReportService.updateById(entity)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("更新用户举报表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "cms", REMARK = "删除用户举报表")
    @ApiOperation("删除用户举报表")
    @GetMapping(value = "/delete/{id}")
    @PreAuthorize("hasAuthority('cms:CmsMemberReport:delete')")
    public Object deleteCmsMemberReport(@ApiParam("用户举报表id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("用户举报表id");
            }
            if (ICmsMemberReportService.removeById(id)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("删除用户举报表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "cms", REMARK = "给用户举报表分配用户举报表")
    @ApiOperation("查询用户举报表明细")
    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('cms:CmsMemberReport:read')")
    public Object getCmsMemberReportById(@ApiParam("用户举报表id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("用户举报表id");
            }
            CmsMemberReport coupon = ICmsMemberReportService.getById(id);
            return new CommonResult().success(coupon);
        } catch (Exception e) {
            log.error("查询用户举报表明细：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }

    }

    @ApiOperation(value = "批量删除用户举报表")
    @RequestMapping(value = "/delete/batch", method = RequestMethod.POST)
    @ResponseBody
    @SysLog(MODULE = "pms", REMARK = "批量删除用户举报表")
    @PreAuthorize("hasAuthority('cms:CmsMemberReport:delete')")
    public Object deleteBatch(@RequestParam("ids") List<Long> ids) {
        boolean count = ICmsMemberReportService.removeByIds(ids);
        if (count) {
            return new CommonResult().success(count);
        } else {
            return new CommonResult().failed();
        }
    }

}
