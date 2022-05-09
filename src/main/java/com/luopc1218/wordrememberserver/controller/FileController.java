package com.luopc1218.wordrememberserver.controller;

import com.luopc1218.wordrememberserver.entity.ApiResponse;
import com.luopc1218.wordrememberserver.entity.ApiResponseStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/file")
public class FileController {
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ApiResponse upload() {
        return ApiResponse.fail(ApiResponseStatus.DEVELOPING);
    }
}
