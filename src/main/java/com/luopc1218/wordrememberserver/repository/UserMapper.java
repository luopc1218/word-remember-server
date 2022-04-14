package com.luopc1218.wordrememberserver.repository;

import com.luopc1218.wordrememberserver.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("select id,name,email from users where id=#{id};")
    List<User> getUserById(Integer id);

    @Select("select id,name,email from users where name=#{name};")
    List<User> getUserByName(String name);

    @Insert("insert into users(name,email,password) value(#{name},#{email},#{password})")
    Boolean addUser(String name, String email, String password);
}
