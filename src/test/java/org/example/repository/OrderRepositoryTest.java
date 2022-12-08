package org.example.repository;

import org.example.domain.Order;
import org.example.repository.common.JpaRepositoryTest;
import org.example.repository.data.OrderDataSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

class OrderRepositoryTest extends JpaRepositoryTest<Order, Long> {
    @Autowired
    private OrderRepository repository;

    @Override
    protected JpaRepository<Order, Long> repository() {
        return repository;
    }

    @Override
    protected Order createTestInstance() {
        return OrderDataSet.testData();
    }
}
