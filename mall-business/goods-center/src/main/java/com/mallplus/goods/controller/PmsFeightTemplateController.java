package com.mallplus.goods.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mallplus.common.annotation.SysLog;
import com.mallplus.common.entity.pms.PmsFeightTemplate;
import com.mallplus.goods.service.IPmsFeightTemplateService;
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
 * 运费模版
 * </p>
 *
 * @author zscat
 * @since 2019-04-19
 */
@Slf4j
@RestController
@Api(tags = "PmsFeightTemplateController", description = "运费模版管理")
@RequestMapping("/pms/PmsFeightTemplate")
public class PmsFeightTemplateController {
    @Resource
    private IPmsFeightTemplateService IPmsFeightTemplateService;

    @SysLog(MODULE = "pms", REMARK = "根据条件查询所有运费模版列表")
    @ApiOperation("根据条件查询所有运费模版列表")
    @GetMapping(value = "/list")
    @PreAuthorize("hasAuthority('pms:PmsFeightTemplate:read')")
    public Object getPmsFeightTemplateByPage(PmsFeightTemplate entity,
                                             @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                             @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize
    ) {
        try {
            return new CommonResult().success(IPmsFeightTemplateService.page(new Page<PmsFeightTemplate>(pageNum, pageSize), new QueryWrapper<>(entity).orderByDesc("create_time")));
        } catch (Exception e) {
            log.error("根据条件查询所有运费模版列表：%s", e.getMessage(), e);
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "pms", REMARK = "保存运费模版")
    @ApiOperation("保存运费模版")
    @PostMapping(value = "/create")
    @PreAuthorize("hasAuthority('pms:PmsFeightTemplate:create')")
    public Object savePmsFeightTemplate(@RequestBody PmsFeightTemplate entity) {
        try {
            if (IPmsFeightTemplateService.save(entity)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("保存运费模版：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "pms", REMARK = "更新运费模版")
    @ApiOperation("更新运费模版")
    @PostMapping(value = "/update/{id}")
    @PreAuthorize("hasAuthority('pms:PmsFeightTemplate:update')")
    public Object updatePmsFeightTemplate(@RequestBody PmsFeightTemplate entity) {
        try {
            if (IPmsFeightTemplateService.updateById(entity)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("更新运费模版：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "pms", REMARK = "删除运费模版")
    @ApiOperation("删除运费模版")
    @GetMapping(value = "/delete/{id}")
    @PreAuthorize("hasAuthority('pms:PmsFeightTemplate:delete')")
    public Object deletePmsFeightTemplate(@ApiParam("运费模版id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("运费模版id");
            }
            if (IPmsFeightTemplateService.removeById(id)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("删除运费模版：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "pms", REMARK = "给运费模版分配运费模版")
    @ApiOperation("查询运费模版明细")
    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('pms:PmsFeightTemplate:read')")
    public Object getPmsFeightTemplateById(@ApiParam("运费模版id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("运费模版id");
            }
            PmsFeightTemplate coupon = IPmsFeightTemplateService.getById(id);
            return new CommonResult().success(coupon);
        } catch (Exception e) {
            log.error("查询运费模版明细：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }

    }

    @ApiOperation(value = "批量删除运费模版")
    @RequestMapping(value = "/delete/batch", method = RequestMethod.POST)
    @ResponseBody
    @SysLog(MODULE = "pms", REMARK = "批量删除运费模版")
    @PreAuthorize("hasAuthority('pms:PmsFeightTemplate:delete')")
    public Object deleteBatch(@RequestParam("ids") List<Long> ids) {
        boolean count = IPmsFeightTemplateService.removeByIds(ids);
        if (count) {
            return new CommonResult().success(count);
        } else {
            return new CommonResult().failed();
        }
    }

}
