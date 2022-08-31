package com.mallplus.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mallplus.common.annotation.SysLog;
import com.mallplus.common.model.SysRole;
import com.mallplus.common.model.SysRoleMenu;
import com.mallplus.common.utils.CommonResult;
import com.mallplus.common.utils.ValidatorUtils;
import com.mallplus.user.service.ISysRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 作者 mallplus E-mail: 951449465@qq.com
 * 角色管理
 */
@Slf4j
@RestController
@Api(tags = "角色模块api")
@RequestMapping("/sys/sysRole")
public class SysRoleController {
    @Autowired
    private ISysRoleService sysRoleService;


    @SysLog(MODULE = "sys", REMARK = "根据条件查询所有角色列表")
    @ApiOperation("根据条件查询所有角色列表")
    @GetMapping(value = "/list")
    @PreAuthorize("hasAuthority('sys:role:read')")
    public Object getRoleByPage(SysRole entity,
                                @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize
    ) {
        try {
            return new CommonResult().success(sysRoleService.page(new Page<SysRole>(pageNum, pageSize), new QueryWrapper<>(entity)));
        } catch (Exception e) {
            log.error("根据条件查询所有角色列表：%s", e.getMessage(), e);
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "sys", REMARK = "保存角色")
    @ApiOperation("保存角色")
    @PostMapping(value = "/create")
    @PreAuthorize("hasAuthority('sys:role:create')")
    public Object saveRole(@RequestBody SysRole entity) {
        try {
            if (sysRoleService.saves(entity)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("保存角色：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "sys", REMARK = "更新角色")
    @ApiOperation("更新角色")
    @PostMapping(value = "/update/{id}")
    @PreAuthorize("hasAuthority('sys:role:update')")
    public Object updateRole(@RequestBody SysRole entity) {
        try {
            if (sysRoleService.updates(entity)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("更新角色：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "sys", REMARK = "删除角色")
    @ApiOperation("删除角色")
    @GetMapping(value = "/delete/{id}")
    @PreAuthorize("hasAuthority('sys:role:delete')")
    public Object deleteRole(@ApiParam("角色id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("角色id");
            }
            if (sysRoleService.removeById(id)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("删除角色：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "sys", REMARK = "给角色分配角色")
    @ApiOperation("查询角色明细")
    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('sys:role:read')")
    public Object getRoleById(@ApiParam("角色id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("角色id");
            }
            SysRole coupon = sysRoleService.getById(id);
            return new CommonResult().success(coupon);
        } catch (Exception e) {
            log.error("查询角色明细：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }

    }

    @ApiOperation(value = "批量删除角色")
    @RequestMapping(value = "/delete/batch", method = RequestMethod.POST)
    @ResponseBody
    @SysLog(MODULE = "pms", REMARK = "批量删除角色")
    @PreAuthorize("hasAuthority('sys:role:delete')")
    public Object deleteBatch(@RequestParam("ids") List<Long> ids) {
        boolean count = sysRoleService.removeByIds(ids);
        if (count) {
            return new CommonResult().success(count);
        } else {
            return new CommonResult().failed();
        }
    }
    @SysLog(MODULE = "sys", REMARK = "获取相应角色权限")
    @ApiOperation("获取相应角色权限")
    @RequestMapping(value = "/permission/{roleId}", method = RequestMethod.GET)
    @ResponseBody
    public Object getPermissionList(@PathVariable Long roleId) {
        List<SysRoleMenu> permissionList = sysRoleService.getRolePermission(roleId);
        return new CommonResult().success(permissionList);
    }
    @SysLog(MODULE = "sys", REMARK = "获取相应角色权限-单表")
    @ApiOperation("获取相应角色权限-单表")
    @RequestMapping(value = "/rolePermission/{roleId}", method = RequestMethod.GET)
    @ResponseBody
    public Object rolePermission(@PathVariable Long  roleId) {
        List<SysRoleMenu> rolePermission = sysRoleService.getRolePermission(roleId);
        return new CommonResult().success(rolePermission);
    }
}
