package pl.kubie.catalogue;

class ConsoleApp {
    public static void main(String... args) {

        View view = new View();
      //  DAO dao = new DAO();
        MovieDao mDao=new MovieDao();
        new Controller(view, mDao);
    }
}
