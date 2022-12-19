package org.example.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ORDERS")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToMany(mappedBy = "order")
    @Builder.Default
    private List<OrderItem> orderItemList = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "DELIVERY_ID")
    private Delivery delivery;

    private LocalDate orderDate;

    @Enumerated(value = EnumType.STRING)
    @Builder.Default
    private OrderStatus status = OrderStatus.ORDER;

    public static Order of(Member member, Delivery delivery, OrderItem... orderItems) {
        Order order = new Order();
        order.setMember(member);
        order.setDelivery(delivery);

        Arrays.stream(orderItems)
            .forEach(order::addOrderItem);

        order.status = OrderStatus.ORDER;
        order.orderDate = LocalDate.now();
        return order;
    }

    public void cancel() {
        if (delivery.getStatus() == DeliveryStatus.COMPLETED) {
            throw new IllegalStateException("이미 배송완료된 상품은 취소가 불가능합니다");
        }

        this.status = OrderStatus.CANCEL;

        orderItemList.forEach(OrderItem::cancel);
    }

    public int getTotalPrice() {
        return orderItemList.stream()
            .mapToInt(OrderItem::getTotalPrice)
            .sum();
    }

    public void addOrderItem(OrderItem orderItem) {
        this.orderItemList.add(orderItem);
        orderItem.setOrder(this);
    }

    public void setMember(Member member) {
        if (this.member != null) {
            this.member.getOrderList().remove(this);
        }

        this.member = member;
        this.member.getOrderList().add(this);
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrder(this);
    }

    @Override
    public String toString() {
        return "Order{" +
            "id=" + id +
            ", member=" + member +
            ", orderDate=" + orderDate +
            ", status=" + status +
            '}';
    }
}
