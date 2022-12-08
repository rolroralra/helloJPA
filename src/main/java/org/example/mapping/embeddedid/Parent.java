package org.example.mapping.embeddedid;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Parent2")
@Table(name = "PARENT2")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Parent {
    @EmbeddedId
    private ParentId id;

    private String name;

    @OneToMany(mappedBy = "parent")
    private List<Child> childList;

    @Override
    public String toString() {
        return "Parent2{" +
            "id=" + id +
            ", name='" + name + '\'' +
            '}';
    }
}
