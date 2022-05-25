package com.luopc1218.wordrememberserver.controller;

import java.util.Map;

import com.luopc1218.wordrememberserver.entity.request.ApiResponse;
import com.luopc1218.wordrememberserver.entity.request.ApiResponseStatus;
import com.luopc1218.wordrememberserver.entity.request.Pagination;
import com.luopc1218.wordrememberserver.service.LexiconService;
import com.luopc1218.wordrememberserver.util.annotation.JsonWebTokenRequire;
import com.luopc1218.wordrememberserver.util.jwt.Jwt;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/lexicons")
public class LexiconsController {

    @Autowired
    private LexiconService lexiconsService;

    @RequestMapping(value = "/getLexiconList", method = RequestMethod.GET)
    public ApiResponse getUserLexicons(HttpServletRequest request,
                                       @RequestParam(value = "page", defaultValue = "1") Integer page,
                                       @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) {
        try {
            String jwtToken = request.getHeader("Authorization");
            Integer userId = Jwt.getUserId(jwtToken);
            Pagination pagination = new Pagination(page, pageSize);
            return ApiResponse.success(lexiconsService.getLexiconList(userId, pagination));
        } catch (Exception e) {
            return ApiResponse.fail(e.getMessage());
        }
    }

    @JsonWebTokenRequire
    @RequestMapping(value = "/createUserLexicon", method = RequestMethod.POST)
    public ApiResponse createUserLexicon(HttpServletRequest request,
                                         @RequestBody Map<String, Object> params) {
        try {
            Integer userId = (Integer) request.getAttribute("CURRENT_USER_ID");
            String name = (String) params.get("name");
            lexiconsService.createUserLexicon(userId, name);
            return ApiResponse.fail(ApiResponseStatus.DEVELOPING);
        } catch (Exception e) {
            return ApiResponse.fail(e.getMessage());
        }
    }

}
