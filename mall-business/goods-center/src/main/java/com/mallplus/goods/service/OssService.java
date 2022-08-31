package com.mallplus.goods.service;


import com.mallplus.common.vo.OssCallbackResult;
import com.mallplus.common.vo.OssPolicyResult;


import javax.servlet.http.HttpServletRequest;

/**
 * oss上传管理Service
 * https://github.com/shenzhuan/mallplus on 2018/5/17.
 */
public interface OssService {
    OssPolicyResult policy();

    OssCallbackResult callback(HttpServletRequest request);
}
