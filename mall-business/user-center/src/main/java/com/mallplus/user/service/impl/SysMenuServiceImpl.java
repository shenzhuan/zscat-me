package com.mallplus.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mallplus.common.model.SysPermission;
import com.mallplus.common.utils.ValidatorUtils;
import com.mallplus.common.vo.Tree;
import com.mallplus.user.mapper.SysMenuMapper;
import com.mallplus.user.mapper.SysRolePermissionMapper;
import com.mallplus.user.model.BuildTree;
import com.mallplus.user.model.SysPermissionNode;
import com.mallplus.user.service.ISysMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author 作者 owen E-mail: 624191343@qq.com
 */
@Slf4j
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysPermission> implements ISysMenuService {
 	@Resource
	private SysRolePermissionMapper sysRoleMenuMapper;
	@Override
	public int updateShowStatus(List<Long> ids, Integer showStatus) {
		SysPermission productCategory = new SysPermission();
		productCategory.setStatus(showStatus);
		return baseMapper.update(productCategory, new QueryWrapper<SysPermission>().in("id",ids));

	}

	@Override
	public List<Tree<SysPermission>> getPermissionsByUserId(Long id) {
		List<Tree<SysPermission>> trees = new ArrayList<Tree<SysPermission>>();
		//  List<SysPermission> menuDOs = permissionMapper.listMenuByUserId(id);
		List<SysPermission> menuDOs = baseMapper.listMenuByUserId(id);
		for (SysPermission sysMenuDO : menuDOs) {
			Tree<SysPermission> tree = new Tree<SysPermission>();
			tree.setId(sysMenuDO.getId().toString());
			if (ValidatorUtils.notEmpty(sysMenuDO.getPid())) {
				tree.setParentId(sysMenuDO.getPid().toString());
			}
			tree.setTitle(sysMenuDO.getName());
			Map<String, Object> attributes = new HashMap<>(16);
			attributes.put("url", sysMenuDO.getUri());
			attributes.put("icon", sysMenuDO.getIcon());
			tree.setMeta(attributes);
			trees.add(tree);
		}
		// 默认顶级菜单为０，根据数据库实际情况调整
		List<Tree<SysPermission>> list = BuildTree.buildList(trees, "0");
		return list;
	}
	/**
	 * 将权限转换为带有子级的权限对象
	 * 当找不到子级权限的时候map操作不会再递归调用covert
	 */
	private SysPermissionNode covert(SysPermission permission, List<SysPermission> permissionList) {
		SysPermissionNode node = new SysPermissionNode();
		BeanUtils.copyProperties(permission, node);
		List<SysPermissionNode> children = permissionList.stream()
				.filter(subPermission -> subPermission.getPid().equals(permission.getId()))
				.map(subPermission -> covert(subPermission, permissionList)).collect(Collectors.toList());
		node.setChildren(children);
		return node;
	}
	@Override
	public List<SysPermissionNode> treeList() {
		List<SysPermission> permissionList = baseMapper.selectList(new QueryWrapper<SysPermission>().orderByAsc("sort"));
		List<SysPermissionNode> result = permissionList.stream()
				.filter(permission -> permission.getPid().equals(0L))
				.map(permission -> covert(permission, permissionList)).collect(Collectors.toList());
		return result;
	}


	/**
     * 查询所有菜单
     */
	@Override
	public List<SysPermission> findAll() {
		return baseMapper.selectList(
                new QueryWrapper<SysPermission>().orderByAsc("sort")
        );
	}

    /**
     * 查询所有一级菜单
     */
	@Override
	public List<SysPermission> findOnes() {
        return baseMapper.selectList(
                new QueryWrapper<SysPermission>()
                        .eq("type", 1)
                        .orderByAsc("sort")
        );
	}
}
