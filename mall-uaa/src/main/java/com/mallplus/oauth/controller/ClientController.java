package com.mallplus.oauth.controller;

import com.mallplus.common.model.PageResult;
import com.mallplus.common.model.Result;
import com.mallplus.oauth.dto.ClientDto;
import com.mallplus.oauth.model.Client;
import com.mallplus.oauth.service.IClientService;
import com.google.common.collect.Maps;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 角色相关接口
 *
 * @author mall
 */
@Api(tags = "应用")
@RestController
@RequestMapping("/clients")
public class ClientController {
    @Autowired
    private IClientService clientService;

    @GetMapping
    @ApiOperation(value = "应用列表")
    public PageResult<Client> list(@RequestParam Map<String, Object> params) {
        return clientService.listClent(params, true);
    }

    @GetMapping("/detail/{id}")
    @ApiOperation(value = "根据id获取应用")
    public Client get(@PathVariable Long id) {
        return clientService.getById(id);
    }

    @GetMapping("/all")
    @ApiOperation(value = "所有应用")
    public List<Client> allClient() {
        PageResult<Client> page = clientService.listClent(Maps.newHashMap(), false);
        return page.getData();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "删除应用")
    public void delete(@PathVariable Long id) {
        clientService.delClient(id);
    }

    @PostMapping("/saveOrUpdate")
    @ApiOperation(value = "保存或者修改应用")
    public Result saveOrUpdate(@RequestBody ClientDto clientDto) {
        return clientService.saveClient(clientDto);
    }
}
