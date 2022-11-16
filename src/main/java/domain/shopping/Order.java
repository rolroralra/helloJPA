package domain.shopping;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ORDERS")
@Getter
@Setter
public class Order {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "DELIVERY_ID")
    private Delivery delivery;

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    public Order() {
        this(null);
    }

    public Order(Member member) {
        this(0L, member, LocalDateTime.now(), OrderStatus.PROCEED);
    }

    @Builder
    public Order(Long id, Member member, LocalDateTime orderDate, OrderStatus status) {
        checkNotNull(id);
        checkNotNull(orderDate);
        checkNotNull(status);

        this.setId(id);
        this.setMember(member);
        this.setOrderItems(new ArrayList<>());
        this.setOrderDate(orderDate);
        this.setStatus(status);
    }

    public static Order of(Member member) {
        checkNotNull(member);

        Order order = new Order();
        order.setMember(member);
        return order;
    }

    public void addOrderItem(Item item, int orderPrice, int count) {
        checkNotNull(item);
        checkArgument(orderPrice >= 0);
        checkArgument(orderPrice <= item.getPrice());
        checkArgument(count >= 0);
        checkArgument(count <= item.getStockQuantity());

        addOrderItem(OrderItem.of(this, item, orderPrice, count));
    }

    public void addOrderItem(OrderItem orderItem) {
        checkNotNull(orderItem);

        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Order{");
        sb.append("id=").append(id);
        sb.append(", member=").append(member);
        sb.append(", orderItems=").append(orderItems);
        sb.append(", orderDate=").append(orderDate);
        sb.append(", status=").append(status);
        sb.append(", delivery=").append(delivery);
        sb.append('}');
        return sb.toString();
    }
}
