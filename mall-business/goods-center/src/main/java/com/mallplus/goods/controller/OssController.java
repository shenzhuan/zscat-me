package com.mallplus.goods.controller;


import com.mallplus.common.annotation.IgnoreAuth;
import com.mallplus.common.annotation.SysLog;
import com.mallplus.common.entity.pms.PmsAlbumPic;
import com.mallplus.common.utils.CommonResult;
import com.mallplus.common.utils.ValidatorUtils;
import com.mallplus.common.vo.BlobUpload;
import com.mallplus.common.vo.OssCallbackResult;
import com.mallplus.common.vo.OssPolicyResult;
import com.mallplus.goods.config.OssAliyunUtil;
import com.mallplus.goods.mapper.PmsAlbumPicMapper;
import com.mallplus.goods.service.OssService;
import com.mallplus.goods.service.impl.OssServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Oss相关操作接口
 * https://github.com/shenzhuan/mallplus on 2018/4/26.
 */
@RestController
@Api(tags = "OssController" , description = "Oss管理")
@RequestMapping("/aliyun/oss")
public class OssController {
    @Autowired
    OssAliyunUtil aliyunOSSUtil;
    @Autowired
    private OssService ossService;
    @Resource
    private PmsAlbumPicMapper albumPicMapper;

    private static ByteArrayInputStream getRandomDataStream(int length) {
        return new ByteArrayInputStream(getRandomBuffer(length));
    }

    private static byte[] getRandomBuffer(int length) {
        final Random randGenerator = new Random();
        final byte[] buff = new byte[length];
        randGenerator.nextBytes(buff);
        return buff;
    }

    @ApiOperation(value = "oss上传签名生成")
    @RequestMapping(value = "/policy" , method = RequestMethod.GET)
    @ResponseBody
    public Object policy() {
        OssPolicyResult result = ossService.policy();
        return new CommonResult().success(result);
    }

    @ApiOperation(value = "oss上传成功回调")
    @RequestMapping(value = "callback" , method = RequestMethod.POST)
    @ResponseBody
    public Object callback(HttpServletRequest request) {
        OssCallbackResult ossCallbackResult = ossService.callback(request);
        return new CommonResult().success(ossCallbackResult);
    }

    @SysLog(MODULE = "图片上传管理" , REMARK = "上传")
    @ApiOperation("上传")
    @RequestMapping(value = "upload" , method = RequestMethod.POST)
    public Object uploadImage(Long groupId, String id, Long uid, String fileType, int type, @RequestPart("file") MultipartFile multipartFile) {
        List<BlobUpload> list = new ArrayList<>();

        String name = aliyunOSSUtil.upload(multipartFile);
        insertPic(groupId, multipartFile, name, "image");
        BlobUpload blobUploadEntity = new BlobUpload();
        blobUploadEntity.setFileName(multipartFile.getOriginalFilename());
        blobUploadEntity.setFileUrl(name);
        blobUploadEntity.setThumbnailUrl(name);
        list.add(blobUploadEntity);


        return new CommonResult().success(list);
    }

    @SysLog(MODULE = "图片上传管理" , REMARK = "上传")
    @ApiOperation("上传")
    @RequestMapping(value = "uploads" , method = RequestMethod.POST)
    public Object uploadImages(Long groupId, Long id, int type, @RequestPart("file") MultipartFile[] multipartFile) {
        List<BlobUpload> list = new ArrayList<>();
        if (multipartFile != null && multipartFile.length > 0) {
            for (int i = 0; i < multipartFile.length; i++) {
                String name = aliyunOSSUtil.upload(multipartFile[i]);
                insertPic(groupId, multipartFile[i], name, "image");
                BlobUpload blobUploadEntity = new BlobUpload();
                blobUploadEntity.setFileName(multipartFile[i].getOriginalFilename());
                blobUploadEntity.setFileUrl(name);
                blobUploadEntity.setThumbnailUrl(name);
                list.add(blobUploadEntity);
            }
        }
        return new CommonResult().success(list);
    }

    @SysLog(MODULE = "图片上传管理" , REMARK = "上传文件")
    @ApiOperation("上传文件")
    @RequestMapping(value = "/uploadFile" , method = RequestMethod.POST)
    public Object uploadFile(Long groupId, String id, Long uid, String fileType, int type, @RequestPart("file") MultipartFile multipartFile) {
        List<BlobUpload> list = new ArrayList<>();
        String fileExtension = getFileExtension(multipartFile.getOriginalFilename()).toLowerCase();
        if (multipartFile != null) {
            if (!(fileType.equals("file") || fileType.equals("video"))) {
                return new CommonResult().failed("文件类型错误");
            }
            if (fileType.equals("file")) {
                if (!fileExtension.equals(".csv")) {
                    return new CommonResult().failed("文件格式错误");
                }
            }
            if (fileType.equals("video")) {
                if (!fileExtension.equals(".mp4")) {
                    return new CommonResult().failed("视频格式错误");
                }
            }
            String name = aliyunOSSUtil.upload(multipartFile);

            return new CommonResult().success(insertPic(groupId, multipartFile, name, fileType));

        }
        return new CommonResult().success(list);
    }

    private PmsAlbumPic insertPic(Long groupId, @RequestPart("file") MultipartFile multipartFile, String name, String type) {
        PmsAlbumPic attachment = new PmsAlbumPic();
        /*if (ValidatorUtils.notEmpty(UserUtils.getCurrentMember())) {
            attachment.setUserId(UserUtils.getCurrentMember().getId());
        }*/
        attachment.setAlbumId(groupId);
        attachment.setCreateTime(new Date());
        attachment.setName(multipartFile.getOriginalFilename());
        //  attachment.setStoreId(Long.parseLong("1"));
        attachment.setType(type);
        attachment.setPic(name);
        albumPicMapper.insert(attachment);
        return attachment;
    }

    @IgnoreAuth
    @PostMapping("/upload1")
    @ApiOperation("上传文件")
    public Object upload(@RequestParam("file") MultipartFile file) throws Exception {
        return new CommonResult().success(aliyunOSSUtil.upload(file));
    }

    @IgnoreAuth
    @PostMapping("/uploads1")
    @ApiOperation("多文件上传文件")
    public Object uploads(@RequestPart("file") MultipartFile[] file) throws Exception {
        StringBuffer stringBuffer = new StringBuffer();
        if (file != null && file.length > 0) {
            for (int i = 0; i < file.length; i++) {
                stringBuffer.append(aliyunOSSUtil.upload(file[i]) + ",");
            }
        }
        return new CommonResult().success(stringBuffer);
    }

    private String getFileExtension(String fileName) {
        int position = fileName.indexOf('.');
        if (position > 0) {
            String temp = fileName.substring(position);
            return temp;
        }
        return "";
    }
}
