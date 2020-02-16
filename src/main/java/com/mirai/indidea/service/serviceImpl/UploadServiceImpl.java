package com.mirai.indidea.service.serviceImpl;

import com.mirai.indidea.service.UploadService;
import com.mirai.indidea.utils.UploadUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class UploadServiceImpl implements UploadService {
    @Override
    public String Upload(MultipartFile file) {
        // 拿到文件名
        String filename = file.getOriginalFilename();

        assert filename != null;
        String suffixName = filename.substring(filename.lastIndexOf("."));
        filename = UUID.randomUUID() + suffixName;

        // 存放上传图片的文件夹
        File fileDir = UploadUtil.getImgDirFile();
        // 输出文件夹绝对路径  -- 这里的绝对路径是相当于当前项目的路径而不是“容器”路径
        System.out.println(fileDir.getAbsolutePath());

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
}
