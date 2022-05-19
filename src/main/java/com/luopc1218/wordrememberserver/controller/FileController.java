package com.luopc1218.wordrememberserver.controller;

import com.luopc1218.wordrememberserver.entity.ApiResponse;
import com.luopc1218.wordrememberserver.util.annotation.JsonWebTokenRequire;
import com.luopc1218.wordrememberserver.util.minio.Minio;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/file")
public class FileController {

    @Autowired
    Minio minio;

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ApiResponse upload(HttpServletRequest request, @RequestParam("file") MultipartFile file) {
        try {
            if (file.isEmpty() || file.getSize() == 0) {
                return ApiResponse.fail("文件为空");
            }
//            else if (file.getSize() >= 2048) {
//                return ApiResponse.fail("文件过大,请上传不大于2m的文件");
//            }
            String fileUrl = minio.upload(file);
            return ApiResponse.success(fileUrl, "上传成功");
        } catch (Exception e) {
            return ApiResponse.fail(e.getMessage());
        }
    }
}
