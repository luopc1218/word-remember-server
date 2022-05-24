package com.luopc1218.wordrememberserver.entity.user;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    private Integer id;
    private String name;
    private String phone;
    private String email;
    private String avatarUrl;
    private Date createTime;


    public User(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public User(Integer id, String name, String phone, String email, String avatarUrl, Date createTime) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.avatarUrl = avatarUrl;
        this.createTime = createTime;
    }

    public User(String name, String phone, String email, String avatarUrl) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.avatarUrl = avatarUrl;
    }
}

