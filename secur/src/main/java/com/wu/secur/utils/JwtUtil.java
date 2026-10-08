package com.wu.secur.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

public class JwtUtil {

    /**
     * JWT 签名密钥
     *
     * HS256 至少需要 256 bit，也就是 32 字节。
     * 学习阶段可以先这样写，正式项目不要把密钥硬编码在源码中。
     */
    private static final String SECRET =
            "wu-security-jwt-secret-key-1234567890";

    /**
     * 默认有效期：24小时
     */
    private static final long DEFAULT_TTL = 24 * 60 * 60 * 1000L;

    private static final SecretKey KEY =
            Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));

    /**
     * 生成 JWT
     */
    public static String createJWT(String subject) {
        return createJWT(subject, DEFAULT_TTL);
    }

    /**
     * 生成 JWT
     *
     * @param subject   JWT 中保存的主体信息，一般可以放 userId/loginId
     * @param ttlMillis JWT 有效时间，单位毫秒
     */
    public static String createJWT(String subject, long ttlMillis) {

        long nowMillis = System.currentTimeMillis();

        Date now = new Date(nowMillis);
        Date expiration = new Date(nowMillis + ttlMillis);

        return Jwts.builder()
                .subject(subject)
                .issuedAt(now)
                .expiration(expiration)
                .signWith(KEY)
                .compact();
    }

    /**
     * 解析 JWT
     */
    public static Claims parseJWT(String jwt) {

        return Jwts.parser()
                .verifyWith(KEY)
                .build()
                .parseSignedClaims(jwt)
                .getPayload();
    }
}