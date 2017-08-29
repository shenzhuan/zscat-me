/**
 * FileName: ConfigProperties.java <br/>
 */
package com.zscat.conf;

/**
 * Project: jinyuanxinyansha-service <br/>
 * Class: com.yxt.common.utils.ConfigProperties <br/>
 * Description: <描述类的功能>. <br/>
 * Copyright: Copyright (c) 2011 <br/>
 * Company: www.qiandai.com <br/>
 * Makedate: 2013-8-1 下午9:02:46 <br/>
 * 
 * @author liupopo
 * @version 1.0
 * @since 1.0
 */
public class ConfigProperties {

	private ConfigProperties() {
	}
	
	private static PropertiesLoader pl = new PropertiesLoader("/config.properties");

//	private static final String APP_CONFIG_FILE_NAME = "config";
//	private static ResourceBundle _appConfigResource = null;

	public static String getValue(String key, String defValue) {
		return pl.getProperty(key, defValue);
//		if (_appConfigResource == null)
//			_appConfigResource = ResourceBundle.getBundle(APP_CONFIG_FILE_NAME);
//		String value = null;
//		if (_appConfigResource.containsKey(key))
//			value = _appConfigResource.getString(key);
//		else
//			value = defValue;
//		return value;
	}

	public static String getValue(String key) {
//		if (_appConfigResource == null)
//			_appConfigResource = ResourceBundle.getBundle(APP_CONFIG_FILE_NAME);
//		return _appConfigResource.getString(key);
		return pl.getProperty(key);
	}

}
