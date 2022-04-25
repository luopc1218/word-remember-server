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

    @Insert("insert into user(name,email,phone) value(#{user.name},#{user.email},#{user.phone});")
    @Options(keyColumn = "id", useGeneratedKeys = true, keyProperty = "id")
        //  执行insert的语句,返回的integer是数据库实际新增的数据数量,不是新增的id
        //  然后用标准类型就可以了
    void addUser(@Param("user") User user);

    @Insert("insert into user_password(id,password) value(#{id},#{password});")
    void addUserPassword(Integer id, String password);

    @Select("select password from user_password where id=#{id};")
    List<UserPasswordMap> getUserPasswordById(Integer id);
}
