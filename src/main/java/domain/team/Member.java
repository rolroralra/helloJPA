package domain.team;

import lombok.*;

import javax.persistence.*;

@Entity(name = "TeamMember")
@Table(name = "TEAM_MEMBER")
@Getter
@Setter

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member {

    public static class Test{
        public String test;
    }

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    @OneToOne
    @JoinColumn(name = "LOCKER_ID")
    private Locker locker;

    @Enumerated(EnumType.STRING)
    public void joinTeam(Team team) {
        this.setTeam(team);
        team.getMembers().add(this);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Member{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", team=").append(team);
        sb.append(", locker=").append(locker);
        sb.append('}');
        return sb.toString();
    }
}
