package com.mallplus.member.service.impl;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mallplus.common.entity.ums.UmsMemberLevel;
import com.mallplus.common.exception.ApiMallPlusException;
import com.mallplus.common.exception.BusinessException;
import com.mallplus.common.entity.ums.UmsMember;
import com.mallplus.common.utils.CommonResult;
import com.mallplus.common.vo.OrderStstic;
import com.mallplus.common.vo.SmsCode;
import com.mallplus.member.config.WxAppletProperties;
import com.mallplus.common.entity.ums.Sms;
import com.mallplus.member.mapper.SysAreaMapper;
import com.mallplus.member.mapper.UmsMemberMapper;
import com.mallplus.member.mapper.UmsMemberMemberTagRelationMapper;
import com.mallplus.member.service.IUmsMemberLevelService;
import com.mallplus.member.service.IUmsMemberService;
import com.mallplus.member.service.RedisService;
import com.mallplus.member.service.SmsService;
import com.mallplus.member.utils.CharUtil;
import com.mallplus.member.utils.CommonUtil;
import com.mallplus.member.vo.MemberDetails;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.CompletableFuture;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author zscat
 * @since 2019-04-19
 */
@Slf4j
@Service
public class UmsMemberServiceImpl extends ServiceImpl<UmsMemberMapper, UmsMember> implements IUmsMemberService {

    @Resource
    private SmsService smsService;
    @Resource
    private UmsMemberMapper memberMapper;
    @Resource
    private IUmsMemberLevelService memberLevelService;

    @Resource
    private PasswordEncoder passwordEncoder;
    @Resource
    private RedisService redisService;
    private static final Logger LOGGER = LoggerFactory.getLogger(UmsMemberServiceImpl.class);

    @Resource
    private SysAreaMapper areaMapper;

    @Value("${redis.key.prefix.authCode}")
    private String REDIS_KEY_PREFIX_AUTH_CODE;
    @Value("${authCode.expire.seconds}")
    private Long AUTH_CODE_EXPIRE_SECONDS;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Value("${aliyun.sms.expire-minute:1}")
    private Integer expireMinute;
    @Value("${aliyun.sms.day-count:30}")
    private Integer dayCount;
    @Resource
    private UmsMemberMemberTagRelationMapper umsMemberMemberTagRelationMapper;


    @Override
    public UmsMember getByUsername(String username) {
        UmsMember umsMember = new UmsMember();
        umsMember.setUsername(username);

        return memberMapper.selectOne(new QueryWrapper<>(umsMember));
    }

    @Override
    public UmsMember getById(Long id) {
        return memberMapper.selectById(id);
    }

    @Override
    public CommonResult register(String username, String password, String telephone, String authCode) {

        //没有该用户进行添加操作
        UmsMember umsMember = new UmsMember();
        umsMember.setUsername(username);
        umsMember.setPhone(telephone);
        umsMember.setPassword(password);
        this.register(umsMember);
        return new CommonResult().success("注册成功", null);
    }
    @Override
    public CommonResult register(UmsMember user) {
        //验证验证码
        if (!verifyAuthCode(user.getCode(), user.getPhone())) {
            return new CommonResult().failed("验证码错误");
        }
        if (!user.getPassword().equals(user.getConfimpassword())){
            return new CommonResult().failed("密码不一致");
        }
        //查询是否已有该用户

        UmsMember queryM = new UmsMember();
        queryM.setUsername(user.getUsername());
      //  queryM.setPassword(passwordEncoder.encode(user.getPassword()));
        UmsMember umsMembers = memberMapper.selectOne(new QueryWrapper<>(queryM));
        if (umsMembers!=null) {
            return new CommonResult().failed("该用户已经存在");
        }
        //没有该用户进行添加操作
        UmsMember umsMember = new UmsMember();
        umsMember.setUsername(user.getUsername());
        umsMember.setPhone(user.getPhone());
        umsMember.setPassword(passwordEncoder.encode(user.getPassword()));
        umsMember.setCreateTime(new Date());
        umsMember.setStatus(1);

        memberMapper.insert(umsMember);
        umsMember.setPassword(null);
        return new CommonResult().success("注册成功", null);
    }
    private String countKey(String phone) {
        return "sms:count:" + LocalDate.now().toString() + ":" + phone;
    }

