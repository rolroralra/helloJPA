package jpa.mappedSuperClass;

import domain.mappedSuperClass.Buyer;
import domain.mappedSuperClass.Seller;
import javax.persistence.EntityManager;
import jpa.JpaMainTemplate;

public class JpaMain extends JpaMainTemplate {
    public static void main(String[] args) {
        new JpaMain().run("test");
    }

    @Override
    public void logic(EntityManager em) {
        Buyer buyer = new Buyer();
        buyer.setName("BUYER01");
        buyer.setPhoneNumber("010-8802-5013");
        buyer.setAddress("SEOUL");

        Buyer buyer2 = new Buyer();
        buyer.setName("BUYER02");
        buyer.setPhoneNumber("NONE");
        buyer.setAddress("BUSAN");

        Seller seller = new Seller();
        seller.setName("SELLER01");
        seller.setShopAddress("DAEGU");

        Seller seller2 = new Seller();
        seller.setName("SELLER02");
        seller.setShopAddress("SEOUL");

        em.persist(buyer);
        em.persist(buyer2);
        em.persist(seller);
        em.persist(seller2);

        em.flush();
        em.clear();

        System.out.println(em.find(Seller.class, seller.getId()));
        System.out.println(em.find(Seller.class, seller2.getId()));

        System.out.println(em.find(Buyer.class, buyer.getId()));
        System.out.println(em.find(Buyer.class, buyer2.getId()));

//        em.find(BaseEntity.class, buyer.getId()); // ERROR
    }
}
