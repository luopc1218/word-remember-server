package com.luopc1218.wordrememberserver.service;

import com.luopc1218.wordrememberserver.entity.user.User;
import com.luopc1218.wordrememberserver.repository.UserMapper;
import com.luopc1218.wordrememberserver.util.jwt.Jwt;
import com.luopc1218.wordrememberserver.entity.user.Password;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.Objects;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User getUserInfo(Integer id) throws RuntimeException {
        User user = userMapper.getUserById(id);
        if (user == null) {
            throw new RuntimeException("用户不存在");
        }
        return user;
    }

    public String signIn(Map<String, String> signInForm) throws RuntimeException {
        User user = userMapper.getUserByName(signInForm.get("name"));
        if (user == null) {
            throw new RuntimeException("用户不存在");
        } else {
            String encryptedPassword = new Password(signInForm.get("password")).getEncryptedPassword();
            Integer userId = user.getId();
            String basePassword = userMapper.getUserPasswordById(userId);
            if (!Objects.equals(basePassword, encryptedPassword)) {
                throw new RuntimeException("密码错误");
            }
            String jwtToken = Jwt.createJwtToken(user);
            return jwtToken;
        }
    }

    public void signUp(Map<String, String> signUpForm) throws RuntimeException {
        String name = signUpForm.get("name");
        if (userMapper.getUserByName(name) != null) {
            throw new RuntimeException("用户已存在");
        }
        String password = signUpForm.get("password");
        String phone = signUpForm.get("phone");
        String email = signUpForm.get("email");
        String avatarUrl = signUpForm.get("avatarUrl");
        User user = new User(signUpForm.get("name"), phone, email, avatarUrl);
        userMapper.addUser(user);
        userMapper.addUserPassword(user.getId(), password);
    }

    public void changeAvatar(Integer id, String url) throws RuntimeException {
        userMapper.changeAvatar(id, url);
    }

    public void changePassword(Integer id, Map<String, Object> changePasswordForm) throws RuntimeException {
        String encryptedPassword = new Password((String) changePasswordForm.get("password")).getEncryptedPassword();
        String basePassword = userMapper.getUserPasswordById(id);
        System.out.println(basePassword + '-' + encryptedPassword);
        if (!Objects.equals(basePassword, encryptedPassword)) {
            throw new RuntimeException("密码错误");
        }
        String encryptedNewPassword = new Password((String) changePasswordForm.get("newPassword"))
                .getEncryptedPassword();
        userMapper.changePassword(id, encryptedNewPassword);
    }
}
