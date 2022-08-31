package com.mallplus.member.controller;


import com.mallplus.common.annotation.IgnoreAuth;
import com.mallplus.common.annotation.SysLog;
import com.mallplus.common.entity.ums.Sms;
import com.mallplus.common.entity.ums.UmsMember;
import com.mallplus.common.redis.template.RedisRepository;
import com.mallplus.common.utils.CommonResult;
import com.mallplus.common.utils.PhoneUtil;
import com.mallplus.common.vo.SmsCode;
import com.mallplus.common.vo.TArticleDO;
import com.mallplus.member.service.IUmsMemberService;
import com.mallplus.member.service.SmsService;
import com.mallplus.member.utils.UserUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.CompletableFuture;

/**
 * 会员登录注册管理Controller
 * https://github.com/shenzhuan/mallplus on 2018/8/3.
 */
@RestController
@Api(tags = "AppletMemberController", description = "小程序登录首页")
@RequestMapping("/api/applet")
public class AppletMemberController  {
    @Autowired
    private IUmsMemberService memberService;


    @Autowired
    private RedisRepository redisRepository;
    @Autowired
    private SmsService smsService;


    @IgnoreAuth
    @ApiOperation("注册")
    @SysLog(MODULE = "applet", REMARK = "小程序注册")
    @PostMapping("login_by_weixin")
    public Object loginByWeixin(HttpServletRequest req) {
        return new CommonResult().success(memberService.loginByWeixin(req));

    }


    /**
     * 小程序主页
     *
     * @param
     * @return
     */
    @IgnoreAuth
    @SysLog(MODULE = "applet", REMARK = "小程序首页")
    @ApiOperation("小程序首页")
    @GetMapping("/index")
    public Object index() {
        List<TArticleDO> nav_icon_list = new ArrayList<>();
        try {
            TArticleDO c1 = new TArticleDO("我的公告", "/pages/topic-list/topic-list", "navigate", "http://www.91weiyi.xyz/addons/zjhj_mall/core/web/uploads/image/86/863a7db352a936743faf8edd5162bb5c.png");
            TArticleDO c2 = new TArticleDO("商品分类", "/pages/cat/cat", "switchTab", "http://www.91weiyi.xyz/addons/zjhj_mall/core/web/uploads/image/35/3570994c06e61b1f0cf719bdb52a0053.png");
            TArticleDO c3 = new TArticleDO("购物车", "/pages/cart/cart", "switchTab", "http://www.91weiyi.xyz/addons/zjhj_mall/core/web/uploads/image/c2/c2b01cf78f79cbfba192d5896eeaecbe.png");
            TArticleDO c4 = new TArticleDO("我的订单", "/pages/order/order?status=9", "navigate", "http://www.91weiyi.xyz/addons/zjhj_mall/core/web/uploads/image/7c/7c80acbbd479b099566cc6c3d34fbcb8.png");
            TArticleDO c5 = new TArticleDO("用户中心", "/pages/user/user", "switchTab", "http://www.91weiyi.xyz/addons/zjhj_mall/core/web/uploads/image/46/46eabbff1e7dc5e416567fc45d4d5df3.png");
            TArticleDO c6 = new TArticleDO("优惠劵", "/pages/coupon/coupon?status=0", "navigate", "http://www.91weiyi.xyz/addons/zjhj_mall/core/web/uploads/image/13/13312a6d56c202330f8c282d8cf84ada.png");
            TArticleDO c7 = new TArticleDO("我的收藏", "/pages/favorite/favorite", "navigate", "http://www.91weiyi.xyz/addons/zjhj_mall/core/web/uploads/image/ca/cab6d8d4785e43bd46dcbb52ddf66f61.png");
            TArticleDO c8 = new TArticleDO("售后订单", "/pages/order/order?status=4", "navigate", "http://www.91weiyi.xyz/addons/zjhj_mall/core/web/uploads/image/cf/cfb32a65d845b4e9a9778020ed2ccac6.png");
            nav_icon_list.add(c1);
            nav_icon_list.add(c2);
            nav_icon_list.add(c3);
            nav_icon_list.add(c4);
            nav_icon_list.add(c5);
            nav_icon_list.add(c6);
            nav_icon_list.add(c7);
            nav_icon_list.add(c8);

            return new CommonResult().success(nav_icon_list);
        } catch (Exception e) {
            e.printStackTrace();
            return new CommonResult().failed();
        }

    }

    @IgnoreAuth
    @ApiOperation("小程序用户详情")
    @SysLog(MODULE = "applet", REMARK = "小程序用户详情")
    @GetMapping("/user")
    public Object user() {
        UmsMember umsMember = UserUtils.getCurrentMember();
        if (umsMember != null && umsMember.getId() != null) {

            return new CommonResult().success(umsMember);
        }
        return new CommonResult().failed();

    }
    /**
     * 发送短信验证码
     *
     * @param phone
     * @return
     */
    @IgnoreAuth
    @ApiOperation("获取验证码")
    @PostMapping(value = "/sms/codes")
    public Object sendSmsCode(@RequestParam String phone) {
        try {
            if (!PhoneUtil.checkPhone(phone)) {
                throw new IllegalArgumentException("手机号格式不正确");
            }
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
            redisRepository.set("member:code:" + phone, sb.toString());
            redisRepository.willExpire("member:code:" + phone, 2 * 60);


            //存储sys_sms
            saveSmsAndSendCode(phone, sb.toString());
            SmsCode smsCode = new SmsCode();
            smsCode.setKey(uuid);

            return new CommonResult().success(smsCode);
        } catch (Exception e) {
            e.printStackTrace();
            return new CommonResult().failed(e.getMessage());
        }
    }
    /**
     * 保存短信记录，并发送短信
     *
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
                params.put("err", e.getMessage());
                smsService.save(sms, params);
                e.printStackTrace();

            }

        });

        // 当天发送验证码次数+1
        String countKey = countKey(phone);
        redisRepository.incr(countKey);
        redisRepository.willExpire(countKey, 1 * 3600 * 24);
    }
    /**
     * 获取当天发送验证码次数
     * 限制号码当天次数
     *
     * @param phone
     * @return
     */
    private void checkTodaySendCount(String phone) {
        String value = redisRepository.get(countKey(phone)).toString();
        if (value != null) {
            Integer count = Integer.valueOf(value);
            if (count > 40) {
                throw new IllegalArgumentException("已超过当天最大次数");
            }
        }

    }
    private String countKey(String phone) {
        return "sms:count:" + LocalDate.now().toString() + ":" + phone;
    }
}
