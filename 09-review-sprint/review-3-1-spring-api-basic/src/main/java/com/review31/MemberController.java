package com.review31;

import org.springframework.web.bind.annotation.*;

@RestController
public class MemberController {
    private final MemberService memberService;

    // 생성자 주입 (스프링이 자동 연결)
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // 회원 등록 API
    @PostMapping("/members")
    public MemberResponseDto register(@RequestBody MemberRequestDto requestDto) {
        return memberService.register(requestDto);
    }
}