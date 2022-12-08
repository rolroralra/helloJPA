package org.example.mapping.pkfk.embeddedid;

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

        ChildId childId = ChildId.builder()
            .parentId(parent.getId())
            .id(1L)
            .build();

        Child child = Child.builder()
            .id(childId)
            .name("child")
            .parent(parent)
            .build();

        em.persist(child);


        GrandChildId grandChildId = GrandChildId.builder()
            .childId(childId)
            .id(7L)
            .build();

        GrandChild grandChild = GrandChild.builder()
            .id(grandChildId)
            .name("grand-child")
            .child(child)
            .build();

        em.persist(grandChild);

        em.flush();
        em.clear();

        Parent findParent = em.find(Parent.class, parent.getId());
        System.out.println("findParent = " + findParent);


        childId = ChildId.builder()
            .id(child.getId().getId())
            .parentId(child.getId().getParentId())
            .build();
        Child findChild = em.find(Child.class, childId);
        System.out.println("findChild = " + findChild);


        grandChildId = GrandChildId.builder()
            .id(grandChild.getId().getId())
            .childId(grandChild.getId().getChildId())
            .build();
        GrandChild findGrandChild = em.find(GrandChild.class,grandChildId);

        System.out.println("findGrandChild = " + findGrandChild);
    }
}
