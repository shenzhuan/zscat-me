package com.mallplus.user.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mallplus.common.annotation.LoginUser;
import com.mallplus.common.annotation.SysLog;
import com.mallplus.common.constant.CommonConstant;
import com.mallplus.common.model.*;
import com.mallplus.common.utils.CommonResult;
import com.mallplus.common.utils.ExcelUtil;
import com.mallplus.common.utils.ValidatorUtils;
import com.mallplus.user.model.SysUserExcel;
import com.mallplus.user.service.ISysRoleService;
import com.mallplus.user.service.ISysUserService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * @author 作者 owen E-mail: 624191343@qq.com
 * 用户
 */
@Slf4j
@RestController
@Api(tags = "用户模块api")
public class SysUserController {
    private static final String ADMIN_CHANGE_MSG = "超级管理员不给予修改";

    @Autowired
    private ISysUserService appUserService;
    @Autowired
    private ISysRoleService roleService;

    /**
     * 当前登录用户 LoginAppUser
     *
     * @return
     */
    @ApiOperation(value = "根据access_token当前登录用户")
    @GetMapping("/users/current")
    public LoginAppUser getLoginAppUser(@LoginUser(isFull = true) SysUser user) {
        return appUserService.getLoginAppUser(user);
    }
    @SysLog(MODULE = "sys", REMARK = "获取当前登录用户信息")
    @ApiOperation(value = "获取当前登录用户信息")
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @ResponseBody
    public Object getAdminInfo(@LoginUser(isFull = true) SysUser user) {

        Map<String, Object> data = new HashMap<>();
        data.put("username", user.getUsername());
        data.put("roles", new String[]{"TEST"});
        data.put("icon", user.getIcon());
        return new CommonResult().success(data);
    }
    /**
     * 查询用户实体对象SysUser
     */
    @GetMapping(value = "/users/name/{username}")
    @ApiOperation(value = "根据用户名查询用户实体")
    @Cacheable(value = "user", key = "#username")
    public SysUser selectByUsername(@PathVariable String username) {
        return appUserService.selectByUsername(username);
    }

    /**
     * 查询用户登录对象LoginAppUser
     */

    @GetMapping(value = "/users-anon/login", params = "username")
    @ApiOperation(value = "根据用户名查询用户")
    public LoginAppUser findByUsername(@RequestParam("username") String username) {
        return appUserService.findByUsername(username);
    }

    /**
     * 通过手机号查询用户、角色信息
     *
     * @param mobile 手机号
     */
    @GetMapping(value = "/users-anon/mobile", params = "mobile")
    @ApiOperation(value = "根据手机号查询用户")
    public SysUser findByMobile(@RequestParam("mobile") String mobile) {
        return appUserService.findByMobile(mobile);
    }

    /**
     * 根据OpenId查询用户信息
     *
     * @param openId openId
     */
    @GetMapping(value = "/users-anon/openId", params = "openId")
    @ApiOperation(value = "根据OpenId查询用户")
    public SysUser findByOpenId(String openId) {
        return appUserService.findByOpenId(openId);
    }

    @GetMapping("/users/{id}")
    public SysUser findUserById(@PathVariable Long id) {
        return appUserService.getById(id);
    }

    /**
     * 管理后台修改用户
     *
     * @param sysUser
     */
    @PutMapping("/users")
    @CachePut(value = "user", key = "#sysUser.username")
    public void updateSysUser(@RequestBody SysUser sysUser) {
        appUserService.updateById(sysUser);
    }

    /**
     * 管理后台给用户分配角色
     *
     * @param id
     * @param roleIds
     */
    @PostMapping("/users/{id}/roles")
    public void setRoleToUser(@PathVariable Long id, @RequestBody Set<Long> roleIds) {
        appUserService.setRoleToUser(id, roleIds);
    }

    /**
     * 获取用户的角色
     *
     * @param
     * @return
     */
    @GetMapping("/users/{id}/roles")
    public List<SysRole> findRolesByUserId(@PathVariable Long id) {
        return appUserService.findRolesByUserId(id);
    }

    @GetMapping("/users/getRolesByUserId")
    public List<SysRole> getRolesByUserId(@RequestParam("id") Long id) {
        return appUserService.findRolesByUserId(id);
    }

