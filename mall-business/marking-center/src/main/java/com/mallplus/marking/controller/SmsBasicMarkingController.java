package com.mallplus.marking.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.mallplus.common.annotation.SysLog;
import com.mallplus.common.entity.sms.SmsBasicMarking;
import com.mallplus.common.utils.CommonResult;
import com.mallplus.common.utils.JsonUtil;
import com.mallplus.common.utils.ValidatorUtils;
import com.mallplus.common.vo.BasicRuls;
import com.mallplus.common.vo.BeanKv;
import com.mallplus.marking.service.ISmsBasicMarkingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author zscat
 * @since 2019-07-07
 */
@Slf4j
@RestController
@Api(tags = "SmsBasicMarkingController", description = "管理")
@RequestMapping("/sms/SmsBasicMarking")
public class SmsBasicMarkingController {
    @Resource
    private com.mallplus.marking.service.ISmsBasicMarkingService ISmsBasicMarkingService;

    @SysLog(MODULE = "sms", REMARK = "根据条件查询所有列表")
    @ApiOperation("根据条件查询所有列表")
    @GetMapping(value = "/list")
    @PreAuthorize("hasAuthority('sms:SmsBasicMarking:read')")
    public Object getSmsBasicMarkingByPage(SmsBasicMarking entity,
                                           @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                           @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize
    ) {
        try {
            return new CommonResult().success(ISmsBasicMarkingService.page(new Page<SmsBasicMarking>(pageNum, pageSize), new QueryWrapper<>(entity).orderByAsc("big_type")));
        } catch (Exception e) {
            log.error("根据条件查询所有列表：%s", e.getMessage(), e);
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "sms", REMARK = "保存")
    @ApiOperation("保存")
    @PostMapping(value = "/create")
    @PreAuthorize("hasAuthority('sms:SmsBasicMarking:create')")
    public Object saveSmsBasicMarking(@RequestBody SmsBasicMarking entity) {
        try {
            entity.setCreateTime(new Date());
            entity.setStatus(2);
            validateParam(entity);

            if (ISmsBasicMarkingService.save(entity)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("保存：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    private void validateParam(@RequestBody SmsBasicMarking entity) {
        // 活动商品  1 按类别  2 部分商品
        if (ValidatorUtils.empty(entity.getActiviGoods())){
            entity.setActiviGoods(0);
        }
        if (ValidatorUtils.empty(entity.getActiviUser())){
            entity.setActiviUser(1);
        }
        if (ValidatorUtils.empty(entity.getSmallType())){
            entity.setSmallType(1);
        }
        if (entity.getActiviGoods()==1){
            if (ValidatorUtils.notEmpty(entity.getProductCategoryRelationList())){
                entity.setGoodsDs(JsonUtil.objectToJson(entity.getProductCategoryRelationList()));
            }
        }
        if (entity.getActiviGoods()==2){
            if (ValidatorUtils.notEmpty(entity.getProductRelationList())){
                entity.setGoodsDs(JsonUtil.objectToJson(entity.getProductRelationList()));
            }
        }
        if (entity.getActiviUser()==2){
            if (ValidatorUtils.notEmpty(entity.getMemberLevelList())){
                entity.setUserLevel(JsonUtil.objectToJson(entity.getMemberLevelList()));
            }
        }
        if (entity.getSmallType()==1) {
            if (ValidatorUtils.notEmpty(entity.getActrule())) {
                entity.setRules(JsonUtil.objectToJson(entity.getActrule()));
            }
        }
        if (entity.getSmallType()==2) {
            if (ValidatorUtils.notEmpty(entity.getActrule1())) {
                entity.setRules(JsonUtil.objectToJson(entity.getActrule1()));
            }
        }
    }

    @SysLog(MODULE = "sms", REMARK = "更新")
    @ApiOperation("更新")
    @PostMapping(value = "/update/{id}")
    @PreAuthorize("hasAuthority('sms:SmsBasicMarking:update')")
    public Object updateSmsBasicMarking(@RequestBody SmsBasicMarking entity) {
        try {
            validateParam(entity);
            if (ISmsBasicMarkingService.updateById(entity)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("更新：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "sms", REMARK = "删除")
    @ApiOperation("删除")
    @GetMapping(value = "/delete/{id}")
    @PreAuthorize("hasAuthority('sms:SmsBasicMarking:delete')")
    public Object deleteSmsBasicMarking(@ApiParam("id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("id");
            }
            if (ISmsBasicMarkingService.removeById(id)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("删除：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "sms", REMARK = "给分配")
    @ApiOperation("查询明细")
    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('sms:SmsBasicMarking:read')")
    public Object getSmsBasicMarkingById(@ApiParam("id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("id");
            }
            SmsBasicMarking entity = ISmsBasicMarkingService.getById(id);
            // 活动商品  1 按类别  2 部分商品
            if (entity.getActiviGoods()==1){
                if (ValidatorUtils.notEmpty(entity.getGoodsDs())){
                    entity.setProductCategoryRelationList(JsonUtil.jsonToList(entity.getGoodsDs(), BeanKv.class));
                }
            }
            if (entity.getActiviGoods()==2){
                if (ValidatorUtils.notEmpty(entity.getGoodsDs())){
                    entity.setProductRelationList(JsonUtil.jsonToList(entity.getGoodsDs(), BeanKv.class));

                }
            }
            if (entity.getActiviUser()==2){
                if (ValidatorUtils.notEmpty(entity.getUserLevel())){
                    entity.setMemberLevelList(JsonUtil.jsonToList(entity.getUserLevel(), BeanKv.class));
                }
            }
            if (entity.getSmallType()==1) {
                if (ValidatorUtils.notEmpty(entity.getRules())) {
                    entity.setActrule(JsonUtil.jsonToList(entity.getRules(), BasicRuls.class));
                }
            }
            if (entity.getSmallType()==2) {
                if (ValidatorUtils.notEmpty(entity.getRules())) {
                    entity.setActrule1(JsonUtil.jsonToList(entity.getRules(),BasicRuls.class));
                }
            }
            return new CommonResult().success(entity);
        } catch (Exception e) {
            log.error("查询明细：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }

    }

    @ApiOperation(value = "批量删除")
    @RequestMapping(value = "/delete/batch", method = RequestMethod.GET)
    @ResponseBody
    @SysLog(MODULE = "pms", REMARK = "批量删除")
    @PreAuthorize("hasAuthority('sms:SmsBasicMarking:delete')")
    public Object deleteBatch(@RequestParam("ids") List<Long> ids) {
        boolean count = ISmsBasicMarkingService.removeByIds(ids);
        if (count) {
            return new CommonResult().success(count);
        } else {
            return new CommonResult().failed();
        }
    }
    @ApiOperation("批量上下架")
    @RequestMapping(value = "/publishStatus", method = RequestMethod.POST)
    @ResponseBody
    @SysLog(MODULE = "pms", REMARK = "批量上下架")
    public Object updatePublishStatus(@RequestParam("id") Long  id,
                                      @RequestParam("status") Integer status,
                                      @RequestParam("bigType") Integer bigType) {
        int count = ISmsBasicMarkingService.updateStatus(id, status,bigType);
        return new CommonResult().success(count);
    }
}
