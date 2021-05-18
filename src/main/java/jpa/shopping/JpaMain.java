package jpa.shopping;

import domain.shopping.Item;
import domain.shopping.Member;
import domain.shopping.Order;
import domain.shopping.OrderItem;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
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

            System.out.println(member_02);

            Order order = createOrder(member_01);
            order.addOrderItem(item_01, 1800, 98);
            order.addOrderItem(item_02, 3400, 30);

            em.persist(order);
            for (OrderItem orderItem : order.getOrderItems()) {
                em.persist(orderItem);
            }
            em.flush();

            System.out.println(em.find(Order.class, 1L));

            System.out.println("===== BEFORE PERSIST =====");
//            em.persist(member);
            System.out.println("===== AFTER PERSIST =====");


            System.out.println("===== BEFORE COMMIT =====");
            tx.commit();
            System.out.println("===== AFTER COMMIT =====");

        } catch (Exception e) {
            System.out.println("===== BEFORE ROLLBACK =====");
            tx.rollback();
            e.printStackTrace();
            System.out.println("===== AFTER ROLLBACK =====");
        } finally {
            em.close();
        }


        emf.close();
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

}
