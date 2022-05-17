package com.luopc1218.wordrememberserver.controller;

import com.luopc1218.wordrememberserver.entity.ApiResponse;
import com.luopc1218.wordrememberserver.entity.ApiResponseStatus;
import com.luopc1218.wordrememberserver.util.annotation.JsonWebTokenRequire;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;

@RestController
@RequestMapping(value = "/file")
public class FileController {
    @JsonWebTokenRequire
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ApiResponse upload(HttpServletRequest request, @RequestParam("file") MultipartFile file) {
        try {
            if (file.isEmpty() || file.getSize() == 0) {
                return ApiResponse.fail("文件为空");
            }
            String folderBase = "www.myfile.com";
            String folder = "E:\\uploadfiles";
            String filePath = folder + "/" + file.getOriginalFilename();
            File localFile = new File(filePath);
            file.transferTo(localFile);
            String resultPath = folderBase  + "/" + file.getOriginalFilename();//存储路径;
            return ApiResponse.success(resultPath,"上传成功");
        } catch (IOException e) {
            return ApiResponse.fail(e.getMessage());
        }
    }
}
