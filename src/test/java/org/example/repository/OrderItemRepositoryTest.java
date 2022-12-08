package org.example.repository;

import org.example.domain.OrderItem;
import org.example.repository.common.JpaRepositoryTest;
import org.example.repository.data.OrderItemDataSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

class OrderItemRepositoryTest extends JpaRepositoryTest<OrderItem, Long> {
    @Autowired
    private OrderItemRepository repository;

    @Override
    protected JpaRepository<OrderItem, Long> repository() {
        return repository;
    }

    @Override
    protected OrderItem createTestInstance() {
        return OrderItemDataSet.testData();
    }
}
