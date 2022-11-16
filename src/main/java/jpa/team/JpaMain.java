package jpa.team;

import domain.team.Level;
import domain.team.Locker;
import domain.team.LockerManager;
import domain.team.Member;
import domain.team.MemberProduct;
import domain.team.MemberProductId;
import domain.team.MemberProductWithIdClass;
import domain.team.Product;
import domain.team.Team;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import jpa.JpaMainTemplate;

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

        Product productA = new Product();
        productA.setName("PRODUCT_A");
        Product productB = new Product();
        productB.setName("PRODUCT_B");

        em.persist(productA);
        em.persist(productB);

        Member memberA = new Member();
        memberA.setTeam(teamA);
        memberA.setName(null);

        em.persist(memberA);


        Member memberB = Member.builder()
            .name("MEMBER_B").team(teamB)
            .productList(new ArrayList<>())
            .build();

        em.persist(memberB);

//        memberB.addProduct(productA);
//        memberB.addProduct(productB);
//        memberA.addProduct(productB);

        memberA.addProduct(productA);
        memberB.addProduct(productA);
        memberB.addProduct(productB);


        Locker locker = new Locker();
        locker.setName("LOCKER_01");
        locker.setMember(memberA);
        memberA.setLocker(locker);
        em.persist(locker);

        LockerManager lockerManager = new LockerManager();
        lockerManager.setName("TARGET_LOCKER_01");
        lockerManager.setMember(memberA);
        em.persist(lockerManager);


        Level level = new Level();
        level.setName("VIP");
        level.addMember(memberA);
        level.addMember(memberB);

        em.persist(level);
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

        Level findLevel = em.find(Level.class, level.getId());
        System.out.println(findLevel.getMemberList());

        Product product1 = em.find(Product.class, productA.getId());
        Product product2 = em.find(Product.class, productB.getId());
        System.out.println(product1.getMemberList().size());
        System.out.println(product2.getMemberList().size());

        MemberProduct memberProduct = new MemberProduct();
        memberProduct.setMember(memberB);
        memberProduct.setProduct(product1);
        memberProduct.setPrice(1000);
        memberProduct.setAmount(10);

        em.persist(memberProduct);

        em.flush();

        Member member = em.find(Member.class, memberB.getId());

        System.out.println(member.getMemberProductList());

        Product product = em.find(Product.class, product1.getId());
        System.out.println(product.getMemberProductList());

        MemberProductWithIdClass memberProductWithIdClass = new MemberProductWithIdClass();
        memberProductWithIdClass.setAmount(20);

        memberProductWithIdClass.setPrice(10);
        memberProductWithIdClass.setProduct(product2);
        memberProductWithIdClass.setMember(memberA);

        em.persist(memberProductWithIdClass);

        em.flush();

        MemberProductId memberProductId = MemberProductId.builder()
            .member(memberA.getId())
            .product(product2.getId())
            .build();
        MemberProductWithIdClass findMemberProduct = em.find(MemberProductWithIdClass.class,
            memberProductId);

        System.out.println(findMemberProduct);
        
        
        

    }
}
