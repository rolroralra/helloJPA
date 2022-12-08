package org.example.repository.data;

import org.example.domain.Member;

public class MemberDataSet {
    public static Member testData() {
        return Member.builder()
            .name("test")
            .address(AddressDataSet.testData())
            .build();
    }


}
