package org.example.domain;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Entity
//@Table(name = "CATEGORY_ITEM")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Deprecated
public class CategoryItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ITEM_ID")
    private Item item;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;

    @Override
    public String toString() {
        return "CategoryItem{" +
            "id=" + id +
            ", item=" + item +
            ", category=" + category +
            '}';
    }

    public void setItem(Item item) {
        if (this.item != null) {
            this.item.getCategoryList().remove(this);
        }

        this.item = item;
//        this.item.getCategoryList().add(this);
    }

    public void setCategory(Category category) {
        if (this.category != null) {
            this.category.getItemList().remove(this);
        }

        this.category = category;
//        this.category.getItemList().add(this);
    }
}
