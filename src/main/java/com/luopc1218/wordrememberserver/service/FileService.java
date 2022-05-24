package com.luopc1218.wordrememberserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.luopc1218.wordrememberserver.util.minio.Minio;

@Service
public class FileService {
    @Autowired
    Minio minio;

    public String upload(MultipartFile file) throws RuntimeException {
        if (file.isEmpty() || file.getSize() == 0) {
            throw new RuntimeException("文件为空");
        }
        // else if (file.getSize() >= 2048) {
        // throw new RuntimeException("文件过大,请上传不大于2m的文件");
        // }
        return minio.upload(file);
    }
}
