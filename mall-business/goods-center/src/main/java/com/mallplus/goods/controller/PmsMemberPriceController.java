package com.mallplus.goods.controller;

import com.mallplus.common.utils.CommonResult;



import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import com.mallplus.common.entity.pms.PmsMemberPrice;
import com.mallplus.goods.service.IPmsMemberPriceService;
import com.mallplus.common.utils.ValidatorUtils;
import com.mallplus.common.annotation.SysLog;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 商品会员价格表
 * </p>
 *
 * @author zscat
 * @since 2019-04-19
 */
@Slf4j
@RestController
@Api(tags = "PmsMemberPriceController", description = "商品会员价格表管理")
@RequestMapping("/pms/PmsMemberPrice")
public class PmsMemberPriceController {
    @Resource
    private IPmsMemberPriceService IPmsMemberPriceService;

    @SysLog(MODULE = "pms", REMARK = "根据条件查询所有商品会员价格表列表")
    @ApiOperation("根据条件查询所有商品会员价格表列表")
    @GetMapping(value = "/list")
    @PreAuthorize("hasAuthority('pms:PmsMemberPrice:read')")
    public Object getPmsMemberPriceByPage(PmsMemberPrice entity,
                                          @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                          @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize
    ) {
        try {
            return new CommonResult().success(IPmsMemberPriceService.page(new Page<PmsMemberPrice>(pageNum, pageSize), new QueryWrapper<>(entity)));
        } catch (Exception e) {
            log.error("根据条件查询所有商品会员价格表列表：%s", e.getMessage(), e);
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "pms", REMARK = "保存商品会员价格表")
    @ApiOperation("保存商品会员价格表")
    @PostMapping(value = "/create")
    @PreAuthorize("hasAuthority('pms:PmsMemberPrice:create')")
    public Object savePmsMemberPrice(@RequestBody PmsMemberPrice entity) {
        try {
            if (IPmsMemberPriceService.save(entity)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("保存商品会员价格表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "pms", REMARK = "更新商品会员价格表")
    @ApiOperation("更新商品会员价格表")
    @PostMapping(value = "/update/{id}")
    @PreAuthorize("hasAuthority('pms:PmsMemberPrice:update')")
    public Object updatePmsMemberPrice(@RequestBody PmsMemberPrice entity) {
        try {
            if (IPmsMemberPriceService.updateById(entity)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("更新商品会员价格表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "pms", REMARK = "删除商品会员价格表")
    @ApiOperation("删除商品会员价格表")
    @GetMapping(value = "/delete/{id}")
    @PreAuthorize("hasAuthority('pms:PmsMemberPrice:delete')")
    public Object deletePmsMemberPrice(@ApiParam("商品会员价格表id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("商品会员价格表id");
            }
            if (IPmsMemberPriceService.removeById(id)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("删除商品会员价格表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "pms", REMARK = "给商品会员价格表分配商品会员价格表")
    @ApiOperation("查询商品会员价格表明细")
    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('pms:PmsMemberPrice:read')")
    public Object getPmsMemberPriceById(@ApiParam("商品会员价格表id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("商品会员价格表id");
            }
            PmsMemberPrice coupon = IPmsMemberPriceService.getById(id);
            return new CommonResult().success(coupon);
        } catch (Exception e) {
            log.error("查询商品会员价格表明细：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }

    }

    @ApiOperation(value = "批量删除商品会员价格表")
    @RequestMapping(value = "/delete/batch", method = RequestMethod.POST)
    @ResponseBody
    @SysLog(MODULE = "pms", REMARK = "批量删除商品会员价格表")
    @PreAuthorize("hasAuthority('pms:PmsMemberPrice:delete')")
    public Object deleteBatch(@RequestParam("ids") List<Long> ids) {
        boolean count = IPmsMemberPriceService.removeByIds(ids);
        if (count) {
            return new CommonResult().success(count);
        } else {
            return new CommonResult().failed();
        }
    }

}
