package com.mallplus.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mallplus.common.annotation.SysLog;
import com.mallplus.common.model.SysDict;
import com.mallplus.common.utils.CommonResult;
import com.mallplus.common.utils.ValidatorUtils;
import com.mallplus.user.service.ISysDictService;
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
 * 系统配置信息表
 * </p>
 *
 * @author zscat
 * @since 2019-05-21
 */
@Slf4j
@RestController
@Api(tags = "SysDictController", description = "系统配置信息表管理")
@RequestMapping("/sys/SysDict")
public class SysDictController {
    @Resource
    private ISysDictService ISysDictService;

    @SysLog(MODULE = "sys", REMARK = "根据条件查询所有系统配置信息表列表")
    @ApiOperation("根据条件查询所有系统配置信息表列表")
    @GetMapping(value = "/list")
    @PreAuthorize("hasAuthority('sys:SysDict:read')")
    public Object getSysDictByPage(SysDict entity,
                                   @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                   @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize
    ) {
        try {
            return new CommonResult().success(ISysDictService.page(new Page<SysDict>(pageNum, pageSize), new QueryWrapper<>(entity)));
        } catch (Exception e) {
            log.error("根据条件查询所有系统配置信息表列表：%s", e.getMessage(), e);
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "sys", REMARK = "保存系统配置信息表")
    @ApiOperation("保存系统配置信息表")
    @PostMapping(value = "/create")
    @PreAuthorize("hasAuthority('sys:SysDict:create')")
    public Object saveSysDict(@RequestBody SysDict entity) {
        try {
            if (ISysDictService.save(entity)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("保存系统配置信息表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "sys", REMARK = "更新系统配置信息表")
    @ApiOperation("更新系统配置信息表")
    @PostMapping(value = "/update/{id}")
    @PreAuthorize("hasAuthority('sys:SysDict:update')")
    public Object updateSysDict(@RequestBody SysDict entity) {
        try {
            if (ISysDictService.updateById(entity)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("更新系统配置信息表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "sys", REMARK = "删除系统配置信息表")
    @ApiOperation("删除系统配置信息表")
    @GetMapping(value = "/delete/{id}")
    @PreAuthorize("hasAuthority('sys:SysDict:delete')")
    public Object deleteSysDict(@ApiParam("系统配置信息表id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("系统配置信息表id");
            }
            if (ISysDictService.removeById(id)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("删除系统配置信息表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "sys", REMARK = "给系统配置信息表分配系统配置信息表")
    @ApiOperation("查询系统配置信息表明细")
    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('sys:SysDict:read')")
    public Object getSysDictById(@ApiParam("系统配置信息表id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("系统配置信息表id");
            }
            SysDict coupon = ISysDictService.getById(id);
            return new CommonResult().success(coupon);
        } catch (Exception e) {
            log.error("查询系统配置信息表明细：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }

    }

    @ApiOperation(value = "批量删除系统配置信息表")
    @RequestMapping(value = "/delete/batch", method = RequestMethod.POST)
    @ResponseBody
    @SysLog(MODULE = "pms", REMARK = "批量删除系统配置信息表")
    @PreAuthorize("hasAuthority('sys:SysDict:delete')")
    public Object deleteBatch(@RequestParam("ids") List<Long> ids) {
        boolean count = ISysDictService.removeByIds(ids);
        if (count) {
            return new CommonResult().success(count);
        } else {
            return new CommonResult().failed();
        }
    }

}
