package com.springlab22.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class TestController {

    @GetMapping("/public/hello")
    public String publicPage() {
        return "Hello Public!";
    }

    @GetMapping("/user/hello")
    public String userPage() {
        return "Hello User!";
    }

    @GetMapping("/admin/hello")
    public String adminPage() {
        return "hello Admin!";
    }
}