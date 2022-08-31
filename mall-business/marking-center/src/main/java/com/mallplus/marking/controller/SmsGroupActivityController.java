package com.mallplus.marking.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mallplus.common.annotation.SysLog;
import com.mallplus.common.vo.SamplePmsProduct;

import com.mallplus.common.entity.sms.SmsGroupActivity;
import com.mallplus.marking.service.ISmsGroupActivityService;
import com.mallplus.common.utils.CommonResult;
import com.mallplus.common.utils.ValidatorUtils;
import com.mallplus.sentinel.config.ConstansValue;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zscat
 * @since 2019-10-12
 */
@Slf4j
@RestController
@RequestMapping("/sms/smsGroupActivity")
public class SmsGroupActivityController {

    @Resource
    private ISmsGroupActivityService smsGroupActivityService;

    @SysLog(MODULE = "cms", REMARK = "根据条件查询所有团购活动表列表")
    @ApiOperation("根据条件查询所有团购活动表列表")
    @GetMapping(value = "/list")
    public Object getSmsGroupActivityByPage(SmsGroupActivity entity,
                                            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize
    ) {
        try {
            return new CommonResult().success(smsGroupActivityService.page(new Page<SmsGroupActivity>(pageNum, pageSize), new QueryWrapper<>(entity).orderByDesc("create_time")));
        } catch (Exception e) {
            log.error("根据条件查询所有团购活动表列表：%s", e.getMessage(), e);
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "cms", REMARK = "保存团购活动表")
    @ApiOperation("保存团购活动表")
    @PostMapping(value = "/create")
    public Object saveSmsGroupActivity(@RequestBody SmsGroupActivity entity) {
        try {
            List<SamplePmsProduct> list = entity.getProductList();
            String goodsIs = "";
            BigDecimal originPrice = BigDecimal.ZERO;
            for (SamplePmsProduct p : list) {
                originPrice = originPrice.add(p.getPrice());
                goodsIs = goodsIs + p.getId() + ",";
            }
            entity.setOriginprice(originPrice);
            entity.setGoodsIds(goodsIs.substring(0, goodsIs.length() - 1));
            if (smsGroupActivityService.save(entity)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("保存团购活动表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "cms", REMARK = "更新团购活动表")
    @ApiOperation("更新团购活动表")
    @PostMapping(value = "/update/{id}")
    public Object updateSmsGroupActivity(@RequestBody SmsGroupActivity entity) {
        try {
            List<SamplePmsProduct> list = entity.getProductList();
            String goodsIs = "";
            BigDecimal originPrice = BigDecimal.ZERO;
            for (SamplePmsProduct p : list) {
                originPrice = originPrice.add(p.getPrice());
                goodsIs = goodsIs + p.getId() + ",";
            }
            entity.setOriginprice(originPrice);
            entity.setGoodsIds(goodsIs.substring(0, goodsIs.length() - 1));
            if (smsGroupActivityService.updateById(entity)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("更新团购活动表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "cms", REMARK = "删除团购活动表")
    @ApiOperation("删除团购活动表")
    @GetMapping(value = "/delete/{id}")
    public Object deleteSmsGroupActivity(@ApiParam("团购活动表id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("团购活动表id");
            }
            if (smsGroupActivityService.removeById(id)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("删除团购活动表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "cms", REMARK = "给团购活动表分配团购活动表")
    @ApiOperation("查询团购活动表明细")
    @GetMapping(value = "/{id}")
    public Object getSmsGroupActivityById(@ApiParam("团购活动表id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("团购活动表id");
            }
            SmsGroupActivity coupon = smsGroupActivityService.getById(id);
            return new CommonResult().success(coupon);
        } catch (Exception e) {
            log.error("查询团购活动表明细：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }

    }

    @ApiOperation(value = "批量删除团购活动表")
    @RequestMapping(value = "/delete/batch", method = RequestMethod.GET)
    @ResponseBody
    @SysLog(MODULE = "pms", REMARK = "批量删除团购活动表")
    public Object deleteBatch(@RequestParam("ids") List<Long> ids) {
        boolean count = smsGroupActivityService.removeByIds(ids);
        if (count) {
            return new CommonResult().success(count);
        } else {
            return new CommonResult().failed();
        }
    }

    @ApiOperation("修改展示状态")
    @RequestMapping(value = "/update/updateShowStatus")
    @ResponseBody
    @SysLog(MODULE = "cms", REMARK = "修改展示状态")
    public Object updateShowStatus(@RequestParam("ids") Long ids,
                                   @RequestParam("status") Integer status) {
        int count = smsGroupActivityService.updateShowStatus(ids, status);
        if (count > 0) {
            return new CommonResult().success(count);
        } else {
            return new CommonResult().failed();
        }
    }
}

