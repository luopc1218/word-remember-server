package com.luopc1218.wordrememberserver.controller;

import com.luopc1218.wordrememberserver.entity.ApiResponse.ApiResponse;
import com.luopc1218.wordrememberserver.entity.ApiResponse.ApiResponseStatus;
import com.luopc1218.wordrememberserver.util.annotation.JsonWebTokenRequire;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/lexicons")
public class LexiconsController {
    @JsonWebTokenRequire
    @RequestMapping(value = "/getUserLexicons", method = RequestMethod.GET)
    public ApiResponse getUserLexicons(HttpServletRequest request) {
        try {
            Integer userId = (Integer) request.getAttribute("CURRENT_USER_ID");
            return ApiResponse.fail(ApiResponseStatus.DEVELOPING);
        } catch (Exception e) {
            return ApiResponse.fail(e.getMessage());
        }
    }
}
