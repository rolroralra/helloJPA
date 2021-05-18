package domain.shopping;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "DELIVERY")
@Getter
@Setter
public class Delivery {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DELIVERY_ID")
    private Long id;

    @OneToOne(mappedBy = "delivery")
    private Order order;

    private Status status;

    private enum Status {
        PROCEED, COMPLETED
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Delivery{");
        sb.append("id=").append(id);
        sb.append(", status=").append(status);
        sb.append(", memberTo=").append(order.getMember());
        sb.append('}');
        return sb.toString();
    }
}
