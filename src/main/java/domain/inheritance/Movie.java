package domain.inheritance;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "MOVIE")
@Getter
@Setter
public class Movie extends ArtItem {
    @Column
    private String director;
    @Column
    private String actor;
}
