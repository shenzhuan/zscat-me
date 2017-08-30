package com.zs.cat.commons.util;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @author 
 * 
 */
public class Charset {

	/**
	 * @see 将null字符串对象转换为空字符""
	 * @param input
	 * @return
	 */
	public static String trim(String input) {
		return input == null ? "" : input.trim();
	}
	
	/**
	 * 判断字符串是否为空
	 * 
	 * @param o
	 * @return boolean
	 */
	public static boolean isEmpty(String str) {
		if (null == str || "".equals(str)) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean isEmpty(String str, boolean trim) {
		if(str == null) 
			return true;
		
		if(trim) 
			str = str.trim();
		
		return (str.length() == 0);
	}
	
	/**
	 * 判断一堆值中是否存在空
	 * @version: 1.00
	 * @history: 2009-5-30 下午10:43:07 [created]
	 * @param strs
	 * @return
	 * @see
	 */
	public static boolean containsEmpty(String... strs) {
		for (String s : strs) {
			if (isEmpty(s)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 判断一堆值中是否全部非空
	 * @version: 1.00
	 * @history: 2009-5-30 下午10:44:25 [created]
	 * @param strs
	 * @return
	 * @see
	 */
	public static boolean containsNoEmpty(String... strs) {
		for (String s : strs) {
			if (isEmpty(s)) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 判断一堆值中是否全部为空
	 * @version: 1.00
	 * @history: 2009-6-1 下午04:21:18 [created]
	 * @param strs
	 * @return
	 * @see
	 */
	public static boolean allEmpty(String... strs) {
		for (String s : strs) {
			if (!isEmpty(s)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 判断集合否为空
	 * @param o
	 * @return boolean
	 */
	public static boolean isEmpty(List list) {
		if (list!=null && list.size()>0) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * @see 中文字符8859_1编码转换为gbk
	 * @param input
	 * @return
	 */
	public static String decode(String input) {
		String output = "";
		if (input != null) {
			try {
				output = new String(input.getBytes("8859_1"), "gbk");
			} catch (Exception e) {
				// TODO: handle exception
				output = "";
			}
		}
		return output;
	}

	/**
	 * @see 将半角逗号分隔的字符串转换为Integer数组。输入如"1,2,3,4,5"
	 * @param keys
	 * @return
	 */
	public static Integer[] str2IntArr(String keys) {
		Integer[] intArr = new Integer[0];
		if (keys == null) {
			return intArr;
		}
		try {
			if (!keys.contains(",")) {
				try {
					intArr = new Integer[] { Integer.valueOf(keys) };
				} catch (Exception e) {
					// TODO: handle exception
					intArr = new Integer[] { 0 };
				}
			} else {
				String[] strArr = keys.split(",");
				intArr = new Integer[strArr.length];
				for (int i = 0; i < strArr.length; i++) {
					intArr[i] = Integer.valueOf(strArr[i]);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			intArr = new Integer[0];
		}
		return intArr;
	}

	/**
	 * @see 将字符串数组转换为半角逗号分隔的字符串。输出如"1,2,3,4,5"
	 * @return
	 */
	public static String strArr2str(String[] strArr) {
		String output = "";
		try {
			for (int i = 0; i < strArr.length; i++) {
				if (output.length() < 1) {
					output = strArr[i];
				} else {
					output += "," + strArr[i];
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			output = "";
		}
		return output;
	}
	
	/**
	 * @see
	 * @param input
	 * @param echoChar
	 * @param fromEnd
	 * @param reserved
	 * @return
	 */
	public static String mask(String input, String echoChar, boolean fromEnd, int reserved) {
		String output = input;
		try {
			String maskStr = "";
			int maskLen = input.length() - reserved;
			int count = 0;
			while (count < maskLen) {
				maskStr += echoChar;
				count++;
			}
			if (fromEnd) {
				output = input.substring(0, reserved) + maskStr; 
			} else {
				output = maskStr + input.substring(maskStr.length());
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return output;
	}

	// TODO: 字节数组 <> 整型

	/**
	 * @see 字节数组转换成整型
	 * @param bArr
	 * @return
	 */
	public static int bytes2int(byte[] bArr) {
		int mask = 0xff;
		int temp = 0;
		int res = 0;
		for (int i = 0; i < 4; i++) {
			res <<= 8;
			temp = bArr[i] & mask;
			res |= temp;
		}
		return res;
	}

	/**
	 * @see 整型转化为字节数组
	 * @param number
	 * @return
	 */
	public static byte[] int2bytes(int number) {
		byte[] bArr = new byte[4];
		for (int i = 0; i < 4; i++) {
			bArr[i] = (byte) (number >>> (24 - i * 8));
		}
		return bArr;
	}

	// TODO: 16进制字符串 <> 字节数组

	/**
	 * @see
	 * @param bArr
	 * @return
	 */
	public static String bytes2hex(byte[] bArr) {
		// 一个字节的数转成16进制字符串
		String hs = "";
		String stmp = "";
		for (int i = 0; i < bArr.length; i++) {
			// 整数转成十六进制表示
			stmp = (Integer.toHexString(bArr[i] & 0XFF));
			if (stmp.length() == 1)
				hs = hs + "0" + stmp;
			else
				hs = hs + stmp;
		}
		return hs.toUpperCase(); // 转成大写
	}
	/**
	 * 向左补齐字符
	 * 
	 * @version: 1.00
	 * @history: 2010-2-22 下午03:36:29 [created]
	 * @param input
	 * @param len
	 * @param padChar
	 * @return
	 * @see
	 */
	public static String lpad(String input, int len, String padChar) {
		if (input.length() >= len) {
			return input;
		}
		//
		StringBuffer sb = new StringBuffer(input);
		for (int i = 0, j = len - input.length(); i < j; i++) {
			sb.insert(0, padChar);
		}
		return sb.toString();
	}

	/**
	 * @see
	 * @param hex
	 * @return
	 */
	public static byte[] hex2Bytes(String hex) {
		int len = (hex.length() / 2);
		byte[] result = new byte[len];
		char[] achar = hex.toCharArray();
		for (int i = 0; i < len; i++) {
			int pos = i * 2;
			result[i] = (byte) (toByte(achar[pos]) << 4 | toByte(achar[pos + 1]));
		}
		return result;
	}

	/**
	 * @see
	 * @param c
	 * @return
	 */
	private static byte toByte(char c) {
		byte b = (byte) "0123456789ABCDEF".indexOf(c);
		return b;
	}
	
	/**
	 * 定义一个一维密码字典，用来产生随机代码
	 * @see
	 * @param n
	 * @return
	 */
	public static final String getRandomString(int n) {
		int randomIndex = 0;
		String randomStr = "";

		// 定义一个一维密码字典，用来产生随机代码
		char[] randomElement = { '1', '2', '3', '4', '5', '6', '7', '8', '9' };

		for (int i = 0; i < n; i++) {
			/* 利用random()方法（函数）产生一个随机的整型数，用来确定字典数组的对应元素 */
			randomIndex = ((new Double(Math.random() * 998)).intValue()) % 9;
			randomStr = String.valueOf(randomElement[randomIndex]) + randomStr;
		}

		return randomStr;

	}
	
	/**
	 * 按子节截取字符串
	 * @param targetString 目标字符串
	 * @param startIndex 开始位置
	 * @param endIndex 结束位置
	 * @param charsetName 编码格式
	 * @return
	 * @throws Exception
	 */
	public static String subStrByByteLen(String targetString, int startIndex,
			int endIndex, String charsetName) throws Exception {

		if (targetString == null || "".equals(targetString.trim()))
			return "";
		if (charsetName == null || "".equals(charsetName))
			throw new UnsupportedEncodingException("subStrByByteLen方法，必须指定编码格式");

		if (startIndex > endIndex)
			throw new Exception("subStrByByteLen方法，开始位置必须小于等于结束位置");

		int byteIndex = endIndex - startIndex;

		if (targetString.getBytes(charsetName).length < byteIndex) {
			throw new Exception("subStrByByteLen方法，超过长度");
		}

		String temp = targetString;
		for (int i = 0; i < targetString.length(); i++) {
			if (temp.getBytes(charsetName).length <= byteIndex) {
				break;
			}
			temp = temp.substring(startIndex - 1, endIndex);
		}
		return temp;
	}
}