    @SysLog(MODULE = "sys", REMARK = "根据条件查询所有用户列表")
    @ApiOperation("根据条件查询所有用户列表")
    @GetMapping(value = "/list")
    public Object getUserByPage(SysUser entity,
                                @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize
    ) {
        try {
            return new CommonResult().success(appUserService.page(new Page<SysUser>(pageNum, pageSize), new QueryWrapper<>(entity).orderByDesc("create_time")));
        } catch (Exception e) {
            log.error("根据条件查询所有用户列表：%s", e.getMessage(), e);
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "sys", REMARK = "保存用户")
    @ApiOperation("保存用户")
    @PostMapping(value = "/register")
    public Object saveUser(@RequestBody SysUser entity) {
        try {
            if (appUserService.saves(entity)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("保存用户：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "sys", REMARK = "更新用户")
    @ApiOperation("更新用户")
    @PostMapping(value = "/update/{id}")
    public Object updateUser(@RequestBody SysUser entity) {
        try {
            if (appUserService.updates(entity.getId(),entity)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("更新用户：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "sys", REMARK = "删除用户")
    @ApiOperation("删除用户")
    @GetMapping(value = "/delete/{id}")
    public Object deleteUser(@ApiParam("用户id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("用户id");
            }
            if (appUserService.removeById(id)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("删除用户：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "sys", REMARK = "给用户分配角色")
    @ApiOperation("查询用户明细")
    @GetMapping(value = "/{id}")
    public Object getUserById(@ApiParam("用户id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("用户id");
            }
            SysUser coupon = appUserService.getById(id);
            return new CommonResult().success(coupon);
        } catch (Exception e) {
            log.error("查询用户明细：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }

    }

    @SysLog(MODULE = "sys", REMARK = "获取指定用户的角色")
    @ApiOperation("获取指定用户的角色")
    @RequestMapping(value = "/userRoleCheck", method = RequestMethod.GET)
    @ResponseBody
    public Object userRoleCheck(@RequestParam("adminId") Long adminId) {

        List<SysRole> roleList = appUserService.findRolesByUserId(adminId);
        List<SysRole> allroleList = roleService.list(new QueryWrapper<>());
        if (roleList != null && roleList.size() > 0) {
        for (SysRole a : allroleList) {
            for (SysRole u : roleList) {
                if (u != null && u.getId() != null) {
                    if (a.getId().equals(u.getId())) {
                        a.setChecked(true);
                    }
                }
            }
        }
            return new CommonResult().success(allroleList);
        }
        return new CommonResult().success(allroleList);
    }
    /**
     * 修改用户状态
     *
     * @param params
     * @return
     */
    @ApiOperation(value = "修改用户状态")
    @GetMapping("/users/updateEnabled")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "enabled", value = "是否启用", required = true, dataType = "Boolean")
    })
    public Result updateEnabled(@RequestParam Map<String, Object> params) {
        Long id = MapUtils.getLong(params, "id");
        if (checkAdmin(id)) {
            return Result.failed(ADMIN_CHANGE_MSG);
        }
        return appUserService.updateEnabled(params);
    }

    /**
     * 管理后台，给用户重置密码
     *
     * @param id
     */
    @PutMapping(value = "/users/{id}/password")
    public Result resetPassword(@PathVariable Long id) {
        if (checkAdmin(id)) {
            return Result.failed(ADMIN_CHANGE_MSG);
        }
        appUserService.updatePassword(id, null, null);
        return Result.succeed("重置成功");
    }

    /**
     * 用户自己修改密码
     */
    @PutMapping(value = "/users/password")
    public Result resetPassword(@RequestBody SysUser sysUser) {
        if (checkAdmin(sysUser.getId())) {
            return Result.failed(ADMIN_CHANGE_MSG);
        }
        appUserService.updatePassword(sysUser.getId(), sysUser.getOldPassword(), sysUser.getNewPassword());
        return Result.succeed("重置成功");
    }


    /**
     * 导出excel
     *
     * @return
     */
    @PostMapping("/users/export")
    public void exportUser(@RequestParam Map<String, Object> params, HttpServletResponse response) throws IOException {
        List<SysUserExcel> result = appUserService.findAllUsers(params);
        //导出操作
        ExcelUtil.exportExcel(result, null, "用户", SysUserExcel.class, "user", response);
    }

    @PostMapping(value = "/users/import")
    public Result importExcl(@RequestParam("file") MultipartFile excl) throws Exception {
        int rowNum = 0;
        if(!excl.isEmpty()) {
            List<SysUserExcel> list = ExcelUtil.importExcel(excl, 0, 1, SysUserExcel.class);
            rowNum = list.size();
            if (rowNum > 0) {
                List<SysUser> users = new ArrayList<>(rowNum);
                list.forEach(u -> {
                    SysUser user = new SysUser();
                    BeanUtil.copyProperties(u, user);
                    user.setPassword(CommonConstant.DEF_USER_PASSWORD);
                 //   user.setType(UserType.BACKEND.name());
                    users.add(user);
                });
                appUserService.saveUsers(users);
            }
        }
        return Result.succeed("导入数据成功，一共【"+rowNum+"】行");
    }

    /**
     * 是否超级管理员
     */
    private boolean checkAdmin(long id) {
        return id == 1L;
    }
}
