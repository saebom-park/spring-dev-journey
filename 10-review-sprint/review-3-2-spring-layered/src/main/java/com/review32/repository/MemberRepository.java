package com.review32.repository;

import com.review32.domain.Member;

import java.util.*;

public interface MemberRepository {
    void save(Member member);
    List<Member> findAll();
    Optional<Member> findById(Long id);
    void clearStore();
}