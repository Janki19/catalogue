package pl.kubie.catalogue;

import java.util.List;
import java.util.Scanner;

public class View {

    private Scanner answer=new Scanner(System.in);
    private final String backTitle="9. To back to main menu";
    private final String borderLine="--------------------------------------------------------------------------";
    private String titleOfFilm;
    private int yearOfFilm;

    public View(){
    }

    /*
        Main menu view

     */
    private final String welcome="MENU FILMS LIBRARY";
    private final String listFilms="1. Show films list.";
    private final String addFilm="2. Add new film.";
    private final String editFilm="3. Edit film.";
    private final String deleteFilm="4. Delete film;";
    private final String exit="0. EXIT";

    public  int displayMainMenuView(){
        System.out.println("\n\t"+welcome+"\n");
        System.out.println("\t\t"+listFilms);
        System.out.println("\t\t"+addFilm);
        System.out.println("\t\t"+editFilm);
        System.out.println("\t\t"+deleteFilm);
        System.out.println("\n\t\t"+exit);

        return answer.nextInt();
    }

      /*
        List view
     */
      private final String welcomeList="FILMS LIST";

    public int dispalyFilmsList(){
        System.out.println(borderLine);
        System.out.println("\n\t"+welcomeList+"\n");

        System.out.println("\t"+backTitle+"\n");
        return answer.nextInt();
    }




     /*
        Add view
     */
    private final String welcomeAdd="ADD FILM TO LIST";
    private final String titleRequest="Type in the title of film:";
    private final String yearRequest="Type in a year of production:";
    private final String nextRequest="Do you want to save this film Y/N";

    public boolean dispalyAdd(){

        boolean ret=false;
        System.out.println(borderLine);
        System.out.println("\n\t"+welcomeAdd+"\n");

            Scanner input=new Scanner(System.in);
            System.out.println(titleRequest);
            this.titleOfFilm=input.nextLine();

            System.out.println("\n"+yearRequest);
            this.yearOfFilm=input.nextInt();

            System.out.println("\n"+nextRequest);
            String yesNo=input.next();

                if(yesNo.equalsIgnoreCase("y")){
                    ret=true;
                }
                else if(yesNo.equalsIgnoreCase("n")){
                    System.out.println("No");
                }
                else{
                    System.out.println("Wrong answer!");
                }
                return ret;
    }

    public String getTitleOfFilm(){
        return titleOfFilm;
    }

    public int getYearOfFilm(){
        return yearOfFilm;
    }

    /*
        Edit view
    */
    private final String welcomeEdit="EDIT FILM";
    public void dispalyEdit(){
        System.out.println(borderLine);
        System.out.println("\n\t"+welcomeEdit+"\n");

        System.out.println("\t"+backTitle+"\n");
    }

    /*
     Delete view
    */
    private final String welcomeDelete="DELETE FILM";
    public void dispalyDelete(){
        System.out.println(borderLine);
        System.out.println("\n\t"+welcomeDelete+"\n");

        System.out.println("\t"+backTitle+"\n");
    }
}
