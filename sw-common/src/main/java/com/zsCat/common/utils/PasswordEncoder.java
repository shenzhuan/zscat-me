package com.zsCat.common.utils;


public class PasswordEncoder {

	public static String encrypt(String src, Object salt) {
		return encrypt(src, String.valueOf(salt));
	}

	/**
	 * 密码加密
	* @param src 密码
	* @param salt 账号
	* @return
	 */
	public static String encrypt(String src, String salt) {
		return EncryptUtils.MD5_HEX(EncryptUtils.MD5_HEX(src) + salt);
	}

	public static void main(String[] args) {
		System.out.println(encrypt("admin", "admin"));
	}
}
