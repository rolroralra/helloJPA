package org.example.repository.data;

import org.example.domain.CategoryItem;

@Deprecated
public class CategoryItemDataSet {
    public static CategoryItem testData() {
        return CategoryItem.builder()
            .category(CategoryDataSet.testData())
            .item(ItemDataSet.testData())
            .build();
    }
}
