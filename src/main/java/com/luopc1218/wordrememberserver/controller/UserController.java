package com.luopc1218.wordrememberserver.controller;

import com.luopc1218.wordrememberserver.entity.ApiResponse;
import com.luopc1218.wordrememberserver.entity.Password;
import com.luopc1218.wordrememberserver.entity.User;
import com.luopc1218.wordrememberserver.service.UserService;
import com.luopc1218.wordrememberserver.util.jwt.Jwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/signIn", method = RequestMethod.POST)
    public ApiResponse signIn(@RequestParam(value = "name") String name, @RequestParam(value = "password") String password) {
        try {
            User user = userService.getUserByName(name);
            if (user != null) {
                String encryptedPassword = new Password(password).getEncryptedPassword();
                Integer userId = user.getId();
                String basePassword = userService.getPasswordById(userId);
                if (!Objects.equals(basePassword, encryptedPassword)) {
                    return ApiResponse.fail("密码错误");
                }
                String jwtToken = Jwt.createJwtToken(user);
                return ApiResponse.success(jwtToken);
            } else {
                return ApiResponse.fail("用户不存在");
            }
        } catch (Exception e) {
            return ApiResponse.fail(e.getMessage());
        }
    }

    @RequestMapping(value = "/signUp", method = RequestMethod.POST)
    public ApiResponse signUp(@RequestParam(value = "name") String name, @RequestParam(value = "password") String password, @RequestParam(value = "email", required = false) String email, @RequestParam(value = "phone", required = false) String phone) {
        try {
            if (userService.getUserByName(name) != null) {
                return ApiResponse.fail("用户已存在");
            }
            String encryptedPassword = new Password(password).getEncryptedPassword();
            userService.addUser(new User(name, email, phone), encryptedPassword);
            return ApiResponse.success();
        } catch (Exception e) {
            return ApiResponse.fail(e.getMessage());
        }
    }
}
