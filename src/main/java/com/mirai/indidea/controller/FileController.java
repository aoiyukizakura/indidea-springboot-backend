package com.mirai.indidea.controller;

import com.mirai.indidea.dto.Result.ResultDto;
import com.mirai.indidea.utils.ResultUtils;
import com.mirai.indidea.utils.UploadUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/uploadFile")
public class FileController {
    @PostMapping()
    public ResultDto<Object> upload(@RequestParam("file") MultipartFile file) {
        if(file != null) {
            String fileName = UploadUtils.Upload(file);
            return ResultUtils.success(fileName);
        } else {
            return ResultUtils.fail();
        }

    }
}
