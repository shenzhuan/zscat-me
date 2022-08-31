package com.mallplus.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mallplus.common.model.SysPermission;

import java.util.List;

/**
 * 菜单
 *
 * @author mall
 */
public interface SysMenuMapper extends BaseMapper<SysPermission> {

    List<SysPermission> listMenuByUserId(Long id);

    List<SysPermission> listPermissByUserId(Long roleId);

}
