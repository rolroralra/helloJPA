package domain.inheritance;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "ART_ITEM")
//@Inheritance(strategy = InheritanceType.JOINED)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // Default
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@DiscriminatorColumn(name = "ITEM_TYPE")
@Getter
@Setter
public abstract class ArtItem {
    @Id @GeneratedValue
    private Long id;
    @Column
    private String name;
    @Column
    private int price;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", price=").append(price);
        return sb.toString();
    }
}
