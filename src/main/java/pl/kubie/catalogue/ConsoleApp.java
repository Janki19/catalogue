package pl.kubie.catalogue;

public class ConsoleApp {
  public static void main(String... args) {

       View view=new View();
       DAO dao=new DAO();
       new Controller(view,dao);
  }
}
