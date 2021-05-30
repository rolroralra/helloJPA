package jpa.inheritance;

import domain.inheritance.Album;
import domain.inheritance.ArtItem;
import domain.inheritance.Book;
import domain.inheritance.Movie;
import jpa.JpaMainTemplate;

import javax.persistence.EntityManager;

public class JpaMain extends JpaMainTemplate {

    public static void main(String[] args) {
        new JpaMain().run();
    }

    @Override
    public void logic(EntityManager em) {
        Movie movie = new Movie();
        movie.setPrice(1000);
        movie.setActor("조인성");
        movie.setName("비열한 거리");
        movie.setDirector("김감독");

        Book book = new Book();
        book.setPrice(200);
        book.setName("흔들려야 어른이 된다");
        book.setAuthor("김난도");

        Album album = new Album();
        album.setName("Geography");
        album.setPrice(3000);
        album.setArtist("Tom Misch");

        System.out.println("===== BEFORE PERSIST =====");

        em.persist(movie);
        em.persist(book);
        em.persist(album);

        System.out.println("===== AFTER PERSIST =====");

        em.flush();
        em.clear();

        System.out.println(em.find(ArtItem.class, movie.getId()));
        System.out.println(em.find(ArtItem.class, book.getId()));
        System.out.println(em.find(ArtItem.class, album.getId()));
    }
}
