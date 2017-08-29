package com.stylefeng.guns.core.template.config;

import java.util.ArrayList;
import java.util.List;

/**
 * Service模板生成的配置
 *
 * @author fengshuonan
 * @date 2017-05-07 22:12
 */
public class ServiceConfig {

    private ContextConfig contextConfig;

    private String servicePathTemplate;
    private String serviceImplPathTemplate;

    private String packageName;

    private List<String> serviceImplImports;
    private List<String> serviceImports;
    public void init() {
        ArrayList<String> imports = new ArrayList<>();
        imports.add("org.springframework.stereotype.Service");
        imports.add("com.stylefeng.guns.modular." + contextConfig.getModuleName() + ".service.I" + contextConfig.getBizEnBigName() + "Service");
        imports.add("com.baomidou.mybatisplus.service.impl.ServiceImpl");
        imports.add("com.stylefeng.guns.modular." + contextConfig.getModuleName() + ".model" + contextConfig.getBizEnBigName());
        imports.add("com.stylefeng.guns.modular." + contextConfig.getModuleName() + ".dao" + contextConfig.getBizEnBigName()+"Dao");
        this.serviceImplImports = imports;

        ArrayList<String> serviceImports = new ArrayList<>();

        serviceImports.add("com.baomidou.mybatisplus.service.IService");
        serviceImports.add("com.stylefeng.guns.modular." + contextConfig.getModuleName() + ".model" + contextConfig.getBizEnBigName());

        this.serviceImports = serviceImports;

        this.servicePathTemplate = "\\src\\main\\java\\com\\stylefeng\\guns\\modular\\" + contextConfig.getModuleName() + "\\service\\I{}Service.java";
        this.serviceImplPathTemplate = "\\src\\main\\java\\com\\stylefeng\\guns\\modular\\" + contextConfig.getModuleName() + "\\service\\impl\\{}ServiceImpl.java";
        this.packageName = "com.stylefeng.guns.modular." + contextConfig.getModuleName() + ".service";
    }

    public List<String> getServiceImports() {
        return serviceImports;
    }

    public void setServiceImports(List<String> serviceImports) {
        this.serviceImports = serviceImports;
    }

    public String getServicePathTemplate() {
        return servicePathTemplate;
    }

    public void setServicePathTemplate(String servicePathTemplate) {
        this.servicePathTemplate = servicePathTemplate;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getServiceImplPathTemplate() {
        return serviceImplPathTemplate;
    }

    public void setServiceImplPathTemplate(String serviceImplPathTemplate) {
        this.serviceImplPathTemplate = serviceImplPathTemplate;
    }

    public List<String> getServiceImplImports() {
        return serviceImplImports;
    }

    public void setServiceImplImports(List<String> serviceImplImports) {
        this.serviceImplImports = serviceImplImports;
    }

    public ContextConfig getContextConfig() {
        return contextConfig;
    }

    public void setContextConfig(ContextConfig contextConfig) {
        this.contextConfig = contextConfig;
    }
}
