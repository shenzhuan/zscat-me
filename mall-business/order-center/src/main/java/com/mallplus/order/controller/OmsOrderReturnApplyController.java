package com.mallplus.order.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mallplus.common.annotation.SysLog;
import com.mallplus.common.entity.oms.OmsOrderReturnApply;
import com.mallplus.order.service.IOmsOrderReturnApplyService;
import com.mallplus.order.vo.OmsUpdateStatusParam;
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
 * 订单退货申请
 * </p>
 *
 * @author zscat
 * @since 2019-04-19
 */
@Slf4j
@RestController
@Api(tags = "OmsOrderReturnApplyController", description = "订单退货申请管理")
@RequestMapping("/oms/OmsOrderReturnApply")
public class OmsOrderReturnApplyController {
    @Resource
    private IOmsOrderReturnApplyService IOmsOrderReturnApplyService;

    @SysLog(MODULE = "oms", REMARK = "根据条件查询所有订单退货申请列表")
    @ApiOperation("根据条件查询所有订单退货申请列表")
    @GetMapping(value = "/list")
    @PreAuthorize("hasAuthority('oms:OmsOrderReturnApply:read')")
    public Object getOmsOrderReturnApplyByPage(OmsOrderReturnApply entity,
                                               @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                               @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize
    ) {
        try {
            return new CommonResult().success(IOmsOrderReturnApplyService.page(new Page<OmsOrderReturnApply>(pageNum, pageSize), new QueryWrapper<>(entity).orderByDesc("create_time")));
        } catch (Exception e) {
            log.error("根据条件查询所有订单退货申请列表：%s", e.getMessage(), e);
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "oms", REMARK = "保存订单退货申请")
    @ApiOperation("保存订单退货申请")
    @PostMapping(value = "/create")
    @PreAuthorize("hasAuthority('oms:OmsOrderReturnApply:create')")
    public Object saveOmsOrderReturnApply(@RequestBody OmsOrderReturnApply entity) {
        try {
            if (IOmsOrderReturnApplyService.save(entity)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("保存订单退货申请：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "oms", REMARK = "更新订单退货申请")
    @ApiOperation("更新订单退货申请")
    @PostMapping(value = "/update/{id}")
    @PreAuthorize("hasAuthority('oms:OmsOrderReturnApply:update')")
    public Object updateOmsOrderReturnApply(@RequestBody OmsOrderReturnApply entity) {
        try {
            if (IOmsOrderReturnApplyService.updateById(entity)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("更新订单退货申请：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "oms", REMARK = "删除订单退货申请")
    @ApiOperation("删除订单退货申请")
    @GetMapping(value = "/delete/{id}")
    @PreAuthorize("hasAuthority('oms:OmsOrderReturnApply:delete')")
    public Object deleteOmsOrderReturnApply(@ApiParam("订单退货申请id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("订单退货申请id");
            }
            if (IOmsOrderReturnApplyService.removeById(id)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("删除订单退货申请：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "oms", REMARK = "给订单退货申请分配订单退货申请")
    @ApiOperation("查询订单退货申请明细")
    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('oms:OmsOrderReturnApply:read')")
    public Object getOmsOrderReturnApplyById(@ApiParam("订单退货申请id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("订单退货申请id");
            }
            OmsOrderReturnApply coupon = IOmsOrderReturnApplyService.getById(id);
            return new CommonResult().success(coupon);
        } catch (Exception e) {
            log.error("查询订单退货申请明细：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }

    }

    @ApiOperation(value = "批量删除订单退货申请")
    @RequestMapping(value = "/delete/batch", method = RequestMethod.POST)
    @ResponseBody
    @SysLog(MODULE = "pms", REMARK = "批量删除订单退货申请")
    @PreAuthorize("hasAuthority('oms:OmsOrderReturnApply:delete')")
    public Object deleteBatch(@RequestParam("ids") List<Long> ids) {
        boolean count = IOmsOrderReturnApplyService.removeByIds(ids);
        if (count) {
            return new CommonResult().success(count);
        } else {
            return new CommonResult().failed();
        }
    }
    @SysLog(MODULE = "oms", REMARK = "获取所有收货地址")
    @ApiOperation("修改申请状态")
    @RequestMapping(value = "/update/status/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Object updateStatus(@PathVariable Long id, @RequestBody OmsUpdateStatusParam statusParam) {
        int count = IOmsOrderReturnApplyService.updateStatus(id, statusParam);
        if (count > 0) {
            return new CommonResult().success(count);
        }
        return new CommonResult().failed();
    }
}
