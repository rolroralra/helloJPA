package domain.team;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "TeamMember")
@Table(name = "TEAM_MEMBER")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LOCKER_ID")
    private Locker locker;

    @OneToOne(mappedBy = "member")
    private LockerManager lockerManager;

    @ManyToOne
    @JoinColumn(name = "LEVEL_ID", insertable = false, updatable = false)
    private Level level;

    @ManyToMany
    @JoinTable(name = "MEMBER_PRODUCT",
        joinColumns = @JoinColumn(name = "MEMBER_ID"),
        inverseJoinColumns = @JoinColumn(name = "PRODUCT_ID"))
    private List<Product> productList = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<MemberProduct> memberProductList = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<MemberProductWithIdClass> memberProductWithIdClassList = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    public void joinTeam(Team team) {
        this.setTeam(team);
        team.getMembers().add(this);
    }

    public void addProduct(Product product) {
        productList.add(product);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Member{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", team=").append(team);
        sb.append(", locker=").append(locker);
        sb.append(", targetLocker=").append(lockerManager);
        sb.append(", level=").append(level);
        sb.append(", productList=").append(productList);
        sb.append('}');
        return sb.toString();
    }
}
