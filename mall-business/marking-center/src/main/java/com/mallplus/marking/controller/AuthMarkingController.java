package com.mallplus.marking.controller;

import com.mallplus.common.annotation.SysLog;
import com.mallplus.common.entity.oms.OmsCartItem;
import com.mallplus.common.entity.sms.SmsBasicGifts;
import com.mallplus.common.entity.sms.SmsBasicMarking;
import com.mallplus.common.entity.sms.SmsCouponHistory;
import com.mallplus.common.utils.CommonResult;
import com.mallplus.common.vo.CartPromotionItem;
import com.mallplus.common.vo.SmsCouponHistoryDetail;
import com.mallplus.marking.service.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 优惠卷表
 * </p>
 *
 * @author zscat
 * @since 2019-04-19
 */
@Slf4j
@RestController
@Api(tags = "AuthMarkingController", description = "")
@RequestMapping("/auth")
public class AuthMarkingController {
    @Resource
    private ISmsCouponService couponService;
    @Resource
    private ISmsBasicGiftsService basicGiftsService;
    @Resource
    private ISmsBasicMarkingService basicMarkingService;
    @Resource
    private ISmsUserRedPacketService userRedPacketService;
    @Resource
    private ISmsRedPacketService ISmsRedPacketService;

    @SysLog(MODULE = "sms", REMARK = "领取红包")
    @ApiOperation(value = "领取红包")
    @RequestMapping(value = "/redPacket/accept/{id}", method = RequestMethod.GET)
    public Object accept(@PathVariable("id") Integer id) {
        int count = ISmsRedPacketService.acceptRedPacket(id,1L);
        if (count == 1) {
            return new CommonResult().success("领取成功");
        } else {
            return new CommonResult().failed("你已经领取此红包");
        }
    }


    @ApiOperation("领取指定优惠券")
    @PostMapping(value = "/acceptCoupon")
    public Object add(@RequestParam(value = "couponId", required = true) Long couponId,
                      @RequestParam(value = "memberId", required = true) Long memberId) {
        return couponService.add(couponId, memberId);
    }

    @ApiOperation("获取用户优惠券列表")
    @ApiImplicitParam(name = "useStatus", value = "优惠券筛选类型:0->未使用；1->已使用；2->已过期",
            allowableValues = "0,1,2", paramType = "query", dataType = "integer")
    @RequestMapping(value = "/listMemberCoupon", method = RequestMethod.GET)
    public Object list(@RequestParam(value = "useStatus", required = false) Integer useStatus, @RequestParam(value = "memberId", required = true) Long memberId) {
        List<SmsCouponHistory> couponHistoryList = couponService.list(useStatus, memberId);
        return new CommonResult().success(couponHistoryList);
    }


    @ApiOperation("获取单个商品得优惠详情")
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    @ResponseBody
    public Object detail(@RequestParam(value = "id", required = false) Long id) {
        List<SmsBasicMarking> basicMarkingList = basicMarkingService.matchGoodsBasicMarking(id);
        List<SmsBasicGifts> basicGiftsList = basicGiftsService.matchGoodsBasicGifts(id);
        Map<String, Object> map = new HashMap<>();
        map.put("basicMarkingList", basicMarkingList);
        map.put("basicGiftsList", basicGiftsList);
        return new CommonResult().success(map);
    }

}
