package domain.team;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "PRODUCT_INFO")
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "productList")
    private List<Member> memberList = new ArrayList<>();

    @OneToMany(mappedBy = "product")
    private List<MemberProduct> memberProductList = new ArrayList<>();

    @OneToMany(mappedBy = "product")
    private List<MemberProductWithIdClass> memberProductWithIdClassList = new ArrayList<>();

    @Override
    public String toString() {
        return "Product{" +
            "id=" + id +
            ", name='" + name + '\'' +
            '}';
    }
}
