package pl.kubie.catalogue;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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
    public void save(MovieModel model){
        getTransaction(model);
        System.out.println("New record added.");
    }





    public void getTransaction(MovieModel model){
        entityManager.getTransaction().begin();
        entityManager.persist(model);
        entityManager.getTransaction().commit();

    }

    public void closeConnection(){
        entityManagerFactory.close();
        entityManager.close();
    }
}


