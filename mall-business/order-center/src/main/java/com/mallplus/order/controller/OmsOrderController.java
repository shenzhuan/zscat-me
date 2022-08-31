package com.mallplus.order.controller;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mallplus.common.annotation.IgnoreAuth;
import com.mallplus.common.annotation.SysLog;
import com.mallplus.common.constant.ConstansValue;
import com.mallplus.common.entity.oms.OmsOrderItem;
import com.mallplus.common.entity.oms.OmsOrderOperateHistory;
import com.mallplus.common.utils.CommonResult;
import com.mallplus.common.utils.DateUtils;
import com.mallplus.common.utils.ValidatorUtils;
import com.mallplus.common.entity.oms.OmsOrder;
import com.mallplus.order.mapper.OmsOrderOperateHistoryMapper;
import com.mallplus.order.service.IOmsOrderItemService;
import com.mallplus.order.service.IOmsOrderService;
import com.mallplus.order.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 订单表
 * </p>
 *
 * @author zscat
 * @since 2019-04-19
 */
@Slf4j
@RestController
@Api(tags = "OmsOrderController", description = "订单表管理")
@RequestMapping("/oms/OmsOrder")
public class OmsOrderController {
    @Resource
    private IOmsOrderService IOmsOrderService;
    @Resource
    private IOmsOrderItemService orderItemService;

    @Resource
    private OmsOrderOperateHistoryMapper omsOrderOperateHistoryMapper;

