package com.luopc1218.wordrememberserver.controller;

import com.luopc1218.wordrememberserver.entity.request.ApiResponse;
import com.luopc1218.wordrememberserver.service.FileService;

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
    FileService fileService;

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ApiResponse upload(HttpServletRequest request, @RequestParam("file") MultipartFile file) {
        try {
            String fileUrl = fileService.upload(file);
            return ApiResponse.success(fileUrl, "上传成功");
        } catch (Exception e) {
            return ApiResponse.fail(e.getMessage());
        }
    }
}
