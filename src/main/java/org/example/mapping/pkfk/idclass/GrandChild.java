package org.example.mapping.pkfk.idclass;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "GrandChild")
@Table(name = "GRAND_CHILD")
@IdClass(GrandChildId.class)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GrandChild {
    @Id
    private Long id;

    @Id
    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "CHILD_ID", referencedColumnName = "ID"),
        @JoinColumn(name = "PARENT_ID", referencedColumnName = "PARENT_ID")
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
