package com.mallplus.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mallplus.common.model.SysRoleUser;
import com.mallplus.user.mapper.SysUserRoleMapper;
import com.mallplus.user.service.ISysUserRoleService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 后台用户和角色关系表 服务实现类
 * </p>
 *
 * @author zscat
 * @since 2019-04-14
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysRoleUser> implements ISysUserRoleService {

}
