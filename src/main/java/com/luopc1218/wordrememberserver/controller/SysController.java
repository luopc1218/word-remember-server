package com.luopc1218.wordrememberserver.controller;

import com.luopc1218.wordrememberserver.entity.request.ApiResponse;
import com.luopc1218.wordrememberserver.service.SysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SysController {

    @Autowired
    private SysService sysService;

    @RequestMapping(value = "/getSysConfig", method = RequestMethod.GET)
    public ApiResponse getSysConfig() {
        try {
            return ApiResponse.success(sysService.getSysConfig());
        } catch (Exception e) {
            return ApiResponse.fail(e.getMessage());
        }
    }
}
