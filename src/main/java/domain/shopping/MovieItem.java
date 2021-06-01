package domain.shopping;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "MOVIE_ITEM")
@DiscriminatorValue(value = "MOVIE")
@Getter
@Setter
public class MovieItem extends Item {
    @Column
    private String director;

    @Column
    private String actor;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MovieItem{");
        sb.append(super.toString());
        sb.append(", director='").append(director).append('\'');
        sb.append(", actor='").append(actor).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
