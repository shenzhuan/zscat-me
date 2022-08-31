package com.mallplus.user.vo;


import com.mallplus.common.exception.ApiMallPlusException;
import com.mallplus.common.utils.DateUtils;
import com.mallplus.user.bo.ColumnDO;
import com.mallplus.user.bo.TableDO;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 代码生成器   工具类
 */
public class GenUtils {


    public static List<String> getTemplates() {
        List<String> templates = new ArrayList<String>();
        templates.add("templates/common/generator/domain.java.vm");
        templates.add("templates/common/generator/Dao.java.vm");
        //templates.add("templates/common/generator/Mapper.java.vm");
        templates.add("templates/common/generator/Mapper.xml.vm");
        templates.add("templates/common/generator/Service.java.vm");
        templates.add("templates/common/generator/ServiceImpl.java.vm");
        templates.add("templates/common/generator/Controller.java.vm");
        templates.add("templates/common/generator/add.vue.vm");
        templates.add("templates/common/generator/index.vue.vm");
        templates.add("templates/common/generator/api.js.vm");
        templates.add("templates/common/generator/path.js.vm");
        templates.add("templates/common/generator/update.vue.vm");

        templates.add("templates/common/generator/BrandDetail.vue.vm");
        templates.add("templates/common/generator/menu.sql.vm");
        return templates;
    }

    /**
     * 生成代码
     */


