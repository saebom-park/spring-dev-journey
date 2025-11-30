package com.saebom.bulletinboard.service;

import com.saebom.bulletinboard.domain.Member;
import com.saebom.bulletinboard.repository.MemberMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class MemberServiceImpl implements MemberService {

    private final MemberMapper memberMapper;

    public MemberServiceImpl(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    @Override
    @Transactional
    public Long registerMember(Member member) {

        Member existedMember = memberMapper.findByUsername(member.getUsername());
        if (existedMember != null) {
            throw new IllegalArgumentException("중복된 회원아이디 입니다. username=" + member.getUsername());
        }

        int inserted = memberMapper.insert(member);
        if (inserted != 1) {
            throw new IllegalStateException("회원 등록에 실패했습니다.");
        }

        return member.getId();
    }

    @Override
    public Member getMember(Long id) {

        Member member = memberMapper.findById(id);
        if (member == null) {
            throw new IllegalArgumentException("회원을 찾을 수 없습니다. id=" + id);
        }

        return member;
    }

    @Override
    public Member findByUsername(String username) {

        Member member = memberMapper.findByUsername(username);
        if (member == null) {
            throw new IllegalArgumentException("회원을 찾을 수 없습니다. username=" + username);
        }

        return member;
    }

    @Override
    public List<Member> getMembers() {
        return memberMapper.findAll();
    }

    @Override
    @Transactional
    public void updateMember(Member member) {

        int updated = memberMapper.update(member);
        if (updated != 1) {
            throw new IllegalStateException("회원 수정에 실패했습니다. username=" + member.getUsername());
        }
    }

    @Override
    @Transactional
    public void deleteMember(Long id) {

        int deleted = memberMapper.deleteById(id);
        if (deleted != 1) {
            throw new IllegalStateException("회원 삭제에 실패했습니다. id=" + id);
        }
    }
}