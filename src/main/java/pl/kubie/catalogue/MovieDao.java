package pl.kubie.catalogue;

import javax.persistence.*;
import java.util.List;

public class MovieDao {
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    public MovieDao() {
        entityManagerFactory = Persistence.createEntityManagerFactory("MyJpa");
        entityManager = entityManagerFactory.createEntityManager();
    }

    /*
    Insert new record to database by two parameters "title" and "year".
    */
    public void addMovie(Movie model) {
        entityManager.getTransaction().begin();
        entityManager.persist(model);
        entityManager.getTransaction().commit();
        System.out.println("New record added/updated");
    }

    /*
    Display all records from data base.
    */
    public List<Movie> findAll() {
        TypedQuery<Movie> query = entityManager.createQuery("SELECT  m FROM Movie m", Movie.class);
        return query.getResultList();
    }

    public void deleteMovie(int id) {
        entityManager.getTransaction().begin();
        entityManager.remove(findById(id));
        entityManager.getTransaction().commit();
    }

    public Movie findById(int id) {
        Movie movie = entityManager.find(Movie.class, id);
        return movie;
    }

    public void updateRating(int id, float rating, int votes) {
        Query query = entityManager.createQuery("UPDATE Movie SET");
    }

    public void closeConnection() {
        entityManager.close();
        entityManagerFactory.close();
    }
}


