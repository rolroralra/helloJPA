package org.example.repository.data;

import org.example.domain.Delivery;
import org.example.domain.DeliveryStatus;

public class DeliveryDataSet {
    public static Delivery testData() {
        return Delivery.builder()
            .order(OrderDataSet.testData())
            .status(DeliveryStatus.IN_PROGRESS)
            .address(AddressDataSet.testData())
            .build();
    }
}
