package com.zscat.marketing.web;

import com.zscat.marketing.config.WxConfigProperties;
import com.zscat.marketing.constant.Constant;
import com.zscat.marketing.model.PromotionUser;
import com.zscat.marketing.service.IPromotionUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Controller
 * Created by Silence on 2016/11/11.
 */
@Controller
public class IndexController  extends  BaseController{
  public static final String VIEW_PREFIX = "/user/";
  public static final String VIEW_URI = Constant.WX_H5_URI + "user";
  public static final String VIEW_SIGNUP = Constant.WX_H5_URI + "user/signup";
  public static final String VIEW_HOME = Constant.WX_H5_URI + "user/home";

  @Autowired
  private IPromotionUserService promotionUserService;

  @Autowired
  WxConfigProperties wxConfigProperties;

  /* 首页 */
  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String myinfos(HttpServletResponse response,
                        HttpServletRequest request, Model model) {
    try {
      PromotionUser myPromotion = this.getCurrentUser(); //获取当前推广员
//      myPromotion = promotionUserService.selectById(myPromotion.getUid());
      if (myPromotion == null) {
        myPromotion.setToken(sessionUtil.generateToken());
        sessionUtil.setCurrentUser(response, myPromotion);
        String toUrl = wxConfigProperties.getPageDomain() + UserController.VIEW_SIGNUP + "?token=" + myPromotion.getToken();
        response.sendRedirect(toUrl);
        return PAGE_REGISTER;
      }

      model.addAttribute("user", myPromotion);
    } catch (Exception e) {
      logger.error("tginfo error", e);
      return PAGE_ERROR;
    }
    return PAGE_USERINFO;
  }

}
