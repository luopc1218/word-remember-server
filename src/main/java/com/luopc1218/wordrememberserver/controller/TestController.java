package com.luopc1218.wordrememberserver.controller;

import com.luopc1218.wordrememberserver.util.annotation.JsonWebTokenRequire;
import com.luopc1218.wordrememberserver.entity.ApiResponse.ApiResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
    @JsonWebTokenRequire
    @RequestMapping(value = "/testget", method = RequestMethod.GET)
    public ApiResponse testget() {
        return ApiResponse.success();
    }

    @JsonWebTokenRequire
    @RequestMapping(value = "/testpost", method = RequestMethod.POST)
    public ApiResponse testpost() {
        return ApiResponse.success();
    }
}
