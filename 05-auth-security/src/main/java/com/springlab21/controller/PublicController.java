package com.springlab21.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PublicController {

    @GetMapping("/public/hello")
    public String publicHello() {
        return "공개 페이지: 누구나 접근 가능!";
    }
}