package org.example.repository.data;

import org.example.domain.OrderItem;

public class OrderItemDataSet {
    public static OrderItem testData() {
        return OrderItem.builder()
            .order(OrderDataSet.testData())
            .item(ItemDataSet.testData())
            .orderPrice(1000)
            .count(10)
            .build();
    }
}
