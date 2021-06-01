package domain.shopping;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

@Entity(name = "SHOPPING_MEMBER")
@Table(name = "SHOPPING_MEMBER")
@Getter
@Setter
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    private Long id;
    @Column
    private String name;
    @Column
    private String city;
    @Column
    private String street;
    @Column
    private String zipcode;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> orders;

    public Member() {
        this(0L, "", "", "", "");
    }

    @Builder
    public Member(Long id, String name, String city, String street, String zipcode) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
        this.orders = new ArrayList<>();
    }

    public static Member of(String name, String city, String street, String zipcode) {
        checkNotNull(name);
        checkNotNull(city);
        checkNotNull(street);
        checkNotNull(zipcode);

        return new Member(0L, name, city, street, zipcode);
    }

    public void takeOrder(Order order) {
        checkNotNull(order);

        orders.add(order);
        order.setMember(this);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Member{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", city='").append(city).append('\'');
        sb.append(", street='").append(street).append('\'');
        sb.append(", zipcode='").append(zipcode).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
