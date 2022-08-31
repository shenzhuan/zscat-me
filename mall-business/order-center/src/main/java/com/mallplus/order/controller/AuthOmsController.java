package com.mallplus.order.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mallplus.common.annotation.IgnoreAuth;
import com.mallplus.common.annotation.SysLog;
import com.mallplus.common.constant.AllEnum;
import com.mallplus.common.constant.ConstansValue;
import com.mallplus.common.entity.oms.OmsCartItem;
import com.mallplus.common.entity.oms.OmsOrder;
import com.mallplus.common.entity.oms.OmsOrderItem;
import com.mallplus.common.entity.oms.UmsMemberReceiveAddress;
import com.mallplus.common.entity.ums.UmsMember;
import com.mallplus.common.exception.ApiMallPlusException;
import com.mallplus.common.feign.MemberFeignClient;
import com.mallplus.common.utils.CommonResult;
import com.mallplus.common.vo.CartParam;
import com.mallplus.common.vo.OrderParam;
import com.mallplus.order.mapper.UmsMemberReceiveAddressMapper;
import com.mallplus.order.service.IOmsCartItemService;
import com.mallplus.order.service.IOmsOrderItemService;
import com.mallplus.order.service.IOmsOrderService;
import com.mallplus.order.service.IUmsMemberReceiveAddressService;
import com.mallplus.order.vo.ConfirmOrderResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: shenzhuan
 * @Date: 2019/4/2 15:02
 * @Description:
 */
@Slf4j
@RestController
@Api(tags = "AuthOmsController", description = "订单管理系统")
@RequestMapping("/auth")
public class AuthOmsController {

    @Autowired
    private IOmsCartItemService cartItemService;
    @Resource
    private IOmsOrderService orderService;
    @Resource
    private IOmsOrderItemService orderItemService;
    @Resource
    private MemberFeignClient memberFeignClient;
    @Resource
    private UmsMemberReceiveAddressMapper addressMapper;
    @Autowired
    private IUmsMemberReceiveAddressService memberReceiveAddressService;

    @ApiOperation("获取订单详情:订单信息、商品信息、操作记录")
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    @ResponseBody
    public Object detail(@RequestParam(value = "id", required = false) Long id) {
        OmsOrder orderDetailResult = null;
        orderDetailResult = orderService.getById(id);
        OmsOrderItem query = new OmsOrderItem();
        query.setOrderId(id);
        List<OmsOrderItem> orderItemList = orderItemService.list(new QueryWrapper<>(query));
        orderDetailResult.setOrderItemList(orderItemList);
        UmsMember member = memberFeignClient.findById(orderDetailResult.getMemberId());
        if(member!=null){
            orderDetailResult.setBlance(member.getBlance());
        }
        return new CommonResult().success(orderDetailResult);
    }

    @IgnoreAuth
    @SysLog(MODULE = "oms", REMARK = "查询订单列表")
    @ApiOperation(value = "查询订单列表")
    @GetMapping(value = "/order/list")
    public Object orderList(OmsOrder order,
                            @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                            @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum) {
        IPage<OmsOrder> page = null;
        if (order.getStatus()!=null && order.getStatus()==0){
            page = orderService.page(new Page<OmsOrder>(pageNum, pageSize), new QueryWrapper<OmsOrder>().eq("member_id", order.getMemberId()).orderByDesc("create_time").select(ConstansValue.sampleOrderList));
        }else {
            order.setMemberId(order.getMemberId());
            page = orderService.page(new Page<OmsOrder>(pageNum, pageSize), new QueryWrapper<>(order).orderByDesc("create_time").select(ConstansValue.sampleOrderList)) ;

        }
        for (OmsOrder omsOrder : page.getRecords()){
            List<OmsOrderItem> itemList = orderItemService.list(new QueryWrapper<OmsOrderItem>().eq("order_id",omsOrder.getId()).eq("type", AllEnum.OrderItemType.GOODS.code()));
            omsOrder.setOrderItemList(itemList);
        }
        return new CommonResult().success(page);
    }


