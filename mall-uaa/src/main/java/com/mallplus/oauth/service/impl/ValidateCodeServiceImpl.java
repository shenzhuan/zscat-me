package com.mallplus.oauth.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.mallplus.common.feign.UserService;
import com.mallplus.common.redis.template.RedisRepository;
import com.mallplus.common.constant.SecurityConstants;
import com.mallplus.common.model.Result;
import com.mallplus.common.model.SysUser;
import com.mallplus.oauth.exception.ValidateCodeException;
import com.mallplus.oauth.service.IValidateCodeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author mall
 * @date 2018/12/10
 */
@Slf4j
@Service
public class ValidateCodeServiceImpl implements IValidateCodeService {
    @Autowired
    private RedisRepository redisRepository;

    @Resource
    private UserService userService;

    /**
     * 保存用户验证码，和randomStr绑定
     *
     * @param client_id 客户端生成
     * @param imageCode 验证码信息
     */
    @Override
    public void saveImageCode(String client_id, String imageCode) {
        redisRepository.setExpire(buildKey(client_id), imageCode, SecurityConstants.DEFAULT_IMAGE_EXPIRE);
    }

    /**
     * 发送验证码
     * <p>
     * 1. 先去redis 查询是否 60S内已经发送
     * 2. 未发送： 判断手机号是否存 ? false :产生4位数字  手机号-验证码
     * 3. 发往消息中心-》发送信息
     * 4. 保存redis
     *
     * @param mobile 手机号
     * @return true、false
     */
    @Override
    public Result sendSmsCode(String mobile) {
        Object tempCode = redisRepository.get(buildKey(mobile));
        if (tempCode != null) {
            log.error("用户:{}验证码未失效{}", mobile, tempCode);
            return Result.failed("验证码未失效，请失效后再次申请");
        }

        SysUser user = userService.findByMobile(mobile);
        if (user != null) {
            log.error("根据用户手机号{}查询用户为空", mobile);
            return Result.failed("手机号不存在");
        }

        String code = RandomUtil.randomNumbers(4);
        log.info("短信发送请求消息中心 -> 手机号:{} -> 验证码：{}", mobile, code);
        redisRepository.setExpire(buildKey(mobile), code, SecurityConstants.DEFAULT_IMAGE_EXPIRE);
        return Result.succeed("true");
    }

    /**
     * 获取验证码
     * @param client_id 前端唯一标识/手机号
     */
    @Override
    public String getCode(String client_id) {
        return (String)redisRepository.get(buildKey(client_id));
    }

    /**
     * 删除验证码
     * @param client_id 前端唯一标识/手机号
     */
    @Override
    public void remove(String client_id) {
        redisRepository.del(buildKey(client_id));
    }

    /**
     * 验证验证码
     */
    @Override
    public void validate(HttpServletRequest request) {
        String client_id = request.getParameter("client_id");
        if (StringUtils.isBlank(client_id)) {
            throw new ValidateCodeException("请在请求参数中携带client_id参数");
        }
        String code = this.getCode(client_id);
        String codeInRequest;
        try {
            codeInRequest = ServletRequestUtils.getStringParameter(request, "validCode");
        } catch (ServletRequestBindingException e) {
            throw new ValidateCodeException("获取验证码的值失败");
        }
        if (StringUtils.isBlank(codeInRequest)) {
            throw new ValidateCodeException("请填写验证码");
        }

        if (code == null) {
            throw new ValidateCodeException("验证码不存在或已过期");
        }

        if (!StringUtils.equals(code, codeInRequest)) {
            throw new ValidateCodeException("验证码不正确");
        }

        this.remove(client_id);
    }

    private String buildKey(String client_id) {
        return SecurityConstants.DEFAULT_CODE_KEY + ":" + client_id;
    }
}
