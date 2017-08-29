package com.stylefeng.guns.core.template.config;

import java.util.ArrayList;
import java.util.List;

/**
 * Dao模板生成的配置
 *
 * @author fengshuonan
 * @date 2017-05-07 22:12
 */
public class DaoConfig {

    private ContextConfig contextConfig;

    private String daoPathTemplate;
    private String xmlPathTemplate;
    private List<String> imports;//所引入的包
    private String packageName;

    public void init() {
        ArrayList<String> imports = new ArrayList<>();
        imports.add("com.baomidou.mybatisplus.mapper.BaseMapper");
        imports.add("com.stylefeng.guns.modular." + contextConfig.getModuleName() + ".model" + contextConfig.getBizEnBigName());
        this.imports = imports;
        this.daoPathTemplate = "\\src\\main\\java\\com\\stylefeng\\guns\\modular\\" + contextConfig.getModuleName() + "\\dao\\{}Dao.java";
        this.xmlPathTemplate = "\\src\\main\\java\\com\\stylefeng\\guns\\modular\\" + contextConfig.getModuleName() + "\\dao\\mapping\\{}Dao.xml";
        this.packageName = "com.stylefeng.guns.modular." + contextConfig.getModuleName() + ".dao";
    }
    public List<String> getImports() {
        return imports;
    }

    public void setImports(List<String> imports) {
        this.imports = imports;
    }
    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getDaoPathTemplate() {
        return daoPathTemplate;
    }

    public void setDaoPathTemplate(String daoPathTemplate) {
        this.daoPathTemplate = daoPathTemplate;
    }

    public String getXmlPathTemplate() {
        return xmlPathTemplate;
    }

    public void setXmlPathTemplate(String xmlPathTemplate) {
        this.xmlPathTemplate = xmlPathTemplate;
    }

    public ContextConfig getContextConfig() {
        return contextConfig;
    }

    public void setContextConfig(ContextConfig contextConfig) {
        this.contextConfig = contextConfig;
    }
}
