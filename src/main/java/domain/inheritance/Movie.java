package domain.inheritance;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "MOVIE")
@DiscriminatorValue(value = "MOVIE")
@Getter
@Setter
public class Movie extends ArtItem {
    @Column
    private String director;
    @Column
    private String actor;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Movie{");
        sb.append(super.toString());
        sb.append(", director='").append(director).append('\'');
        sb.append(", actor='").append(actor).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
