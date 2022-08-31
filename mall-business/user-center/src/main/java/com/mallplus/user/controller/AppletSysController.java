package com.mallplus.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mallplus.common.annotation.IgnoreAuth;
import com.mallplus.common.annotation.SysLog;
import com.mallplus.common.entity.pms.PmsFavorite;
import com.mallplus.common.entity.pms.PmsProduct;
import com.mallplus.common.entity.pms.PmsProductAttributeCategory;
import com.mallplus.common.entity.ums.UmsMember;
import com.mallplus.common.model.SysStore;
import com.mallplus.common.redis.constant.RedisToolsConstant;
import com.mallplus.common.redis.template.RedisUtil;
import com.mallplus.common.utils.CommonResult;
import com.mallplus.user.mapper.SysStoreMapper;
import com.mallplus.user.service.ISysStoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2019/8/7.
 */
@Slf4j
@RestController
@Api(tags = "AppletSysController", description = "")
@RequestMapping("/notAuth")
public class AppletSysController {

    @Resource
    private ISysStoreService ISysStoreService;

    @GetMapping(value = "/selectStoreById")
    SysStore selectStoreById(Long id){
        return ISysStoreService.getById(id);
    }

    @GetMapping(value = "/selectStoreList")
    List<SysStore> selectStoreList(QueryWrapper<SysStore> sysStoreQueryWrapper){
        return  ISysStoreService.list(sysStoreQueryWrapper);
    }
}
