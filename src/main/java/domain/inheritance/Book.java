package domain.inheritance;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "BOOK")
@Getter
@Setter
public class Book extends ArtItem {
    @Column
    private String author;
}
