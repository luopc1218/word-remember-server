package com.luopc1218.wordrememberserver.repository;

import org.apache.ibatis.annotations.*;
import com.luopc1218.wordrememberserver.entity.user.User;

@Mapper
public interface UserMapper {
        @Results(value = {
                        @Result(property = "id", column = "id", id = true),
                        @Result(property = "name", column = "name"),
                        @Result(property = "phone", column = "phone"),
                        @Result(property = "email", column = "email"),
                        @Result(property = "avatarUrl", column = "avatarUrl"),
                        @Result(property = "createTime", column = "createTime"),
        })
        @Select("select id,name,phone,email,avatarUrl,createTime from user where id=#{id};")
        User getUserById(Integer id);

        @Results(value = {
                        @Result(property = "id", column = "id", id = true),
                        @Result(property = "name", column = "name"),
                        @Result(property = "phone", column = "phone"),
                        @Result(property = "email", column = "email"),
                        @Result(property = "avatarUrl", column = "avatarUrl"),
                        @Result(property = "createTime", column = "createTime"),
        })
        @Select("select * from user where name=#{name};")
        User getUserByName(String name);

        @Insert("insert into user(name,email,phone,avatarUrl) value(#{user.name},#{user.email},#{user.phone},#{user.avatarUrl});")
        @Options(keyColumn = "id", useGeneratedKeys = true, keyProperty = "id")
        // 执行insert的语句,返回的integer是数据库实际新增的数据数量,不是新增的id
        // 然后用标准类型就可以了
        Integer addUser(@Param("user") User user);

        @Insert("insert into user_password(id,password) value(#{id},#{password});")
        void addUserPassword(Integer id, String password);

        @Select("select password from user_password where id=#{id};")
        String getUserPasswordById(Integer id);

        @Update("update user set avatarUrl=#{url} where id=#{id};")
        void changeAvatar(Integer id, String url);

        @Update("update user_password set password=#{newPassword} where id=#{id}")
        void changePassword(Integer id, String newPassword);
}
