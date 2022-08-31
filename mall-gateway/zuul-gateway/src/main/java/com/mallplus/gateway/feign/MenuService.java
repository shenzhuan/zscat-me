package com.mallplus.gateway.feign;

import com.mallplus.common.constant.ServiceNameConstants;
import com.mallplus.common.model.SysPermission;
import com.mallplus.gateway.feign.fallback.MenuServiceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author mall
 */
@FeignClient(name = ServiceNameConstants.USER_SERVICE, fallbackFactory = MenuServiceFallbackFactory.class, decode404 = true)
public interface MenuService {
	/**
	 * 角色菜单列表
	 * @param roleCodes
	 */
	@GetMapping(value = "/menus/{roleCodes}")
	List<SysPermission> findByRoleCodes(@PathVariable("roleCodes") String roleCodes);
}
