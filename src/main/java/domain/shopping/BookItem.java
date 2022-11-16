package domain.shopping;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "BOOK_ITEM")
@DiscriminatorValue(value = "BOOK")
@Getter
@Setter
public class BookItem extends Item {
    @Column
    private String author;

    @Column
    private String isbn;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BookItem{");
        sb.append(super.toString());
        sb.append(", author='").append(author).append('\'');
        sb.append(", isbn='").append(isbn).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
