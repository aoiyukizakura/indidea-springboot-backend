package com.mirai.indidea.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class UploadUtil {

    public final static String IMG_PATH_PREFIX = "static/uploads";

    public static File getImgDirFile(){

        String fileDirPath = new String("src/main/resources/" + IMG_PATH_PREFIX);

        File fileDir = new File(fileDirPath);
        if(!fileDir.exists()){
            // 递归生成文件夹
            fileDir.mkdirs();
        }
        return fileDir;
    }

//    public static String Upload(MultipartFile file) {
//        if (file.isEmpty())
//            return null;
//        String fileName = file.getOriginalFilename();
//
//        assert fileName != null : null; //        断言失败则返回null
//        String suffixName = fileName.substring(fileName.lastIndexOf("."));
//        System.out.println(suffixName);
//        String filePath = "/uploads/";
//        fileName = UUID.randomUUID() + suffixName;
//        File dest = new File(filePath + fileName);
//        if (!dest.getParentFile().exists()) {
//            dest.getParentFile().mkdirs();
//        }
//        try {
//            file.transferTo(dest);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return fileName;
//    }

}
