package domain.shopping;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "BOOK_ITEM")
@DiscriminatorValue(value = "BOOK")
@Getter
@Setter
public class BookItem extends Item {
    private String author;
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
