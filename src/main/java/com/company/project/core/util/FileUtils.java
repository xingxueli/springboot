package com.company.project.core.util;

import com.company.project.core.execption.Asserts;
import com.company.project.entity.vo.BusinessResultCode;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

@UtilityClass
public class FileUtils {

    /**
     * Convert a  given MultipartFile to a java.io.File for s3 upload
     * @param multipartFile the given MultipartFile
     * @return java.io.File
     */
    @SneakyThrows
    public static File convertMultipartFileToFile(MultipartFile multipartFile, String tempDir) {
        String tmpFileName =tempDir+ multipartFile.getOriginalFilename();

        File targetFile = new File(tmpFileName);
        multipartFile.transferTo(targetFile);
        return targetFile;
    }

    /**
     * temp dir
     * @param tempDir
     * @param suffix
     * @return
     */
    public static String getTempFilePath(String tempDir,String suffix) {

        Assert.notNull(tempDir, "tempDir can not be null.");
        Assert.isTrue(tempDir.endsWith("/"), "tempDir must end with /");
        Assert.isTrue(new File(tempDir).exists(), "tempDir must exist.");

        return tempDir + "tmpFile" +ThreadLocalRandom.current().nextLong() +"."+suffix;
    }
    public static String getFileSuffix(String fileName){
        int suffixIndex = fileName.lastIndexOf(".");
        if(suffixIndex>0){
            return fileName.substring(suffixIndex);
        }
        return "";
    }
    /**
     * @param path 目标文件完整路径（含文件名）
     * @param file
     */
    public static void upload2Server(String path,MultipartFile file){
        if(file.isEmpty()){
            Asserts.fail(BusinessResultCode.INVALID_PARAM,"file can't be empty！");
        }

        //目标文件
        File dest = new File(path);
        //创建父目录
        FileUtils.recurMkdir(dest);

        try {
            file.transferTo(dest); //保存文件
        } catch (IllegalStateException e) {
            e.printStackTrace();
            Asserts.fail(BusinessResultCode.FAIL_UPLOAD_FILE,e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            Asserts.fail(BusinessResultCode.FAIL_UPLOAD_FILE,e.getMessage());
        }
    }

    /**
     * 递归创建父目录
     * @param file
     */
    private static void recurMkdir(File file){
        //父目录不存在
        if(!file.getParentFile().exists()){
            FileUtils.recurMkdir(file.getParentFile());
            file.getParentFile().mkdir();
        }
    }

    public static String getFormatString(String fileName){
        Asserts.notNull(fileName,"file name can't be empty!");
        int index = fileName.lastIndexOf(".");
        if(index == -1){
            Asserts.fail(BusinessResultCode.FAIL_UPLOAD_FILE,"not support file format for file name:"+fileName);
        }
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    public static String getOriginalFilename(String fileName){
        Asserts.notNull(fileName,"file name can't be empty!");
        int index = fileName.lastIndexOf(".");
        if(index == -1){
            Asserts.fail(BusinessResultCode.FAIL_UPLOAD_FILE,"not support file format for file name:"+fileName);
        }
        return fileName.substring(0,fileName.lastIndexOf("."));
    }

    public static String rename(MultipartFile file,String newName){
        String originalName = file.getOriginalFilename();
        String format = getFormatString(originalName);
        newName = newName + "." + format;
        return newName;
    }

    public static Boolean fileExists(String path){
        File file = new File(path);
        return file.exists();
    }
}
