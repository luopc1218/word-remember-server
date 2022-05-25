package com.luopc1218.wordrememberserver.util.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.luopc1218.wordrememberserver.entity.user.User;

import java.util.Date;

public class Jwt {

    //    过期时间：12小时
    public static final long EXPIRE_TIME = 1000 * 60 * 60 * 12;
    public static final String IssuerName = "word-remember-server";
    public static final Algorithm algorithm = Algorithm.HMAC256(IssuerName);

    public static String createJwtToken(User user) {
        return JWT.create().withExpiresAt(new Date(System.currentTimeMillis() + EXPIRE_TIME)).withClaim("userId", user.getId()).withClaim("userName", user.getName()).withIssuer(IssuerName).sign(Jwt.algorithm);
    }

    public static Boolean checkJwtToken(String jwtToken) {
        try {
            JWTVerifier jwtVerifier = JWT.require(Jwt.algorithm).build();
            jwtVerifier.verify(jwtToken.split(" ")[1]);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static DecodedJWT getJwtToken(String jwtToken) {
        JWTVerifier jwtVerifier = JWT.require(Jwt.algorithm).build();
        return jwtVerifier.verify(jwtToken.split(" ")[1]);
    }

    public static Integer getUserId(String jwtToken) {
        if (Jwt.checkJwtToken(jwtToken)) {
            DecodedJWT decodedJWT = Jwt.getJwtToken(jwtToken);
            return decodedJWT.getClaim("userId").asInt();
        }
        return null;
    }
}
