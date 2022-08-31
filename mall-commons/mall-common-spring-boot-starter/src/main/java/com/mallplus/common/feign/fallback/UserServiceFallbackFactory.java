package com.mallplus.common.feign.fallback;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mallplus.common.feign.UserService;
import com.mallplus.common.model.*;
import com.mallplus.common.utils.CommonResult;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * userService降级工场
 *
 * @author mall
 * @date 2019/1/18
 */
@Slf4j
@Component
public class UserServiceFallbackFactory implements FallbackFactory<UserService> {
    @Override
    public UserService create(Throwable throwable) {
        return new UserService() {
            @Override
            public SysUser selectByUsername(String username) {
                log.error("通过用户名查询用户异常:{}", username, throwable);
                return new SysUser();
            }

            @Override
            public LoginAppUser findByUsername(String username) {
                log.error("通过用户名查询用户异常:{}", username, throwable);
                return new LoginAppUser();
            }

            @Override
            public LoginAppUser findByMobile(String mobile) {
                log.error("通过手机号查询用户异常:{}", mobile, throwable);
                return new LoginAppUser();
            }

            @Override
            public LoginAppUser findByOpenId(String openId) {
                log.error("通过openId查询用户异常:{}", openId, throwable);
                return new LoginAppUser();
            }
            @Override
            public LoginAppUser findById(Long id) {
                log.error("通过Id查询用户异常:{}", id, throwable);
                return new LoginAppUser();
            }
            @Override
            public Object saveSysAdminLog(SysAdminLog entity){
                log.error("保存日志异常:{}", entity, throwable);
                return new CommonResult().failed();
            }

            @Override
            public SysStore selectStoreById(Long id) {
                return null;
            }

            @Override
            public Object selectStoreList(QueryWrapper<SysStore> sysStoreQueryWrapper) {
                return null;
            }

            @Override
            public List<SysRole> getRolesByUserId(Long id) {
                return null;
            }
        };
    }
}
