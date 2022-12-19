package org.example.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ORDER_ITEM")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ITEM_ID")
    private Item item;

    @ManyToOne
    @JoinColumn(name = "ORDER_ID")
    private Order order;

    private Integer orderPrice;

    private Integer count;

    public static OrderItem of(Item item, int orderPrice, int count) {
        OrderItem orderItem = OrderItem.builder()
            .item(item)
            .orderPrice(orderPrice)
            .count(count)
            .build();

        item.removeStock(count);

        return orderItem;
    }

    public void cancel() {
        this.item.addStock(count);
    }

    public int getTotalPrice() {
        return orderPrice * count;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
            "id=" + id +
            ", item=" + item +
            ", order=" + order +
            ", orderPrice=" + orderPrice +
            ", count=" + count +
            '}';
    }
}
