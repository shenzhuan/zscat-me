package com.mallplus.member.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mallplus.common.annotation.SysLog;
import com.mallplus.common.utils.CommonResult;
import com.mallplus.common.utils.ValidatorUtils;
import com.mallplus.common.entity.ums.UmsGrowthChangeHistory;
import com.mallplus.member.service.IUmsGrowthChangeHistoryService;
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
 * 成长值变化历史记录表
 * </p>
 *
 * @author zscat
 * @since 2019-04-19
 */
@Slf4j
@RestController
@Api(tags = "UmsGrowthChangeHistoryController", description = "成长值变化历史记录表管理")
@RequestMapping("/ums/UmsGrowthChangeHistory")
public class UmsGrowthChangeHistoryController {
    @Resource
    private IUmsGrowthChangeHistoryService IUmsGrowthChangeHistoryService;

    @SysLog(MODULE = "ums", REMARK = "根据条件查询所有成长值变化历史记录表列表")
    @ApiOperation("根据条件查询所有成长值变化历史记录表列表")
    @GetMapping(value = "/list")
    @PreAuthorize("hasAuthority('ums:UmsGrowthChangeHistory:read')")
    public Object getUmsGrowthChangeHistoryByPage(UmsGrowthChangeHistory entity,
                                                  @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                  @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize
    ) {
        try {
            return new CommonResult().success(IUmsGrowthChangeHistoryService.page(new Page<UmsGrowthChangeHistory>(pageNum, pageSize), new QueryWrapper<>(entity)));
        } catch (Exception e) {
            log.error("根据条件查询所有成长值变化历史记录表列表：%s", e.getMessage(), e);
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "ums", REMARK = "保存成长值变化历史记录表")
    @ApiOperation("保存成长值变化历史记录表")
    @PostMapping(value = "/create")
    @PreAuthorize("hasAuthority('ums:UmsGrowthChangeHistory:create')")
    public Object saveUmsGrowthChangeHistory(@RequestBody UmsGrowthChangeHistory entity) {
        try {
            if (IUmsGrowthChangeHistoryService.save(entity)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("保存成长值变化历史记录表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "ums", REMARK = "更新成长值变化历史记录表")
    @ApiOperation("更新成长值变化历史记录表")
    @PostMapping(value = "/update/{id}")
    @PreAuthorize("hasAuthority('ums:UmsGrowthChangeHistory:update')")
    public Object updateUmsGrowthChangeHistory(@RequestBody UmsGrowthChangeHistory entity) {
        try {
            if (IUmsGrowthChangeHistoryService.updateById(entity)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("更新成长值变化历史记录表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "ums", REMARK = "删除成长值变化历史记录表")
    @ApiOperation("删除成长值变化历史记录表")
    @GetMapping(value = "/delete/{id}")
    @PreAuthorize("hasAuthority('ums:UmsGrowthChangeHistory:delete')")
    public Object deleteUmsGrowthChangeHistory(@ApiParam("成长值变化历史记录表id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("成长值变化历史记录表id");
            }
            if (IUmsGrowthChangeHistoryService.removeById(id)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("删除成长值变化历史记录表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "ums", REMARK = "给成长值变化历史记录表分配成长值变化历史记录表")
    @ApiOperation("查询成长值变化历史记录表明细")
    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('ums:UmsGrowthChangeHistory:read')")
    public Object getUmsGrowthChangeHistoryById(@ApiParam("成长值变化历史记录表id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("成长值变化历史记录表id");
            }
            UmsGrowthChangeHistory coupon = IUmsGrowthChangeHistoryService.getById(id);
            return new CommonResult().success(coupon);
        } catch (Exception e) {
            log.error("查询成长值变化历史记录表明细：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }

    }

    @ApiOperation(value = "批量删除成长值变化历史记录表")
    @RequestMapping(value = "/delete/batch", method = RequestMethod.POST)
    @ResponseBody
    @SysLog(MODULE = "pms", REMARK = "批量删除成长值变化历史记录表")
    @PreAuthorize("hasAuthority('ums:UmsGrowthChangeHistory:delete')")
    public Object deleteBatch(@RequestParam("ids") List<Long> ids) {
        boolean count = IUmsGrowthChangeHistoryService.removeByIds(ids);
        if (count) {
            return new CommonResult().success(count);
        } else {
            return new CommonResult().failed();
        }
    }

}
