package jpa;

import domain.Member;
import java.util.List;
import javax.persistence.EntityManager;

public class JpaMain extends JpaMainTemplate {
    public static void main(String[] args) {
        new JpaMain().run("test");
    }

    private static void insertMember(EntityManager em, Member member) {
        em.persist(member);
    }

    private static void updateMember(EntityManager em, Long id, Member member) {
        Member oldMember = em.find(Member.class, id);
        oldMember.setUsername(member.getUsername());
    }

    private static void deleteMember(EntityManager em, Long id) {
        Member findMember = em.find(Member.class, id);
        em.remove(findMember);
    }

    private static Member selectMember(EntityManager em, Long id) {
        return em.find(Member.class, id);
    }

    private static List<Member> selectAllMembers(EntityManager em) {
        return em.createQuery("select m from Member as m", Member.class)
                .setMaxResults(10)
                .getResultList();
    }

    @Override
    public void logic(EntityManager em) {
        Member member = new Member();
        member.setUsername("root");
        member.setRoleType(Member.RoleType.ADMIN);

        Member member2 = new Member();
        member2.setUsername("admin");
        member2.setRoleType(Member.RoleType.ADMIN);

        Member member3 = new Member();
        member3.setUsername("guest");
        member3.setRoleType(Member.RoleType.GUEST);


        System.out.println("===== BEFORE PERSIST =====");
        em.persist(member);
        em.persist(member2);
        em.persist(member3);
        System.out.println("===== AFTER PERSIST =====");
        System.out.println(member);
        System.out.println(member2);
        System.out.println(member3);
    }
}
