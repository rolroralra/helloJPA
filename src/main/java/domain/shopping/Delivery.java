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

    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
    private Order order;

    @Enumerated(value = EnumType.STRING)
    private Status status;

    private enum Status {
        PROCEED, COMPLETED
    }

    public Delivery() {
        this(null);
    }

    public Delivery(Order order) {
        this.order = order;
        this.status = Status.PROCEED;
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
