package org.example.mapping.fk;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "GrandChild5")
@Table(name = "GRAND_CHILD5")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GrandChild {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "CHILD_ID")
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
