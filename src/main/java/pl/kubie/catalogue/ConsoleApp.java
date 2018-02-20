package pl.kubie.catalogue;

public class ConsoleApp {
  public static void main(String... args) {

       ObjectModel model=new ObjectModel();
       View view=new View();
       new Controller(model,view);


  }
}
