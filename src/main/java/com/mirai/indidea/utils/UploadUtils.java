package com.mirai.indidea.utils;


import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UploadUtils {

    public final static String IMG_PATH_PREFIX = "static/uploads";

    private static File getImgDirFile(){

        String fileDirPath = new String("src/main/resources/" + IMG_PATH_PREFIX);

        File fileDir = new File(fileDirPath);
        if(!fileDir.exists()){
            // 递归生成文件夹
            fileDir.mkdirs();
        }
        return fileDir;
    }

    public static String Upload(MultipartFile file) {
        // 拿到文件名
        String filename = file.getOriginalFilename();
        assert filename != null;
        String suffixName = filename.substring(filename.lastIndexOf("."));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        //获取当前时间并作为时间戳
        String timeStamp = simpleDateFormat.format(new Date());
        filename = timeStamp + suffixName;  //使用时间戳
//        filename = UUID.randomUUID() + suffixName; //使用UUID

        // 存放上传图片的文件夹
        File fileDir = getImgDirFile();

        // 输出文件夹绝对路径  -- 这里的绝对路径是相当于当前项目的路径而不是“容器”路径
//        System.out.println(fileDir.getAbsolutePath());
        try {
            // 构建真实的文件路径
            File newFile = new File(fileDir.getAbsolutePath() + File.separator + filename);
            System.out.println(newFile.getAbsolutePath());

            // 上传图片到 -> “绝对路径”
            file.transferTo(newFile);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return filename;
    }

    public static boolean Delete(String filename) {
        File fileDir = getImgDirFile();
        File file = new File(fileDir.getAbsolutePath() + File.separator + filename);
        if(file.exists()) {
            return file.delete();
        } else {
            return false;
        }
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
