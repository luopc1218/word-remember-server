package com.luopc1218.wordrememberserver.controller;

import com.luopc1218.wordrememberserver.entity.ApiResponse;
import com.luopc1218.wordrememberserver.entity.Password;
import com.luopc1218.wordrememberserver.entity.User;
import com.luopc1218.wordrememberserver.entity.forms.SignUpForm;
import com.luopc1218.wordrememberserver.service.UserService;
import com.luopc1218.wordrememberserver.util.annotation.JsonWebTokenRequire;
import com.luopc1218.wordrememberserver.util.jwt.Jwt;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
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
    public ApiResponse getUserInfo(HttpServletRequest request, @RequestHeader("Authorization") String accessToken) {
        try {
            Integer userId = (Integer) request.getAttribute("CURRENT_USER_ID");
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
    public ApiResponse signIn(@RequestBody Map<String, String> signInForm) {
        try {
            User user = userService.getUserByName(signInForm.get("name"));
            if (user != null) {
                String encryptedPassword = new Password(signInForm.get("password")).getEncryptedPassword();
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
    public ApiResponse signUp(@RequestBody Map<String, String> signUpForm) {
        String name = signUpForm.get("name");
        String password = signUpForm.get("password");
        String phone = signUpForm.get("phone");
        String email = signUpForm.get("email");
        String avatarUrl = signUpForm.get("avatarUrl");
        try {
            if (userService.getUserByName(name) != null) {
                return ApiResponse.fail("用户已存在");
            }
            String encryptedPassword = new Password(password).getEncryptedPassword();
            userService.addUser(new User(signUpForm.get("name"), phone, email,avatarUrl), encryptedPassword);
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
}
