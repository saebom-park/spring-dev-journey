package com.review32.controller;

import com.review32.dto.MemberRequestDto;
import com.review32.dto.MemberResponseDto;
import com.review32.service.MemberService;
import org.springframework.web.bind.annotation.*;

@RestController
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/members")
    public MemberResponseDto register(@RequestBody MemberRequestDto requestDto) {
        return memberService.register(requestDto);
    }
}