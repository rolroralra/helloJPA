package org.example.repository;

import org.example.domain.Delivery;
import org.example.repository.common.JpaRepositoryTest;
import org.example.repository.data.DeliveryDataSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

class DeliveryRepositoryTest extends JpaRepositoryTest<Delivery, Long> {

    @Autowired
    private DeliveryRepository repository;

    @Override
    protected JpaRepository<Delivery, Long> repository() {
        return repository;
    }

    @Override
    protected Delivery createTestInstance() {
        return DeliveryDataSet.testData();
    }
}
