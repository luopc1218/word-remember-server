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


    public Boolean addUser(User user) {
        userMapper.addUser(user.getName(), user.getEmail(), DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        return true;
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