    @ResponseBody
    @GetMapping("/submitPreview")
    public Object submitPreview(OrderParam orderParam) {
        try {
            ConfirmOrderResult result = orderService.submitPreview(orderParam);
            return new CommonResult().success(result);
        } catch (ApiMallPlusException e) {
            return new CommonResult().failed(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 提交订单
     *
     * @param orderParam
     * @return
     */
    @ApiOperation("根据购物车信息生成订单")
    @RequestMapping(value = "/generateOrder")
    @ResponseBody
    public Object generateOrder(OrderParam orderParam) {
        return orderService.generateOrder(orderParam);
    }

    @ApiOperation("发起拼团")
    @RequestMapping(value = "/addGroup")
    @ResponseBody
    public Object addGroup(OrderParam orderParam) {
        try {
            return new CommonResult().success(orderService.addGroup(orderParam));
        } catch (ApiMallPlusException e) {
            return new CommonResult().failed(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @ApiOperation("提交拼团")
    @RequestMapping(value = "/acceptGroup")
    @ResponseBody
    public Object acceptGroup(OrderParam orderParam) {
        try {
            return orderService.acceptGroup(orderParam);
        } catch (ApiMallPlusException e) {
            return new CommonResult().failed(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @ApiOperation("添加商品到购物车")
    @RequestMapping(value = "/addCart")
    @ResponseBody
    public Object addCart(CartParam cartParam) {
        return orderService.addCart(cartParam);
    }

    @ApiOperation("获取某个会员的购物车列表")
    @RequestMapping(value = "/cartList", method = RequestMethod.GET)
    @ResponseBody
    public Object list(@RequestParam(value = "memberId", required = true) Long memberId) {

        List<OmsCartItem> cartItemList = cartItemService.list(memberId, null);
        return new CommonResult().success(cartItemList);

    }

    @ApiOperation("修改购物车中某个商品的数量")
    @RequestMapping(value = "/update/quantity", method = RequestMethod.GET)
    @ResponseBody
    public Object updateQuantity(@RequestParam Long id,
                                 @RequestParam Integer quantity,
                                 @RequestParam(value = "memberId", required = true) Long memberId) {
        int count = cartItemService.updateQuantity(id, memberId, quantity);
        if (count > 0) {
            return new CommonResult().success(count);
        }
        return new CommonResult().failed();
    }


    @ApiOperation("删除购物车中的某个商品")
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam(value = "cart_id_list", required = true) String cart_id_list, @RequestParam(value = "memberId", required = true) Long memberId) {
        if (StringUtils.isEmpty(cart_id_list)) {
            return new CommonResult().failed("参数为空");
        }
        List<Long> resultList = new ArrayList<>(cart_id_list.split(",").length);
        for (String s : cart_id_list.split(",")) {
            resultList.add(Long.valueOf(s));
        }
        int count = cartItemService.delete(memberId, resultList);
        if (count > 0) {
            return new CommonResult().success(count);
        }
        return new CommonResult().failed();
    }

    @ApiOperation("清空购物车")
    @RequestMapping(value = "/clear", method = RequestMethod.POST)
    @ResponseBody
    public Object clear(@RequestParam(value = "memberId", required = false) Long memberId) {
        int count = cartItemService.clear(memberId);
        if (count > 0) {
            return new CommonResult().success(count);
        }
        return new CommonResult().failed();
    }
    @SysLog(MODULE = "订单管理", REMARK = "订单确认收货")
    @ApiOperation("订单确认收货")
    @RequestMapping(value = "/confimDelivery", method = RequestMethod.POST)
    @ResponseBody
    public Object confimDelivery(@ApiParam("订单id") @RequestParam Long id) {
        try {
            return orderService.confimDelivery(id);
        } catch (Exception e) {
            return new CommonResult().failed();
        }
    }
    @SysLog(MODULE = "订单管理", REMARK = "订单申请退款")
    @ApiOperation("申请退款")
    @RequestMapping(value = "/applyRefund", method = RequestMethod.POST)
    @ResponseBody
    public Object applyRefund(@ApiParam("订单id") @RequestParam Long id) {
        try {
            return orderService.applyRefund(id);
        } catch (Exception e) {
            log.error("订单确认收货：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
    }

    @SysLog(MODULE = "oms", REMARK = "添加订单评论")
    @ApiOperation(value = "添加订单评论")
    @PostMapping(value = "/orderevaluate")
    public Object addGoodsConsult( @RequestParam(value = "orderId", defaultValue = "1") Long orderId,
                                   @RequestParam(value = "items", defaultValue = "10") String items) throws Exception {

        return orderService.orderComment(orderId,items);
    }


    @ApiOperation("删除收货地址")
    @RequestMapping(value = "/deleteAddress")
    @ResponseBody
    public Object delete(@RequestParam(value = "id", required = false, defaultValue = "0") Long id) {
        boolean count = memberReceiveAddressService.removeById(id);
        if (count) {
            return new CommonResult().success(count);
        }
        return new CommonResult().failed();
    }

    @ApiOperation("修改收货地址")
    @RequestMapping(value = "/saveAddress")
    @ResponseBody
    public Object update(UmsMemberReceiveAddress address) {
        boolean count = false;
        if (address.getDefaultStatus() != null && address.getDefaultStatus() == 1) {
            addressMapper.updateStatusByMember(address.getMemberId());
        }
        if (address != null && address.getId() != null) {
            count = memberReceiveAddressService.updateById(address);
        } else {
            count = memberReceiveAddressService.save(address);
        }
        if (count) {
            return new CommonResult().success(count);
        }
        return new CommonResult().failed();
    }

    @IgnoreAuth
    @ApiOperation("显示所有收货地址")
    @RequestMapping(value = "/listAddress", method = RequestMethod.GET)
    @ResponseBody
    public Object listAddress(@RequestParam(value = "memberId", required = false, defaultValue = "0") Long memberId) {

        List<UmsMemberReceiveAddress> addressList = memberReceiveAddressService.list(new QueryWrapper<UmsMemberReceiveAddress>().eq("member_id", memberId));
        return new CommonResult().success(addressList);

    }

    @IgnoreAuth
    @ApiOperation("显示所有收货地址")
    @RequestMapping(value = "/detailAddress", method = RequestMethod.GET)
    @ResponseBody
    public Object getItem(@RequestParam(value = "id", required = false, defaultValue = "0") Long id) {
        UmsMemberReceiveAddress address = memberReceiveAddressService.getById(id);
        return new CommonResult().success(address);
    }

    @IgnoreAuth
    @ApiOperation("显示默认收货地址")
    @RequestMapping(value = "/getItemDefautl", method = RequestMethod.GET)
    @ResponseBody
    public Object getItemDefautl(@RequestParam(value = "memberId", required = false, defaultValue = "0") Long memberId) {
        UmsMemberReceiveAddress address = memberReceiveAddressService.getDefaultItem(memberId);
        return new CommonResult().success(address);
    }

    /**
     * @param id
     * @return
     */
    @ApiOperation("设为默认地址")
    @RequestMapping(value = "/address-set-default")
    @ResponseBody
    public Object setDefault(@RequestParam(value = "id", required = false, defaultValue = "0") Long id
            , @RequestParam(value = "memberId", required = false, defaultValue = "0") Long memberId) {
        int count = memberReceiveAddressService.setDefault(id, memberId);
        if (count > 0) {
            return new CommonResult().success(count);
        }
        return new CommonResult().failed();
    }
}
