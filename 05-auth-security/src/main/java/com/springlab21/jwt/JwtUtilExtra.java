package com.springlab21.jwt;

import com.springlab21.jwt.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JwtUtilExtra {
    private static final long ACCESS_EXP = 1000 * 60 * 30; // 30분
    private static final long REFRESH_EXP = 1000 * 60 * 60 * 24 * 7; // 7일

    // Access 토큰 생성
    public static String createAccessToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_EXP))
                .signWith(JwtUtil.getSecretKey())
                .compact();
    }

    // Refresh 토큰 생성
    public static String createRefreshToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + REFRESH_EXP))
                .signWith(JwtUtil.getSecretKey())
                .compact();
    }
}