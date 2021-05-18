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
    //@Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String name;

//    @Column(name = "TEAM_ID")
//    private Long teamId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    @Enumerated(EnumType.STRING)
    public void joinTeam(Team team) {
        this.setTeam(team);
        team.getMembers().add(this);
    }
}
