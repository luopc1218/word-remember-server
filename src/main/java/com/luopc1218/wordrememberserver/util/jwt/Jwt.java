package com.luopc1218.wordrememberserver.util.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;

public class Jwt {

    //    过期时间：12小时
    public static final long EXPIRE_TIME = 1000 * 60 * 60 * 12;
    public static Algorithm algorithm = Algorithm.HMAC256("word-remember-server");


    public static String createJwtToken(String id) {
        return JWT.create().withExpiresAt(new Date()).withAudience(id).sign(Jwt.algorithm);
    }

    public static Boolean checkJwtToken(String jwtToken) {
        return false;
    }
}
