package domain.team;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "LOCKER_MANAGER")
@Getter
@Setter
public class LockerManager {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @OneToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @Override
    public String toString() {
        return "TargetLocker{" +
            "id=" + id +
            ", name='" + name + '\'' +
            '}';
    }
}
