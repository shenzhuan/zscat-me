package com.zs.cat.commons.util;

public class LatLonUtil {

	private static final double PI = 3.14159265; // 圆周率
	private static final double EARTH_RADIUS = 6378137; // 地球半径
	private static final double RAD = Math.PI / 180.0; // 一百八十度角

	/**
	 * @param raidus
	 * 单位米 return minLat 
	 * 最小经度 minLng 
	 * 最小纬度 maxLat 
	 * 最大经度 maxLng 
	 * 最大纬度 minLat
	 */
	public static double[] getAround(double lat, double lon, int raidus) {

		Double latitude = lat;// 传值给经度
		Double longitude = lon;// 传值给纬度

		Double degree = (24901 * 1609) / 360.0; // 获取每度
		double raidusMile = raidus;

		Double dpmLat = 1 / degree;
		Double radiusLat = dpmLat * raidusMile;
		// 获取最小纬度
		Double minLat = latitude - radiusLat;
		// 获取最大纬度
		Double maxLat = latitude + radiusLat;

		Double mpdLng = degree * Math.cos(latitude * (PI / 180));
		Double dpmLng = 1 / mpdLng;
		Double radiusLng = dpmLng * raidusMile;
		//获取最小经度
		Double minLng = longitude - radiusLng;
		// 获取最大经度
		Double maxLng = longitude + radiusLng;
		
		System.out.println("jingdu" + minLat + "weidu" + minLng + "zuidajingdu"
				+ maxLat + "zuidaweidu" + maxLng);

		return new double[] { minLat, minLng, maxLat, maxLng };
	}
	//测试方法
	public static void main(String [] src){
		getAround(36.68027, 117.12744, 1000);
	}

}