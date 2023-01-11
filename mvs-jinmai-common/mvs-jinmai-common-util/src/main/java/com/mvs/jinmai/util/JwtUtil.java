package com.mvs.jinmai.util;

import io.jsonwebtoken.*;

import java.util.Date;

public class JwtUtil {
    private static final String SECRET = "helloniuigedauabi";

    public static String createToken(String subject, long time) {
        return Jwts.builder().setSubject(subject).setExpiration(new Date(System.currentTimeMillis() + time)).signWith(SignatureAlgorithm.HS256, SECRET).compact();
    }

    public static String parserToken(String token) {
        return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody().getSubject();
    }

//    public static void main(String[] args) {
//        String subject = "hello";
//        String token = createToken(subject);
//        System.out.println(token);
//        token = createToken(subject);
//        System.out.println(token);
//    }
}
