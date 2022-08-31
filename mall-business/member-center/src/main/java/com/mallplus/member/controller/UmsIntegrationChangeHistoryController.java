package com.mallplus.member.controller;

import com.mallplus.common.utils.CommonResult;



import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import com.mallplus.common.entity.ums.UmsIntegrationChangeHistory;
import com.mallplus.member.service.IUmsIntegrationChangeHistoryService;
import com.mallplus.common.utils.ValidatorUtils;
import com.mallplus.common.annotation.SysLog;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 积分变化历史记录表
 * </p>
 *
 * @author zscat
 * @since 2019-04-19
 */
@Slf4j
@RestController
@Api(tags = "UmsIntegrationChangeHistoryController", description = "积分变化历史记录表管理")
@RequestMapping("/ums/UmsIntegrationChangeHistory")
public class UmsIntegrationChangeHistoryController {
    @Resource
    private IUmsIntegrationChangeHistoryService IUmsIntegrationChangeHistoryService;

    @SysLog(MODULE = "ums", REMARK = "根据条件查询所有积分变化历史记录表列表")
    @ApiOperation("根据条件查询所有积分变化历史记录表列表")
    @GetMapping(value = "/list")
    @PreAuthorize("hasAuthority('ums:UmsIntegrationChangeHistory:read')")
    public Object getUmsIntegrationChangeHistoryByPage(UmsIntegrationChangeHistory entity,
                                                       @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                       @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize
    ) {
        try {
            return new CommonResult().success(IUmsIntegrationChangeHistoryService.page(new Page<UmsIntegrationChangeHistory>(pageNum, pageSize), new QueryWrapper<>(entity)));
        } catch (Exception e) {
            log.error("根据条件查询所有积分变化历史记录表列表：%s", e.getMessage(), e);
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "ums", REMARK = "保存积分变化历史记录表")
    @ApiOperation("保存积分变化历史记录表")
    @PostMapping(value = "/create")
    @PreAuthorize("hasAuthority('ums:UmsIntegrationChangeHistory:create')")
    public Object saveUmsIntegrationChangeHistory(@RequestBody UmsIntegrationChangeHistory entity) {
        try {
            if (IUmsIntegrationChangeHistoryService.save(entity)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("保存积分变化历史记录表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "ums", REMARK = "更新积分变化历史记录表")
    @ApiOperation("更新积分变化历史记录表")
    @PostMapping(value = "/update/{id}")
    @PreAuthorize("hasAuthority('ums:UmsIntegrationChangeHistory:update')")
    public Object updateUmsIntegrationChangeHistory(@RequestBody UmsIntegrationChangeHistory entity) {
        try {
            if (IUmsIntegrationChangeHistoryService.updateById(entity)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("更新积分变化历史记录表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "ums", REMARK = "删除积分变化历史记录表")
    @ApiOperation("删除积分变化历史记录表")
    @GetMapping(value = "/delete/{id}")
    @PreAuthorize("hasAuthority('ums:UmsIntegrationChangeHistory:delete')")
    public Object deleteUmsIntegrationChangeHistory(@ApiParam("积分变化历史记录表id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("积分变化历史记录表id");
            }
            if (IUmsIntegrationChangeHistoryService.removeById(id)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("删除积分变化历史记录表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "ums", REMARK = "给积分变化历史记录表分配积分变化历史记录表")
    @ApiOperation("查询积分变化历史记录表明细")
    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('ums:UmsIntegrationChangeHistory:read')")
    public Object getUmsIntegrationChangeHistoryById(@ApiParam("积分变化历史记录表id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("积分变化历史记录表id");
            }
            UmsIntegrationChangeHistory coupon = IUmsIntegrationChangeHistoryService.getById(id);
            return new CommonResult().success(coupon);
        } catch (Exception e) {
            log.error("查询积分变化历史记录表明细：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }

    }

    @ApiOperation(value = "批量删除积分变化历史记录表")
    @RequestMapping(value = "/delete/batch", method = RequestMethod.POST)
    @ResponseBody
    @SysLog(MODULE = "pms", REMARK = "批量删除积分变化历史记录表")
    @PreAuthorize("hasAuthority('ums:UmsIntegrationChangeHistory:delete')")
    public Object deleteBatch(@RequestParam("ids") List<Long> ids) {
        boolean count = IUmsIntegrationChangeHistoryService.removeByIds(ids);
        if (count) {
            return new CommonResult().success(count);
        } else {
            return new CommonResult().failed();
        }
    }

}
