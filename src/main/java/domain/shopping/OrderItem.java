package domain.shopping;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;


@Entity
@Table(name = "ORDER_ITEM")
@Getter
@Setter
public class OrderItem {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_ITEM_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_ID")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ITEM_ID")
    private Item item;

    @Column
    private int orderPrice;

    @Column
    private int count;

    public OrderItem() {
        this(null, null, 0, 0);
    }

    public OrderItem(Order order, Item item, int orderPrice, int count) {
        this(0L, order, item, orderPrice, count);
    }

    @Builder
    public OrderItem(Long id, Order order, Item item, int orderPrice, int count) {
        checkNotNull(id);
        checkArgument(orderPrice >= 0);
        checkArgument(count >= 0);

        this.setId(id);
        this.setOrder(order);
        this.setItem(item);
        this.setOrderPrice(orderPrice);
        this.setCount(count);
    }

    public static OrderItem of(Order order, Item item, int orderPrice, int count) {
        checkNotNull(order);
        checkNotNull(item);
        checkArgument(orderPrice <= item.getPrice());
        checkArgument(count <= item.getStockQuantity());

        return new OrderItem(order ,item, orderPrice, count);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("OrderItem{");
        sb.append("id=").append(id);
        sb.append(", item=").append(item);
        sb.append(", orderPrice=").append(orderPrice);
        sb.append(", count=").append(count);
        sb.append('}');
        return sb.toString();
    }
}
