package org.example.service;

import com.google.common.base.Preconditions;
import lombok.RequiredArgsConstructor;
import org.example.domain.Delivery;
import org.example.domain.Item;
import org.example.domain.Member;
import org.example.domain.Order;
import org.example.domain.OrderItem;
import org.example.repository.MemberRepository;
import org.example.repository.OrderRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemService itemService;

    public Long takeOrder(Long memberId, Long itemId, int count) {
        Member member = memberRepository.findById(memberId)
            .orElseThrow(() -> new IllegalArgumentException("Member does not exists."));

        Item item = itemService.findOne(itemId)
            .orElseThrow(() -> new IllegalArgumentException("Item does not exists."));

        Preconditions.checkArgument(count <= item.getStockQuantity(), "Over Stock Quantity");

        Delivery delivery = Delivery.builder()
            .address(member.getAddress())
            .build();

        OrderItem orderItem = OrderItem.of(item, item.getPrice(), count);

        Order order = Order.of(member, delivery, orderItem);

        orderRepository.save(order);

        return order.getId();
    }

    public void cancelOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
            .orElseThrow(() -> new IllegalArgumentException("Order does not exists"));

        order.cancel();
    }

    public Page<Order> findAll(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }
}
