package com.mallplus.common.utils;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidatorUtils {

	/**
	 * 判断内容不为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean notEmpty(Object str) {
		if (str != null && str.toString().trim().length() > 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 判断内容不为空（包含数组）
	 * 
	 * @param str
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static boolean notEmptyIncludeArray(Object str) {
		if (str != null && String.valueOf(str).trim().length() > 0) {
			if (str instanceof Object[]) {// 增加了数组长度判断
				Object[] array = (Object[]) str;
				if (array.length > 0)
					return true;

				return false;
			} else if (str instanceof List<?>) {// 增加了数组长度判断
				List<Object> list = (List<Object>) str;
				if (list.size() > 0)
					return true;

				return false;
			}

			return true;
		} else {
			return false;
		}
	}

	/**
	 * 判断内容是空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(Object str) {
		if (str == null || str.toString().trim().length() == 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 检查对象是否为空
	 * 
	 * @param obj
	 *            要检查的数据(数据类型: String、Number、Boolean、Collection、Map、Object[])
	 * @return true: 为空; false: 不为空 <li>String：值为 null、""、"0" 时返回 true <li>
	 *         Number：值为 null、0 时返回 true <li>Boolean：值为 null、false 时返回 true <li>
	 *         Collection：值为 null、size=0 时返回 true <li>Map：值为 null、size=0 时返回
	 *         true <li>Object[]：值为 null、length=0 时返回 true
	 */
	@SuppressWarnings("unchecked")
	public static boolean empty(Object obj) {
		if (obj == null) {
			return true;
		} else if (obj instanceof String && (obj.equals(""))) {
			return true;
		} else if (obj instanceof Number && ((Number) obj).doubleValue() == 0) {
			return true;
		} else if (obj instanceof Boolean && !((Boolean) obj)) {
			return true;
		} else if (obj instanceof Collection && ((Collection) obj).isEmpty()) {
			return true;
		} else if (obj instanceof Map && ((Map) obj).isEmpty()) {
			return true;
		} else if (obj instanceof Object[] && ((Object[]) obj).length == 0) {
			return true;
		}
		return false;
	}

	/**
	 * 判断是否是合法邮箱地址
	 * 
	 * @param email
	 * @return
	 */
	public static boolean isEmail(String email) {
		Pattern p = Pattern
				.compile("^\\w+([\\-+.]\\w+)*@\\w+([-.]\\w+)*\\.[a-z]{2,3}");
		Matcher m = p.matcher(email);
		return m.matches();
	}

	/**
	 * 只包含英文字母和数字、下划线
	 * 
	 * @param str
	 * @return
	 */
	public static boolean onlyNumAndChar(String str) {
		String regex = "^[a-zA-Z0-9_]+$";
		Pattern pattern = Pattern.compile(regex);
		return pattern.matcher(str).matches();
	}

	/**
	 * 必须包含字母
	 *
	 * @param str
	 * @return
	 */
	public static boolean hasLetterAndNum(String str) {
		Pattern pattern = Pattern.compile("^(?=.*[a-zA-Z].*).{6,}$");
		return pattern.matcher(str).matches();
	}

	/**
	 * 是否长度符合
	 * 
	 * @param str
	 * @param min
	 *            最小
	 * @param max
	 *            最大
	 * @return
	 */
	public static boolean lengthBetween(String str, int min, int max) {
		return str.length() >= min && str.length() <= max;
	}

	/**
	 * 判断字符串是否是数字
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}
	public static Double rountTwo(Double num) {
		if (num != null) {
			BigDecimal b = new BigDecimal(num);
			Double d = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			return d;
		} else {
			return null;
		}
	}
	public static String doubleTrans(double d){
		if(Math.round(d)-d==0){
			return String.valueOf((long)d);
		}
		return String.valueOf(d);
	}
}
