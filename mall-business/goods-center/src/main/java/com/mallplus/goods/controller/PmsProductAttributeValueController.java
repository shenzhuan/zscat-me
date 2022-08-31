package com.mallplus.goods.controller;

import com.mallplus.common.utils.CommonResult;



import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import com.mallplus.common.entity.pms.PmsProductAttributeValue;
import com.mallplus.goods.service.IPmsProductAttributeValueService;
import com.mallplus.common.utils.ValidatorUtils;
import com.mallplus.common.annotation.SysLog;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 存储产品参数信息的表
 * </p>
 *
 * @author zscat
 * @since 2019-04-19
 */
@Slf4j
@RestController
@Api(tags = "PmsProductAttributeValueController", description = "存储产品参数信息的表管理")
@RequestMapping("/pms/PmsProductAttributeValue")
public class PmsProductAttributeValueController {
    @Resource
    private IPmsProductAttributeValueService IPmsProductAttributeValueService;

    @SysLog(MODULE = "pms", REMARK = "根据条件查询所有存储产品参数信息的表列表")
    @ApiOperation("根据条件查询所有存储产品参数信息的表列表")
    @GetMapping(value = "/list")
    @PreAuthorize("hasAuthority('pms:PmsProductAttributeValue:read')")
    public Object getPmsProductAttributeValueByPage(PmsProductAttributeValue entity,
                                                    @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                    @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize
    ) {
        try {
            return new CommonResult().success(IPmsProductAttributeValueService.page(new Page<PmsProductAttributeValue>(pageNum, pageSize), new QueryWrapper<>(entity)));
        } catch (Exception e) {
            log.error("根据条件查询所有存储产品参数信息的表列表：%s", e.getMessage(), e);
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "pms", REMARK = "保存存储产品参数信息的表")
    @ApiOperation("保存存储产品参数信息的表")
    @PostMapping(value = "/create")
    @PreAuthorize("hasAuthority('pms:PmsProductAttributeValue:create')")
    public Object savePmsProductAttributeValue(@RequestBody PmsProductAttributeValue entity) {
        try {
            if (IPmsProductAttributeValueService.save(entity)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("保存存储产品参数信息的表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "pms", REMARK = "更新存储产品参数信息的表")
    @ApiOperation("更新存储产品参数信息的表")
    @PostMapping(value = "/update/{id}")
    @PreAuthorize("hasAuthority('pms:PmsProductAttributeValue:update')")
    public Object updatePmsProductAttributeValue(@RequestBody PmsProductAttributeValue entity) {
        try {
            if (IPmsProductAttributeValueService.updateById(entity)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("更新存储产品参数信息的表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "pms", REMARK = "删除存储产品参数信息的表")
    @ApiOperation("删除存储产品参数信息的表")
    @GetMapping(value = "/delete/{id}")
    @PreAuthorize("hasAuthority('pms:PmsProductAttributeValue:delete')")
    public Object deletePmsProductAttributeValue(@ApiParam("存储产品参数信息的表id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("存储产品参数信息的表id");
            }
            if (IPmsProductAttributeValueService.removeById(id)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("删除存储产品参数信息的表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "pms", REMARK = "给存储产品参数信息的表分配存储产品参数信息的表")
    @ApiOperation("查询存储产品参数信息的表明细")
    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('pms:PmsProductAttributeValue:read')")
    public Object getPmsProductAttributeValueById(@ApiParam("存储产品参数信息的表id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("存储产品参数信息的表id");
            }
            PmsProductAttributeValue coupon = IPmsProductAttributeValueService.getById(id);
            return new CommonResult().success(coupon);
        } catch (Exception e) {
            log.error("查询存储产品参数信息的表明细：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }

    }

    @ApiOperation(value = "批量删除存储产品参数信息的表")
    @RequestMapping(value = "/delete/batch", method = RequestMethod.POST)
    @ResponseBody
    @SysLog(MODULE = "pms", REMARK = "批量删除存储产品参数信息的表")
    @PreAuthorize("hasAuthority('pms:PmsProductAttributeValue:delete')")
    public Object deleteBatch(@RequestParam("ids") List<Long> ids) {
        boolean count = IPmsProductAttributeValueService.removeByIds(ids);
        if (count) {
            return new CommonResult().success(count);
        } else {
            return new CommonResult().failed();
        }
    }

}
