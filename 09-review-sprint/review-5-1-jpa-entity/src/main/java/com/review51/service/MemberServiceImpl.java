package com.review51.service;

import com.review51.repository.MemberRepository;
import com.review51.dto.MemberRequestDto;
import com.review51.dto.MemberResponseDto;
import com.review51.domain.Member;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

    // constructor
    public MemberServiceImpl(MemberRepository memberRepository) { this.memberRepository = memberRepository; }

    @Override
    public void save(MemberRequestDto requestDto) {
        Member member = new Member(requestDto.getName());
        memberRepository.save(member);
    }

    @Override
    public List<MemberResponseDto> findAll() {
        return memberRepository.findAll().stream().map(
                member -> new MemberResponseDto(member.getId(), member.getName())
        ).collect(Collectors.toList());
    }
}