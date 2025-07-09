package com.springlab9;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/users")
public class UserSearchController {
    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable Long id) {
        return new UserDto(id, "봄이", "spring@dev.com");
    }

    @GetMapping("/search")
    public String search(@RequestParam String keyword) {
        return "검색어: " + keyword;
    }
}