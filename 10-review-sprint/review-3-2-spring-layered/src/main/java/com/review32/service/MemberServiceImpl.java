package com.review32.service;

import com.review32.domain.Member;
import com.review32.dto.MemberResponseDto;
import com.review32.repository.MemberRepository;
import com.review32.dto.MemberRequestDto;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public MemberResponseDto register(MemberRequestDto requestDto) {
        Member member = new Member(requestDto.getName(), requestDto.getEmail(), requestDto.getAge());
        memberRepository.save(member);
        return new MemberResponseDto(member.getId(), member.getName());
    }
}