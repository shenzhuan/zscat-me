package com.mallplus.goods.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mallplus.common.annotation.SysLog;
import com.mallplus.common.entity.pms.SmsHomeNewProduct;
import com.mallplus.goods.service.ISmsHomeNewProductService;
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
 * 新鲜好物表
 * </p>
 *
 * @author zscat
 * @since 2019-04-19
 */
@Slf4j
@RestController
@Api(tags = "SmsHomeNewProductController", description = "新鲜好物表管理")
@RequestMapping("/marking/SmsHomeNewProduct")
public class SmsHomeNewProductController {
    @Resource
    private ISmsHomeNewProductService ISmsHomeNewProductService;

    @SysLog(MODULE = "sms", REMARK = "根据条件查询所有新鲜好物表列表")
    @ApiOperation("根据条件查询所有新鲜好物表列表")
    @GetMapping(value = "/list")
    @PreAuthorize("hasAuthority('marking:SmsHomeNewProduct:read')")
    public Object getSmsHomeNewProductByPage(SmsHomeNewProduct entity,
                                             @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                             @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize
    ) {
        try {
            return new CommonResult().success(ISmsHomeNewProductService.page(new Page<SmsHomeNewProduct>(pageNum, pageSize), new QueryWrapper<>(entity)));
        } catch (Exception e) {
            log.error("根据条件查询所有新鲜好物表列表：%s", e.getMessage(), e);
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "sms", REMARK = "保存新鲜好物表")
    @ApiOperation("保存新鲜好物表")
    @PostMapping(value = "/create")
    @PreAuthorize("hasAuthority('marking:SmsHomeNewProduct:create')")
    public Object saveSmsHomeNewProduct(@RequestBody List<SmsHomeNewProduct> homeBrandList) {
        try {
            if (ISmsHomeNewProductService.create(homeBrandList)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("保存新鲜好物表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "sms", REMARK = "更新新鲜好物表")
    @ApiOperation("更新新鲜好物表")
    @PostMapping(value = "/update/{id}")
    @PreAuthorize("hasAuthority('marking:SmsHomeNewProduct:update')")
    public Object updateSmsHomeNewProduct(@RequestBody SmsHomeNewProduct entity) {
        try {
            if (ISmsHomeNewProductService.updateById(entity)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("更新新鲜好物表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "sms", REMARK = "删除新鲜好物表")
    @ApiOperation("删除新鲜好物表")
    @GetMapping(value = "/delete/{id}")
    @PreAuthorize("hasAuthority('marking:SmsHomeNewProduct:delete')")
    public Object deleteSmsHomeNewProduct(@ApiParam("新鲜好物表id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("新鲜好物表id");
            }
            if (ISmsHomeNewProductService.removeById(id)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("删除新鲜好物表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "sms", REMARK = "给新鲜好物表分配新鲜好物表")
    @ApiOperation("查询新鲜好物表明细")
    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('marking:SmsHomeNewProduct:read')")
    public Object getSmsHomeNewProductById(@ApiParam("新鲜好物表id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("新鲜好物表id");
            }
            SmsHomeNewProduct coupon = ISmsHomeNewProductService.getById(id);
            return new CommonResult().success(coupon);
        } catch (Exception e) {
            log.error("查询新鲜好物表明细：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }

    }

    @ApiOperation(value = "批量删除新鲜好物表")
    @RequestMapping(value = "/delete/batch", method = RequestMethod.POST)
    @ResponseBody
    @SysLog(MODULE = "pms", REMARK = "批量删除新鲜好物表")
    @PreAuthorize("hasAuthority('marking:SmsHomeNewProduct:delete')")
    public Object deleteBatch(@RequestParam("ids") List<Long> ids) {
        boolean count = ISmsHomeNewProductService.removeByIds(ids);
        if (count) {
            return new CommonResult().success(count);
        } else {
            return new CommonResult().failed();
        }
    }
    @ApiOperation("添加首页推荐品牌")
    @RequestMapping(value = "/batchCreate", method = RequestMethod.POST)
    @ResponseBody
    public Object create(@RequestBody List<SmsHomeNewProduct> homeBrandList) {
        boolean count = ISmsHomeNewProductService.saveBatch(homeBrandList);
        if (count) {
            return new CommonResult().success(count);
        }
        return new CommonResult().failed();
    }
    @ApiOperation("批量修改推荐状态")
    @RequestMapping(value = "/update/recommendStatus", method = RequestMethod.POST)
    @ResponseBody
    public Object updateRecommendStatus(@RequestParam("ids") List<Long> ids, @RequestParam Integer recommendStatus) {
        int count = ISmsHomeNewProductService.updateRecommendStatus(ids, recommendStatus);
        if (count > 0) {
            return new CommonResult().success(count);
        }
        return new CommonResult().failed();
    }
    @ApiOperation("修改推荐排序")
    @RequestMapping(value = "/update/sort/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Object updateSort(@PathVariable Long id, Integer sort) {
        int count = ISmsHomeNewProductService.updateSort(id, sort);
        if (count > 0) {
            return new CommonResult().success(count);
        }
        return new CommonResult().failed();
    }
}
