package org.example.domain;

import com.google.common.base.Preconditions;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ITEM_INFO")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer price;

    private Integer stockQuantity;

    @ManyToMany(mappedBy = "itemList")
    @Builder.Default
    private List<Category> categoryList = new ArrayList<>();

    @Override
    public String toString() {
        return "Item{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", price=" + price +
            ", stockQuantity=" + stockQuantity +
            '}';
    }

    public void addCategory(Category category) {
        if (!categoryList.contains(category)) {
            categoryList.add(category);
        }
    }

    public void removeCategory(Category category) {
        categoryList.remove(category);
    }

    public void addStock(Integer count) {
        Preconditions.checkArgument(count > 0, String.format("count should be greater than 0 [%d]", count));

        this.stockQuantity += count;
    }

    public void removeStock(int count) {
        Preconditions.checkArgument(count > 0, String.format("count should be greater than 0 [%d]", count));
        Preconditions.checkArgument(count <= this.stockQuantity, String.format("count should be less or equal than %d [%d]", this.stockQuantity, count));

        this.stockQuantity -= count;
    }
}
