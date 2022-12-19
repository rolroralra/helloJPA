package org.example.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;
import org.example.domain.Address;
import org.example.domain.Member;
import org.example.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class MemberServiceTest {
    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @DisplayName("회원가입 테스트")
    @Test
    void join() {
        // Given
        Member member = Member.builder()
            .name("rolroralra")
            .address(Address.builder().city("Seoul").street("Songpa").zipCode("11023").build())
            .build();

        // When
        Long savedId = memberService.join(member);
        Optional<Member> findById = memberRepository.findById(savedId);

        // Then
        assertThat(findById)
            .isPresent()
            .get()
            .isNotNull()
            .isEqualTo(member);
    }

    @DisplayName("회원 목록 조회 테스트")
    @Test
    void findMembers() {
        // Given
        Member member = Member.builder()
            .name("rolroralra")
            .address(Address.builder().city("Seoul").street("Songpa").zipCode("11023").build())
            .build();

        memberService.join(member);

        // When
        Page<Member> findMembers = memberRepository.findAll(PageRequest.of(0, 10));

        // Then
        assertThat(findMembers)
            .isNotEmpty()
            .hasSizeGreaterThanOrEqualTo(1);
    }

    @DisplayName("회원 상세 조회 테스트")
    @Test
    void findOne() {
        // Given
        Member member = Member.builder()
            .name("rolroralra")
            .address(Address.builder().city("Seoul").street("Songpa").zipCode("11023").build())
            .build();

        Long savedId = memberService.join(member);

        // When
        Optional<Member> findById = memberRepository.findById(savedId);

        // Then
        assertThat(findById)
            .isPresent()
            .get()
            .isEqualTo(member);
    }

    @DisplayName("중복 회원 예외처리")
    @Test
    void duplicatedMemberException() {
        // Given
        Member member = Member.builder()
            .name("rolroralra")
            .address(Address.builder().city("Seoul").street("Songpa").zipCode("11023").build())
            .build();

        memberService.join(member);

        Member duplicatedMember = Member.builder()
            .name("rolroralra")
            .address(Address.builder().city("Seoul2").street("Songpa2").zipCode("110232").build())
            .build();

        // Expected
        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() ->
                memberService.join(duplicatedMember)
            );
    }
}
