package com.saebom.bulletinboard.service;

import com.saebom.bulletinboard.domain.Member;

import java.util.List;

public interface MemberService {

    boolean isUsernameDuplicate(String username);
    Long registerMember(Member member);

    Member getMember(Long id);
    Member findByUsername(String username);
    List<Member> getMembers();

    void updateMember(Member member);
    void deleteMember(Long id);

}