/*********************************************************
 *********************************************************
 ********************                  *******************
 *************                                ************
 *******                  _oo0oo_                  *******
 ***                     o8888888o                     ***
 *                       88" . "88                       *
 *                       (| -_- |)                       *
 *                       0\  =  /0                       *
 *                     ___/`---'\___                     *
 *                   .' \\|     |// '.                   *
 *                  / \\|||  :  |||// \                  *
 *                 / _||||| -:- |||||- \                 *
 *                |   | \\\  -  /// |   |                *
 *                | \_|  ''\---/''  |_/ |                *
 *                \  .-\__  '-'  ___/-. /                *
 *              ___'. .'  /--.--\  `. .'___              *
 *           ."" '<  `.___\_<|>_/___.' >' "".            *
 *          | | :  `- \`.;`\ _ /`;.`/ - ` : | |          *
 *          \  \ `_.   \_ __\ /__ _/   .-` /  /          *
 *      =====`-.____`.___ \_____/___.-`___.-'=====       *
 *                        `=---='                        *
 *      ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~      *
 *********__佛祖保佑__永无BUG__验收通过__钞票多多__*********
 *********************************************************/
package com.zscat.conf;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletContext;

import org.apache.commons.lang3.StringUtils;
import org.beetl.ext.spring.BeetlGroupUtilConfiguration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Project: fw_admin <br/>
 * File: AdminBeetlGroupUtilConfiguration.java <br/>
 * Class: com.yxt.admin.ext.beetl.AdminBeetlGroupUtilConfiguration <br/>
 * Description: <描述类的功能>. <br/>
 * Copyright: Copyright (c) 2011 <br/>
 * Company: http://www.yxtsoft.com/ <br/>
 * Makedate: 2015年11月30日 下午2:46:53 <br/>
 * 
 * @author liuzhanhong
 * @version 1.0
 * @since 1.0
 */
public class JbaseBeetlGroupUtilConfiguration extends BeetlGroupUtilConfiguration {

	private static final Logger L = LoggerFactory.getLogger(JbaseBeetlGroupUtilConfiguration.class);

	@Autowired
	private Map<String, JbaseFunctionPackage> funcpack;

	private String ctxPath;
	private String rootPath;

	// java中获得文件路径
	// 1、Thread.currentThread().getContextClassLoader().getResource("").toURI().getPath()
	// 2、MyClass.class.getClassLoader().getResource("").toURI().getPath()
	// 3、ClassLoader.getSystemResource("").toURI().getPath()
	// 4、MyClass.class.getResource("").toURI().getPath()
	// 5、MyClass.class.getResource("/").toURI().getPath()
	// 6、newFile("/").getAbsolutePath().toURI().getPath()
	// 7、System.getProperty("user.dir").toURI().getPath()

	@Override
	public void setServletContext(ServletContext sc) {
		super.setServletContext(sc);
		rootPath = sc.getRealPath("/");
		L.info("项目临时根路径为：{}", rootPath);

		try {
			rootPath =JbaseBeetlGroupUtilConfiguration.class.getClassLoader().getResource("").toURI().getPath();
			// ClassLoader classLoader = org.jbase.Application.class.getClassLoader();
			// URL resource = classLoader.getResource("");
			// rootPath = resource.toURI().getPath();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		L.info("项目运行根路径为：{}", rootPath);
		String resourcerootpath = ConfigProperties.getValue("resource.rootpath");
		if (StringUtils.isNotBlank(resourcerootpath)) {
			rootPath = resourcerootpath;
		}
		GlobalConstants.Real_Path_Root = rootPath;
		
		L.info("项目classpath root根路径为：{}", rootPath);
		
		ctxPath = sc.getContextPath();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.beetl.ext.spring.BeetlGroupUtilConfiguration#initOther()
	 */
	@Override
	protected void initOther() {
		super.initOther();
		initSharedVars();

		// 注册自定义函数包
		for (Entry<String, JbaseFunctionPackage> entry : funcpack.entrySet()) {
			groupTemplate.registerFunctionPackage(entry.getKey(), entry.getValue());
		}
//		groupTemplate.registerTag(name, tagCls);

		initGlobalConstants();

	}

	/**
	 * 描述 : <描述函数实现的功能>. <br/>
	 * <p>
	 * 
	 */
	private void initSharedVars() {
		if (sharedVars == null)
			sharedVars = new HashMap<String, Object>();
		String _base = ctxPath;
		String hasNginx = ConfigProperties.getValue("has_nginx");
		if ("true".equals(hasNginx)) {
			_base = "";
		}
		sharedVars.put("BASE", _base);
		sharedVars.put("STATIC", _base + "");
		String imageurl = ConfigProperties.getValue("resource.image_url");
		if (StringUtils.isEmpty(imageurl)) {
			imageurl = _base + "/upload";
		}
		sharedVars.put("IMAGEPATH", imageurl);
		L.info("项目 IMAGEPATH 根路径为：{}", imageurl);
		groupTemplate.setSharedVars(sharedVars);
	}

	/**
	 * 描述 : <描述函数实现的功能>. <br/>
	 * <p>
	 * 
	 */
	private void initGlobalConstants() {
		String uploadpath = ConfigProperties.getValue("resource.uploadpath");
		if (StringUtils.isBlank(uploadpath)) {
			uploadpath = rootPath + "static/upload";
		}
		GlobalConstants.image_upload_path = uploadpath;
		L.info("项目image_upload_path根路径为：{}", GlobalConstants.image_upload_path);

		String cmstplpath = ConfigProperties.getValue("cms.tplpath");
		if (StringUtils.isBlank(cmstplpath)) {
			cmstplpath = rootPath + "templates/tpl";
		}
		GlobalConstants.cms_template_path = cmstplpath;
		L.info("项目 cms_template_path 根路径为：{}", GlobalConstants.cms_template_path);
		
		String ueditorconfigpath = ConfigProperties.getValue("ueditor.config.path");
		if (StringUtils.isBlank(ueditorconfigpath)) {
			ueditorconfigpath = rootPath;
		}
		GlobalConstants.ueditor_config_path = cmstplpath;
		L.info("项目 ueditor_config_path 根路径为：{}", GlobalConstants.ueditor_config_path);
	}

}
