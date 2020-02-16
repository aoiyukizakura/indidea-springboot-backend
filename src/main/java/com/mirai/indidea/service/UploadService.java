package com.mirai.indidea.service;

import org.springframework.web.multipart.MultipartFile;

public interface UploadService {
    String Upload(MultipartFile file);
}
