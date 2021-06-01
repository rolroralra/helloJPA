package domain.proxy;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "ProxyMember")
@Table(name = "PROXY_MEMBER")
@Getter
@Setter
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Member{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", teamClass=").append(team.getClass().getSimpleName());
        sb.append(", teamId=").append(team.getId());
        sb.append('}');
        return sb.toString();
    }
}