    @SysLog(MODULE = "oms", REMARK = "根据条件查询所有订单表列表")
    @ApiOperation("根据条件查询所有订单表列表")
    @GetMapping(value = "/list")
    @PreAuthorize("hasAuthority('oms:OmsOrder:read')")
    public Object getOmsOrderByPage(OmsOrder entity,
                                    @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                    @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize
    ) {
        try {
            return new CommonResult().success(IOmsOrderService.page(new Page<OmsOrder>(pageNum, pageSize), new QueryWrapper<>(entity).orderByDesc("create_time").select(ConstansValue.sampleOrderList)));
        } catch (Exception e) {
            log.error("根据条件查询所有订单表列表：%s", e.getMessage(), e);
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "oms", REMARK = "保存订单表")
    @ApiOperation("保存订单表")
    @PostMapping(value = "/create")
    @PreAuthorize("hasAuthority('oms:OmsOrder:create')")
    public Object saveOmsOrder(@RequestBody OmsOrder entity) {
        try {
            if (IOmsOrderService.save(entity)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("保存订单表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "oms", REMARK = "更新订单表")
    @ApiOperation("更新订单表")
    @PostMapping(value = "/update/{id}")
    @PreAuthorize("hasAuthority('oms:OmsOrder:update')")
    public Object updateOmsOrder(@RequestBody OmsOrder entity) {
        try {
            if (IOmsOrderService.updateById(entity)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("更新订单表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "oms", REMARK = "删除订单表")
    @ApiOperation("删除订单表")
    @GetMapping(value = "/delete/{id}")
    @PreAuthorize("hasAuthority('oms:OmsOrder:delete')")
    public Object deleteOmsOrder(@ApiParam("订单表id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("订单表id");
            }
            if (IOmsOrderService.removeById(id)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("删除订单表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "oms", REMARK = "给订单表分配订单表")
    @ApiOperation("查询订单表明细")
    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('oms:OmsOrder:read')")
    public Object getOmsOrderById(@ApiParam("订单表id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("订单表id");
            }
            OmsOrder coupon = IOmsOrderService.getById(id);
            coupon.setOrderItemList(orderItemService.list(new QueryWrapper<OmsOrderItem>().eq("order_id",coupon.getId())));
            coupon.setHistoryList(omsOrderOperateHistoryMapper.selectList(new QueryWrapper<OmsOrderOperateHistory>().eq("order_id",coupon.getId())));
            return new CommonResult().success(coupon);
        } catch (Exception e) {
            log.error("查询订单表明细：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }

    }

    @ApiOperation(value = "批量删除订单表")
    @RequestMapping(value = "/delete/batch", method = RequestMethod.POST)
    @ResponseBody
    @SysLog(MODULE = "pms", REMARK = "批量删除订单表")
    @PreAuthorize("hasAuthority('oms:OmsOrder:delete')")
    public Object deleteBatch(@RequestParam("ids") List<Long> ids) {
        boolean count = IOmsOrderService.removeByIds(ids);
        if (count) {
            return new CommonResult().success(count);
        } else {
            return new CommonResult().failed();
        }
    }

    @SysLog(MODULE = "oms", REMARK = "批量发货")
    @ApiOperation("批量发货")
    @RequestMapping(value = "/update/delivery", method = RequestMethod.POST)
    @ResponseBody
    public Object delivery(@RequestBody List<OmsOrderDeliveryParam> deliveryParamList) {
        int count = IOmsOrderService.delivery(deliveryParamList);
        if (count > 0) {
            return new CommonResult().success(count);
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "oms", REMARK = "批量关闭订单")
    @ApiOperation("批量关闭订单")
    @RequestMapping(value = "/update/close", method = RequestMethod.POST)
    @ResponseBody
    public Object close(@RequestParam("ids") List<Long> ids, @RequestParam String note) {
        int count = IOmsOrderService.close(ids, note);
        if (count > 0) {
            return new CommonResult().success(count);
        }
        return new CommonResult().failed();
    }
    @SysLog(MODULE = "oms", REMARK = "修改收货人信息")
    @ApiOperation("修改收货人信息")
    @RequestMapping(value = "/update/receiverInfo", method = RequestMethod.POST)
    @ResponseBody
    public Object updateReceiverInfo(@RequestBody OmsReceiverInfoParam receiverInfoParam) {
        int count = IOmsOrderService.updateReceiverInfo(receiverInfoParam);
        if (count > 0) {
            return new CommonResult().success(count);
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "oms", REMARK = "修改订单费用信息")
    @ApiOperation("修改订单费用信息")
    @RequestMapping(value = "/update/moneyInfo", method = RequestMethod.POST)
    @ResponseBody
    public Object updateReceiverInfo(@RequestBody OmsMoneyInfoParam moneyInfoParam) {
        int count = IOmsOrderService.updateMoneyInfo(moneyInfoParam);
        if (count > 0) {
            return new CommonResult().success(count);
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "oms", REMARK = "备注订单")
    @ApiOperation("备注订单")
    @RequestMapping(value = "/update/note", method = RequestMethod.POST)
    @ResponseBody
    public Object updateNote(@RequestParam("id") Long id,
                             @RequestParam("note") String note,
                             @RequestParam("status") Integer status) {
        int count = IOmsOrderService.updateNote(id, note, status);
        if (count > 0) {
            return new CommonResult().success(count);
        }
        return new CommonResult().failed();
    }
    /**
     * 订单状态：0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单
     *
     * @return
     */
    @ApiOperation("首页订单统计")
    @SysLog(MODULE = "home", REMARK = "首页订单统计")
    @RequestMapping(value = "/orderStatic", method = RequestMethod.GET)
    public Object orderStatic() throws Exception {
        HomeOrderData data = new HomeOrderData();
        List<OmsOrder> orderList = IOmsOrderService.list();
        int nowOrderCount = 0; // 今日订单
        BigDecimal nowOrderPay = new BigDecimal(0); //今日销售总额

        int yesOrderCount = 0; // 昨日订单
        BigDecimal yesOrderPay = new BigDecimal(0); //日销售总额

        int qiOrderCount = 0; // 7日订单
        BigDecimal qiOrderPay = new BigDecimal(0); //7日销售总额

        int monthOrderCount = 0; // 本月订单
        BigDecimal monthOrderPay = new BigDecimal(0); //本月销售总额

        int weekOrderCount = 0; // 本月订单
        BigDecimal weekOrderPay = new BigDecimal(0); //本月销售总额

        int status0 = 0;
        int status1 = 0;
        int status2 = 0;
        int status3 = 0;
        int status4 = 0;
        int status5 = 0;
        OrderStatusCount count = new OrderStatusCount();

        for (OmsOrder order : orderList) {
            if (DateUtil.formatDate(order.getCreateTime()).equals(DateUtil.formatDate(new Date()))
                    && (order.getStatus() == 1 || order.getStatus() == 2 || order.getStatus() == 3)) {
                nowOrderCount++;
                nowOrderPay = nowOrderPay.add(order.getPayAmount());
            }
            if (DateUtil.formatDate(order.getCreateTime()).equals(DateUtils.addDay(new Date(), -1))
                    && (order.getStatus() == 1 || order.getStatus() == 2 || order.getStatus() == 3)) {
                yesOrderCount++;
                yesOrderPay = yesOrderPay.add(order.getPayAmount());
            }
            if (DateUtils.calculateDaysNew(order.getCreateTime(), new Date()) >= 7
                    && (order.getStatus() == 1 || order.getStatus() == 2 || order.getStatus() == 3)) {
                qiOrderCount++;
                qiOrderPay = qiOrderPay.add(order.getPayAmount());
            }
            if (order.getCreateTime().getTime()>=DateUtils.geFirstDayDateByMonth().getTime()
                    && (order.getStatus() == 1 || order.getStatus() == 2 || order.getStatus() == 3)) {
                monthOrderCount++;
                monthOrderPay = monthOrderPay.add(order.getPayAmount());
            }
            if (order.getCreateTime().getTime()>=DateUtils.getFirstDayOfWeek().getTime()
                    && (order.getStatus() == 1 || order.getStatus() == 2 || order.getStatus() == 3)) {
                weekOrderCount++;
                weekOrderPay = weekOrderPay.add(order.getPayAmount());
            }
            if (order.getStatus() == 0) {
                status0++;
            }
            if (order.getStatus() == 1) {
                status1++;
            }
            if (order.getStatus() == 2) {
                status2++;
            }
            if (order.getStatus() == 3) {
                status3++;
            }
            if (order.getStatus() == 4) {
                status4++;
            }
            if (order.getStatus() == 5) {
                status5++;
            }

        }
        count.setStatus0(status0);
        count.setStatus1(status1);
        count.setStatus2(status2);
        count.setStatus3(status3);
        count.setStatus4(status4);
        count.setStatus5(status5);

        data.setNowOrderCount(nowOrderCount);
        data.setNowOrderPay(nowOrderPay);
        data.setYesOrderCount(yesOrderCount);
        data.setYesOrderPay(yesOrderPay);
        data.setQiOrderCount(qiOrderCount);
        data.setQiOrderPay(qiOrderPay);
        data.setOrderStatusCount(count);
        data.setMonthOrderCount(monthOrderCount);
        data.setMonthOrderPay(monthOrderPay);
        data.setWeekOrderCount(weekOrderCount);
        data.setWeekOrderPay(weekOrderPay);
        return new CommonResult().success(data);
    }

    @IgnoreAuth
    @ApiOperation("小程序用户详情")
    @SysLog(MODULE = "applet", REMARK = "小程序用户详情")
    @GetMapping("/order/front_static")
    public Object user(Long userId) {

            OmsOrder param = new OmsOrder();
            param.setMemberId(userId);
            List<OmsOrder> list = IOmsOrderService.list(new QueryWrapper<>(param));
            int status0 = 0;
            int status1 = 0;
            int status2 = 0;
            int status3 = 0;
            int status4 = 0;
            int status5 = 0;
            OrderStatusCount count = new OrderStatusCount();
            for (OmsOrder consult : list) {
                if (consult.getStatus() == 0) {
                    status0++;
                }
                if (consult.getStatus() == 1) {
                    status1++;
                }
                if (consult.getStatus() == 2) {
                    status2++;
                }
                if (consult.getStatus() == 3) {
                    status2++;
                }
                if (consult.getStatus() == 4) {
                    status4++;
                }
                if (consult.getStatus() == 5) {
                    status5++;
                }
            }
            count.setStatus0(status0);
            count.setStatus1(status1);
            count.setStatus2(status2);
            count.setStatus3(status3);
            count.setStatus4(status4);
            count.setStatus5(status5);

            return new CommonResult().success(count);

    }
}
