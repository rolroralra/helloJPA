package org.example.repository;

import org.example.domain.CategoryItem;
import org.example.repository.common.JpaRepositoryTest;
import org.example.repository.data.CategoryItemDataSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

@Deprecated
class CategoryItemRepositoryTest extends JpaRepositoryTest<CategoryItem, Long> {

    @Autowired
    private CategoryItemRepository repository;

    @Override
    protected JpaRepository<CategoryItem, Long> repository() {
        return repository;
    }

    @Override
    protected CategoryItem createTestInstance() {
        return CategoryItemDataSet.testData();
    }
}
