package com.luopc1218.wordrememberserver.util.minio;

import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.http.Method;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Component
public class Minio {
    static String bucketName = "word-remember";

    @Autowired
    private MinioClient minioClient;

    public String upload(MultipartFile file) {
        try {
            String originalFileName = file.getOriginalFilename();
            PutObjectArgs objectArgs = PutObjectArgs.builder().bucket(bucketName).object(originalFileName).contentType(file.getContentType()).stream(file.getInputStream(), file.getSize(), -1).build();
            minioClient.putObject(objectArgs);
            return minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder().method(Method.GET).bucket(bucketName).object(originalFileName).build());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
