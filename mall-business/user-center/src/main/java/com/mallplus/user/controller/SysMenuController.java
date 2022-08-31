package com.mallplus.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mallplus.common.annotation.LoginUser;
import com.mallplus.common.annotation.SysLog;
import com.mallplus.common.model.PageResult;
import com.mallplus.common.model.Result;
import com.mallplus.common.model.SysPermission;
import com.mallplus.common.model.SysUser;
import com.mallplus.common.utils.CommonResult;
import com.mallplus.common.utils.ValidatorUtils;
import com.mallplus.user.model.SysPermissionNode;
import com.mallplus.user.service.ISysMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @author 作者 mallplus E-mail: 951449465@qq.com
 */
@RestController
@Api(tags = "菜单模块api")
@Slf4j
@RequestMapping("/sys/SysPermission")
public class SysMenuController {
    @Autowired
    private ISysMenuService menuService;





    @SysLog(MODULE = "sys", REMARK = "根据条件查询所有后台用户权限表列表")
    @ApiOperation("根据条件查询所有后台用户权限表列表")
    @GetMapping(value = "/list")
    @PreAuthorize("hasAuthority('sys:SysPermission:read')")
    public Object getRoleByPage(SysPermission entity,
                                @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize
    ) {
        try {
            if (ValidatorUtils.notEmpty(entity.getName())) {
                return new CommonResult().success(menuService.list(new QueryWrapper<SysPermission>(new SysPermission()).like("name" , entity.getName()).orderByAsc("sort")));
            }
            return new CommonResult().success(menuService.list(new QueryWrapper<>(entity).orderByAsc("sort")));

        } catch (Exception e) {
            log.error("根据条件查询所有后台用户权限表列表：%s", e.getMessage(), e);
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "sys", REMARK = "保存后台用户权限表")
    @ApiOperation("保存后台用户权限表")
    @PostMapping(value = "/create")
    @PreAuthorize("hasAuthority('sys:SysPermission:create')")
    public Object saveRole(@RequestBody SysPermission entity) {
        try {
            entity.setStatus(1);
            entity.setCreateTime(new Date());
            entity.setSort(0);
            if (menuService.save(entity)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("保存后台用户权限表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "sys", REMARK = "更新后台用户权限表")
    @ApiOperation("更新后台用户权限表")
    @PostMapping(value = "/update/{id}")
    @PreAuthorize("hasAuthority('sys:SysPermission:update')")
    public Object updateRole(@RequestBody SysPermission entity) {
        try {
            if (menuService.updateById(entity)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("更新后台用户权限表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "sys", REMARK = "删除后台用户权限表")
    @ApiOperation("删除后台用户权限表")
    @GetMapping(value = "/delete/{id}")
    @PreAuthorize("hasAuthority('sys:SysPermission:delete')")
    public Object deleteRole(@ApiParam("后台用户权限表id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("后台用户权限表id");
            }
            if (menuService.removeById(id)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("删除后台用户权限表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "sys", REMARK = "给后台用户权限表分配后台用户权限表")
    @ApiOperation("查询后台用户权限表明细")
    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('sys:SysPermission:read')")
    public Object getRoleById(@ApiParam("后台用户权限表id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("后台用户权限表id");
            }
            SysPermission coupon = menuService.getById(id);
            return new CommonResult().success(coupon);
        } catch (Exception e) {
            log.error("查询后台用户权限表明细：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }

    }

    @ApiOperation(value = "批量删除后台用户权限表")
    @RequestMapping(value = "/delete/batch", method = RequestMethod.POST)
    @SysLog(MODULE = "pms", REMARK = "批量删除后台用户权限表")
    @PreAuthorize("hasAuthority('sys:SysPermission:delete')")
    public Object deleteBatch(@RequestParam("ids") List<Long> ids) {
        boolean count = menuService.removeByIds(ids);
        if (count) {
            return new CommonResult().success(count);
        } else {
            return new CommonResult().failed();
        }
    }


    @ApiOperation(value = "查询所有菜单")
    @GetMapping("/findAlls")
    public PageResult<SysPermission> findAlls() {
        List<SysPermission> list = menuService.findAll();
        return PageResult.<SysPermission>builder().data(list).code(0).count((long) list.size()).build();
    }

    @ApiOperation(value = "获取菜单以及顶级菜单")
    @GetMapping("/findOnes")
    public PageResult<SysPermission> findOnes() {
        List<SysPermission> list = menuService.findOnes();
        return PageResult.<SysPermission>builder().data(list).code(0).count((long) list.size()).build();
    }

    /**
     * 添加菜单 或者 更新
     *
     * @param menu
     * @return
     */
    @ApiOperation(value = "新增菜单")
    @PostMapping("saveOrUpdate")
    public Result saveOrUpdate(@RequestBody SysPermission menu) {
        try {
            menuService.saveOrUpdate(menu);
            return Result.succeed("操作成功");
        } catch (Exception ex) {
            log.error("memu-saveOrUpdate-error", ex);
            return Result.failed("操作失败");
        }
    }


    @SysLog(MODULE = "sys", REMARK = "获取所有权限列表")
    @ApiOperation("获取所有权限列表")
    @RequestMapping(value = "/findPermissions", method = RequestMethod.GET)
    public Object findPermissions(@LoginUser(isFull = true) SysUser user) {
        Long userId = user.getId();
        return new CommonResult().success(menuService.getPermissionsByUserId(userId));
    }

    @SysLog(MODULE = "sys", REMARK = "以层级结构返回所有权限")
    @ApiOperation("以层级结构返回所有权限")
    @RequestMapping(value = "/treeList", method = RequestMethod.GET)
    public Object treeList() {
        List<SysPermissionNode> permissionNodeList = menuService.treeList();
        return new CommonResult().success(permissionNodeList);
    }
}
