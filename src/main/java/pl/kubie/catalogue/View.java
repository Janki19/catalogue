package pl.kubie.catalogue;

import java.util.List;
import java.util.Scanner;

class View {

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
    private final String filmsList="[1] Show films list.";
    private final String addFilm="[2] Add new film.";
    private final String exit="[0] EXIT";

    public  int displayMainMenuView(){
        answer=new Scanner(System.in);
        System.out.println("\n\t"+welcome+"\n");
        System.out.println("\t\t"+filmsList);
        System.out.println("\t\t"+addFilm);
        System.out.println("\n\t\t"+exit);

        return checkMainMenuChoice(answer.nextLine());
    }

      /*
        Films list view
     */
      private final String welcomeList="FILMS LIST";

    public int dispalyFilmsList(List<FilmsModel>list){
        int retanswer=0;
        System.out.println(borderLine);
        System.out.println("\n\t"+welcomeList+"\n");
        System.out.println(list);
        System.out.println("\t"+backTitle+"\n");

        while(true) {
            answer=new Scanner(System.in);
            try {
                int getanswer = answer.nextInt();
                if (getanswer == 9) {
                    retanswer = getanswer;
                    break;
                }else System.out.println("Try again!");
            } catch (Exception e) {
                System.out.println("Try again!");
            }
        }
       return retanswer;
    }

     /*
        Add film view
     */
    private final String welcomeAdd="ADD FILM TO LIST";
    private final String titleRequest="Type in the title of film:";
    private final String yearRequest="Type in a year of production:";
    private final String saveRequest="Do you want to save this film Y/N";

    public boolean dispalyAdd(){
        answer=new Scanner(System.in);
        boolean ret=false;
        System.out.println(borderLine);
        System.out.println("\n\t"+welcomeAdd+"\n");

            System.out.println(titleRequest);
            this.titleOfFilm=answer.nextLine();

            System.out.println("\n"+yearRequest);
            this.yearOfFilm=checkAddManuYear(answer.nextLine());

            System.out.println("\n"+saveRequest);
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

    /*
    Methods to check input-------
     */

    //Check Main Menu choice
    private int checkMainMenuChoice(String input){
        try {
            int number = Integer.valueOf(input);
            if (number == 1 || number == 2 || number == 0) {
                ret =number;
            }
            else{
                wrongAnswerMainMenu();
            }
        }catch(Exception e){
            wrongAnswerMainMenu();
        }
        return ret;
    }

    private void wrongAnswerMainMenu(){
        System.out.println("\tTry again!");
        answer=new Scanner(System.in);
        checkMainMenuChoice(answer.nextLine());
    }

    //Check Add New film input

    private String checkAddManuYear(String year){

        String tempYear=null;
        boolean digitProof=true;
        boolean lengthProof=true;

            if(year.length()>4){
                lengthProof=false;
            }
            for (int i = 0; i < year.length(); i++) {
                if(Character.isDigit(year.charAt(i))) {
                }else{
                    digitProof=false;
                    break;
                }
            }
            if(digitProof&&lengthProof){
                tempYear=year;
            }
            else{
                wrongAnswerYear();
            }
            return tempYear;
    }

    private void wrongAnswerYear(){
        System.out.println("\tTry again!");
        answer=new Scanner(System.in);
        checkAddManuYear(answer.nextLine());
    }

    //Check films list input
    private int checkAddManuYear(int number) {
        int choice = Integer.valueOf(number);

        return choice;
    }


}
