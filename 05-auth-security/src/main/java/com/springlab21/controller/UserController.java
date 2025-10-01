package com.springlab21.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/user/profile")
    public String profile() {
        return "일반 사용자의 프로필 메세지";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/user/only-admin")
    public String onlyAdmin() {
        return "이건 관리자만 볼 수 있는 사용자 API";
    }

    @PreAuthorize("#username == authentication.name")
    @GetMapping("/user/{username}/profile")
    public String myProfile(@PathVariable String username) {
        return username + "님의 개인 프로필";
    }
}