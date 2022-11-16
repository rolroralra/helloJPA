package jpa.cascadeOrphan;

import domain.cascadeOrphan.Child;
import domain.cascadeOrphan.Parent;
import javax.persistence.EntityManager;
import jpa.JpaMainTemplate;

public class JpaMain extends JpaMainTemplate {
    public static void main(String[] args) {
        new JpaMain().run();
    }
    @Override
    public void logic(EntityManager em) {
        Parent parent = new Parent();
        parent.setName("PARENT_1");

        Child child = new Child();
        child.setName("CHILD_1");
        Child child2 = new Child();
        child2.setName("CHILD_2");
        Child child3 = new Child();
        child3.setName("CHILD_3");

        parent.addChild(child);
        parent.addChild(child2);
        parent.addChild(child3);

        em.persist(parent);
//        em.persist(child);
//        em.persist(child2);
//        em.persist(child3);

        em.flush();
        em.clear();

        Parent findParent = em.find(Parent.class, parent.getId());
        findParent.getChildren().remove(0);

        em.remove(findParent);

    }
}
