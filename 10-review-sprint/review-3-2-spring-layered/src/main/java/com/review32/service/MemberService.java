package com.review32.service;

import com.review32.dto.MemberRequestDto;
import com.review32.dto.MemberResponseDto;

public interface MemberService {
    MemberResponseDto register(MemberRequestDto requestDto);
}