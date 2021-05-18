package domain.inheritance;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
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


}
