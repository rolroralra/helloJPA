package org.example.repository;

import org.example.domain.Item;
import org.example.repository.common.JpaRepositoryTest;
import org.example.repository.data.ItemDataSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

class ItemRepositoryTest extends JpaRepositoryTest<Item, Long> {

    @Autowired
    private ItemRepository repository;

    @Override
    protected JpaRepository<Item, Long> repository() {
        return repository;
    }

    @Override
    protected Item createTestInstance() {
        return ItemDataSet.testData();
    }
}
