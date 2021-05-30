package domain.shopping;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ALBUM_ITEM")
@DiscriminatorValue(value = "ALBUM")
@Getter
@Setter
public class AlbumItem extends Item {
    private String artist;
    private String etc;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AlbumItem{");
        sb.append(super.toString());
        sb.append(", artist='").append(artist).append('\'');
        sb.append(", etc='").append(etc).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
