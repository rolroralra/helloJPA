package domain.shopping;

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
    @ManyToOne
    @JoinColumn(name = "ORDER_ID")
    private Order order;
    @ManyToOne
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
        this.id = 0L;
        this.order = order;
        this.item = item;
        this.orderPrice = orderPrice;
        this.count = count;
    }

    public static OrderItem of(Order order, Item item, int orderPrice, int count) {
        checkNotNull(order);
        checkNotNull(item);
        checkArgument(orderPrice >= 0);
        checkArgument(orderPrice <= item.getPrice());
        checkArgument(count >= 0);
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
