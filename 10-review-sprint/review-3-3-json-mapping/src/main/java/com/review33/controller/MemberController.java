package com.review33.controller;

import com.review33.domain.*;
import com.review33.service.*;
import com.review33.dto.*;

import java.util.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;

    // constructor
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/register")
    public MemberResponseDto register(@RequestBody MemberRequestDto requestDto) {
        return memberService.register(requestDto);
    }

    @GetMapping("/find")
    public List<MemberResponseDto> findMembers() {
        return memberService.getMembers();
    }

    @GetMapping("/{id}")
    public MemberResponseDto findMember(@PathVariable Long id) {
        return memberService.getMember(id);
    }
}