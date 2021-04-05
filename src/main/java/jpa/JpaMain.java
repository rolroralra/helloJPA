package jpa;

import model.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
//            Member member = new Member(1L, "root");
//            em.persist(member);

//            Member member = new Member(3L, "rolroralra");
//            insertMember(em, member);
//            updateMember(em, 3L, new Member(3L, "guest"));

//            selectAllMembers(em).forEach(System.out::println);
            System.out.println(selectMember(em, 3L));

            System.out.println("===== BEFORE COMMIT =====");
            tx.commit();
            System.out.println("===== BEFORE COMMIT =====");

        } catch (Exception e) {
            System.out.println("===== BEFORE ROLLBACK =====");
            tx.rollback();
            System.out.println("===== BEFORE ROLLBACK =====");
        } finally {
            em.close();
        }


        emf.close();
    }

    private static void insertMember(EntityManager em, Member member) {
        em.persist(member);
    }

    private static void updateMember(EntityManager em, Long id, Member member) {
        Member oldMember = em.find(Member.class, id);
        oldMember.setName(member.getName());
    }

    private static Member selectMember(EntityManager em, Long id) {
        return em.find(Member.class, id);
    }

    private static List<Member> selectAllMembers(EntityManager em) {
        return em.createQuery("select m from Member as m", Member.class)
                .setMaxResults(10)
                .getResultList();
    }




}
