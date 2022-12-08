package org.example.mapping.pkfk.idclass;

import jakarta.persistence.EntityManager;
import org.example.template.JpaMainTemplate;

public class SampleCode extends JpaMainTemplate {

    public static void main(String[] args) {
        new SampleCode().run();
    }

    @Override
    protected void logic(EntityManager em) {
        Parent parent = Parent.builder()
            .name("parent")
            .build();

        em.persist(parent);

        Child child = Child.builder()
            .id(1L)
            .name("child")
            .parent(parent)
            .build();

        em.persist(child);

        GrandChild grandChild = GrandChild.builder()
            .id(1L)
            .name("grand-child")
            .child(child)
            .build();

        em.persist(grandChild);

        em.flush();
        em.clear();

        Parent findParent = em.find(Parent.class, parent.getId());
        System.out.println("findParent = " + findParent);



        ChildId childId = ChildId.builder()
            .id(child.getId())
            .parent(parent.getId())
            .build();
        Child findChild = em.find(Child.class, childId);
        System.out.println("findChild = " + findChild);


        GrandChildId grandChildId = GrandChildId.builder()
            .id(grandChild.getId())
            .child(childId)
            .build();
        GrandChild findGrandChild = em.find(GrandChild.class,grandChildId);

        System.out.println("findGrandChild = " + findGrandChild);
    }
}
