package com.saebom.bulletinboard.service;

import com.saebom.bulletinboard.domain.Member;

import java.util.List;

public interface MemberService {

    boolean isUsernameDuplicate(String username);
    Long registerMember(Member member);

    Long loginMember(String username, String password);

    Member getMember(Long id);
    Member findByUsername(String username);
    List<Member> getMembers();

    void updateMember(Member member);
    void validatePassword(Long id, String rawPassword);
    void updatePassword(Long id, String newPassword);
    void deleteMember(Long id);

}