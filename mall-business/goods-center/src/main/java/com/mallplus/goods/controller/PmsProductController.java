package com.mallplus.goods.controller;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mallplus.common.annotation.SysLog;
import com.mallplus.common.constant.ConstansValue;
import com.mallplus.common.entity.pms.PmsProduct;
import com.mallplus.common.entity.pms.PmsProductVertifyRecord;
import com.mallplus.common.utils.CommonResult;
import com.mallplus.common.utils.DateUtils;
import com.mallplus.common.utils.ValidatorUtils;
import com.mallplus.goods.service.IPmsProductService;
import com.mallplus.goods.vo.PmsProductParam;
import com.mallplus.goods.vo.PmsProductResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品信息
 * </p>
 *
 * @author zscat
 * @since 2019-04-19
 */
@Slf4j
@RestController
@Api(tags = "PmsProductController", description = "商品信息管理")
@RequestMapping("/pms/PmsProduct")
public class PmsProductController {
    @Resource
    private IPmsProductService IPmsProductService;

    @SysLog(MODULE = "pms", REMARK = "根据条件查询所有商品信息列表")
    @ApiOperation("根据条件查询所有商品信息列表")
    @GetMapping(value = "/list")
    @PreAuthorize("hasAuthority('pms:PmsProduct:read')")
    public Object getPmsProductByPage(PmsProduct entity,
                                      @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                      @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize
    ) {
        try {
            return new CommonResult().success(IPmsProductService.page(new Page<PmsProduct>(pageNum, pageSize), new QueryWrapper<>(entity).orderByDesc("create_time").select(ConstansValue.sampleGoodsList)));
        } catch (Exception e) {
            log.error("根据条件查询所有商品信息列表：%s", e.getMessage(), e);
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "pms", REMARK = "保存商品信息")
    @ApiOperation("保存商品信息")
    @PostMapping(value = "/create")
    @PreAuthorize("hasAuthority('pms:PmsProduct:create')")
    public Object savePmsProduct(@RequestBody PmsProductParam productParam) {
        try {
            int count = IPmsProductService.create(productParam);
            if (count > 0) {
                return new CommonResult().success(count);
            } else {
                return new CommonResult().failed();
            }
        } catch (Exception e) {
            log.error("保存商品信息：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }

    }

    @SysLog(MODULE = "pms", REMARK = "更新商品信息")
    @ApiOperation("更新商品信息")
    @PostMapping(value = "/update/{id}")
    @PreAuthorize("hasAuthority('pms:PmsProduct:update')")
    public Object updatePmsProduct(@PathVariable Long id, @RequestBody PmsProductParam productParam) {
        try {
            int count = IPmsProductService.update(id, productParam);
            if (count > 0) {
                return new CommonResult().success(count);
            } else {
                return new CommonResult().failed();
            }
        } catch (Exception e) {
            log.error("更新商品信息：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }

    }

    @SysLog(MODULE = "pms", REMARK = "删除商品信息")
    @ApiOperation("删除商品信息")
    @GetMapping(value = "/delete/{id}")
    @PreAuthorize("hasAuthority('pms:PmsProduct:delete')")
    public Object deletePmsProduct(@ApiParam("商品信息id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("商品信息id");
            }
            if (IPmsProductService.removeById(id)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("删除商品信息：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "pms", REMARK = "给商品信息分配商品信息")
    @ApiOperation("查询商品信息明细")
    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('pms:PmsProduct:read')")
    public Object getPmsProductById(@ApiParam("商品信息id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("商品信息id");
            }
            PmsProduct coupon = IPmsProductService.getById(id);
            return new CommonResult().success(coupon);
        } catch (Exception e) {
            log.error("查询商品信息明细：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }

    }
    @GetMapping(value = "/id", params = "id")
    @ApiOperation(value = "根据Id查询")
    public PmsProduct findById(Long id) {
        return IPmsProductService.getById(id);
    }

    @GetMapping("/goods/{id}")
    public PmsProduct findUserById(@PathVariable Long id) {
        return IPmsProductService.getById(id);
    }

    @ApiOperation(value = "批量删除商品信息")
    @RequestMapping(value = "/delete/batch", method = RequestMethod.POST)
    @ResponseBody
    @SysLog(MODULE = "pms", REMARK = "批量删除商品信息")
    @PreAuthorize("hasAuthority('pms:PmsProduct:delete')")
    public Object deleteBatch(@RequestParam("ids") List<Long> ids) {
        boolean count = IPmsProductService.removeByIds(ids);
        if (count) {
            return new CommonResult().success(count);
        } else {
            return new CommonResult().failed();
        }
    }

    @ApiOperation("根据商品id获取商品编辑信息")
    @RequestMapping(value = "/updateInfo/{id}", method = RequestMethod.GET)
    @ResponseBody
    @SysLog(MODULE = "pms", REMARK = "根据商品id获取商品编辑信息")
    @PreAuthorize("hasAuthority('pms:product:read')")
    public Object getUpdateInfo(@PathVariable Long id) {
        PmsProductResult productResult = IPmsProductService.getUpdateInfo(id);
        return new CommonResult().success(productResult);
    }

    @ApiOperation("根据商品id获取审核信息")
    @RequestMapping(value = "/fetchVList/{id}", method = RequestMethod.GET)
    @ResponseBody
    @SysLog(MODULE = "pms", REMARK = "据商品id获取审核信息")
    public Object fetchVList(@PathVariable Long id) {
        List<PmsProductVertifyRecord> list = IPmsProductService.getProductVertifyRecord(id);
        return new CommonResult().success(list);
    }

    @ApiOperation("批量修改审核状态")
    @RequestMapping(value = "/update/verifyStatus")
    @ResponseBody
    @SysLog(MODULE = "pms", REMARK = "批量修改审核状态")
    @PreAuthorize("hasAuthority('pms:product:update')")
    public Object updateVerifyStatus(@RequestParam("ids") Long ids,
                                     @RequestParam("verifyStatus") Integer verifyStatus,
                                     @RequestParam("detail") String detail) {
        int count = IPmsProductService.updateVerifyStatus(ids, verifyStatus, detail);
        if (count > 0) {
            return new CommonResult().success(count);
        } else {
            return new CommonResult().failed();
        }
    }

    @ApiOperation("批量上下架")
    @RequestMapping(value = "/update/publishStatus", method = RequestMethod.POST)
    @ResponseBody
    @SysLog(MODULE = "pms", REMARK = "批量上下架")
    @PreAuthorize("hasAuthority('pms:product:update')")
    public Object updatePublishStatus(@RequestParam("ids") List<Long> ids,
                                      @RequestParam("publishStatus") Integer publishStatus) {
        int count = IPmsProductService.updatePublishStatus(ids, publishStatus);
        if (count > 0) {
            return new CommonResult().success(count);
        } else {
            return new CommonResult().failed();
        }
    }

    @ApiOperation("批量推荐商品")
    @RequestMapping(value = "/update/recommendStatus", method = RequestMethod.POST)
    @ResponseBody
    @SysLog(MODULE = "pms", REMARK = "批量推荐商品")
    @PreAuthorize("hasAuthority('pms:product:update')")
    public Object updateRecommendStatus(@RequestParam("ids") List<Long> ids,
                                        @RequestParam("recommendStatus") Integer recommendStatus) {
        int count = IPmsProductService.updateRecommendStatus(ids, recommendStatus);
        if (count > 0) {
            return new CommonResult().success(count);
        } else {
            return new CommonResult().failed();
        }
    }

    @ApiOperation("批量设为新品")
    @RequestMapping(value = "/update/newStatus", method = RequestMethod.POST)
    @ResponseBody
    @SysLog(MODULE = "pms", REMARK = "批量设为新品")
    @PreAuthorize("hasAuthority('pms:product:update')")
    public Object updateNewStatus(@RequestParam("ids") List<Long> ids,
                                  @RequestParam("newStatus") Integer newStatus) {
        int count = IPmsProductService.updateNewStatus(ids, newStatus);
        if (count > 0) {
            return new CommonResult().success(count);
        } else {
            return new CommonResult().failed();
        }
    }

    @ApiOperation("批量修改删除状态")
    @RequestMapping(value = "/update/deleteStatus", method = RequestMethod.POST)
    @ResponseBody
    @SysLog(MODULE = "pms", REMARK = "批量修改删除状态")
    @PreAuthorize("hasAuthority('pms:product:delete')")
    public Object updateDeleteStatus(@RequestParam("ids") List<Long> ids,
                                     @RequestParam("deleteStatus") Integer deleteStatus) {
        int count = IPmsProductService.updateDeleteStatus(ids, deleteStatus);
        if (count > 0) {
            return new CommonResult().success(count);
        } else {
            return new CommonResult().failed();
        }
    }

    @ApiOperation("首页商品统计")
    @SysLog(MODULE = "home", REMARK = "首页商品统计")
    @RequestMapping(value = "/goodsStatic", method = RequestMethod.GET)
    public Object goodsStatic() throws Exception {
        StopWatch stopWatch = new StopWatch("首页商品统计");
        stopWatch.start("首页商品列表2");
        List<PmsProduct> goodsList = IPmsProductService.list(new QueryWrapper<>(new PmsProduct()).select("publish_status", "create_time"));

        stopWatch.stop();
        stopWatch.start("首页商品");
        int onCount = 0;
        int offCount = 0;
        int nowCount = 0;
        for (PmsProduct goods : goodsList) {
            if (goods.getPublishStatus() == 1) { // 上架状态：0->下架；1->上架
                onCount++;
            }
            if (goods.getPublishStatus() == 0) { // 上架状态：0->下架；1->上架
                offCount++;
            }
            if (DateUtils.format(goods.getCreateTime()).equals(DateUtils.format(new Date()))) {
                nowCount++;
            }
        }
        Map<String, Object> map = new HashMap();
        map.put("onCount", onCount);
        map.put("offCount", offCount);
        map.put("nowCount", nowCount);
        map.put("allCount", goodsList.size());
        stopWatch.stop();
        log.info(stopWatch.prettyPrint());
        return new CommonResult().success(map);
    }
}
