package com.luopc1218.wordrememberserver.repository;

import com.luopc1218.wordrememberserver.entity.User;
import com.luopc1218.wordrememberserver.entity.UserPasswordMap;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("select id,name,email from user where id=#{id};")
    List<User> getUserById(Integer id);

    @Select("select * from user where name=#{name};")
    List<User> getUserByName(String name);

    @Insert("insert into user(name,email,phone) value(#{name},#{email},#{phone});")
    @Options(keyColumn = "id", useGeneratedKeys = true, keyProperty = "param1")
    Integer addUser(@Param("name") String name, @Param("email") String email, @Param("phone") String phone);

    @Insert("insert into user_password(id,password) value(#{id},#{password});")
    void addUserPassword(Integer id, String password);
}
