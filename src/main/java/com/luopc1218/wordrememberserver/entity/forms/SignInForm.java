package com.luopc1218.wordrememberserver.entity.forms;

import lombok.Data;

@Data
public class SignInForm {
    private String name;
    private  String password;

    public SignInForm(String name, String password) {
        this.name = name;
        this.password = password;
    }
}
