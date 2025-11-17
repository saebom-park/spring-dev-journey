package com.review32.repository;

import com.review32.domain.Member;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public class MemoryMemberRepository implements MemberRepository {
    private Long sequence = 0L;
    private Map<Long, Member> store = new HashMap<>();

    public void save(Member member) {
        sequence++;
        member.setId(sequence);
        store.put(sequence, member);
    }

    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    public void clearStore() {
        sequence = 0L;
        store.clear();
    }
}