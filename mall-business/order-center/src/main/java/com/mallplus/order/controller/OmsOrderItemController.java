package com.mallplus.order.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mallplus.common.annotation.SysLog;
import com.mallplus.common.entity.oms.OmsOrderItem;
import com.mallplus.order.service.IOmsOrderItemService;
import com.mallplus.common.utils.CommonResult;
import com.mallplus.common.utils.ValidatorUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 订单中所包含的商品
 * </p>
 *
 * @author zscat
 * @since 2019-04-19
 */
@Slf4j
@RestController
@Api(tags = "OmsOrderItemController", description = "订单中所包含的商品管理")
@RequestMapping("/oms/OmsOrderItem")
public class OmsOrderItemController {
    @Resource
    private IOmsOrderItemService IOmsOrderItemService;

    @SysLog(MODULE = "oms", REMARK = "根据条件查询所有订单中所包含的商品列表")
    @ApiOperation("根据条件查询所有订单中所包含的商品列表")
    @GetMapping(value = "/list")
    @PreAuthorize("hasAuthority('oms:OmsOrderItem:read')")
    public Object getOmsOrderItemByPage(OmsOrderItem entity,
                                        @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                        @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize
    ) {
        try {
            return new CommonResult().success(IOmsOrderItemService.page(new Page<OmsOrderItem>(pageNum, pageSize), new QueryWrapper<>(entity)));
        } catch (Exception e) {
            log.error("根据条件查询所有订单中所包含的商品列表：%s", e.getMessage(), e);
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "oms", REMARK = "保存订单中所包含的商品")
    @ApiOperation("保存订单中所包含的商品")
    @PostMapping(value = "/create")
    @PreAuthorize("hasAuthority('oms:OmsOrderItem:create')")
    public Object saveOmsOrderItem(@RequestBody OmsOrderItem entity) {
        try {
            if (IOmsOrderItemService.save(entity)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("保存订单中所包含的商品：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "oms", REMARK = "更新订单中所包含的商品")
    @ApiOperation("更新订单中所包含的商品")
    @PostMapping(value = "/update/{id}")
    @PreAuthorize("hasAuthority('oms:OmsOrderItem:update')")
    public Object updateOmsOrderItem(@RequestBody OmsOrderItem entity) {
        try {
            if (IOmsOrderItemService.updateById(entity)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("更新订单中所包含的商品：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "oms", REMARK = "删除订单中所包含的商品")
    @ApiOperation("删除订单中所包含的商品")
    @GetMapping(value = "/delete/{id}")
    @PreAuthorize("hasAuthority('oms:OmsOrderItem:delete')")
    public Object deleteOmsOrderItem(@ApiParam("订单中所包含的商品id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("订单中所包含的商品id");
            }
            if (IOmsOrderItemService.removeById(id)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("删除订单中所包含的商品：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "oms", REMARK = "给订单中所包含的商品分配订单中所包含的商品")
    @ApiOperation("查询订单中所包含的商品明细")
    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('oms:OmsOrderItem:read')")
    public Object getOmsOrderItemById(@ApiParam("订单中所包含的商品id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("订单中所包含的商品id");
            }
            OmsOrderItem coupon = IOmsOrderItemService.getById(id);
            return new CommonResult().success(coupon);
        } catch (Exception e) {
            log.error("查询订单中所包含的商品明细：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }

    }

    @ApiOperation(value = "批量删除订单中所包含的商品")
    @RequestMapping(value = "/delete/batch", method = RequestMethod.POST)
    @ResponseBody
    @SysLog(MODULE = "pms", REMARK = "批量删除订单中所包含的商品")
    @PreAuthorize("hasAuthority('oms:OmsOrderItem:delete')")
    public Object deleteBatch(@RequestParam("ids") List<Long> ids) {
        boolean count = IOmsOrderItemService.removeByIds(ids);
        if (count) {
            return new CommonResult().success(count);
        } else {
            return new CommonResult().failed();
        }
    }

}
