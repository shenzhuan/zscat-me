package com.zscat.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

/**
 * IP获取工具类
 * File                 : IpGetter.java
 * Copy Right           : 天翼电子商户有限公司 www.cat.com.cn
 * Project              : com.cat.css.commons
 * JDK version used     : JDK 1.6
 * Comments             : 
 * Version              : 1.00
 * Modification history : 2014-10-21 上午11:07:47 [created]
 * Author               : Wanbo Mu 穆万波
 * Email                : muwanbo@tisson.cn
 **/
public class IpGetter {
	
	/**
	 * 获取本地IP
	 * @version: 1.00
	 * @history: 2014-10-21 上午11:08:00 [created]
	 * @author Wanbo Mu 穆万波
	 * @return
	 * @see
	 */
	public static String getLocalIP() {
		String sIP = "";
		InetAddress ip = null;
		try {
			boolean bFindIP = false;
			Enumeration<NetworkInterface> netInterfaces = (Enumeration<NetworkInterface>) NetworkInterface.getNetworkInterfaces();
			while (netInterfaces.hasMoreElements()) {
				if (bFindIP) {
					break;
				}
				NetworkInterface ni = (NetworkInterface) netInterfaces.nextElement();
				// ----------特定情况，可以考虑用ni.getName判断
				// 遍历所有ip
				Enumeration<InetAddress> ips = ni.getInetAddresses();
				while (ips.hasMoreElements()) {
					ip = (InetAddress) ips.nextElement();
					if (ip.isSiteLocalAddress() && !ip.isLoopbackAddress() // 127.开头的都是lookback地址
							&& ip.getHostAddress().indexOf(":") == -1) {
						bFindIP = true;
						break;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (null != ip) {
			sIP = ip.getHostAddress();
		}
		return sIP;
	}
}