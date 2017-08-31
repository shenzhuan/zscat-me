package com.zscat.marketing.web;

import com.github.pagehelper.PageInfo;
import com.zscat.marketing.constant.Constant;
import com.zscat.marketing.model.PromotionUser;
import com.zscat.marketing.service.IPromotionUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 下线管理
 *
 * @author : zscat
 * @version : 1.0
 * @created on  : 2017/6/29  下午8:55
 */
@Controller
@RequestMapping(Constant.WX_H5_URI + "below")
public class BelowController extends BaseController {

    @Resource
    private IPromotionUserService ipromotionUserService;

    /**
     * 下线管理
     *
     * @return
     */
    @RequestMapping(value = "/index", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView index() {
        ModelAndView model =new ModelAndView();
        model.setViewName("below-user");
        PromotionUser  user = ipromotionUserService.selectById(sessionUtil.getCurrentUser().getUid());
        model.addObject("user", user);
        String schedu="0";
        if (user.getLevel() == 1){
            schedu = "16%";
        }else if(user.getLevel() == 2){
            schedu = "32%";
        }else if(user.getLevel() == 3){
            schedu = "48%";
        }else if(user.getLevel() == 4){
            schedu = "64%";
        }else if(user.getLevel() == 5){
            schedu = "80%";
        }else if(user.getLevel() == 6){
            schedu = "100%";
        }
        model.addObject("schedu", schedu);
      /*  PromotionUser p =new PromotionUser();
        p.setUpUid(user.getUid());
        List<PromotionUser> allC = ipromotionUserService.select(p);
        model.addObject("allC",allC);*/


        String upgradeSchedule = ipromotionUserService.getUpgradeSchedule();
        model.addObject("upSchedule",upgradeSchedule);

        return model;
    }
    /**
     * 下线管理推广员列表
     *
     * @return
     */
    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView accountInfo(HttpServletRequest request) {
        ModelAndView model =new ModelAndView();
        model.setViewName("ajax-below");
        Map<String, Object> params = new HashMap<>();
        String ifcode = request.getParameter("ifcode");
        if (StringUtils.isEmpty(ifcode)){
         //   params.put("uid","102837");
             params.put("ifcode",sessionUtil.getCurrentUser().getIfcode());
        }else {
            params.put("ifcode",ifcode);
        }

        PageInfo<PromotionUser> pages =  ipromotionUserService.findAllOneChild(params);
        model.addObject("pages",pages);
        String upgradeSchedule = ipromotionUserService.getUpgradeSchedule();
        model.addObject("upSchedule",upgradeSchedule);
        return model;
    }








}
