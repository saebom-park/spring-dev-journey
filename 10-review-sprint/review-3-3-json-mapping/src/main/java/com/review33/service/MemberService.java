package com.review33.service;

import com.review33.dto.*;
import java.util.List;

public interface MemberService {
    MemberResponseDto register(MemberRequestDto requestDto);
    List<MemberResponseDto> getMembers();
    MemberResponseDto getMember(Long id);
}