package pl.kubie.catalogue;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

class ConsoleApp {
    public static void main(String... args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("MyJpa");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Controller controller = new Controller(new View(), new MovieDao(entityManager));
        MenuSwitch menuSwitch = new MenuSwitch(controller);
        menuSwitch.switchMainMenu();

        entityManager.close();
        entityManagerFactory.close();
    }

}
