package com.company.project.web;


import com.baomidou.mybatisplus.extension.api.Assert;
import com.company.project.core.execption.Asserts;
import com.company.project.dao.FileMapper;
import com.company.project.entity.File;
import com.company.project.entity.vo.FileListRequest;
import com.company.project.entity.vo.FileUploadModel;
import com.company.project.service.FileService;
import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * <p>
 * 文件表 前端控制器
 * </p>
 *
 * @author pzblog
 * @since 2023-07-12
 */
@RestController
@RequestMapping("/file")
@Slf4j
public class FileController {

    public static final String CONTENT_TYPE_MUTIPART = "multipart/form-data";

    @Autowired
    FileService fileService;
    /**
     * 批量上传文件
     *
     * @param fileUploadModel
     * @return
     */
    @PostMapping(value = "/upload" , consumes = CONTENT_TYPE_MUTIPART)
    public void uploadFile(FileUploadModel fileUploadModel) throws IOException {
        if (fileUploadModel == null) {
            log.info("fileUploadModel = null");
        }
        final MultipartFile[] files = fileUploadModel.getFiles();
        if (null == files || files.length <= 0) {
            Assert.fail("files can't be empty!");
        }
        for (MultipartFile file : files) {
            fileService.buildResource(file);
        }
    }

    @PostMapping(value = "/getFileList")
    public void getFileList(FileListRequest fileListRequest) throws IOException {
//        if (fileUploadModel == null) {
//            log.info("fileUploadModel = null");
//        }
//        final MultipartFile[] files = fileUploadModel.getFiles();
//        if (null == files || files.length <= 0) {
//            Assert.fail("files can't be empty!");
//        }
//        for (MultipartFile file : files) {
//            Blob blob = new Blob();
//            blob.setName(file.getName());
//            blob.setBinData(file.getBytes());
//            blobMapper.insert(blob);
//        }
    }

    @GetMapping(value = "/download")
    public void download(@RequestParam Long fileId, HttpServletResponse response) throws IOException {
        //文件的下载
        File file = fileService.getById(fileId);
        Preconditions.checkNotNull(file,"文件不存在");
        // 获得待下载文件所在文件夹的绝对路径
        String realPath =file.getPath();
        // 设置响应头、以附件形式打开文件
        response.setHeader("content-disposition", "attachment; fileName=" + URLEncoder.encode(file.getName(),"UTF-8"));
        //获得输出流对象
        ServletOutputStream outputStream = response.getOutputStream();
        byte[] data = file.getBinData();
        int len = data.length;
        outputStream.write(data, 0, len);
        outputStream.close();
    }
}