    public static void generatorCode(Map<String, String> table,
                                     List<Map<String, String>> columns, ZipOutputStream zip) {
        //配置信息
        Configuration config = getConfig();
        //表信息
        TableDO tableDO = new TableDO();
        tableDO.setTableName(table.get("tableName"));
        tableDO.setComments(table.get("tableComment"));
        //表名转换成Java类名
        String className = tableToJava(tableDO.getTableName(), config.getString("tablePrefix"), config.getString("autoRemovePre"));
        tableDO.setClassName(className);
        tableDO.setClassname(StringUtils.uncapitalize(className));

        //列信息
        List<ColumnDO> columsList = new ArrayList<>();
        for (Map<String, String> column : columns) {
            ColumnDO columnDO = new ColumnDO();
            columnDO.setColumnName(column.get("columnName"));
            columnDO.setDataType(column.get("dataType"));
            columnDO.setComments(column.get("columnComment"));
            columnDO.setExtra(column.get("extra"));

            //列名转换成Java属性名
            String attrName = columnToJava(columnDO.getColumnName());
            columnDO.setAttrName(attrName);
            columnDO.setAttrname(StringUtils.uncapitalize(attrName));

            //列的数据类型，转换成Java类型
            String attrType = config.getString(columnDO.getDataType(), "unknowType");
            columnDO.setAttrType(attrType);

            //是否主键
            if ("PRI".equalsIgnoreCase(column.get("columnKey")) && tableDO.getPk() == null) {
                tableDO.setPk(columnDO);
            }

            columsList.add(columnDO);
        }
        tableDO.setColumns(columsList);

        //没主键，则第一个字段为主键
        if (tableDO.getPk() == null) {
            tableDO.setPk(tableDO.getColumns().get(0));
        }

        //设置velocity资源加载器
        Properties prop = new Properties();
        prop.put("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        Velocity.init(prop);

        String packages = "com.zscat.mallplus";
        String Module = tableDO.getTableName().split("_")[0].substring(0, 1).toUpperCase() + tableDO.getTableName().split("_")[0].substring(1).toLowerCase();
        //封装模板数据
        Map<String, Object> map = new HashMap<>(16);
        map.put("tableName", tableDO.getTableName());
        map.put("comments", tableDO.getComments());
        map.put("pk", tableDO.getPk());
        map.put("module", tableDO.getTableName().split("_")[0]);
        map.put("Module", Module);
        map.put("className", tableDO.getClassName());
        map.put("classname", tableDO.getClassname());
        map.put("pathName", packages.substring(packages.lastIndexOf(".") + 1));
        map.put("columns", tableDO.getColumns());
        map.put("package", packages);
        map.put("author", config.getString("author"));
        map.put("email", config.getString("email"));
        map.put("datetime", DateUtils.format(new Date(), DateUtils.DATE_TIME_PATTERN));
        VelocityContext context = new VelocityContext(map);

        //获取模板列表
        List<String> templates = getTemplates();
        for (String template : templates) {
            //渲染模板
            StringWriter sw = new StringWriter();
            Template tpl = Velocity.getTemplate(template, "UTF-8");
            tpl.merge(context, sw);

            try {
                //添加到zip
                zip.putNextEntry(new ZipEntry(getFileName(
                        tableDO.getTableName().split("_")[0], template, tableDO.getClassname(), tableDO.getClassName(), packages.substring(packages.lastIndexOf(".") + 1), Module)));
                IOUtils.write(sw.toString(), zip, "UTF-8");
                IOUtils.closeQuietly(sw);
                zip.closeEntry();
            } catch (IOException e) {
                throw new ApiMallPlusException("渲染模板失败，表名：" + tableDO.getTableName(), e);
            }
        }
    }


    /**
     * 列名转换成Java属性名
     */
    public static String columnToJava(String columnName) {
        return WordUtils.capitalizeFully(columnName, new char[]{'_'}).replace("_", "");
    }

    /**
     * 表名转换成Java类名
     */
    public static String tableToJava(String tableName, String tablePrefix, String autoRemovePre) {
        if ("true".equals(autoRemovePre)) {
            tableName = tableName.substring(tableName.indexOf("_") + 1);
        }
        if (StringUtils.isNotBlank(tablePrefix)) {
            tableName = tableName.replace(tablePrefix, "");
        }

        return columnToJava(tableName);
    }

    /**
     * 获取配置信息
     */
    public static Configuration getConfig() {
        try {
            return new PropertiesConfiguration("generator.properties");
        } catch (org.apache.commons.configuration.ConfigurationException e) {
            throw new ApiMallPlusException("获取配置文件失败，", e);
        }
    }

    /**
     * 获取文件名
     */
    public static String getFileName(String module, String template, String classname, String className, String packageName, String Module) {
        String packagePath = "main" + File.separator + "java" + File.separator;
        //String modulesname=config.getString("packageName");
        if (StringUtils.isNotBlank(packageName)) {
            packagePath += packageName.replace(".", File.separator) + File.separator;
        }

        if (template.contains("domain.java.vm")) {
            return Module + className + ".java";
        }

        if (template.contains("Dao.java.vm")) {
            return Module + className + "Mapper.java";
        }

//		if(template.contains("Mapper.java.vm")){
//			return packagePath + "dao" + File.separator + className + "Mapper.java";
//		}
        // templates.add("templates/common/generator/menu.sql.vm");
        if (template.contains("menu.sql.vm")) {
            return Module + className + "menu.sql";
        }
        if (template.contains("Service.java.vm")) {
            return "I" + Module + className + "Service.java";
        }

        if (template.contains("ServiceImpl.java.vm")) {
            return Module + className + "ServiceImpl.java";
        }

        if (template.contains("Controller.java.vm")) {
            return Module + className + "Controller.java";
        }

        if (template.contains("Mapper.xml.vm")) {
            return Module + className + "Mapper.xml";
        }
        if (template.contains("api.js.vm")) {
            return classname + ".js";
        }
        if (template.contains("path.js.vm")) {
            return classname + "path" + ".js";
        }
        if (template.contains("add.vue.vm")) {
            return classname + File.separator + "add.vue";
        }

        if (template.contains("index.vue.vm")) {
            return classname + File.separator + "index.vue";
        }
        if (template.contains("update.vue.vm")) {
            return classname + File.separator + "update.vue";
        }
        if (template.contains("BrandDetail.vue.vm")) {
            return classname + File.separator + "components" + File.separator + className + "Detail.vue";
        }

        return null;
    }
}
