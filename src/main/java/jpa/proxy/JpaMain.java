package jpa.proxy;

import domain.proxy.Member;
import domain.proxy.Team;
import javax.persistence.EntityManager;
import jpa.JpaMainTemplate;

public class JpaMain extends JpaMainTemplate {
    public static void main(String[] args) {
        new JpaMain().run();
    }

    @Override
    public void logic(EntityManager em) {

        Member member = new Member();
        member.setName("rolroralra");

        Member member2 = new Member();
        member2.setName("test");

        Member member3 = new Member();
        member3.setName("root");

        Member member4 = new Member();
        member4.setName("admin");

        Team team = new Team();
        team.setName("USER");

        Team team2 = new Team();
        team2.setName("ADMIN");

        member.setTeam(team);
        member2.setTeam(team);
        member3.setTeam(team2);
        member4.setTeam(team2);


        em.persist(team);
        em.persist(team2);

        em.persist(member);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);

        em.flush();
        em.clear();


        System.out.println(em.createQuery("select m from ProxyMember m join fetch m.team").getResultList());
//        System.out.println(em.createQuery("select t from ProxyTeam t where t.id = 1").getResultList());

//        System.out.println(em.find(Member.class, member.getId()));
//        System.out.println(em.find(Member.class, member.getId()));
//        System.out.println(em.find(Member.class, member2.getId()));
//        System.out.println(em.find(Member.class, member3.getId()));
//        System.out.println(em.find(Member.class, member4.getId()));
//
//        System.out.println(em.find(Team.class, team.getId()));
//        System.out.println(em.find(Team.class, team2.getId()));
    }
}
