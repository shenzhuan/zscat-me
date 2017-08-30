package com.zs.cat.commons.util;

import java.io.File;

/**
 * java删除所有文件和文件夹
 * 创建人：zs 创建时间：2015年1月12日
 * @version
 */
public class DelAllFile {
	
	public static void main(String args[]) {
		delFolder("e:/e/a");			//只删除e下面a及a下面所有文件和文件夹,e不会被删掉
		//delFolder("D:/WEBSerser/apache-tomcat-8.0.15/me-webapps/UIMYSQL/WEB-INF/classes/../../admin00/ftl/code");	
		//delFolder("D:\\WEBSerser\\apache-tomcat-8.0.15\\me-webapps\\UIMYSQL\\admin00\\ftl\\code");
		//delFolder("D:/WEBSerser/apache-tomcat-8.0.15/me-webapps/UIMYSQL/WEB-INF/classes/../../admin00/ftl/code");
		System.out.println("deleted");
	}

	/**
	 * @param folderPath 文件路径 (只删除此路径的最末路径下所有文件和文件夹)
	 */
	public static void delFolder(String folderPath) {
		try {
			delAllFile(folderPath); 	// 删除完里面所有内容
			String filePath = folderPath;
			filePath = filePath.toString();
			java.io.File myFilePath = new java.io.File(filePath);
			myFilePath.delete(); 		// 删除空文件夹
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 删除指定文件夹下所有文件
	 * @param path 文件夹完整绝对路径
	 */
	public static boolean delAllFile(String path) {
		boolean flag = false;
		File file = new File(path);
		if (!file.exists()) {
			return flag;
		}
		if (!file.isDirectory()) {
			return flag;
		}
		String[] tempList = file.list();
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
			if (path.endsWith(File.separator)) {
				temp = new File(path + tempList[i]);
			} else {
				temp = new File(path + File.separator + tempList[i]);
			}
			if (temp.isFile()) {
				temp.delete();
			}
			if (temp.isDirectory()) {
				delAllFile(path + "/" + tempList[i]);	// 先删除文件夹里面的文件
				delFolder(path + "/" + tempList[i]);	// 再删除空文件夹
				flag = true;
			}
		}
		return flag;
	}
}
