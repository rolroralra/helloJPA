package org.example.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.IntStream;
import org.example.domain.Member;
import org.example.repository.common.JpaRepositoryTest;
import org.example.repository.data.MemberDataSet;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

class MemberRepositoryTest extends JpaRepositoryTest<Member, Long> {
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

    @DisplayName("test")
    @Test
    void test() {
        // Given
        IntStream.range(0, 10).forEach(i ->
            repository.save(createTestInstance()));

        // When
        List<Member> members = repository.findByNameLike2("es");

        // Then
        assertThat(members).hasSizeGreaterThan(1);
        System.out.println("members = " + members);
    }
}
