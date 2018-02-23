package pl.kubie.catalogue;

import java.util.List;
import java.util.Scanner;

public class View {

    private Scanner answer;
    private final String backTitle="9. Back to main menu";
    private final String borderLine="--------------------------------------------------------------------------";
    private String titleOfFilm;
    private String yearOfFilm;
    private int ret=99;

    public View(){
    }

    /*
        Main menu view

     */
    private final String welcome="MENU FILMS LIBRARY";
    private final String filmsList="1. Show films list.";
    private final String addFilm="2. Add new film.";
    private final String exit="0. EXIT";

    public  int displayMainMenuView(){
        answer=new Scanner(System.in);
        System.out.println("\n\t"+welcome+"\n");
        System.out.println("\t\t"+filmsList);
        System.out.println("\t\t"+addFilm);
        System.out.println("\n\t\t"+exit);

        return checkMenuChoice(answer.nextLine());
    }

      /*
        Films list view
     */
      private final String welcomeList="FILMS LIST";

    public int dispalyFilmsList(List<FilmsModel>list){
        answer=new Scanner(System.in);
        System.out.println(borderLine);
        System.out.println("\n\t"+welcomeList+"\n");
        System.out.println(list);
        System.out.println("\t"+backTitle+"\n");
       return answer.nextInt();
    }




     /*
        Add film view
     */
    private final String welcomeAdd="ADD FILM TO LIST";
    private final String titleRequest="Type in the title of film:";
    private final String yearRequest="Type in a year of production:";
    private final String nextRequest="Do you want to save this film Y/N";

    public boolean dispalyAdd(){
        answer=new Scanner(System.in);
        boolean ret=false;
        System.out.println(borderLine);
        System.out.println("\n\t"+welcomeAdd+"\n");

            System.out.println(titleRequest);
            this.titleOfFilm=answer.nextLine();

            System.out.println("\n"+yearRequest);
            this.yearOfFilm=answer.nextLine();

            System.out.println("\n"+nextRequest);
            String yesNo=answer.next();

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

    public String getYearOfFilm(){
        return yearOfFilm;
    }
    public void clearTitleYear(){
        this.titleOfFilm=null;
        this.yearOfFilm=null;
    }

    public void wrongAnswerAgain(){
        System.out.println("\tTry again!");
        answer=new Scanner(System.in);
        checkMenuChoice(answer.nextLine());
    }

    public int checkMenuChoice(String input){
        try {
            int number = Integer.valueOf(input);
            if (number == 1 || number == 2 || number == 0) {
                ret = Integer.valueOf(number);
            }
            else{
                wrongAnswerAgain();
            }
        }catch(Exception e){
            wrongAnswerAgain();
        }
        return ret;
    }
}
