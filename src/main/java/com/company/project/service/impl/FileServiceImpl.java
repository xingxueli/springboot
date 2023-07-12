package com.company.project.service.impl;

import com.company.project.core.util.DateUtil;
import com.company.project.core.util.FileUtils;
import com.company.project.entity.File;
import com.company.project.dao.FileMapper;
import com.company.project.service.FileService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;


/**
 * <p>
 * 文件表 服务实现类
 * </p>
 *
 * @author pzblog
 * @since 2023-07-12
 */
@Service
public class FileServiceImpl extends ServiceImpl<FileMapper, File> implements FileService {

    @Autowired
    FileMapper fileMapper;

    public File buildResource(MultipartFile file) throws IOException {

        String path, fileName;

        //rename file 重命名
        String originalName = FileUtils.getOriginalFilename(file.getOriginalFilename());
        String newName = originalName + "_" + DateUtil.formatDateTime(new Date());
        fileName = FileUtils.rename(file, newName);
        //最后真实的相对路径（数据库中保存的路径）
        path = "root" + "/" + fileName;

        Long size = file.getSize();
        String format = FileUtils.getFormatString(file.getOriginalFilename());
        File tbFile = File.builder()
                .format(format)
                .originalName(file.getOriginalFilename())
                .name(fileName)
                .size(size)
                .path(path)
                .binData(file.getBytes())
                .build();
        save(tbFile);
        return tbFile;
    }
}
