package com.mallplus.user.controller;


import com.alibaba.fastjson.JSON;
import com.mallplus.common.utils.CommonResult;
import com.mallplus.user.service.GeneratorService;
import com.mallplus.user.vo.GenUtils;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/gen")
@Controller
public class GeneratorController {
    String prefix = "common/generator";
    @Autowired
    GeneratorService generatorService;

    @GetMapping()
    String generator() {
        return prefix + "/list";
    }

    @ResponseBody
    @GetMapping("/list")
    Object list() {
        List<Map<String, Object>> list = generatorService.list();
        return new CommonResult().success(list);
    }

    ;


    @RequestMapping(value = "/code/{tableName}")
    @ResponseBody
    public Object code(@PathVariable String tableName, HttpServletResponse response) throws IOException {
        String[] tableNames = new String[]{tableName};
        byte[] data = generatorService.generatorCode(tableNames);
        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=\"mall.zip\"");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Headers", "X-Requested-With,content-type,token");
        response.setHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, DELETE, TRACE, OPTIONS, PATCH");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        IOUtils.write(data, response.getOutputStream());
        return CommonResult.SUCCESS;

    }

    @RequestMapping(value = "/code1/{tableName}")
    public void code1(@PathVariable String tableName, HttpServletResponse response) throws IOException {
        String[] tableNames = new String[]{tableName};
        byte[] data = generatorService.generatorCode(tableNames);
        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=\"mall.zip\"");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Headers", "X-Requested-With,content-type,token");
        response.setHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, DELETE, TRACE, OPTIONS, PATCH");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        IOUtils.write(data, response.getOutputStream());


    }

    @RequestMapping("/batchCode")
    public void batchCode(HttpServletRequest request, HttpServletResponse response, String tables) throws IOException {
        String[] tableNames = new String[]{};
        tableNames = JSON.parseArray(tables).toArray(tableNames);
        byte[] data = generatorService.generatorCode(tableNames);
        response.reset();

        response.setHeader("Content-Disposition", "attachment; filename=\"mall.zip\"");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Headers", "X-Requested-With,content-type,token");
        response.setHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, DELETE, TRACE, OPTIONS, PATCH");

        IOUtils.write(data, response.getOutputStream());
    }

    @GetMapping("/edit")
    public String edit(Model model) {
        Configuration conf = GenUtils.getConfig();
        Map<String, Object> property = new HashMap<>(16);
        property.put("author", conf.getProperty("author"));
        property.put("email", conf.getProperty("email"));
        property.put("package", conf.getProperty("package"));
        property.put("autoRemovePre", conf.getProperty("autoRemovePre"));
        property.put("tablePrefix", conf.getProperty("tablePrefix"));
        model.addAttribute("property", property);
        return prefix + "/edit";
    }

    @ResponseBody
    @PostMapping("/update")
    Object update(@RequestParam Map<String, Object> map) {
        try {
            PropertiesConfiguration conf = new PropertiesConfiguration("generator.properties");
            conf.setProperty("author", map.get("author"));
            conf.setProperty("email", map.get("email"));
            conf.setProperty("package", map.get("package"));
            conf.setProperty("autoRemovePre", map.get("autoRemovePre"));
            conf.setProperty("tablePrefix", map.get("tablePrefix"));
            conf.save();
        } catch (Exception e) {
            return CommonResult.FAILED;
        }
        return CommonResult.SUCCESS;
    }
}
