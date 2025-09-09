package com.springlab21.controller;

import com.springlab21.jwt.JwtUtil;
import com.springlab21.dto.LoginRequest;
import com.springlab21.dto.LoginResponse;
import com.springlab21.dto.SecureResponse;
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

    @GetMapping("/secure")
    public SecureResponse secure(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        if (JwtUtil.validateToken(token)) {
            String username = JwtUtil.getUsername(token);
            return new SecureResponse("보호된 리소스 접근 성공", username);
        } else {
            return new SecureResponse("토큰이 유효하지 않습니다", null);
        }
    }

    @GetMapping("/secure2")
    public SecureResponse secure2(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        try {
            String username = JwtUtil.getUsername(token);
            return new SecureResponse("보호된 리소스 접근 성공", username);
        } catch (Exception e) {
            return new SecureResponse("토큰이 유효하지 않습니다", null);
        }
    }

}