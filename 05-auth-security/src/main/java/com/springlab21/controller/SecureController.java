package com.springlab21.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecureController {

    @GetMapping("/secure/hello")
    public String secureHello() {
        return "보호된 페이지: 로그인 성공 후 접근 가능!";
    }
}