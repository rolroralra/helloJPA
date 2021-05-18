package domain.team;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "LOCKER")
@Setter
@Getter
public class Locker {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LOCKER_ID")
    private Long id;

    @Column
    private String name;

    @OneToOne(mappedBy = "locker")
    private Member member;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Locker{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
