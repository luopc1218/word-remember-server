package com.luopc1218.wordrememberserver.entity.user;
import lombok.Data;
import org.springframework.util.DigestUtils;

@Data
public class Password {
    private String password;

    public Password(String password) {
        this.password = password;
    }

    public String getEncryptedPassword() {
        return DigestUtils.md5DigestAsHex(this.password.getBytes());
    }
}
