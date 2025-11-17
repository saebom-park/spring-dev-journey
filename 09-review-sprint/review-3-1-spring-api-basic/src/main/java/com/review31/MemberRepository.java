package com.review31;

import org.springframework.stereotype.Repository;
import java.util.*;

@Repository // 이 클래스가 Repository 임을 스프링에 알림 (DI 대상)
public class MemberRepository {

    private final Map<Long, Member> store = new HashMap<>();
    private Long sequence = 0L; // ID 자동 증가용

    // 저장
    public Member save(Member member) {
        sequence++;
        member.setId(sequence);
        store.put(sequence, member);
        return member;
    }

    // 단건 조회
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    // 다건 조회
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }
    
    // 초기화 (테스트 용도)
    public void clearStore() {
        store.clear();
        sequence = 0L;
    }
}