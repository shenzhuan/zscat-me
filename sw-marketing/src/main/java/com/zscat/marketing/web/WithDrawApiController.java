package com.zscat.marketing.web;

import com.github.pagehelper.PageInfo;

import com.zsCat.common.utils.DateUtils;
import com.zscat.marketing.config.BaseConfig;
import com.zscat.marketing.constant.Constant;
import com.zscat.marketing.constant.RedisKey;
import com.zscat.marketing.model.PromotionUser;
import com.zscat.marketing.model.Withdraw;
import com.zscat.marketing.service.IActiveUserService;
import com.zscat.marketing.service.IPromotionUserService;
import com.zscat.marketing.service.IWithDrawService;
import com.zscat.marketing.service.IincomeService;
import com.zscat.marketing.utils.SessionUtil;
import com.zscat.marketing.vo.ResultObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.DecimalFormat;
import java.util.Date;

/**
 * 所以 ajax 请求在这里
 * uri: wx/h5/api
 *
 * @author : zscat
 * @version : 1.0
 * @created on  : 2017/6/28  下午5:43
 */
@RestController
@RequestMapping(Constant.WX_H5_URI +"withDraw")
public class WithDrawApiController {

    @Resource
    private BaseConfig baseConfig;
    @Resource
    private  IincomeService iincomeService;
    @Resource
    private IActiveUserService iActiveUserService;
    @Resource
    private IPromotionUserService ipromotionUserService;
    @Resource
    private IWithDrawService iWithDrawService;
    @Resource
    private SessionUtil sessionUtil;
    @RequestMapping("/toWithDraw")
    public ModelAndView toWithDraw(
            @RequestParam(required = false,defaultValue = "1")int pageNum,
            @RequestParam(required = false,defaultValue = "15")int pageSize,
                                   @RequestParam(required = false)Date startDate,
                                   @RequestParam(required = false)Date endDate) {
        ModelAndView model =new ModelAndView();
        PromotionUser promotionUser = null;
        if (sessionUtil.getCurrentUser()!=null){
            promotionUser = ipromotionUserService.selectById(sessionUtil.getCurrentUser().getUid());
            model.addObject("user",promotionUser);
            String rate = ipromotionUserService.selectWithDrawLevel(promotionUser.getLevel());
            model.addObject("rate",rate);
            DecimalFormat df   = new DecimalFormat("######0.00");
            double balance =promotionUser.getTotalIncome()*Double.parseDouble(rate)/10000;
            model.addObject("balance",df.format(balance));
        }
        model.addObject("today","2");// 本月 已提现
        int today = DateUtils.getCurrentDay();
        if (today == 1 ){
            String isWithDraw = baseConfig.redisLink().hget(RedisKey.WITHDRARLIST,sessionUtil.getCurrentUser().getUid()+"");
            if (!"1".equals(isWithDraw)){
                model.addObject("today","2");// 本月未提现
            }
        }
        model.setViewName("/income/withdraw");
        return model;
    }

    @RequestMapping("/withdrawHistory")
    public ModelAndView withdrawhistory(HttpServletRequest request,
            @RequestParam(required = false,defaultValue = "15")int pageSize,
            @RequestParam(required = false)Date startDate,
            @RequestParam(required = false)Date endDate) {
        ModelAndView model =new ModelAndView();
        String pageNum = request.getParameter("pagenum");
        int pageNums =1;
        if ("2".equals(pageNum)){
            pageSize =1000;
        }
        PageInfo<Withdraw> list =  iWithDrawService.selectPageByDate( sessionUtil.getCurrentUser().getUid(),pageNums, pageSize, startDate, endDate,"operationtime");
        model.addObject("pages",list);
        model.setViewName("/income/withdraw-history");
        return model;
    }
    @RequestMapping("/withdrawHistory1")
    public ModelAndView withdrawhistory1(HttpServletRequest request,
                                        @RequestParam(required = false,defaultValue = "15")int pageSize,
                                        @RequestParam(required = false)Date startDate,
                                        @RequestParam(required = false)Date endDate) {
        ModelAndView model =new ModelAndView();
        String pageNum = request.getParameter("pagenum");
        int pageNums =1;
        if ("2".equals(pageNum)){
            pageSize =1000;
        }
        PageInfo<Withdraw> list =  iWithDrawService.selectPageByDate( sessionUtil.getCurrentUser().getUid(),pageNums, pageSize, startDate, endDate,"operationtime");
        model.addObject("pages",list);
        model.setViewName("/income/withdraw-history1");
        return model;
    }
    @RequestMapping("/withDraw")
    @ResponseBody
    public ResultObject withDraw(@RequestParam(required = true)String level,
                                 @RequestParam(required = true)String balance,
                                 @RequestParam(required = true)String account) {
        ResultObject ro = new ResultObject();
        Long uid=0L;
        if (sessionUtil.getCurrentUser()!=null){
            uid = sessionUtil.getCurrentUser().getUid();
        }else {
            ro.setMsg("请登录");
            ro.setSuccess(false);
            return ro;
        }
        if(Double.parseDouble(account)<100){
            ro.setMsg("提现金额不能小于1元！");
            ro.setSuccess(false);
            return ro;
        }
        Withdraw withdraw =new Withdraw();

        if (!"1".equals(level) || !"2".equals(level)){
            if (balance.equals(account)){
                //全部提现 直接退出推广员计划
//                PromotionUser  promotionUser = ipromotionUserService.selectById(uid);
//                promotionUser.setStatus(2);
//                ipromotionUserService.updateById(promotionUser);
            }
        }
        withdraw.setMoney(Long.parseLong(account));
        withdraw.setCreateTime(new Date());
        withdraw.setStatus(0);
        withdraw.setUpdateTime(new Date());
        withdraw.setUid(uid);
        withdraw.setPayType("wxpay");
        withdraw.setOperationtime(new Date());
       // iWithDrawService.insert(withdraw);
            iWithDrawService.withdraw(withdraw);
        ro.setMsg("申请提现成功！");
        ro.setSuccess(true);

        return ro;
    }
    @RequestMapping(value = "/test", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView accountInfo( ) {
        ModelAndView model =new ModelAndView();
        model.setViewName("below-user");
        return model;
    }
}
