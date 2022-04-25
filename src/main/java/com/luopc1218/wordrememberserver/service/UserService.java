package com.luopc1218.wordrememberserver.service;

import com.luopc1218.wordrememberserver.entity.User;
import com.luopc1218.wordrememberserver.repository.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;


    public void addUser(User user, String password) {
        Integer userId = userMapper.addUser(user.getName(), user.getEmail(), user.getPhone());
        System.out.println(userId);
//        userMapper.addUserPassword(userId, user.getPassword());
    }

    public User getUserById(Integer id) {
        List<User> result = userMapper.getUserById(id);
        if (result.isEmpty()) {
            return null;
        } else {
            return result.get(0);
        }
    }

    public User getUserByName(String name) {
        List<User> result = userMapper.getUserByName(name);
        if (result.isEmpty()) {
            return null;
        } else {
            return result.get(0);
        }
    }
}