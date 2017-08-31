package com.zscat.marketing.web;


import com.zscat.marketing.config.BaseConfig;
import com.zscat.marketing.constant.Constant;
import com.zscat.marketing.constant.RedisKey;
import com.zscat.marketing.model.IncomeData;
import com.zscat.marketing.model.PromotionUser;
import com.zscat.marketing.service.IPromotionUserService;
import com.zscat.marketing.service.IincomeService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 收入管理
 *
 * @author : zscat
 * @version : 1.0
 * @created on  : 2017/6/29  下午8:55
 */
@Controller
@RequestMapping(Constant.WX_H5_URI + "income")
public class IncomeController extends BaseController {
    public static final String VIEW_PREFIX = "/income/";
    @Resource
    private IPromotionUserService ipromotionUserService;
    @Resource
    private BaseConfig baseConfig;



    @Resource
    private IincomeService incomeService;

    /**
     * 收入首页
     *
     * @return
     */
    @RequestMapping(value = "/index", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView total() {
        ModelAndView model =new ModelAndView();
        PromotionUser promotionUser = null;
        String view = VIEW_PREFIX + "total";
        if (sessionUtil.getCurrentUser()!=null){
            promotionUser = ipromotionUserService.selectById(sessionUtil.getCurrentUser().getUid());
            model.addObject("user",promotionUser);
            model.addObject("rate",baseConfig.redisLink().hget(RedisKey.WITHDRAWLEVEL,promotionUser.getLevel()+""));
        }
        model.setViewName(view);
        return model;
    }
    /**
     * 收入首页
     *
     * @return
     */
    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView list(HttpServletResponse response, @RequestParam(required = false) String starttime, @RequestParam(required = false) String endtime) {
        ModelAndView model =new ModelAndView();
        PromotionUser promotionUser = this.getCurrentUser();
        String view = VIEW_PREFIX + "below-income";
        List<IncomeData> result = new ArrayList<>();
        try {
            List<IncomeData> list = incomeService.getIncomeDataList(promotionUser.getUid(), starttime, endtime);

            model.addObject("list", list);
        } catch (Exception e) {
            logger.error("get list error", e);
        }

        model.setViewName(view);
        return model;
    }

}
