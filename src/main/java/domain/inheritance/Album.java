package domain.inheritance;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

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
