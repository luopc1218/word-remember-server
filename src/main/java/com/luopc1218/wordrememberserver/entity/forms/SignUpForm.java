package com.luopc1218.wordrememberserver.entity.forms;

import lombok.Data;
@Data
public class SignUpForm {
    private String name;
    private  String password;
    private String phone;
    private String email;

    public SignUpForm(String name, String password, String phone, String email) {
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.email = email;
    }
}
