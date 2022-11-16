package domain.team;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "LEVEL")
@Getter
@Setter
public class Level {

    @Id @GeneratedValue
    @Column(name = "LEVEL_ID")
    private Long id;

    private String name;

    @OneToMany
    @JoinColumn(name = "LEVEL_ID")
    private List<Member> memberList = new ArrayList<>();

    public void addMember(Member member) {
        memberList.add(member);
    }

    @Override
    public String toString() {
        return "Level{" +
            "id=" + id +
            ", name='" + name + '\'' +
            '}';
    }
}
