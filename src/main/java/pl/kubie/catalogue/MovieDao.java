package pl.kubie.catalogue;

import javax.persistence.*;
import java.time.LocalDate;
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
    public void addUpdMovie(Movie model,MoviesRate moviesRate) {

        entityManager.getTransaction().begin();
        entityManager.persist(model);
        entityManager.persist(moviesRate);
        entityManager.getTransaction().commit();
        System.out.println("Movie added/updated");
    }

    /*
    Display all records from data base.
    */
    public List<Movie> findAll() {
        TypedQuery<Movie> query = entityManager.createQuery("SELECT m FROM Movie m", Movie.class);
        return query.getResultList();
    }

    public void deleteMovie(int id) {
        entityManager.getTransaction().begin();
        entityManager.remove(findById(id));
        entityManager.getTransaction().commit();
    }

    public void updateRate(MoviesRate moviesRate){
        entityManager.getTransaction().begin();
        entityManager.persist(moviesRate);
        entityManager.getTransaction().commit();
    }

    public Movie findById(int id) {
        Movie movie = entityManager.find(Movie.class, id);
        return movie;
    }

    public List<Movie> searchByTitle(String eTitle) {
        TypedQuery<Movie> query = entityManager.createQuery("SELECT m FROM Movie m WHERE m.title LIKE :eTitle", Movie.class);
        query.setParameter("eTitle", "%" + eTitle + "%");
        return query.getResultList();
    }

    public List<Movie> searchByDate(LocalDate eDate) {
        TypedQuery<Movie> query = entityManager.createQuery("SELECT m FROM Movie m WHERE m.dateNow = :eDate", Movie.class);
        query.setParameter("eDate", eDate);
        return query.getResultList();
    }


    public void closeConnection() {
        entityManager.close();
        entityManagerFactory.close();
    }
}


