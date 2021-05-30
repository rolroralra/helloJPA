package jpa.team;

import domain.team.Locker;
import domain.team.Member;
import domain.team.Team;
import jpa.JpaMainTemplate;

import javax.persistence.EntityManager;

public class JpaMain extends JpaMainTemplate {
    public static void main(String[] args) {
        new JpaMain().run();
    }

    @Override
    public void logic(EntityManager em) {
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


        Locker locker = new Locker();
        locker.setName("LOCKER_01");
        locker.setMember(memberA);
        memberA.setLocker(locker);

        em.persist(locker);
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
    }
}
