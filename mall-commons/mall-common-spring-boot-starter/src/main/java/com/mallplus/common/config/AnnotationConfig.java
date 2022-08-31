package com.mallplus.common.config;

import java.util.ArrayList;
import java.util.List;

/**
 * 加解密配置类
 * 
 * @author zscat
 * 
 * @date 2019-01-12
 * 
 * @about 2019-04-30
 *
 */

public class AnnotationConfig {
	/**
	 * 需要对响应内容进行加密的接口URI<br>
	 * 比如：/user/list<br>
	 * 不支持@PathVariable格式的URI
	 */
	private List<String> notLoginUriList = new ArrayList<String>();

	
	public AnnotationConfig() {
		super();
	}
	
	public AnnotationConfig(List<String> notLoginUriList) {
		super();
		this.notLoginUriList = notLoginUriList;

	}

	public List<String> getRequestNoNeedDecyptUriList() {
		// 不需要加密的url
		if (ApiEncryptDataInit.notLoginUriList.size() > 0) {
			return ApiEncryptDataInit.notLoginUriList;
		}
		return notLoginUriList;
	}
	public void setNotLoginUriList(List<String> notLoginUriList) {
		this.notLoginUriList = notLoginUriList;
	}

}
