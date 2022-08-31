package com.mallplus.goods.config;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.DeleteObjectsRequest;
import com.aliyun.oss.model.DeleteObjectsResult;
import com.aliyun.oss.model.PutObjectRequest;
import com.mallplus.common.vo.OssAliyunField;
import com.mallplus.util.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author: Peter
 * @date: 2018-4-11
 */
@Component
public class OssAliyunUtil {

    @Resource(name = "defaultOssAliyunField")
    private OssAliyunField defaultOssAliyunField;

    /**
     * 上传文件（选择默认的OSS配置）
     *
     * @param file
     * @return
     */
    public String upload(MultipartFile file) {
        String url = null;
        try {
            url = upload(defaultOssAliyunField, getKey(defaultOssAliyunField.getPrefix(), this.getSuffix(file)),
                    file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return url;
    }

    public static String getSuffix(final MultipartFile file) {
        if (file == null || file.getSize() == 0) {
            return null;
        }
        String fileName = file.getOriginalFilename();
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    /**
     * 上传文件
     *
     * @param suffix
     * @param inputStream
     * @return
     */
    public String upload(String suffix, InputStream inputStream) {
        return upload(defaultOssAliyunField, getKey(defaultOssAliyunField.getPrefix(), suffix), inputStream);
    }

    /**
     * 上传文件
     *
     * @param ossAliyunField 配置类，不同的配置类上传的配置就不一样
     * @param file
     * @return
     */
    public String upload(OssAliyunField ossAliyunField, MultipartFile file) {
        String url = null;
        try {
            url = upload(ossAliyunField, getKey(ossAliyunField.getPrefix(), this.getSuffix(file)),
                    file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return url;
    }

    /**
     * 上传文件<>基础方法</>
     *
     * @param accessKeyId     授权 ID
     * @param accessKeySecret 授权密钥
     * @param bucketName      桶名
     * @param endpoint        节点名
     * @param styleName       样式名
     * @param key             文件名
     * @param inputStream     文件流
     * @return 访问路径 ，结果为null时说明上传失败
     */
    public String upload(String accessKeyId, String accessKeySecret, String bucketName, String endpoint, String
            styleName, String key, InputStream inputStream) {
        if (inputStream == null) {
            return null;
        }
        String url = null;
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        try {
            // 带进度条的上传
            ossClient.putObject(new PutObjectRequest(bucketName, key, inputStream));
        } catch (OSSException oe) {
            oe.printStackTrace();
            key = null;
        } catch (ClientException ce) {
            ce.printStackTrace();
            key = null;
        } catch (Exception e) {
            e.printStackTrace();
            key = null;
        } finally {
            ossClient.shutdown();
        }
        if (key != null) {
            // 拼接文件访问路径。由于拼接的字符串大多为String对象，而不是""的形式，所以直接用+拼接的方式没有优势
            StringBuffer sb = new StringBuffer();
            sb.append("http://").append(bucketName).append(".").append(endpoint).append("/").append(key);
            if (StringUtils.isNotBlank(styleName)) {
                sb.append("/").append(styleName);
            }
            url = sb.toString();
        }
        return url;
    }

    /**
     * 上传文件
     *
     * @param field       配置对象 此值从OssAliyunConfig当中选择的
     * @param key         文件名
     * @param inputStream 文件流
     * @return
     */
    public String upload(OssAliyunField field, String key, InputStream inputStream) {
        return upload(field.getAccessKeyId(), field.getAccessKeySecret(), field.getBucketName(), field
                .getEndPoint(), field.getStyleName(), key, inputStream);
    }

    /**
     * 删除单个文件<>基础方法</>
     *
     * @param accessKeyId     授权 ID
     * @param accessKeySecret 授权密钥
     * @param bucketName      桶名
     * @param endpoint        节点名
     * @param key             文件名
     */
    public void delete(final String accessKeyId, final String accessKeySecret, final String bucketName,
                       final String endpoint, final String key) {
        // 创建OSSClient实例
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        // 删除Object
        ossClient.deleteObject(bucketName, key);
        // 关闭client
        ossClient.shutdown();
    }

    /**
     * 删除单个文件
     *
     * @param field OSS相关配置
     * @param key   文件名
     */
    public void delete(OssAliyunField field, String key) {
        delete(field.getAccessKeyId(), field.getAccessKeySecret(), field.getBucketName(), field
                .getEndPoint(), key);
    }

    /**
     * 删除多个文件<>基础方法</>
     *
     * @param accessKeyId     授权 ID
     * @param accessKeySecret 授权密钥
     * @param bucketName      桶名
     * @param endpoint        节点名
     * @param keys            多个文件名的集合
     */
    public void delete(final String accessKeyId, final String accessKeySecret, final String bucketName,
                       final String endpoint, final List<String> keys) {
        // 创建OSSClient实例
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        // 删除Objects
        DeleteObjectsResult deleteObjectsResult = ossClient.deleteObjects(new DeleteObjectsRequest(bucketName)
                .withKeys(keys));
        List<String> deletedObjects = deleteObjectsResult.getDeletedObjects();
        // 关闭client
        ossClient.shutdown();
    }

    /**
     * 删除多个文件
     *
     * @param field OSS相关配置
     * @param keys  多个文件名的集合
     */
    public void delete(OssAliyunField field, List<String> keys) {
        delete(field.getAccessKeyId(), field.getAccessKeySecret(), field.getBucketName(), field
                .getEndPoint(), keys);
    }

    /**
     * 获取文件名（bucket里的唯一key）
     * 上传和删除时除了需要bucketName外还需要此值
     *
     * @param prefix 前缀（非必传），可以用于区分是哪个模块或子项目上传的文件
     * @param suffix 后缀（非必传）, 可以是 png jpg 等
     * @return
     */
    public String getKey(final String prefix, final String suffix) {
        //生成uuid,替换 - 的目的是因为后期可能会用 - 将key进行split，然后进行分类统计
        String uuid = UUID.randomUUID().toString().replaceAll("-" , "");
        //文件路径
        String path = DateUtils.format(new Date(), "yyyyMMdd") + "-" + uuid;

        if (StringUtils.isNotBlank(prefix)) {
            path = prefix + "-" + path;
        }
        if (suffix != null) {
            if (suffix.startsWith(".")) {
                path = path + suffix;
            } else {
                path = path + "." + suffix;
            }
        }
        return path;
    }

}

