package com.mallplus.pms.service;

import com.mallplus.UserCenterApp;
import com.mallplus.common.model.SysRoleMenu;
import com.mallplus.user.service.ISysRolePermissionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * SysUserServiceTest单元测试用例
 *
 * @author mall
 */
@SpringBootTest(classes = UserCenterApp.class)
@RunWith(SpringRunner.class)
public class SysUserServiceTest {
	@Autowired
	private ISysRolePermissionService rolePermissionService;

	@Test
	public void testFindByUsername() {
		List<SysRoleMenu> list = new ArrayList<>();
		for (int i=1;i<150;i++){
			SysRoleMenu r = new SysRoleMenu();
			r.setPermissionId(Long.valueOf(i));
			r.setRoleId(1L);
			list.add(r);

		}
		rolePermissionService.saveBatch(list);
	}


}
