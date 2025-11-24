package com.saebom.bulletinboard.repository;

import com.saebom.bulletinboard.domain.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class MemberMapperTest {

    @Autowired
    private MemberMapper memberMapper;

    @Test
    @DisplayName("Member insert 후 findById, findByUsername, findAll 동작 확인")
    void insertAndFindTest() {

        // given
        Member member = Member.createUser(
                "test_user",
                "{noop}encoded-password",
                "테스트유저",
                "test@example.com"
        );

        // when
        int inserted = memberMapper.insert(member);

        // then
        assertThat(inserted).isEqualTo(1);
        assertThat(member.getId()).isNotNull();

        // findById
        Member findById = memberMapper.findById(member.getId());
        assertThat(findById).isNotNull();
        assertThat(findById.getUsername()).isEqualTo("test_user");

        // findByUsername
        Member findByUsername = memberMapper.findByUsername("test_user");
        assertThat(findByUsername).isNotNull();
        assertThat(findByUsername.getId()).isEqualTo(member.getId());

        // findAll
        List<Member> all = memberMapper.findAll();
        assertThat(all).isNotEmpty();
        assertThat(all)
                .extracting(Member::getUsername)
                .contains("test_user");
    }
}