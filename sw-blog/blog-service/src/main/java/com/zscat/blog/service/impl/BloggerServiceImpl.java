 /*
  * Powered By zsCat, Since 2014 - 2020
  */
package com.zscat.blog.service.impl;

import java.util.List;

import com.zsCat.common.base.ServiceMybatis;
import com.zsCat.common.utils.PasswordEncoder;
import com.zscat.blog.api.model.Blogger;
import com.zscat.blog.api.service.BloggerService;
import com.alibaba.dubbo.config.annotation.Service;



/**
 * 
 * @author zsCat 2017-4-14 13:56:18
 * @Email: 951449465@qq.com
 * @version 4.0v
 */
@Service(version = "1.0.0",retries = 0,timeout = 60000)
public class BloggerServiceImpl extends ServiceMybatis<Blogger> implements BloggerService {

	@Override
	public Blogger checkBlogger(String username, String password) {
		Blogger sysUser = new Blogger();
		String secPwd = PasswordEncoder.encrypt(password, username);
		sysUser.setUsername(username);
		sysUser.setPassword(secPwd);
		List<Blogger> users = this.select(sysUser);
		if(users != null && users.size() == 1 && users.get(0) != null) {
			return users.get(0);
		}
		return null;
	}

 

    
}
