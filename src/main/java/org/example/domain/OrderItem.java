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

    public void setItem(Item item) {
        this.item = item;
    }

    public void setOrder(Order order) {
        if (this.order != null) {
            this.order.getOrderItemList().remove(this);
        }

        this.order = order;
        this.order.getOrderItemList().add(this);
    }
}
