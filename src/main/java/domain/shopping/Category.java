package domain.shopping;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CATEGORY")
@Getter
@Setter
public class Category {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CATEGORY_ID")
    private Long id;

    @Column
    private String name;

    @ManyToMany(mappedBy = "categoryList")
    private List<Item> items = new ArrayList<>();

    public void addItem(Item... items) {
        for (Item item : items) {
            if (!this.items.contains(item)) {
                this.items.add(item);
            }
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Category{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
