package domain.shopping;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.*;

@Entity
@Table(name = "ITEM")
@Getter
@Setter
public class Item {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ITEM_ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PRICE")
    private int price;

    @Column(name = "STOCK_QUANTITY")
    private int stockQuantity;

    @ManyToMany
    @JoinTable(
            name = "CATEGORY_ITEM",
            joinColumns = {@JoinColumn(name = "ITEM_ID")},
            inverseJoinColumns = {@JoinColumn(name = "CATEGORY_ID")}
    )
    private List<Category> categoryList;

    public Item() {
        this(0L, "", 0, 0);
    }

    @Builder
    public Item(Long id, String name, int price, int stockQuantity) {
        checkNotNull(id);
        checkNotNull(name);
        checkArgument(price >= 0);
        checkArgument(stockQuantity >= 0);

        this.id = id;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.categoryList = new ArrayList<>();
    }

    public static Item of(String name, int price, int stockQuantity) {
        return new Item(0L, name, price, stockQuantity);
    }

    public void addCategory(Category... categories) {
        for (Category category : categories) {
            if (!categoryList.contains(category)) {
                categoryList.add(category);
                category.addItem(this);
            }
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Item{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", price=").append(price);
        sb.append(", stockQuantity=").append(stockQuantity);
        sb.append(", categoryList=").append(categoryList);
        sb.append('}');
        return sb.toString();
    }
}
