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

import java.util.Collections;
import java.util.Map;

/**
 * Project: jb_common <br/>
 * File: GlobalConstants.java <br/>
 * Class: org.jbase.GlobalConstants <br/>
 * Description: <描述类的功能>. <br/>
 * Copyright: Copyright (c) 2011 <br/>
 * Company: http://www.yxtsoft.com/ <br/>
 * Makedate: 2016年4月13日 上午10:14:26 <br/>
 * 
 * @author liuzhanhong
 * @version 1.0
 * @since 1.0
 */
public class GlobalConstants {

	private static Map<String, Object> map = Collections.emptyMap();

	public static Object get(String key) {
		return map.get(key);
	}

	public static void set(String key, Object obj) {
		map.put(key, obj);
	}
	
	public static String image_upload_path;
	public static String cms_template_path;
	
	public static String Real_Path_Root="/";
	
	public static String ueditor_config_path="/";
	

}
