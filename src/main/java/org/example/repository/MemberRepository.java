package org.example.repository;

import java.util.List;
import org.example.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    List<Member> findMembersByName(String name);
}
