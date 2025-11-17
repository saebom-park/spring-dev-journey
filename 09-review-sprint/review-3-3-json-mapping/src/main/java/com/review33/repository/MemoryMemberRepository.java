package com.review33.repository;

import com.review33.domain.Member;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public class MemoryMemberRepository implements MemberRepository {
    private Long sequence = 0L;
    private Map<Long, Member> store = new HashMap<>();

    @Override
    public void save(Member member) {
        sequence++;
        member.setId(sequence);
        store.put(sequence, member);
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

}