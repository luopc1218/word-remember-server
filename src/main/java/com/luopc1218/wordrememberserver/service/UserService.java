package com.luopc1218.wordrememberserver.service;

import com.luopc1218.wordrememberserver.entity.User;
import com.luopc1218.wordrememberserver.repository.UserMapper;
import org.apache.ibatis.annotations.Options;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;


    public void addUser(User user, String password) {
        /**
         * 通过 {@link Options} 配置了记录自增值后,mybatis会写入到到对象 {@param user}
         */
        userMapper.addUser(user);
        //  直接通过get读出来即可
        System.out.println(user.getId());
        userMapper.addUserPassword(user.getId(), password);
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

    public void example() {

    }
}
