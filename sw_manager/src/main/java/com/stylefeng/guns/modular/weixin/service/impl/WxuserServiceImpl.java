package com.stylefeng.guns.modular.weixin.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.modular.weixin.dao.WxmessageDao;
import com.stylefeng.guns.modular.weixin.dao.WxuserDao;
import com.stylefeng.guns.modular.weixin.model.WxMessage;
import com.stylefeng.guns.modular.weixin.model.WxUser;
import org.springframework.stereotype.Service;
import com.stylefeng.guns.modular.weixin.service.IWxuserService;

/**
 * 微信后台Dao
 *
 * @author fengshuonan
 * @Date 2017-08-01 19:04:49
 */
@Service
public class WxuserServiceImpl extends ServiceImpl<WxuserDao, WxUser>  implements IWxuserService {


}
