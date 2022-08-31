package com.mallplus.goods.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mallplus.common.annotation.SysLog;
import com.mallplus.common.entity.pms.SmsHomeBrand;
import com.mallplus.goods.service.ISmsHomeBrandService;
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
 * 首页推荐品牌表
 * </p>
 *
 * @author zscat
 * @since 2019-04-19
 */
@Slf4j
@RestController
@Api(tags = "SmsHomeBrandController", description = "首页推荐品牌表管理")
@RequestMapping("/marking/SmsHomeBrand")
public class SmsHomeBrandController {
    @Resource
    private ISmsHomeBrandService ISmsHomeBrandService;

    @SysLog(MODULE = "sms", REMARK = "根据条件查询所有首页推荐品牌表列表")
    @ApiOperation("根据条件查询所有首页推荐品牌表列表")
    @GetMapping(value = "/list")
    @PreAuthorize("hasAuthority('marking:SmsHomeBrand:read')")
    public Object getSmsHomeBrandByPage(SmsHomeBrand entity,
                                        @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                        @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize
    ) {
        try {
            return new CommonResult().success(ISmsHomeBrandService.page(new Page<SmsHomeBrand>(pageNum, pageSize), new QueryWrapper<>(entity)));
        } catch (Exception e) {
            log.error("根据条件查询所有首页推荐品牌表列表：%s", e.getMessage(), e);
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "sms", REMARK = "保存首页推荐品牌表")
    @ApiOperation("保存首页推荐品牌表")
    @PostMapping(value = "/create")
    @PreAuthorize("hasAuthority('marking:SmsHomeBrand:create')")
    public Object batchCreate(@RequestBody List<SmsHomeBrand> homeBrandList) {
        boolean count = ISmsHomeBrandService.saveBatch(homeBrandList);
        if (count) {
            return new CommonResult().success(count);
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "sms", REMARK = "更新首页推荐品牌表")
    @ApiOperation("更新首页推荐品牌表")
    @PostMapping(value = "/update/{id}")
    @PreAuthorize("hasAuthority('marking:SmsHomeBrand:update')")
    public Object updateSmsHomeBrand(@RequestBody SmsHomeBrand entity) {
        try {
            if (ISmsHomeBrandService.updateById(entity)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("更新首页推荐品牌表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "sms", REMARK = "删除首页推荐品牌表")
    @ApiOperation("删除首页推荐品牌表")
    @GetMapping(value = "/delete/{id}")
    @PreAuthorize("hasAuthority('marking:SmsHomeBrand:delete')")
    public Object deleteSmsHomeBrand(@ApiParam("首页推荐品牌表id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("首页推荐品牌表id");
            }
            if (ISmsHomeBrandService.removeById(id)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("删除首页推荐品牌表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "sms", REMARK = "给首页推荐品牌表分配首页推荐品牌表")
    @ApiOperation("查询首页推荐品牌表明细")
    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('marking:SmsHomeBrand:read')")
    public Object getSmsHomeBrandById(@ApiParam("首页推荐品牌表id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("首页推荐品牌表id");
            }
            SmsHomeBrand coupon = ISmsHomeBrandService.getById(id);
            return new CommonResult().success(coupon);
        } catch (Exception e) {
            log.error("查询首页推荐品牌表明细：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }

    }

    @ApiOperation(value = "批量删除首页推荐品牌表")
    @RequestMapping(value = "/delete/batch", method = RequestMethod.POST)
    @ResponseBody
    @SysLog(MODULE = "pms", REMARK = "批量删除首页推荐品牌表")
    @PreAuthorize("hasAuthority('marking:SmsHomeBrand:delete')")
    public Object deleteBatch(@RequestParam("ids") List<Long> ids) {
        boolean count = ISmsHomeBrandService.removeByIds(ids);
        if (count) {
            return new CommonResult().success(count);
        } else {
            return new CommonResult().failed();
        }
    }

    @SysLog(MODULE = "sms", REMARK = "修改品牌排序")
    @ApiOperation("修改品牌排序")
    @RequestMapping(value = "/update/sort/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Object updateSort(@PathVariable Long id, Integer sort) {
        int count = ISmsHomeBrandService.updateSort(id, sort);
        if (count > 0) {
            return new CommonResult().success(count);
        }
        return new CommonResult().failed();
    }
    @SysLog(MODULE = "sms", REMARK = "批量修改推荐状态")
    @ApiOperation("批量修改推荐状态")
    @RequestMapping(value = "/update/recommendStatus", method = RequestMethod.POST)
    @ResponseBody
    public Object updateRecommendStatus(@RequestParam("ids") List<Long> ids, @RequestParam Integer recommendStatus) {
        int count = ISmsHomeBrandService.updateRecommendStatus(ids, recommendStatus);
        if (count > 0) {
            return new CommonResult().success(count);
        }
        return new CommonResult().failed();
    }
}
