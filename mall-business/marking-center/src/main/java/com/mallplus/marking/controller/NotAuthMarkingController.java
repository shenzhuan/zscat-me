package com.mallplus.marking.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mallplus.common.annotation.IgnoreAuth;
import com.mallplus.common.annotation.SysLog;
import com.mallplus.common.entity.oms.OmsCartItem;
import com.mallplus.common.entity.sms.*;
import com.mallplus.common.utils.CommonResult;
import com.mallplus.common.utils.DateUtils;
import com.mallplus.common.vo.CartMarkingVo;
import com.mallplus.common.vo.OrderParam;
import com.mallplus.common.vo.SmsCouponHistoryDetail;
import com.mallplus.marking.mapper.*;
import com.mallplus.marking.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

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
@Api(tags = "NotAuthMarkingController", description = "")
@RequestMapping("/")
public class NotAuthMarkingController {
    @Resource
    private ISmsCouponService couponService;
    @Resource
    private SmsCouponHistoryMapper couponHistoryMapper;
    @Resource
    private SmsFlashPromotionProductRelationMapper smsFlashPromotionProductRelationMapper;
    @Resource
    private ISmsRedPacketService redPacketService;
    @Resource
    private ISmsUserRedPacketService userRedPacketService;
    @Resource
    private SmsGroupMemberMapper groupMemberMapper;
    @Resource
    private SmsGroupMapper groupMapper;
    @Resource
    private ISmsBasicGiftsService basicGiftsService;
    @Resource
    private ISmsBasicMarkingService basicMarkingService;
    @Resource
    private SmsGroupActivityMapper groupActivityMapper;
    @IgnoreAuth
    @SysLog(MODULE = "sms", REMARK = "根据条件查询所有红包列表")
    @ApiOperation("根据条件查询所有红包列表")
    @PostMapping(value = "/notAuth/redPacket/list")
    public Object getSmsRedPacketByPage(@RequestBody SmsRedPacket entity) {
        try {
            List<SmsRedPacket> redPacketList = redPacketService.list(new QueryWrapper<>());
            SmsUserRedPacket userRedPacket = new SmsUserRedPacket();
            userRedPacket.setUserId(entity.getUserId());
            List<SmsUserRedPacket> list = userRedPacketService.list(new QueryWrapper<>(userRedPacket));
            for(SmsRedPacket vo : redPacketList){
                if (list!=null && list.size()>0){
                    for (SmsUserRedPacket vo1 : list){
                        if(vo.getId().equals(vo1.getRedPacketId())){
                            vo.setStatus(1);
                            vo.setReciveAmount(vo1.getAmount());
                            break;
                        }
                    }
                }
            }
            return new CommonResult().success(redPacketList);
        } catch (Exception e) {
            log.error("根据条件查询所有红包列表：%s", e.getMessage(), e);
        }
        return new CommonResult().failed();
    }
    @IgnoreAuth
    @SysLog(MODULE = "sms", REMARK = "根据条件查询所有红包列表")
    @ApiOperation("根据条件查询所有红包列表")
    @GetMapping(value = "/notAuth/coupon/list")
    public Object getCouponByPage() {
        try {
            return new CommonResult().success(couponService.selectNotRecive());
        } catch (Exception e) {
            log.error("根据条件查询所有红包列表：%s", e.getMessage(), e);
        }
        return new CommonResult().failed();
    }
    @SysLog(MODULE = "sms", REMARK = "根据条件查询所有导航栏列表")
    @ApiOperation("根据条件查询所有导航栏列表")
    @GetMapping(value = "/notAuth/nav/list")
    public Object getNavByPage() {
        try {
            return new CommonResult().success(couponService.selectNotRecive());
        } catch (Exception e) {
            log.error("根据条件查询所有红包列表：%s", e.getMessage(), e);
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "sms", REMARK = "根据条件查询所有导航栏列表")
    @ApiOperation("根据条件查询所有导航栏列表")
    @PostMapping(value = "/notAuth/acceptGroup")
    public Object acceptGroup(@RequestBody OrderParam orderParam) {
        Long nowT = System.currentTimeMillis();
        SmsGroup group = groupMapper.selectById(orderParam.getGroupId());
        Date endTime = DateUtils.convertStringToDate(DateUtils.addHours(group.getEndTime(), group.getHours()), "yyyy-MM-dd HH:mm:ss");

        if (nowT > group.getStartTime().getTime() && nowT < endTime.getTime()) {
            SmsGroupMember groupMember = new SmsGroupMember();

            if(orderParam.getGroupType()==1){
                groupMember.setMainId(orderParam.getMemberId());
                groupMember.setGoodsId(orderParam.getGoodsId());
                SmsGroupMember    exist = groupMemberMapper.selectOne(new QueryWrapper<>(groupMember));
                if (exist!=null){
                    return new CommonResult().failed("你已经参加过此活动");
                }
                groupMember.setName(orderParam.getMemberIcon());
                groupMember.setStatus(2);
                groupMember.setOrderId(orderParam.getOrderId()+"");
                groupMember.setMainId(orderParam.getMemberId());
                groupMember.setCreateTime(new Date());
                groupMember.setGroupId(group.getId());

                groupMember.setMemberId(orderParam.getMemberId()+"");
                groupMember.setExipreTime(System.currentTimeMillis()+(group.getHours()*60*60*60));
                groupMemberMapper.insert(groupMember);
            }else{
                groupMember = groupMemberMapper.selectById(orderParam.getMgId());
                String []mids = groupMember.getMemberId().split(",");
                for (int i=0;i<mids.length;i++){
                    if (orderParam.getMemberId().toString().equals(mids[i])){
                        return new CommonResult().failed("你已经参加过此活动");
                    }
                }

                groupMember.setName(groupMember.getName()+","+orderParam.getMemberIcon());
                groupMember.setOrderId(groupMember.getOrderId()+","+orderParam.getOrderId());
                groupMember.setMemberId(groupMember.getMemberId()+","+orderParam.getMemberId());
                groupMemberMapper.updateById(groupMember);
            }

        } else {
            return new CommonResult().failed("活动已经结束");
        }
        return new CommonResult().failed("活动已经结束");
    }


    @ApiOperation("根据条件查询所有拼团列表")
    @GetMapping(value = "/notAuth/listGroup")
    public List<SmsGroup> listGroup(){
        return    groupMapper.selectList(new QueryWrapper<>());
    }


    @ApiOperation("根据条件查询拼团")
    @GetMapping(value = "/notAuth/getGroupById")
    public SmsGroup getGroupById(@RequestParam("id") Long id){
        return groupMapper.selectById(id);
    }


    @ApiOperation("根据条件查询拼团用户表")
    @GetMapping(value = "/notAuth/selectGroupMemberList")
    public Object selectGroupMemberList(@RequestParam("id") Long id){
        return groupMemberMapper.selectList(new QueryWrapper<SmsGroupMember>().eq("group_id",id));
    }

    @PostMapping(value = "/notAuth/updateGroupById")
    public void updateGroupById(@RequestBody SmsGroup group) {
groupMapper.updateById(group);
    }

    @PostMapping(value = "/notAuth/matchOrderBasicMarking")
   public SmsBasicMarking matchOrderBasicMarking(@RequestBody CartMarkingVo vo){
        return basicMarkingService.matchOrderBasicMarking(vo);
    }

    @PostMapping(value = "/notAuth/matchOrderBasicGifts")
    public List<SmsBasicGifts> matchOrderBasicGifts(@RequestBody CartMarkingVo vo) {
       return basicGiftsService.matchOrderBasicGifts(vo);
    }

    @PostMapping(value = "/notAuth/getFlashPromotionProductRelationById")
    public SmsFlashPromotionProductRelation getFlashPromotionProductRelationById(@RequestParam("id") Integer id) {
       return smsFlashPromotionProductRelationMapper.selectById(id);
    }

    @PostMapping(value = "/notAuth/updateFlashPromotionProductRelationById")
    public void updateFlashPromotionProductRelationById(@RequestBody SmsFlashPromotionProductRelation relation) {
        smsFlashPromotionProductRelationMapper.updateById(relation);
    }

    @PostMapping(value = "/notAuth/getSmsGroupActivityById")
    public SmsGroupActivity getSmsGroupActivityById(@RequestParam("groupActivityId") Long groupActivityId) {
        return groupActivityMapper.selectById(groupActivityId);
    }

    @PostMapping(value = "/notAuth/getCouponById")
    public SmsCoupon getCouponById(@RequestParam("couponId") Long couponId) {
        return couponService.getById(couponId);
    }

    @PostMapping(value = "/notAuth/getcouponHistoryById")
    public SmsCouponHistory getcouponHistoryById(@RequestParam("memberCouponId") Long memberCouponId) {
        return  couponHistoryMapper.selectById(memberCouponId);
    }

    @PostMapping(value = "/notAuth/getPromotionProductList")
    public List<SmsCouponHistoryDetail> listCart(@RequestBody CartMarkingVo vo) {
        return couponService.listCart(vo);
    }


}
