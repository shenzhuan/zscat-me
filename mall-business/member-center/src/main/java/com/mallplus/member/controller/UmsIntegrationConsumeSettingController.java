package com.mallplus.member.controller;

import com.mallplus.common.utils.CommonResult;



import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import com.mallplus.common.entity.ums.UmsIntegrationConsumeSetting;
import com.mallplus.member.service.IUmsIntegrationConsumeSettingService;
import com.mallplus.common.utils.ValidatorUtils;
import com.mallplus.common.annotation.SysLog;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 积分消费设置
 * </p>
 *
 * @author zscat
 * @since 2019-04-19
 */
@Slf4j
@RestController
@Api(tags = "UmsIntegrationConsumeSettingController", description = "积分消费设置管理")
@RequestMapping("/ums/UmsIntegrationConsumeSetting")
public class UmsIntegrationConsumeSettingController {
    @Resource
    private IUmsIntegrationConsumeSettingService IUmsIntegrationConsumeSettingService;

    @SysLog(MODULE = "ums", REMARK = "根据条件查询所有积分消费设置列表")
    @ApiOperation("根据条件查询所有积分消费设置列表")
    @GetMapping(value = "/list")
    @PreAuthorize("hasAuthority('ums:UmsIntegrationConsumeSetting:read')")
    public Object getUmsIntegrationConsumeSettingByPage(UmsIntegrationConsumeSetting entity,
                                                        @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                        @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize
    ) {
        try {
            return new CommonResult().success(IUmsIntegrationConsumeSettingService.page(new Page<UmsIntegrationConsumeSetting>(pageNum, pageSize), new QueryWrapper<>(entity)));
        } catch (Exception e) {
            log.error("根据条件查询所有积分消费设置列表：%s", e.getMessage(), e);
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "ums", REMARK = "保存积分消费设置")
    @ApiOperation("保存积分消费设置")
    @PostMapping(value = "/create")
    @PreAuthorize("hasAuthority('ums:UmsIntegrationConsumeSetting:create')")
    public Object saveUmsIntegrationConsumeSetting(@RequestBody UmsIntegrationConsumeSetting entity) {
        try {
            if (IUmsIntegrationConsumeSettingService.save(entity)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("保存积分消费设置：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "ums", REMARK = "更新积分消费设置")
    @ApiOperation("更新积分消费设置")
    @PostMapping(value = "/update/{id}")
    @PreAuthorize("hasAuthority('ums:UmsIntegrationConsumeSetting:update')")
    public Object updateUmsIntegrationConsumeSetting(@RequestBody UmsIntegrationConsumeSetting entity) {
        try {
            if (IUmsIntegrationConsumeSettingService.updateById(entity)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("更新积分消费设置：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "ums", REMARK = "删除积分消费设置")
    @ApiOperation("删除积分消费设置")
    @GetMapping(value = "/delete/{id}")
    @PreAuthorize("hasAuthority('ums:UmsIntegrationConsumeSetting:delete')")
    public Object deleteUmsIntegrationConsumeSetting(@ApiParam("积分消费设置id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("积分消费设置id");
            }
            if (IUmsIntegrationConsumeSettingService.removeById(id)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("删除积分消费设置：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "ums", REMARK = "给积分消费设置分配积分消费设置")
    @ApiOperation("查询积分消费设置明细")
    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('ums:UmsIntegrationConsumeSetting:read')")
    public Object getUmsIntegrationConsumeSettingById(@ApiParam("积分消费设置id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("积分消费设置id");
            }
            UmsIntegrationConsumeSetting coupon = IUmsIntegrationConsumeSettingService.getById(id);
            return new CommonResult().success(coupon);
        } catch (Exception e) {
            log.error("查询积分消费设置明细：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }

    }

    @ApiOperation(value = "批量删除积分消费设置")
    @RequestMapping(value = "/delete/batch", method = RequestMethod.POST)
    @ResponseBody
    @SysLog(MODULE = "pms", REMARK = "批量删除积分消费设置")
    @PreAuthorize("hasAuthority('ums:UmsIntegrationConsumeSetting:delete')")
    public Object deleteBatch(@RequestParam("ids") List<Long> ids) {
        boolean count = IUmsIntegrationConsumeSettingService.removeByIds(ids);
        if (count) {
            return new CommonResult().success(count);
        } else {
            return new CommonResult().failed();
        }
    }

}
