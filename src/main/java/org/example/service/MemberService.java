package org.example.service;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.example.domain.Member;
import org.example.repository.MemberRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional(rollbackFor = {Exception.class})
    public Long join(Member member) {
        validateDuplicatedMember(member);

        Member savedMember = memberRepository.save(member);

        return savedMember.getId();
    }

    @Transactional(readOnly = true)
    public Page<Member> findMembers(Pageable pageable) {
        return memberRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

    private void validateDuplicatedMember(Member member) {
        List<Member> findMembers = memberRepository.findMembersByName(member.getName());

        if (!findMembers.isEmpty()) {
            throw new IllegalArgumentException(String.format("Member already exists [%s]", member.getName()));
        }
    }

}
