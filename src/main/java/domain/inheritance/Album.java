package domain.inheritance;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ALBUM")
@DiscriminatorValue(value = "ALBUM")
@Getter
@Setter
public class Album extends ArtItem {
    @Column
    private String artist;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Album{");
        sb.append(super.toString());
        sb.append(", artist='").append(artist).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
