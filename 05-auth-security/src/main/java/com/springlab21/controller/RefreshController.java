package com.springlab21.controller;

import com.springlab21.jwt.JwtUtil;
import com.springlab21.jwt.JwtUtilExtra;
import com.springlab21.dto.LoginRequest;
import com.springlab21.dto.TokenResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/jwt-extra")
public class RefreshController {

    @PostMapping("/login")
    public TokenResponse createAccessToken(@RequestBody LoginRequest request) {
        if ("spring".equals(request.getUsername()) && "1234".equals(request.getPassword())) {
            String accessToken = JwtUtilExtra.createAccessToken(request.getUsername());
            String refreshToken = JwtUtilExtra.createRefreshToken(request.getUsername());
            return new TokenResponse("로그인 성공", accessToken, refreshToken);
        } else {
            return new TokenResponse("로그인 실패", null, null);
        }
    }

    @PostMapping("/refresh")
    public TokenResponse createRefreshToken(@RequestHeader("Authorization") String authHeader) {
        String refreshToken = authHeader.replace("Bearer ", "");
        if (JwtUtil.validateToken(refreshToken)) {
            String accessToken = JwtUtilExtra.createAccessToken(JwtUtil.getUsername(refreshToken));
            return new TokenResponse("재발급 성공", accessToken, refreshToken);
        } else {
            return new TokenResponse("재발급 실패", null, null);
        }
    }
}