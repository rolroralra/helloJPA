package org.example.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.Optional;
import org.example.domain.Address;
import org.example.domain.Item;
import org.example.domain.Member;
import org.example.domain.Order;
import org.example.domain.OrderStatus;
import org.example.repository.OrderRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class OrderServiceTest {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    @DisplayName("상품 주문")
    @Test
    void test() {
        // Given
        Member member = createMember();
        Item item = createItem();

        Integer stockQuantity = item.getStockQuantity();

        int orderCount = 2;

        // When
        Long orderId = orderService.takeOrder(member.getId(), item.getId(), orderCount);

        // Then
        Optional<Order> findById = orderRepository.findById(orderId);
        assertThat(findById)
            .isPresent();

        Order order = findById.get();

        assertThat(order)
            .isNotNull();

        assertThat(order.getStatus())
            .isEqualTo(OrderStatus.ORDER);

        assertThat(order.getTotalPrice())
            .isEqualTo(item.getPrice() * orderCount);

        assertThat(order.getOrderItemList())
            .hasSize(1);

        assertThat(order.getDelivery().getAddress())
            .isEqualTo(member.getAddress());

        assertThat(item.getStockQuantity())
            .isEqualTo(stockQuantity - orderCount);
    }

    @DisplayName("재고 수량 초과")
    @Test
    void stockOver() {
        // Given
        Member member = createMember();
        Item item = createItem();

        int stockOverOrderCount = item.getStockQuantity() + 1;

        Long memberId = member.getId();
        Long itemId = item.getId();

        // Expected
        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() ->
                orderService.takeOrder(memberId, itemId, stockOverOrderCount)
            ).withMessageContaining("Over Stock Quantity");
    }

    @DisplayName("주문 취소")
    @Test
    void cancelOrder() {
        // Given
        Member member = createMember();
        Item item = createItem();

        int stockQuantity = item.getStockQuantity();
        int orderCount = 1;

        Long orderId = orderService.takeOrder(member.getId(), item.getId(), orderCount);

        // When
        orderService.cancelOrder(orderId);

        // Then
        Optional<Order> findById = orderRepository.findById(orderId);
        assertThat(findById)
            .isPresent();

        Order order = findById.get();

        assertThat(order.getStatus())
            .isEqualTo(OrderStatus.CANCEL);
        assertThat(item.getStockQuantity())
            .isEqualTo(stockQuantity);
    }

    private Member createMember() {
        Member member = Member.builder()
            .name("rolroralra")
            .address(Address.builder().city("seoul").street("songpa").zipCode("110101").build())
            .build();

        em.persist(member);

        return member;
    }

    private Item createItem() {
        Item item = Item.builder()
            .name("item01")
            .price(20000)
            .stockQuantity(100)
            .build();

        em.persist(item);

        return item;
    }

}
