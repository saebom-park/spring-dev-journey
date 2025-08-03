package com.review51.controller;

import com.review51.service.MemberService;
import com.review51.dto.MemberRequestDto;
import com.review51.dto.MemberResponseDto;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;

    // constructor
    public MemberController(MemberService memberService) { this.memberService = memberService; }

    @PostMapping("/register") // 경로 생략 가능 (Rest 규칙에 더 적합하고 간결)
    public void save(@RequestBody MemberRequestDto requestDto) {
        memberService.save(requestDto);
    }

    @GetMapping("/select") // 경로 생략 가능 (Rest 규칙에 더 적합하고 간결)
    public List<MemberResponseDto> findAll() {
        return memberService.findAll();
    }
}