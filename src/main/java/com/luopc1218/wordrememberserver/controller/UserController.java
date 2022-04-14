package com.luopc1218.wordrememberserver.controller;

import com.luopc1218.wordrememberserver.entity.ApiResponseStatus;
import com.luopc1218.wordrememberserver.entity.ApiResponse;
import com.luopc1218.wordrememberserver.entity.User;
import com.luopc1218.wordrememberserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/getUserById", method = RequestMethod.POST)
    public ApiResponse signUp(@RequestParam(value = "id", required = true) Integer id) {
        if (userService.getUserById(id) == null) {
            return ApiResponse.fail("用户不存在");
        }
        return ApiResponse.success(userService.getUserById(id));
    }

    @RequestMapping(value = "/signUp", method = RequestMethod.POST)
    public ApiResponse signUp(@RequestParam(value = "name", required = true) String name, @RequestParam(value = "password", required = true) String password, @RequestParam(value = "email", required = false) String email) {
        try {
            if (userService.getUserByName(name) != null) {
                return ApiResponse.fail("用户已存在");
            }
            userService.addUser(new User(name, email, DigestUtils.md5DigestAsHex(password.getBytes())));
            return ApiResponse.success();
        } catch (Exception e) {
            return ApiResponse.fail(e.getMessage());
        }
    }
}
