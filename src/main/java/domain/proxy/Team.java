package domain.proxy;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "ProxyTeam")
@Table(name = "PROXY_TEAM")
@Getter
@Setter
public class Team {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @OneToMany(mappedBy = "team")
    private List<Member> members = new ArrayList<>();

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Team{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", members=").append(members);
        sb.append('}');
        return sb.toString();
    }
}
