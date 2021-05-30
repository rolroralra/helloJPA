package jpa.proxy;

import domain.team.Member;
import jpa.JpaMainTemplate;
import org.hibernate.Hibernate;

import javax.persistence.EntityManager;

public class JpaMain extends JpaMainTemplate {
    public static void main(String[] args) {
        new JpaMain().run();
    }

    @Override
    public void logic(EntityManager em) {
        new jpa.team.JpaMain().logic(em);
        System.out.println();

        em.clear();
//        System.out.println(em.find(Member.class, 1L));
        Member member = em.getReference(Member.class, 1L);

        // org.hibernate.LazyInitializationException : could not initialize proxy - no Session
//         em.detach(member);
//         em.clear();
//         em.close();

        System.out.println(member.getName());

        System.out.printf("isLoaded = %s\n", emf.getPersistenceUnitUtil().isLoaded(member));
        if (!emf.getPersistenceUnitUtil().isLoaded(member)) {
            Hibernate.initialize(member);
        }

        System.out.println("=================");
        System.out.println(member.getTeam());
        System.out.println("=================");
        System.out.println(em.getReference(Member.class, 1L));
        System.out.println("=================");
        System.out.println(member.getClass());
        System.out.println("=================");
        System.out.println(em.getReference(Member.class, 1L) instanceof Member);

        System.out.println(em.getReference(Member.class, 1L));
        System.out.println(em.find(Member.class, 1L));
        System.out.println(em.getReference(Member.class, em.find(Member.class, 1L).getId()).getClass());

        System.out.println("=================");

        member = em.find(Member.class, 1L);
        Member reference = em.getReference(Member.class, member.getId());
        System.out.println(member.getClass());
        System.out.println(reference.getClass());


    }
}
