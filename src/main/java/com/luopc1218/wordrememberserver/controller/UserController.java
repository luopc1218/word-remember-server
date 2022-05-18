package com.luopc1218.wordrememberserver.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.luopc1218.wordrememberserver.entity.ApiResponse;
import com.luopc1218.wordrememberserver.entity.Password;
import com.luopc1218.wordrememberserver.entity.User;
import com.luopc1218.wordrememberserver.entity.forms.SignInForm;
import com.luopc1218.wordrememberserver.entity.forms.SignUpForm;
import com.luopc1218.wordrememberserver.service.UserService;
import com.luopc1218.wordrememberserver.util.annotation.JsonWebTokenRequire;
import com.luopc1218.wordrememberserver.util.jwt.Jwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @JsonWebTokenRequire
    @RequestMapping(value = "/checkSignIn", method = RequestMethod.GET)
    public ApiResponse checkSignIn(@RequestHeader("Authorization") String accessToken) {
        try {
            return ApiResponse.success(true);
        } catch (Exception e) {
            return ApiResponse.fail(e.getMessage());
        }
    }

    @JsonWebTokenRequire
    @RequestMapping(value = "/getUserInfo", method = RequestMethod.GET)
    public ApiResponse getUserInfo(@RequestHeader("Authorization") String accessToken) {
        try {
            DecodedJWT decodedJWT = Jwt.getJwtToken(accessToken);
            Integer userId = decodedJWT.getClaim("userId").asInt();
            User user = userService.getUserById(userId);
            if (user != null) {
                return ApiResponse.success(user);
            } else {
                return ApiResponse.fail("用户不存在");
            }
        } catch (Exception e) {
            return ApiResponse.fail(e.getMessage());
        }
    }


    @RequestMapping(value = "/signIn", method = RequestMethod.POST)
    public ApiResponse signIn(@RequestBody SignInForm signInForm) {
        try {
            User user = userService.getUserByName(signInForm.getName());
            if (user != null) {
                String encryptedPassword = new Password(signInForm.getPassword()).getEncryptedPassword();
                Integer userId = user.getId();
                String basePassword = userService.getPasswordById(userId);
                if (!Objects.equals(basePassword, encryptedPassword)) {
                    return ApiResponse.fail("密码错误");
                }
                String jwtToken = Jwt.createJwtToken(user);
                return ApiResponse.success(jwtToken, "登陆成功");
            } else {
                return ApiResponse.fail("用户不存在");
            }
        } catch (Exception e) {
            return ApiResponse.fail(e.getMessage());
        }
    }

    @RequestMapping(value = "/signUp", method = RequestMethod.POST)
    public ApiResponse signUp(@RequestBody SignUpForm signUpForm) {
        try {
            if (userService.getUserByName(signUpForm.getName()) != null) {
                return ApiResponse.fail("用户已存在");
            }
            String encryptedPassword = new Password(signUpForm.getPassword()).getEncryptedPassword();
            userService.addUser(new User(signUpForm.getName(), signUpForm.getPhone(), signUpForm.getEmail()), encryptedPassword);
            return ApiResponse.success("注册成功");
        } catch (Exception e) {
            return ApiResponse.fail(e.getMessage());
        }
    }
}
