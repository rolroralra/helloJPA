package org.example.repository;

import org.example.domain.Member;
import org.example.repository.common.JpaRepositoryTest;
import org.example.repository.data.MemberDataSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

public class MemberRepositoryTest extends JpaRepositoryTest<Member, Long> {
    @Autowired
    private MemberRepository repository;

    @Override
    protected JpaRepository<Member, Long> repository() {
        return repository;
    }

    @Override
    protected Member createTestInstance() {
        return MemberDataSet.testData();
    }
}
