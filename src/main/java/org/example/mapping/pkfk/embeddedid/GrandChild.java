package org.example.mapping.pkfk.embeddedid;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "GrandChild4")
@Table(name = "GRAND_CHILD4")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GrandChild {
    @EmbeddedId
    private GrandChildId id;

    @MapsId("childId")
    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "PARENT_ID", referencedColumnName = "PARENT_ID"),
        @JoinColumn(name = "CHILD_ID", referencedColumnName = "ID"),
    })
    private Child child;

    private String name;

    @Override
    public String toString() {
        return "GrandChild{" +
            "id=" + id +
            ", child=" + child +
            ", name='" + name + '\'' +
            '}';
    }
}
