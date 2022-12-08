package org.example.mapping.idclass;

import jakarta.persistence.EntityManager;
import org.example.template.JpaMainTemplate;

public class SampleCode extends JpaMainTemplate {

    public static void main(String[] args) {
        new SampleCode().run();
    }

    @Override
    protected void logic(EntityManager em) {

        Parent parent = Parent.builder()
            .id1(1L)
            .id2(1L)
            .name("parent1")
            .build();
        Parent parent2 = Parent.builder()
            .id1(1L)
            .id2(2L)
            .name("parent2")
            .build();

        Child child = Child.builder()
            .name("child1")
            .parent(parent)
            .build();

        Child child2 = Child.builder()
            .name("child2")
            .parent(parent)
            .build();

        Child child3 = Child.builder()
            .name("child3")
            .parent(parent2)
            .build();
        
        em.persist(parent);
        em.persist(parent2);
        em.persist(child);
        em.persist(child2);
        em.persist(child3);
        em.flush();
        em.clear();

        ParentId parentId = ParentId.builder()
            .id1(parent.getId1())
            .id2(parent.getId2())
            .build();

        Parent findParent = em.find(Parent.class, parentId);
        System.out.println("findParent = " + findParent);
        System.out.println("findParent.getChildList() = " + findParent.getChildList());


        Child findChild = em.find(Child.class, child.getId());
        System.out.println("findChild = " + findChild);

    }
}
