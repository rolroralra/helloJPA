package org.example.domain.test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.List;
import org.example.domain.Address;
import org.example.domain.Category;
import org.example.domain.Delivery;
import org.example.domain.DeliveryStatus;
import org.example.domain.Item;
import org.example.domain.Member;
import org.example.domain.Order;
import org.example.domain.OrderItem;
import org.example.domain.OrderStatus;
import org.example.template.JpaMainTemplate;

public class SampleCode extends JpaMainTemplate {

    public static void main(String[] args) {
        new SampleCode().run();
    }

    @Override
    protected void logic(EntityManager em) {

        Member member = Member.builder()
            .name("test")
            .address(
                Address.builder()
                    .city("Seoul")
                    .street("Songpa")
                    .zipCode("1001")
                    .build()
            ).build();
        em.persist(member);

        Order order = Order.builder()
            .orderDate(LocalDate.now())
            .member(member)
            .status(OrderStatus.ORDER)
            .build();

        Order order2 = Order.builder()
            .orderDate(LocalDate.now())
            .member(member)
            .status(OrderStatus.ORDER)
            .build();

        order.setMember(member);
        order2.setMember(member);

        em.persist(order);
        em.persist(order2);
        em.flush();

        Item item = Item.builder()
            .name("Item1001")
            .stockQuantity(100)
            .price(1000)
            .build();
        Item item2 = Item.builder()
            .name("Item1002")
            .stockQuantity(20)
            .price(2000)
            .build();
        Item item3 = Item.builder()
            .name("Item1003")
            .stockQuantity(50)
            .price(3000)
            .build();
        em.persist(item);
        em.persist(item2);
        em.persist(item3);

        OrderItem orderItem = OrderItem.builder()
            .order(order)
            .item(item)
            .count(1)
            .orderPrice(item.getPrice())
            .build();
        OrderItem orderItem2 = OrderItem.builder()
            .order(order)
            .item(item2)
            .count(2)
            .orderPrice(item2.getPrice() * 2)
            .build();
        OrderItem orderItem3 = OrderItem.builder()
            .order(order2)
            .item(item2)
            .count(3)
            .orderPrice(item2.getPrice() * 3)
            .build();
        OrderItem orderItem4 = OrderItem.builder()
            .order(order2)
            .item(item3)
            .count(4)
            .orderPrice(item3.getPrice() * 4)
            .build();

        order.getOrderItemList().add(orderItem);
        order.getOrderItemList().add(orderItem2);

        order2.getOrderItemList().add(orderItem3);
        order2.getOrderItemList().add(orderItem4);


        em.persist(orderItem);
        em.persist(orderItem2);
        em.persist(orderItem3);
        em.persist(orderItem4);
        em.flush();

        Delivery delivery = Delivery.builder()
            .address(order.getMember().getAddress())
            .status(DeliveryStatus.IN_PROGRESS)
            .order(order)
            .build();

        em.persist(delivery);
        em.flush();

        Category category = Category.builder()
            .name("category1")
            .build();
        Category category2 = Category.builder()
            .name("category2")
            .build();
        Category category3 = Category.builder()
            .name("category3")
            .build();

        category.addItem(item);
        category.addItem(item3);

        category2.addItem(item3);
        category2.addItem(item2);

        category3.addItem(item3);

        em.persist(category);
        em.persist(category2);
        em.persist(category3);
        em.flush();

        category.removeItem(item3);
        em.flush();

        Member findMember = em.find(Member.class, member.getId());
        System.out.println("findMember = " + findMember);
        System.out.println("member.getOrderList() = " +  member.getOrderList());
        System.out.println("findMember.getOrderList() = " + findMember.getOrderList());
        System.out.println("findMember.getOrderList().size() = " + findMember.getOrderList().size());

        Order findOrder = em.find(Order.class, order.getId());
        System.out.println("findOrder = " + findOrder);
        System.out.println("findOrder.getMember() = " + findOrder.getMember());
        System.out.println("findOrder.getOrderItemList() = " + findOrder.getOrderItemList());
        System.out.println("findOrder.getOrderItemList().size() = " + findOrder.getOrderItemList().size());


        OrderItem findOrderItem = em.find(OrderItem.class, orderItem.getId());
        System.out.println("findOrderItem = " + findOrderItem);
        System.out.println("findOrderItem.getOrder() = " + findOrderItem.getOrder());
        System.out.println("findOrderItem.getItem() = " + findOrderItem.getItem());

        Item findItem = em.find(Item.class, item3.getId());
        System.out.println("findItem = " + findItem);
        System.out.println("findItem.getCategoryItemList() = " + findItem.getCategoryList());
        
        Category findCategory = em.find(Category.class, category.getId());
        System.out.println("findCategory = " + findCategory);
        System.out.println("findCategory.getCategoryItemList() = " + findCategory.getItemList());

        em.clear();

        // JQPL
        String jpql = "select m from Member as m where m.name = 'test'";
        List<Member> resultList = em.createQuery(jpql, Member.class).getResultList();
        System.out.println("resultList = " + resultList);

        // Criteria
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Member> query = cb.createQuery(Member.class);

        Root<Member> m = query.from(Member.class);
        CriteriaQuery<Member> cq = query.select(m)
            .where(cb.equal(m.get("name"), "test"));

        List<Member> resultListByCriteria = em.createQuery(cq).getResultList();
        System.out.println("resultListByCriteria = " + resultListByCriteria);

        // Native SQL
        String sql = "SELECT * FROM MEMBER WHERE NAME = 'test'";
        List<Member> resultListByNativeQuery = em.createNativeQuery(sql, Member.class).getResultList();
        System.out.println("resultListByNativeQuery = " + resultListByNativeQuery);


    }
}
