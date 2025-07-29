package com.review31;

import org.springframework.stereotype.Service;

// 이 클래스가 비즈니스 로직을 담당하는 계층임을 스프링에게 알림
@Service
public class MemberService {
    private final MemberRepository memberRepository;

    // 생성자 주입 (Spring이 자동으로 Repository 주입해줌)
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 회원 등록 메서드
    public MemberResponseDto register(MemberRequestDto requestDto) {
        // 1. 요청 DTO → 엔티티로 변환
        Member member = new Member(requestDto.getName(), requestDto.getEmail(), requestDto.getAge());

        // 2. 저장소에 저장
        Member saved = memberRepository.save(member);

        // 3. 응답용 DTO 생성
        return new MemberResponseDto(saved.getId(), saved.getName());
    }

}