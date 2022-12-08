package org.example.repository.data;

import java.time.LocalDate;
import org.example.domain.Order;
import org.example.domain.OrderStatus;

public class OrderDataSet {
    public static Order testData() {
        return Order.builder()
            .status(OrderStatus.ORDER)
            .member(MemberDataSet.testData())
            .orderDate(LocalDate.now())
            .build();
    }
}
