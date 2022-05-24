package com.luopc1218.wordrememberserver.controller;

import com.luopc1218.wordrememberserver.entity.request.ApiResponse;
import com.luopc1218.wordrememberserver.entity.request.Pagination;
import com.luopc1218.wordrememberserver.service.LexiconService;
import com.luopc1218.wordrememberserver.util.annotation.JsonWebTokenRequire;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/lexicons")
public class LexiconsController {

    @Autowired
    private LexiconService lexiconsService;

    @JsonWebTokenRequire
    @RequestMapping(value = "/getUserLexicons", method = RequestMethod.GET)
    public ApiResponse getUserLexicons(HttpServletRequest request,
            @RequestParam(value = "page", defaultValue = "1") String page,
            @RequestParam(value = "pageSize", defaultValue = "20") String pageSize) {
        try {
            Pagination pagination = new Pagination(page, pageSize);
            Integer userId = (Integer) request.getAttribute("CURRENT_USER_ID");
            return ApiResponse.success(lexiconsService.getUserLexicons(userId, pagination));
        } catch (Exception e) {
            return ApiResponse.fail(e.getMessage());
        }
    }
}
