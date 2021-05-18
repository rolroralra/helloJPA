package jpa.inheritance;

import domain.inheritance.Book;
import domain.inheritance.Movie;

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

            Movie movie = new Movie();
            movie.setPrice(1000);
            movie.setActor("조인성");
            movie.setName("비열한 거리");
            movie.setDirector("김감독");

            Book book = new Book();
            book.setPrice(200);
            book.setName("BookName");
            book.setAuthor("Author_01");



            System.out.println("===== BEFORE PERSIST =====");

            em.persist(movie);
            em.persist(book);

            System.out.println("===== AFTER PERSIST =====");

            em.flush();
            em.clear();

            em.find(Movie.class, 1L);
            em.find(Book.class, 2L);

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
