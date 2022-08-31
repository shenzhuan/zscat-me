package com.mallplus.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mallplus.common.annotation.SysLog;
import com.mallplus.common.model.SysStore;
import com.mallplus.common.utils.CommonResult;
import com.mallplus.common.utils.ValidatorUtils;
import com.mallplus.common.vo.ApiContext;
import com.mallplus.user.service.ISysStoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * <p>
 * <p>
 * </p>
 *
 * @author zscat
 * @since 2019-05-18
 */
@Slf4j
@RestController
@Api(tags = "SysStoreController", description = "管理")
@RequestMapping("/sys/SysStore")
public class SysStoreController {
    @Resource
    private ISysStoreService ISysStoreService;
    @Autowired
    private ApiContext apiContext;

    @SysLog(MODULE = "sys", REMARK = "根据条件查询所有列表")
    @ApiOperation("根据条件查询所有列表")
    @GetMapping(value = "/setStoreId/{id}")
    public Object setStoreId(@ApiParam("id") @PathVariable Long id) {
        try {
            apiContext.setCurrentProviderId(id);
            return new CommonResult().success();
        } catch (Exception e) {
            log.error("根据条件查询所有列表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
    }


    @SysLog(MODULE = "sys", REMARK = "根据条件查询所有列表")
    @ApiOperation("根据条件查询所有列表")
    @GetMapping(value = "/list")
    public Object getSysStoreByPage(SysStore entity,
                                    @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                    @RequestParam(value = "pageSize", defaultValue = "30") Integer pageSize
    ) {
        try {
            return new CommonResult().success(ISysStoreService.page(new Page<SysStore>(pageNum, pageSize), new QueryWrapper<>(entity).orderByDesc("create_time")));
        } catch (Exception e) {
            log.error("根据条件查询所有列表：%s", e.getMessage(), e);
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "sys", REMARK = "保存")
    @ApiOperation("保存")
    @PostMapping(value = "/create")
    public Object saveSysStore(@RequestBody SysStore entity) {
        try {
            if (ISysStoreService.saveStore(entity)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("保存：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "sys", REMARK = "更新")
    @ApiOperation("更新")
    @PostMapping(value = "/update/{id}")
    public Object updateSysStore(@RequestBody SysStore entity) {
        try {
            if (ISysStoreService.updateById(entity)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("更新：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "sys", REMARK = "删除")
    @ApiOperation("删除")
    @GetMapping(value = "/delete/{id}")
    public Object deleteSysStore(@ApiParam("id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("id");
            }
            if (ISysStoreService.removeById(id)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("删除：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "sys", REMARK = "给分配")
    @ApiOperation("查询明细")
    @GetMapping(value = "/{id}")
    public Object getSysStoreById(@ApiParam("id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("id");
            }
            SysStore coupon = ISysStoreService.getById(id);
            return new CommonResult().success(coupon);
        } catch (Exception e) {
            log.error("查询明细：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }

    }

    @ApiOperation(value = "批量删除")
    @RequestMapping(value = "/delete/batch", method = RequestMethod.POST)
    @ResponseBody
    @SysLog(MODULE = "pms", REMARK = "批量删除")
    public Object deleteBatch(@RequestParam("ids") List<Long> ids) {
        boolean count = ISysStoreService.removeByIds(ids);
        if (count) {
            return new CommonResult().success(count);
        } else {
            return new CommonResult().failed();
        }
    }

}