    private String smsRedisKey(String str) {
        return "sms:" + str;
    }
    @Override
    public SmsCode generateCode(String phone) {
        //生成流水号
        String uuid = UUID.randomUUID().toString();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            sb.append(random.nextInt(10));
        }
        Map<String, String> map = new HashMap<>(2);
        map.put("code", sb.toString());
        map.put("phone", phone);

        //短信验证码缓存15分钟，
        redisService.set(REDIS_KEY_PREFIX_AUTH_CODE + phone, sb.toString());
        redisService.expire(REDIS_KEY_PREFIX_AUTH_CODE + phone, expireMinute*60);
        log.info("缓存验证码：{}", map);

        //存储sys_sms
        saveSmsAndSendCode(phone, sb.toString());
        SmsCode smsCode = new SmsCode();
        smsCode.setKey(uuid);
        return smsCode;
    }
    /**
     * 保存短信记录，并发送短信
     * @param phone
     * @param code
     */
    private void saveSmsAndSendCode(String phone, String code) {
        checkTodaySendCount(phone);

        Sms sms = new Sms();
        sms.setPhone(phone);
        sms.setParams(code);
        Map<String, String> params = new HashMap<>();
        params.put("code", code);
        smsService.save(sms, params);

        //异步调用阿里短信接口发送短信
        CompletableFuture.runAsync(() -> {
            try {
                smsService.sendSmsMsg(sms);
            } catch (Exception e) {
                params.put("err",  e.getMessage());
                smsService.save(sms, params);
                e.printStackTrace();
                log.error("发送短信失败：{}", e.getMessage());
            }

        });

        // 当天发送验证码次数+1
        String countKey = countKey(phone);
        redisService.increment(countKey, 1L);
        redisService.expire(countKey, 1*3600*24);
    }
    /**
     * 获取当天发送验证码次数
     * 限制号码当天次数
     * @param phone
     * @return
     */
    private void checkTodaySendCount(String phone) {
        String value =   redisService.get(countKey(phone));
        if (value != null) {
            Integer count = Integer.valueOf(value );
            if (count > dayCount) {
                throw new IllegalArgumentException("已超过当天最大次数");
            }
        }

    }
    @Override
    public CommonResult generateAuthCode(String telephone) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            sb.append(random.nextInt(10));
        }
        //验证码绑定手机号并存储到redis
        redisService.set(REDIS_KEY_PREFIX_AUTH_CODE + telephone, sb.toString());
        redisService.expire(REDIS_KEY_PREFIX_AUTH_CODE + telephone, expireMinute);
        return new CommonResult().success("获取验证码成功", sb.toString());
    }

    @Override
    public CommonResult updatePassword(String telephone, String password, String authCode) {
        UmsMember example = new UmsMember();
        example.setPhone(telephone);
        UmsMember member = memberMapper.selectOne(new QueryWrapper<>(example));
        if (member==null) {
            return new CommonResult().failed("该账号不存在");
        }
        //验证验证码
        if (!verifyAuthCode(authCode, telephone)) {
            return new CommonResult().failed("验证码错误");
        }

        member.setPassword(passwordEncoder.encode(password));
        memberMapper.updateById(member);
        return new CommonResult().success("密码修改成功", null);
    }

    @Override
    public UmsMember getCurrentMember() {
        try {
            SecurityContext ctx = SecurityContextHolder.getContext();
            Authentication auth = ctx.getAuthentication();
            MemberDetails memberDetails = (MemberDetails) auth.getPrincipal();
            return memberDetails.getUmsMember();
        }catch (Exception e){
            return new UmsMember();
        }
    }

    @Override
    public void updateIntegration(Long id, Integer integration) {
        UmsMember record = new UmsMember();
        record.setId(id);
        record.setIntegration(integration);
        memberMapper.updateById(record);
    }


    //对输入的验证码进行校验
    public boolean verifyAuthCode(String authCode, String telephone) {
        if (StringUtils.isEmpty(authCode)) {
            return false;
        }
        String realAuthCode = redisService.get(REDIS_KEY_PREFIX_AUTH_CODE + telephone);
        return authCode.equals(realAuthCode);
    }

    @Override
    public UmsMember queryByOpenId(String openId) {
        UmsMember queryO = new UmsMember();
        queryO.setWeixinOpenid(openId);
        return memberMapper.selectOne(new QueryWrapper<>(queryO));
    }

    @Override
    public Object loginByWeixin(HttpServletRequest req) {
        try {
            String code = req.getParameter("code");
            if (StringUtils.isEmpty(code)) {
                throw new BusinessException("code is empty");
            }
            String userInfos = req.getParameter("userInfo");

            String signature = req.getParameter("signature");

            Map<String, Object> me = JSONUtil.parseObj(userInfos);
            if (null == me) {
                throw new BusinessException("登录失败");
            }

            Map<String, Object> resultObj = new HashMap<String, Object>();
            //
            //获取openid
            String requestUrl = this.getWebAccess(code);//通过自定义工具类组合出小程序需要的登录凭证 code

            JSONObject sessionData = com.mallplus.util.CommonUtil.httpsRequest(requestUrl, "GET", null);

            if (null == sessionData || StringUtils.isEmpty(sessionData.getStr("openid"))) {
                throw new BusinessException("登录失败");
            }
            //验证用户信息完整性
            String sha1 = com.mallplus.util.CommonUtil.getSha1(userInfos + sessionData.getStr("session_key"));
            if (!signature.equals(sha1)) {
                throw new BusinessException("登录失败");
            }
            UmsMember userVo = this.findByOpenId(sessionData.getStr("openid"));
            resultObj.put("userId",userVo.getId());
            if (null == userVo) {
                UmsMember umsMember = new UmsMember();
                umsMember.setUsername("wxapplet" + CharUtil.getRandomString(12));
                umsMember.setSourceType(1);
                umsMember.setPassword(passwordEncoder.encode("123456"));
                umsMember.setCreateTime(new Date());
                umsMember.setStatus(1);
                umsMember.setBlance(new BigDecimal(0));
                umsMember.setIntegration(0);
                umsMember.setHistoryIntegration(0);
                umsMember.setWeixinOpenid(sessionData.getStr("openid"));
                if (StringUtils.isEmpty(me.get("avatarUrl").toString())) {
                    //会员头像(默认头像)
                    umsMember.setIcon("/upload/img/avatar/01.jpg");
                } else {
                    umsMember.setIcon(me.get("avatarUrl").toString());
                }
                // umsMember.setGender(Integer.parseInt(me.get("gender")));
                umsMember.setNickname(me.get("nickName").toString());
                memberMapper.insert(umsMember);
                resultObj.put("userId",umsMember.getId());
            }
            resultObj.put("openId",sessionData.getStr("openid"));

            return   resultObj;

        } catch (ApiMallPlusException e) {
            e.printStackTrace();
            throw new BusinessException("登录失败");
        }catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException("登录失败");
        }

    }

    public Object loginByWeixin1(HttpServletRequest req) {
        try {
            String code = req.getParameter("code");
            if (StringUtils.isEmpty(code)) {
                System.out.println("code is empty");
            }
            String userInfos = req.getParameter("userInfo");

            String signature = req.getParameter("signature");

            Map<String, Object> me = JSONUtil.parseObj(userInfos);
            if (null == me) {
                return new CommonResult().failed("登录失败");
            }

            Map<String, Object> resultObj = new HashMap<String, Object>();
            //
            //获取openid
            String requestUrl = this.getWebAccess(code);//通过自定义工具类组合出小程序需要的登录凭证 code

            JSONObject sessionData = CommonUtil.httpsRequest(requestUrl, "GET", null);

            if (null == sessionData || StringUtils.isEmpty(sessionData.getStr("openid"))) {
                return new CommonResult().failed("登录失败");
            }
            //验证用户信息完整性
            String sha1 = CommonUtil.getSha1(userInfos + sessionData.getStr("session_key"));
            if (!signature.equals(sha1)) {
                return new CommonResult().failed("登录失败");
            }
            UmsMember userVo = this.queryByOpenId(sessionData.getStr("openid"));
            String token = null;
            if (null == userVo) {
                UmsMember umsMember = new UmsMember();
                umsMember.setUsername("wxapplet" + CharUtil.getRandomString(12));
                umsMember.setSourceType(1);
                umsMember.setPassword(passwordEncoder.encode("123456"));
                umsMember.setCreateTime(new Date());
                umsMember.setStatus(1);
                umsMember.setBlance(new BigDecimal(0));
                umsMember.setIntegration(0);
                umsMember.setHistoryIntegration(0);
                umsMember.setWeixinOpenid(sessionData.getStr("openid"));
                if (StringUtils.isEmpty(me.get("avatarUrl").toString())) {
                    //会员头像(默认头像)
                    umsMember.setIcon("/upload/img/avatar/01.jpg");
                } else {
                    umsMember.setIcon(me.get("avatarUrl").toString());
                }
                // umsMember.setGender(Integer.parseInt(me.get("gender")));
                umsMember.setNickname(me.get("nickName").toString());

                memberMapper.insert(umsMember);
              //  token = jwtTokenUtil.generateToken(umsMember.getUsername());
                resultObj.put("userId", umsMember.getId());
            }else {
              //  token = jwtTokenUtil.generateToken(userVo.getUsername());
                resultObj.put("userId", userVo.getId());
            }

           // resultObj.put("tokenHead", tokenHead);
           // resultObj.put("token", token);
            resultObj.put("userInfo", me);

            return new CommonResult().success(resultObj);
        } catch (ApiMallPlusException e) {
            e.printStackTrace();
            return new CommonResult().failed(e.getMessage());
        }catch (Exception e) {
            e.printStackTrace();
            return new CommonResult().failed(e.getMessage());
        }

    }

    @Override
    public Map<String, Object> login(UmsMember user) {

        Map<String, Object> tokenMap = new HashMap<>();
        String token = null;


        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);

        return tokenMap;

    }
    @Override
    public String refreshToken(String oldToken) {
        String token = oldToken.substring(tokenHead.length());
        /*if (jwtTokenUtil.canRefresh(token)) {
            return jwtTokenUtil.refreshToken(token);
        }*/
        return null;
    }
    @Autowired
    private WxAppletProperties wxAppletProperties;

    //替换字符串
    public String getCode(String APPID, String REDIRECT_URI, String SCOPE) {
        return String.format(wxAppletProperties.getGetCode(), APPID, REDIRECT_URI, SCOPE);
    }

    //替换字符串
    public String getWebAccess(String CODE) {

        return String.format(wxAppletProperties.getWebAccessTokenhttps(),
                wxAppletProperties.getAppId(),
                wxAppletProperties.getSecret(),
                CODE);
    }

    //替换字符串
    public String getUserMessage(String access_token, String openid) {
        return String.format(wxAppletProperties.getUserMessage(), access_token, openid);
    }


    /**
     * 根据openId查询用户
     * @param openId
     * @return
     */
    @Override
    public UmsMember findByOpenId(String openId) {
        UmsMember users = baseMapper.selectOne(
                new QueryWrapper<UmsMember>().eq("weixin_openid", openId)
        );
        return users;
    }

    @Override
    public void updataMemberOrderInfo() {
        /*List<OrderStstic> orders =  omsOrderMapper.listOrderGroupByMemberId();
        List<UmsMemberLevel> levelList = memberLevelService.list(new QueryWrapper<UmsMemberLevel>().orderByDesc("price"));
        for (OrderStstic o : orders){
            UmsMember member = new UmsMember();
            member.setId(o.getMemberId());
            member.setBuyMoney(o.getTotalPayAmount());
            for (UmsMemberLevel level: levelList){
                if (member.getBuyMoney().compareTo(level.getPrice())>=0){
                    member.setMemberLevelId(level.getId());
                    member.setMemberLevelName(level.getName());
                    break;
                }
            }
            member.setBuyCount(o.getTotalCount());
            memberMapper.updateById(member);
        }*/
    }


}

