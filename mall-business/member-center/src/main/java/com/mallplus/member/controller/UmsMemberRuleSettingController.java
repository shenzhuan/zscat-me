package com.mallplus.member.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mallplus.common.annotation.SysLog;
import com.mallplus.common.utils.CommonResult;
import com.mallplus.common.utils.ValidatorUtils;
import com.mallplus.common.entity.ums.UmsMemberRuleSetting;
import com.mallplus.member.service.IUmsMemberRuleSettingService;
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
 * 会员积分成长规则表
 * </p>
 *
 * @author zscat
 * @since 2019-04-19
 */
@Slf4j
@RestController
@Api(tags = "UmsMemberRuleSettingController", description = "会员积分成长规则表管理")
@RequestMapping("/ums/UmsMemberRuleSetting")
public class UmsMemberRuleSettingController {
    @Resource
    private IUmsMemberRuleSettingService IUmsMemberRuleSettingService;

    @SysLog(MODULE = "ums", REMARK = "根据条件查询所有会员积分成长规则表列表")
    @ApiOperation("根据条件查询所有会员积分成长规则表列表")
    @GetMapping(value = "/list")
    @PreAuthorize("hasAuthority('ums:UmsMemberRuleSetting:read')")
    public Object getUmsMemberRuleSettingByPage(UmsMemberRuleSetting entity,
                                                @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize
    ) {
        try {
            return new CommonResult().success(IUmsMemberRuleSettingService.page(new Page<UmsMemberRuleSetting>(pageNum, pageSize), new QueryWrapper<>(entity)));
        } catch (Exception e) {
            log.error("根据条件查询所有会员积分成长规则表列表：%s", e.getMessage(), e);
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "ums", REMARK = "保存会员积分成长规则表")
    @ApiOperation("保存会员积分成长规则表")
    @PostMapping(value = "/create")
    @PreAuthorize("hasAuthority('ums:UmsMemberRuleSetting:create')")
    public Object saveUmsMemberRuleSetting(@RequestBody UmsMemberRuleSetting entity) {
        try {
            if (IUmsMemberRuleSettingService.save(entity)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("保存会员积分成长规则表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "ums", REMARK = "更新会员积分成长规则表")
    @ApiOperation("更新会员积分成长规则表")
    @PostMapping(value = "/update/{id}")
    @PreAuthorize("hasAuthority('ums:UmsMemberRuleSetting:update')")
    public Object updateUmsMemberRuleSetting(@RequestBody UmsMemberRuleSetting entity) {
        try {
            if (IUmsMemberRuleSettingService.updateById(entity)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("更新会员积分成长规则表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "ums", REMARK = "删除会员积分成长规则表")
    @ApiOperation("删除会员积分成长规则表")
    @GetMapping(value = "/delete/{id}")
    @PreAuthorize("hasAuthority('ums:UmsMemberRuleSetting:delete')")
    public Object deleteUmsMemberRuleSetting(@ApiParam("会员积分成长规则表id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("会员积分成长规则表id");
            }
            if (IUmsMemberRuleSettingService.removeById(id)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("删除会员积分成长规则表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "ums", REMARK = "给会员积分成长规则表分配会员积分成长规则表")
    @ApiOperation("查询会员积分成长规则表明细")
    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('ums:UmsMemberRuleSetting:read')")
    public Object getUmsMemberRuleSettingById(@ApiParam("会员积分成长规则表id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("会员积分成长规则表id");
            }
            UmsMemberRuleSetting coupon = IUmsMemberRuleSettingService.getById(id);
            return new CommonResult().success(coupon);
        } catch (Exception e) {
            log.error("查询会员积分成长规则表明细：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }

    }

    @ApiOperation(value = "批量删除会员积分成长规则表")
    @RequestMapping(value = "/delete/batch", method = RequestMethod.POST)
    @ResponseBody
    @SysLog(MODULE = "pms", REMARK = "批量删除会员积分成长规则表")
    @PreAuthorize("hasAuthority('ums:UmsMemberRuleSetting:delete')")
    public Object deleteBatch(@RequestParam("ids") List<Long> ids) {
        boolean count = IUmsMemberRuleSettingService.removeByIds(ids);
        if (count) {
            return new CommonResult().success(count);
        } else {
            return new CommonResult().failed();
        }
    }

}
