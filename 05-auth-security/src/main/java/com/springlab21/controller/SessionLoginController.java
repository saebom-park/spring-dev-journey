package com.springlab21.controller;

import com.springlab21.dto.LoginRequest;
import com.springlab21.dto.LoginResponse;
import com.springlab21.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/session")
public class SessionLoginController {
    private UserService userService;

    // constructor
    public SessionLoginController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request, HttpSession session) {
        boolean authenticated = userService.authenticate(request.getUsername(), request.getPassword());

        if (authenticated) {
            session.setAttribute("username", request.getUsername());
            return new LoginResponse("로그인 성공", request.getUsername());
        } else {
            return new LoginResponse("로그인 실패", null);
        }
    }

    @PostMapping("/check")
    public LoginResponse check(HttpSession session) {
        String username = (String) session.getAttribute("username");

        if (username == null) {
            return new LoginResponse("로그인 필요", null);
        }
        return new LoginResponse("현재 로그인 사용자", username);
    }

    @PostMapping("/logout")
    public LoginResponse logout(HttpSession session) {
        session.invalidate();
        return new LoginResponse("로그아웃 완료", null);
    }
}