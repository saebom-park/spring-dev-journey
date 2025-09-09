package com.springlab21.controller;

import com.springlab21.dto.LoginRequest;
import com.springlab21.dto.LoginResponse;
import com.springlab21.jwt.JwtUtil;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/jwt")
public class JwtLoginController {

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        if ("spring".equals(request.getUsername()) && "1234".equals(request.getPassword())) {
            String token = JwtUtil.createToken(request.getUsername());
            return new LoginResponse("로그인 성공", token);
        } else {
            return new LoginResponse("로그인 실패", null);
        }
    }

    @PostMapping("/secure")
    public LoginResponse secure(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        if (JwtUtil.validateToken(token)) {
            String username = JwtUtil.getUsername(token);
            return new LoginResponse("보호된 리소스 접근 성공", token);
        } else {
            return new LoginResponse("토큰이 유효하지 않습니다", null);
        }
    }

    @PostMapping("/secure2")
    public LoginResponse secure2(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        try {
            String username = JwtUtil.getUsername(token);
            return new LoginResponse("보호된 리소스 접근 성공", token);
        } catch (Exception e) {
            return new LoginResponse("토큰이 유효하지 않습니다.", null);
        }
    }
}