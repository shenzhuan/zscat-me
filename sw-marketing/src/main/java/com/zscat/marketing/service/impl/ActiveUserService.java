package com.zscat.marketing.service.impl;

import com.zscat.base.ServiceMybatis;
import com.zscat.marketing.model.ActiveUser;
import com.zscat.marketing.service.IActiveUserService;
import org.springframework.stereotype.Service;

/**
 * @author : zscat
 * @version : 1.0
 * @created on  : 2017/6/27  下午5:43
 */
@Service
public class ActiveUserService extends ServiceMybatis<ActiveUser> implements IActiveUserService {
}
