package com.review51.service;

import com.review51.dto.MemberRequestDto;
import com.review51.dto.MemberResponseDto;
import java.util.List;

public interface MemberService {
    void save(MemberRequestDto requestDto);
    List<MemberResponseDto> findAll();
}