package domain.team;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "MEMBER_PRODUCT_1")
@IdClass(value = MemberProductId.class)
@Getter
@Setter
public class MemberProductWithIdClass {

    @Id
    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @Id
    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    private Integer amount;

    private Integer price;

    @Override
    public String toString() {
        return "MemberProductWithIdClass{" +
            "member=" + member +
            ", product=" + product +
            ", amount=" + amount +
            ", price=" + price +
            '}';
    }


}
