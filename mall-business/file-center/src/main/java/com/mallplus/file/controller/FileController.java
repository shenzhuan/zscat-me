package com.mallplus.file.controller;

import com.mallplus.common.model.PageResult;
import com.mallplus.common.model.Result;
import com.mallplus.file.config.OssServiceFactory;
import com.mallplus.file.model.FileInfo;
import com.mallplus.file.model.FileType;
import com.mallplus.file.service.IFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * 文件上传 同步oss db双写 目前仅实现了阿里云,七牛云
 * 参考src/main/view/upload.html
 *
 * @author 作者 mallplus E-mail: 951449465@qq.com
 */
@RestController
public class FileController {

    @Autowired
    private OssServiceFactory fileServiceFactory;

    /**
     * 文件上传
     * 根据fileType选择上传方式
     *
     * @param file
     * @return
     * @throws Exception
     */
    @PostMapping("/files-anon")
    public FileInfo upload(@RequestParam("file") MultipartFile file) throws Exception {
        String fileType = FileType.QINIU.toString();
        IFileService fileService = fileServiceFactory.getFileService(fileType);
        return fileService.upload(file);
    }

    /**
     * 文件删除
     *
     * @param id
     */
    @GetMapping("/files/{id}")
    public Result delete(@PathVariable String id) {
        try {
            FileInfo fileInfo = fileServiceFactory.getFileService(FileType.QINIU.toString()).getById(id);
            if (fileInfo != null) {
                IFileService fileService = fileServiceFactory.getFileService(fileInfo.getSource());
                fileService.removeById(fileInfo);
            }
            return Result.succeed("操作成功");
        } catch (Exception ex) {
            return Result.failed("操作失败");
        }
    }

    /**
     * 文件查询
     *
     * @param params
     * @return
     */
    @GetMapping("/files")
    public PageResult<FileInfo> findFiles(@RequestParam Map<String, Object> params) {
        return fileServiceFactory.getFileService(FileType.QINIU.toString()).findList(params);
    }
}
