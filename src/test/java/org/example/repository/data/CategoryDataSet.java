package org.example.repository.data;

import java.util.ArrayList;
import org.example.domain.Category;

public class CategoryDataSet {
    public static Category testData() {
        return Category.builder()
            .name("category")
            .itemList(new ArrayList<>())
            .build();
    }
}
