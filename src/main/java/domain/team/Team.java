package domain.team;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TEAM_TABLE")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Team {
    @Id @GeneratedValue
    //@Column(name = "TEAM_ID")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "team")
    private List<Member> members = new ArrayList<>();
}
