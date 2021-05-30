package jpa;

import org.hibernate.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public abstract class JpaMainTemplate {
    protected EntityManagerFactory emf;
    protected EntityManager em;
    protected EntityTransaction tx;

    public void run() {
        run("test");
    }

    public void run(String persistenceUnitName) {
        emf = Persistence.createEntityManagerFactory(persistenceUnitName);

        em = emf.createEntityManager();

        tx = em.getTransaction();
        tx.begin();

        try {
            logic(em);

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

    abstract public void logic(EntityManager em);

}
