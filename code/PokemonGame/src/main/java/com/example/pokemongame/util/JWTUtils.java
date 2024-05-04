package com.example.pokemongame.util;

import com.mysql.jdbc.StringUtils;
import io.jsonwebtoken.*;

import java.util.Date;

/**
 * 这里是与token有关的工具类，但是不会用token，所以就没有使用
 */
public class JWTUtils {

    private static int tokenExpiration = 24 * 60 * 60 * 1000; // 这里设置token有效期 为 1天
    private static String tokenSignKey = "wuming@040223";//密钥

    /**
     * 此方法可以指定JWT中存放用户的哪些数据信息，添加参数就可以
     */
    public static String createToken(String username) {
        String token = Jwts.builder()
                .setSubject("AUTH-USER")
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpiration))
                .claim("username", username)
                .signWith(SignatureAlgorithm.HS512, tokenSignKey)
                .compressWith(CompressionCodecs.GZIP)
                .compact();
        return token;
    }

    public static boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static <T> T getData(String token, String dataName, Class<T> clazz) {
        try {
            if (StringUtils.isNullOrEmpty(token)) return null;
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token);
            Claims claims = claimsJws.getBody();
            return (T) claims.get(dataName);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static Integer getUserId(String token) {
        try {
            if (StringUtils.isNullOrEmpty(token)) return null;
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token);
            Claims claims = claimsJws.getBody();
            Integer userId = (Integer) claims.get("userId");
            return userId.intValue();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static String getUsername(String token) {
        try {
            if (StringUtils.isNullOrEmpty(token)) return "";
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token);
            Claims claims = claimsJws.getBody();
            return (String) claims.get("username");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}