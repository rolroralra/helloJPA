package org.example.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.domain.Member;
import org.example.repository.MemberRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private final EntityManager em;
    private final MemberRepository memberRepository;

    @Transactional(rollbackFor = {Exception.class})
    @SuppressWarnings("unchecked")
    public Long join(Member member) {
        validateDuplicatedMember(member);
        Query query = em.createQuery("select m.name from Member m");
        List<Object> resultList = query.getResultList();

        resultList.forEach(result -> log.info(result.toString()));
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
        List<Member> findMembers = memberRepository.findByName(member.getName());

        if (!findMembers.isEmpty()) {
            throw new IllegalArgumentException(String.format("Member already exists [%s]", member.getName()));
        }
    }

}
