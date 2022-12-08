package org.example.repository.data;

import org.example.domain.Address;

public class AddressDataSet {
    public static Address testData() {
        return Address.builder()
            .city("Seoul")
            .street("Songpa")
            .zipCode("1001")
            .build();
    }
}
