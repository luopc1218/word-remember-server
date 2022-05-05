package com.luopc1218.wordrememberserver.entity;

import lombok.Data;

@Data
public class User {
    private Integer id;
    private String name;
    private String phone;
    private String email;

    public User(String name, String phone,String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public User(Integer id, String name,String phone, String email) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }
}

