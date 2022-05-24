package com.luopc1218.wordrememberserver.controller;

import com.luopc1218.wordrememberserver.entity.request.ApiResponse;
import com.luopc1218.wordrememberserver.entity.user.User;
import com.luopc1218.wordrememberserver.service.UserService;
import com.luopc1218.wordrememberserver.util.annotation.JsonWebTokenRequire;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @JsonWebTokenRequire
    @RequestMapping(value = "/checkSignIn", method = RequestMethod.GET)
    public ApiResponse checkSignIn(@RequestHeader("Authorization") String accessToken) {
        return ApiResponse.success(true);
    }

    @JsonWebTokenRequire
    @RequestMapping(value = "/getUserInfo", method = RequestMethod.GET)
    public ApiResponse getUserInfo(HttpServletRequest request, @RequestHeader("Authorization") String accessToken) {
        try {
            Integer userId = (Integer) request.getAttribute("CURRENT_USER_ID");
            User user = userService.getUserInfo(userId);
            return ApiResponse.success(user);
        } catch (Exception e) {
            return ApiResponse.fail(e.getMessage());
        }
    }

    @RequestMapping(value = "/signIn", method = RequestMethod.POST)
    public ApiResponse signIn(@RequestBody Map<String, String> signInForm) {
        try {
            String token = userService.signIn(signInForm);
            return ApiResponse.success(token, "登录成功");
        } catch (Exception e) {
            return ApiResponse.fail(e.getMessage());
        }
    }

    @RequestMapping(value = "/signUp", method = RequestMethod.POST)
    public ApiResponse signUp(@RequestBody Map<String, String> signUpForm) {
        try {
            userService.signUp(signUpForm);
            return ApiResponse.success("注册成功");
        } catch (Exception e) {
            return ApiResponse.fail(e.getMessage());
        }
    }

    @JsonWebTokenRequire
    @RequestMapping(value = "/changeAvatar", method = RequestMethod.POST)
    public ApiResponse changeAvatar(HttpServletRequest request, @RequestBody Map<String, Object> params) {
        try {
            Integer userId = (Integer) request.getAttribute("CURRENT_USER_ID");
            String url = (String) params.get("url");
            userService.changeAvatar(userId, url);
            return ApiResponse.success("修改成功");
        } catch (Exception e) {
            return ApiResponse.fail(e.getMessage());
        }
    }

    @JsonWebTokenRequire
    @RequestMapping(value = "/changePassword", method = RequestMethod.POST)
    public ApiResponse changePassword(HttpServletRequest request, @RequestBody Map<String, Object> changePasswordForm) {
        try {
            Integer userId = (Integer) request.getAttribute("CURRENT_USER_ID");
            userService.changePassword(userId, changePasswordForm);
            return ApiResponse.success("修改成功");
        } catch (Exception e) {
            return ApiResponse.fail(e.getMessage());
        }
    }
}
