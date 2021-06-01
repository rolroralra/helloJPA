package domain.cascadeOrphan;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Parent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Child> children = new ArrayList<>();

    public void addChild(Child child) {
        children.add(child);
        child.setParent(this);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Parent{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", children=").append(children);
        sb.append('}');
        return sb.toString();
    }
}
