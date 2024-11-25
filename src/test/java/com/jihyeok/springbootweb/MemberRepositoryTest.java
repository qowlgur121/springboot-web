package com.jihyeok.springbootweb;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;

    @AfterEach
    public void cleanUp() {
        memberRepository.deleteAll();
    }

    @Sql("/insert-members.sql") // 이 애노테이션을 사용하면 테스트 실행하기 전에 해당 SQL 스크립트를 실행할 수 있다.
    @Test
    void getAllMembers() {
        // when
        List<Member> members = memberRepository.findAll();

        // then
        Assertions.assertThat(members.size()).isEqualTo(3);
    }

    @Sql("/insert-members.sql")
    @Test
    void getMemberById() {
        // when
        Member member = memberRepository.findById(2L).get();

        // then
        Assertions.assertThat(member.getName()).isEqualTo("B");
    }

    @Sql("/insert-members.sql")
    @Test
    void getMemberByName() {
        // when
        Member member = memberRepository.findByName("C").get();

        // then
        Assertions.assertThat(member.getId()).isEqualTo(3);

    }

    @Test
    void saveMember() {
        // given
        Member member = new Member(1L, "A");

        // when
        memberRepository.save(member);

        // then
        Assertions.assertThat(memberRepository.findById(1L).get().getName()).isEqualTo("A");
    }

    @Test
    void saveMembers() {
        // given
        List<Member> members = List.of(new Member(2L, "B"),
                new Member(3L, "C"));

        // when
        memberRepository.saveAll(members);

        // then
        Assertions.assertThat(memberRepository.findAll().size()).isEqualTo(2);

    }

    @Sql("/insert-members.sql")
    @Test
    void deleteMemberById() {
        // when
        memberRepository.deleteById(2L);

        // then
        Assertions.assertThat(memberRepository.findById(2L).isEmpty()).isTrue();

    }

    @Sql("/insert-members.sql")
    @Test
    void deleteAll() {
        // when
        memberRepository.deleteAll();

        // then
        Assertions.assertThat(memberRepository.findAll().size()).isZero();

    }

    @Sql("/insert-members.sql")
    @Test
    void update() {
        // given
        Member member = memberRepository.findById(2L).get();

        // when
        member.changeName("BC");

        // then
        Assertions.assertThat(memberRepository.findById(2L).get().getName()).isEqualTo("BC");

    }
}

