package com.zscat.marketing.web;

import com.alibaba.fastjson.JSONObject;
import com.zscat.marketing.config.BaseConfig;
import com.zscat.marketing.config.WxConfigProperties;
import com.zscat.marketing.constant.Constant;
import com.zscat.marketing.model.PromotionUser;
import com.zscat.marketing.service.IincomeService;
import com.zscat.marketing.vo.ResultObject;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 推广员用户相关
 *
 * @author : zscat
 * @version : 1.0
 * @created on  : 2017/6/27  下午5:46
 */
@Controller
@RequestMapping(Constant.WX_H5_URI + "user")
public class UserController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    public static final String VIEW_PREFIX = "/user/";
    public static final String VIEW_URI = Constant.WX_H5_URI + "user";
    public static final String VIEW_SIGNUP = Constant.WX_H5_URI + "user/signup";
    public static final String VIEW_HOME = Constant.WX_H5_URI + "user/home";

    //page
    public static final String PAGE_REGISTER = "/user/signup"; //注册推广员
    public static final String PAGE_USERINFO = "/user/info"; //推广员信息
    public static final String PAGE_INCOMES = "/income/below-income"; //收入明细
    public static final String PAGE_BELOW_TGUSERS = "/below-user"; //下线推广员
    public static final String PAGE_ERROR = "/error"; //错误页面

//    @Autowired
//    private IPromotionUserService promotionUserService;

    @Resource
    private BaseConfig baseConfig;



    @Resource
    private IincomeService incomeService;

    @Autowired
    WxConfigProperties wxConfigProperties;

    /**
     * 注册
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/signup", method = {RequestMethod.POST, RequestMethod.GET})
    public String signup(HttpServletResponse response,
                         HttpServletRequest request,
                         @ModelAttribute PromotionUser user,
                         @RequestParam(required = false) String token,
                         Model model) {
        logger.info("signup page start----");
        String view = VIEW_PREFIX + "signup";
        //get请求是，set
        if (request.getMethod().equals(RequestMethod.GET.name()) && StringUtils.isNoneBlank(token)){
            user = sessionUtil.getCurrentUser(token);
            model.addAttribute("user", user);
        }

        return PAGE_REGISTER;
    }

    /**
     * 注册推广员
     * @param request
     * @return
     */
    @RequestMapping("/register")
    @ResponseBody
    public ResultObject register(HttpServletResponse response,
                                 HttpServletRequest request,
                                 @RequestParam(required = true)String phone,

                                 Model model) {
        ResultObject ro = new ResultObject();
        model.addAttribute("phone", phone);//如果错误返回

        if (StringUtils.isEmpty(phone)) {
            logger.error("参数错误");
            ro.setMsg("参数错误");
            ro.setCode(10001);
            ro.setSuccess(false);
            return ro;

        }

            PromotionUser upUser = promotionUserService.selectOne(new PromotionUser(phone));
            logger.info("upUser:{}", upUser);
            if ( upUser != null) {
                //判断上线是否升级
                String toUrl = wxConfigProperties.getPageDomain() + "/wx/h5/user/tginfo";
                logger.info("toUrl:{}",toUrl);
                ro.setMsg("success");
                ro.setCode(0);
                ro.setSuccess(true);
                JSONObject data = new JSONObject();
                data.put("url",toUrl);
                ro.setData(data);
                return ro;

            } else {
                PromotionUser puser = this.getCurrentUser();
                puser.setPhone(phone);
                puser.setUid(System.currentTimeMillis());
                promotionUserService.insert(puser);
                //保存session
                this.setSession(response, puser);

                String toUrl = wxConfigProperties.getPageDomain() + "/wx/h5/user/tginfo";
                logger.info("toUrl:{}",toUrl);
                ro.setMsg("success");
                ro.setCode(0);
                ro.setSuccess(true);
                JSONObject data = new JSONObject();
                data.put("url",toUrl);
                ro.setData(data);
                return ro;
            }


    }


    /**
     * 获取推广员信息
     * @return
     */
    @RequestMapping(value = "/tginfo", method = {RequestMethod.POST, RequestMethod.GET})
    public String myinfos(HttpServletResponse response,
                          HttpServletRequest request, Model model) {
        try {
            PromotionUser myPromotion = this.getCurrentUser(); //获取当前推广员
            logger.info("tginfo currentUser={}", myPromotion);

            myPromotion = promotionUserService.selectById(myPromotion.getUid());

            if (myPromotion == null) {
                String toUrl = wxConfigProperties.getPageDomain() + UserController.VIEW_SIGNUP;
                response.sendRedirect(toUrl);
                return PAGE_REGISTER;
            } else {
                this.setSession(response, myPromotion);
            }

            model.addAttribute("user", myPromotion);
        } catch (Exception e) {
            logger.error("tginfo error", e);
            return PAGE_ERROR;
        }
        return PAGE_USERINFO;
    }

}
