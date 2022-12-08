package org.example.mapping.embeddedid;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Child2")
@Table(name = "CHILD2")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Child {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "PARENT_ID1", referencedColumnName = "ID1"),
        @JoinColumn(name = "PARENT_ID2", referencedColumnName = "ID2"),
    })
    private Parent parent;

    private String name;

    @Override
    public String toString() {
        return "Child{" +
            "id=" + id +
            ", parent=" + parent +
            ", name='" + name + '\'' +
            '}';
    }
}
