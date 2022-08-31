package com.mallplus.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 作者 zscat E-mail: 951449465@qq.com
 * @version 创建时间：2017年11月12日 上午22:57:51
 * 正则表达式手机号码校验类 
 */
public class PhoneUtil {

	private static String REGEX = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0,5-9]))\\d{8}$";
	private static Pattern P = Pattern.compile(REGEX);

	/**
	 * 校验手机号
	 * 
	 * @param phone
	 * @return
	 */
	public static boolean checkPhone(String phone) {
		if (phone == null || phone.length() != 11) {
			return Boolean.FALSE;
		}

		Matcher m = P.matcher(phone);
		return m.matches();
	}
}
