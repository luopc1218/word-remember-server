package com.luopc1218.wordrememberserver.controller;

import com.luopc1218.wordrememberserver.entity.ApiResponse;
import com.luopc1218.wordrememberserver.entity.ApiResponseStatus;
import com.luopc1218.wordrememberserver.util.annotation.JsonWebTokenRequire;
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
    public ApiResponse upload(@RequestParam("file") MultipartFile file) {
        try {
            String folder = "D:\\uploadfiles";
            String filePath = folder + "/" + file.getOriginalFilename();
            File localFile = new File(filePath);
            file.transferTo(localFile);
            return ApiResponse.success(filePath);
        } catch (IOException e) {
            return ApiResponse.fail(e.getMessage());
        }
    }
}
