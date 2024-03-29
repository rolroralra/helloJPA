package org.example.repository.common;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import jakarta.persistence.Id;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@Rollback
@ActiveProfiles(value = "test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public abstract class JpaRepositoryTest<T, ID> {

    protected abstract JpaRepository<T, ID> repository();

    protected abstract T createTestInstance();

    @SuppressWarnings("unchecked")
    protected ID idFromEntity(T entity) throws RuntimeException {
        for (Field field : entity.getClass().getDeclaredFields()) {
            for (Annotation declaredAnnotation : field.getDeclaredAnnotations()) {
                if (declaredAnnotation.annotationType() == Id.class) {
                    try {
                        field.setAccessible(true);
                        return (ID) field.get(entity);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }

        throw new RuntimeException();
    }

    @Test
    protected void findById() {
        // given
        T entity = createTestInstance();
        T savedEntity = repository().save(entity);

        // when
        Optional<T> findById = repository().findById(idFromEntity(savedEntity));

        // then
        assertThat(findById)
            .isNotEmpty()
            .get()
            .isEqualTo(savedEntity);
    }

    @ParameterizedTest
    @CsvSource(value = {"0,10", "1,10", "2,10", "3,10"})
    protected void findAll(int page, int size) {
        // given
        PageRequest pageRequest = PageRequest.of(page, size);

        // when
        Page<T> result = repository().findAll(pageRequest);

        // then
        assertThat(result.getContent())
            .isNotNull();
    }

    @Test
    protected void save() {
        // given
        T entity = createTestInstance();

        // when
        T savedEntity = repository().save(entity);

        // then
        assertThat(savedEntity)
            .isNotNull();
    }

    @Test
    protected void delete() {
        // given
        T entity = createTestInstance();
        T savedEntity = repository().save(entity);

        // when
        repository().deleteById(idFromEntity(savedEntity));
        Optional<T> optionalUser = repository().findById(idFromEntity(savedEntity));

        // then
        assertThat(optionalUser).isNotPresent();
    }
}
