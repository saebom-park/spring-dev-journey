package com.review33.service;

import org.springframework.stereotype.*;
import com.review33.domain.*;
import com.review33.dto.*;
import com.review33.repository.*;
import java.util.*;

@Service
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

    // constructor
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public MemberResponseDto register(MemberRequestDto requestDto) {
        Member member = new Member(requestDto.getName(), requestDto.getEmail(), requestDto.getAge());
        memberRepository.save(member);
        return new MemberResponseDto(member.getId(), member.getName(), member.getEmail(), member.getAge());
    }

    @Override
    public List<MemberResponseDto> getMembers() {
        List<Member> members = memberRepository.findAll();
        List<MemberResponseDto> responseMembers = new ArrayList<>();
        for (Member member : members) {
            MemberResponseDto responseDto = new MemberResponseDto(member.getId(), member.getName(), member.getEmail(), member.getAge());
            responseMembers.add(responseDto);
        }
        return responseMembers;
    }

    @Override
    public MemberResponseDto getMember(Long id) {
        Optional<Member> optionalMember = memberRepository.findById(id);
        Member member = optionalMember.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
        return new MemberResponseDto(member.getId(), member.getName(), member.getEmail(), member.getAge());
    }
}