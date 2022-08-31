package com.mallplus.marking.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mallplus.common.annotation.SysLog;
import com.mallplus.common.entity.sms.SmsPaimai;
import com.mallplus.common.entity.sms.SmsPaimaiLog;
import com.mallplus.marking.service.ISmsPaimaiLogService;
import com.mallplus.marking.service.ISmsPaimaiService;
import com.mallplus.common.utils.CommonResult;
import com.mallplus.common.utils.ValidatorUtils;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

/**
 * <p>
 * 竞拍 前端控制器
 * </p>
 *
 * @author zscat
 * @since 2019-10-19
 */
@Slf4j
@RestController
@RequestMapping("/sms/smsPaimai")
public class SmsPaimaiController {

    @Resource
    private ISmsPaimaiService ISmsCouponService;
    @Resource
    private ISmsPaimaiLogService smsPaimaiLogService;

    @SysLog(MODULE = "sms", REMARK = "根据条件查询所有竞拍表列表")
    @ApiOperation("根据条件查询所有竞拍表列表")
    @GetMapping(value = "/list")
    public Object getSmsCouponByPage(SmsPaimai entity,
                                     @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                     @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize
    ) {
        try {
            return new CommonResult().success(ISmsCouponService.page(new Page<SmsPaimai>(pageNum, pageSize), new QueryWrapper<>(entity).orderByDesc("create_time")));
        } catch (Exception e) {
            log.error("根据条件查询所有竞拍表列表：%s", e.getMessage(), e);
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "sms", REMARK = "根据条件查询所有竞拍表列表")
    @ApiOperation("根据条件查询所有竞拍表列表")
    @GetMapping(value = "/fetchPaiMaiLog")
    public Object fetchPaiMaiLog(SmsPaimaiLog entity,
                                 @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                 @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize
    ) {
        try {
            return new CommonResult().success(smsPaimaiLogService.page(new Page<SmsPaimaiLog>(pageNum, pageSize), new QueryWrapper<>(entity).orderByDesc("create_time")));
        } catch (Exception e) {
            log.error("根据条件查询所有竞拍表列表：%s", e.getMessage(), e);
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "sms", REMARK = "保存竞拍表")
    @ApiOperation("保存竞拍表")
    @PostMapping(value = "/create")

    public Object saveSmsCoupon(@RequestBody SmsPaimai entity) {
        try {
            entity.setCreateTime(new Date());
            if (ISmsCouponService.save(entity)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("保存竞拍表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "sms", REMARK = "更新竞拍表")
    @ApiOperation("更新竞拍表")
    @PostMapping(value = "/update/{id}")
    public Object updateSmsCoupon(@RequestBody SmsPaimai entity) {
        try {
            if (ISmsCouponService.updateById(entity)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("更新竞拍表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "sms", REMARK = "删除竞拍表")
    @ApiOperation("删除竞拍表")
    @GetMapping(value = "/delete/{id}")
    public Object deleteSmsCoupon(@ApiParam("竞拍表id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("竞拍表id");
            }
            if (ISmsCouponService.removeById(id)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("删除竞拍表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "sms", REMARK = "给竞拍表分配竞拍表")
    @ApiOperation("查询竞拍表明细")
    @GetMapping(value = "/{id}")
    public Object getSmsCouponById(@ApiParam("竞拍表id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("竞拍表id");
            }
            SmsPaimai coupon = ISmsCouponService.getById(id);
            return new CommonResult().success(coupon);
        } catch (Exception e) {
            log.error("查询竞拍表明细：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }

    }

    @ApiOperation("修改展示状态")
    @RequestMapping(value = "/update/updateShowStatus")
    @ResponseBody
    public Object updateShowStatus(@RequestParam("ids") Long ids,
                                   @RequestParam("showStatus") Integer showStatus) {
        SmsPaimai record = new SmsPaimai();
        record.setStatus(showStatus);
        record.setId(ids);
        return new CommonResult().success(ISmsCouponService.updateById(record));
    }
}

