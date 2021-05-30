package jpa.shopping;

import domain.shopping.*;
import jpa.JpaMainTemplate;

import javax.persistence.EntityManager;

public class JpaMain extends JpaMainTemplate {
    public static void main(String[] args) {
        new JpaMain().run();
    }

    private static Member createMember(String name, String city, String street, String zipcode) {
        return Member.of(name, city, street, zipcode);
    }

    private static Item createItem(String name, int price, int stockQuantity) {
        return Item.of(name, price, stockQuantity);
    }

    private static Order createOrder(Member member) {
        return Order.of(member);
    }

    @Override
    public void logic(EntityManager em) {
        em.persist(createMember("rolroralra", "SEOUL", "SONGPA", "101"));
        em.persist(createMember("guest", "DAEGU", "BOOM", "104"));

        em.persist(createItem("ITEM_01", 2000, 100));
        em.persist(createItem("ITEM_02", 3500, 70));
        em.persist(createItem("ITEM_03", 5000, 30));

        em.flush();

        Member member_01 = em.find(Member.class, 1L);
        Member member_02 = em.find(Member.class, 2L);

        Item item_01 = em.find(Item.class, 1L);
        Item item_02 = em.find(Item.class, 2L);
        Item item_03 = em.find(Item.class, 3L);

        Category category_01 = new Category();
        category_01.setName("KR");

        Category category_02 = new Category();
        category_02.setName("EU");

        Category category_03 = new Category();
        category_03.setName("US");

        em.persist(category_01);
        em.persist(category_02);
        em.persist(category_03);

        item_01.addCategory(category_02, category_03);
        item_02.addCategory(category_01);
        item_03.addCategory(category_01, category_03);

        System.out.println(member_02);

        Order order = createOrder(member_01);
        order.addOrderItem(item_01, 1800, 98);
        order.addOrderItem(item_02, 3400, 30);

        Delivery delivery = new Delivery();
        delivery.setOrder(order);
        order.setDelivery(delivery);

        em.persist(order);
        em.persist(delivery);

        for (OrderItem orderItem : order.getOrderItems()) {
            em.persist(orderItem);
        }
        em.flush();

        System.out.println(em.find(Order.class, 1L));
        System.out.println(em.find(Category.class, 1L).getItems());
        System.out.println(em.find(Category.class, 2L).getItems());
        System.out.println(em.find(Category.class, 3L).getItems());
    }
}
