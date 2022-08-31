package com.mallplus.goods.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mallplus.common.annotation.SysLog;
import com.mallplus.common.entity.pms.PmsProductVertifyRecord;
import com.mallplus.goods.service.IPmsProductVertifyRecordService;
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
 * 商品审核记录
 * </p>
 *
 * @author zscat
 * @since 2019-04-19
 */
@Slf4j
@RestController
@Api(tags = "PmsProductVertifyRecordController", description = "商品审核记录管理")
@RequestMapping("/pms/PmsProductVertifyRecord")
public class PmsProductVertifyRecordController {
    @Resource
    private IPmsProductVertifyRecordService IPmsProductVertifyRecordService;

    @SysLog(MODULE = "pms", REMARK = "根据条件查询所有商品审核记录列表")
    @ApiOperation("根据条件查询所有商品审核记录列表")
    @GetMapping(value = "/list")
    @PreAuthorize("hasAuthority('pms:PmsProductVertifyRecord:read')")
    public Object getPmsProductVertifyRecordByPage(PmsProductVertifyRecord entity,
                                                   @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                   @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize
    ) {
        try {
            return new CommonResult().success(IPmsProductVertifyRecordService.page(new Page<PmsProductVertifyRecord>(pageNum, pageSize), new QueryWrapper<>(entity).orderByDesc("create_time")));
        } catch (Exception e) {
            log.error("根据条件查询所有商品审核记录列表：%s", e.getMessage(), e);
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "pms", REMARK = "保存商品审核记录")
    @ApiOperation("保存商品审核记录")
    @PostMapping(value = "/create")
    @PreAuthorize("hasAuthority('pms:PmsProductVertifyRecord:create')")
    public Object savePmsProductVertifyRecord(@RequestBody PmsProductVertifyRecord entity) {
        try {
            if (IPmsProductVertifyRecordService.save(entity)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("保存商品审核记录：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "pms", REMARK = "更新商品审核记录")
    @ApiOperation("更新商品审核记录")
    @PostMapping(value = "/update/{id}")
    @PreAuthorize("hasAuthority('pms:PmsProductVertifyRecord:update')")
    public Object updatePmsProductVertifyRecord(@RequestBody PmsProductVertifyRecord entity) {
        try {
            if (IPmsProductVertifyRecordService.updateById(entity)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("更新商品审核记录：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "pms", REMARK = "删除商品审核记录")
    @ApiOperation("删除商品审核记录")
    @GetMapping(value = "/delete/{id}")
    @PreAuthorize("hasAuthority('pms:PmsProductVertifyRecord:delete')")
    public Object deletePmsProductVertifyRecord(@ApiParam("商品审核记录id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("商品审核记录id");
            }
            if (IPmsProductVertifyRecordService.removeById(id)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("删除商品审核记录：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "pms", REMARK = "给商品审核记录分配商品审核记录")
    @ApiOperation("查询商品审核记录明细")
    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('pms:PmsProductVertifyRecord:read')")
    public Object getPmsProductVertifyRecordById(@ApiParam("商品审核记录id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("商品审核记录id");
            }
            PmsProductVertifyRecord coupon = IPmsProductVertifyRecordService.getById(id);
            return new CommonResult().success(coupon);
        } catch (Exception e) {
            log.error("查询商品审核记录明细：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }

    }

    @ApiOperation(value = "批量删除商品审核记录")
    @RequestMapping(value = "/delete/batch", method = RequestMethod.POST)
    @ResponseBody
    @SysLog(MODULE = "pms", REMARK = "批量删除商品审核记录")
    @PreAuthorize("hasAuthority('pms:PmsProductVertifyRecord:delete')")
    public Object deleteBatch(@RequestParam("ids") List<Long> ids) {
        boolean count = IPmsProductVertifyRecordService.removeByIds(ids);
        if (count) {
            return new CommonResult().success(count);
        } else {
            return new CommonResult().failed();
        }
    }

}
