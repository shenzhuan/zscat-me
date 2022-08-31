package com.mallplus.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mallplus.common.utils.CommonResult;
import com.mallplus.common.vo.SmsCode;
import com.mallplus.common.entity.ums.UmsMember;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author zscat
 * @since 2019-04-19
 */
public interface IUmsMemberService extends IService<UmsMember> {

    Object loginByWeixin(HttpServletRequest req);



    /**
     * 根据用户名获取会员
     */
    UmsMember getByUsername(String username);

    /**
     * 根据会员编号获取会员
     */
    UmsMember getById(Long id);

    /**
     * 用户注册
     */
    @Transactional
    CommonResult register(String username, String password, String telephone, String authCode);

    /**
     * 生成验证码
     */
    CommonResult generateAuthCode(String telephone);

    /**
     * 修改密码
     */
    @Transactional
    CommonResult updatePassword(String telephone, String password, String authCode);

    /**
     * 获取当前登录会员
     */
    UmsMember getCurrentMember();

    /**
     * 根据会员id修改会员积分
     */
    void updateIntegration(Long id, Integer integration);


    public UmsMember queryByOpenId(String openId);


    Map<String, Object> login(UmsMember umsMember);

    String refreshToken(String token);

    Object register(UmsMember umsMember);


    SmsCode generateCode(String phone);

    UmsMember findByOpenId(String openId);

    void updataMemberOrderInfo();
}

