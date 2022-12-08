package org.example.repository;

import org.example.domain.Category;
import org.example.repository.common.JpaRepositoryTest;
import org.example.repository.data.CategoryDataSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

class CategoryRepositoryTest extends JpaRepositoryTest<Category, Long> {

    @Autowired
    private CategoryRepository repository;

    @Override
    protected JpaRepository<Category, Long> repository() {
        return repository;
    }

    @Override
    protected Category createTestInstance() {
        return CategoryDataSet.testData();
    }
}
