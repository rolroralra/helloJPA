package org.example.mapping.idclass;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "PARENT")
@IdClass(ParentId.class)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Parent {
    @Id
    private Long id1;

    @Id
    private Long id2;

    private String name;

    @OneToMany(mappedBy = "parent")
    private List<Child> childList;

    @Override
    public String toString() {
        return "Parent{" +
            "id1=" + id1 +
            ", id2=" + id2 +
            ", name='" + name + '\'' +
            '}';
    }
}
