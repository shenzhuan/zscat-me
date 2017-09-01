 /*
  * Powered By zsCat, Since 2014 - 2020
  */
package com.zscat.shop.service.impl;

import java.util.List;

import com.alibaba.dubbo.config.annotation.Service;

import com.zsCat.common.base.ServiceMybatis;
import com.zsCat.common.utils.PasswordEncoder;
import com.zscat.shop.model.Member;
import com.zscat.shop.service.MemberService;


 /**
 * 
 * @author zsCat 2017-4-14 13:56:18
 * @Email: 951449465@qq.com
 * @version 4.0v
 */
@Service(version = "1.0.0",retries = 0,timeout = 60000)
public class MemberServiceImpl extends ServiceMybatis<Member> implements MemberService {

	@Override
	public Member checkUser(String username, String password) {
		Member sysUser = new Member();
		String secPwd = PasswordEncoder.encrypt(password, username);
		sysUser.setUsername(username);
		sysUser.setPassword(secPwd);
		List<Member> users = this.select(sysUser);
		if(users != null && users.size() == 1 && users.get(0) != null) {
			return users.get(0);
		}
		return null;
	}

  
    
}
