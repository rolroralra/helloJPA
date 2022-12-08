package org.example.repository.data;

import java.util.ArrayList;
import org.example.domain.Item;

public class ItemDataSet {
    public static Item testData() {
        return Item.builder()
            .name("item1001")
            .price(1000)
            .stockQuantity(100)
            .categoryList(new ArrayList<>())
            .build();
    }
}
