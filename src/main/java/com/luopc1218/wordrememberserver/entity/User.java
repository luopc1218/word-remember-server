package com.luopc1218.wordrememberserver.entity;

import lombok.Data;

@Data
public class User {
    private Integer id;
    private String name;
    private String email;
    private String password;

    public User(Integer id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
