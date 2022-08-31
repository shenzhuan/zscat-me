package com.mallplus.common.feign;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mallplus.common.constant.ServiceNameConstants;
import com.mallplus.common.feign.fallback.UserServiceFallbackFactory;
import com.mallplus.common.model.*;
import com.mallplus.common.vo.SmsCode;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author mall
 */
@FeignClient(name = ServiceNameConstants.USER_SERVICE, fallbackFactory = UserServiceFallbackFactory.class, decode404 = true)
public interface UserService {
    /**
     * feign rpc访问远程/users/{username}接口
     * 查询用户实体对象SysUser
     *
     * @param username
     * @return
     */
    @GetMapping(value = "/users/name/{username}")
    SysUser selectByUsername(@PathVariable("username") String username);

    /**
     * feign rpc访问远程/users-anon/login接口
     *
     * @param username
     * @return
     */
    @GetMapping(value = "/users-anon/login", params = "username")
    LoginAppUser findByUsername(@RequestParam("username") String username);

    /**
     * 通过手机号查询用户、角色信息
     *
     * @param mobile 手机号
     */
    @GetMapping(value = "/users-anon/mobile", params = "mobile")
    LoginAppUser findByMobile(@RequestParam("mobile") String mobile);

    /**
     * 根据OpenId查询用户信息
     *
     * @param openId openId
     */
    @GetMapping(value = "/users-anon/openId", params = "openId")
    LoginAppUser findByOpenId(@RequestParam("openId") String openId);

    @GetMapping(value = "/users-anon/id", params = "id")
    LoginAppUser findById(Long id);

    @PostMapping(value = "/sys/SysAdminLog/create")
     Object saveSysAdminLog(@RequestBody SysAdminLog entity);

    @GetMapping(value = "/notAuth/selectStoreList")
    SysStore selectStoreById(@RequestParam("id") Long id);

    @GetMapping(value = "/notAuth/selectStoreList")
    Object selectStoreList(@RequestParam("sysStoreQueryWrapper") QueryWrapper<SysStore> sysStoreQueryWrapper);

    @GetMapping("/users/getRolesByUserId")
    List<SysRole> getRolesByUserId(@RequestParam("id") Long id);


}
