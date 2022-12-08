package org.example.mapping.fk;

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
            .name("child")
            .parent(parent)
            .build();

        em.persist(child);

        GrandChild grandChild = GrandChild.builder()
            .name("grand-child")
            .child(child)
            .build();

        em.persist(grandChild);

        em.flush();
        em.clear();

        Parent findParent = em.find(Parent.class, parent.getId());
        System.out.println("findParent = " + findParent);

        Child findChild = em.find(Child.class, child.getId());
        System.out.println("findChild = " + findChild);

        GrandChild findGrandChild = em.find(GrandChild.class, grandChild.getId());

        System.out.println("findGrandChild = " + findGrandChild);
    }
}
