package domain.inheritance;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "BOOK")
@DiscriminatorValue(value = "BOOK")
@Getter
@Setter
public class Book extends ArtItem {
    @Column
    private String author;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Book{");
        sb.append(super.toString());
        sb.append(", author='").append(author).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
