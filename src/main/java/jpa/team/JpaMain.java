package jpa.team;

import domain.team.Member;
import domain.team.Team;

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
            Team teamA = new Team();
            teamA.setName("TEAM_A");
            em.persist(teamA);

            Team teamB = new Team();
            teamB.setName("TEAM_B");
            em.persist(teamB);

            Member memberA = new Member();
            memberA.setTeam(teamA);
            memberA.setName("MEMBER_A");
            em.persist(memberA);

            Member memberB = Member.builder().name("MEMBER_B").team(teamB).build();
            em.persist(memberB);

            em.flush();
            em.clear();

//            Team findTeam = em.find(Team.class, team.getId());
            Member findMember = em.find(Member.class, memberA.getId());

            System.out.println("TEAM: " + findMember.getTeam());
            System.out.println("MEMBERS:");
            findMember.getTeam().getMembers().forEach(System.out::println);

            findMember.setTeam(teamB);

            em.flush();
            em.clear();

            Team findTeam = em.find(Team.class, teamB.getId());
            findTeam.getMembers().forEach(System.out::println);


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
}
