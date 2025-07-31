package com.review33.repository;

import com.review33.domain.Member;
import java.util.*;

public interface MemberRepository {
    void save(Member member);
    List<Member> findAll();
    Optional<Member> findById(Long id);
}