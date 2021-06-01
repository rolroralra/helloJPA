package domain.cascadeOrphan;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Child {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENT_ID")
    private Parent parent;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Child{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", parent=").append(parent.getId());
        sb.append('}');
        return sb.toString();
    }
}
