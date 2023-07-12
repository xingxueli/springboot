package com.company.project.service;

import com.company.project.entity.File;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * <p>
 * 文件表 服务类
 * </p>
 *
 * @author pzblog
 * @since 2023-07-12
 */
public interface FileService extends IService<File> {
    File buildResource(MultipartFile file) throws IOException;
}
