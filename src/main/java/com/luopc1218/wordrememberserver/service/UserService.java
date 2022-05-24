package com.luopc1218.wordrememberserver.service;

import com.luopc1218.wordrememberserver.entity.user.User;
import com.luopc1218.wordrememberserver.entity.user.UserPasswordMap;
import com.luopc1218.wordrememberserver.repository.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public void addUser(User user, String password) {
        userMapper.addUser(user);
        userMapper.addUserPassword(user.getId(), password);
    }

    public User getUserById(Integer id) {
        return userMapper.getUserById(id);
    }

    public String getPasswordById(Integer id) {
        List<UserPasswordMap> result = userMapper.getUserPasswordById(id);
        return result.get(0).getPassword();
    }

    public User getUserByName(String name) {
        return userMapper.getUserByName(name);
    }

    public void changeAvatar(Integer id, String url) {
        userMapper.changeAvatar(id, url);
    }

    public void changePassword(Integer id, String newPassword) {
        userMapper.changePassword(id, newPassword);
    }
}
