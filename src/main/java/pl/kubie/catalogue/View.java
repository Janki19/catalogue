package pl.kubie.catalogue;

import java.util.List;
import java.util.Scanner;

class View {

    private Scanner answer;
    private final String backTitle = "[9] Back to main menu";
    private final String borderLine = "--------------------------------------------------------------------------";
    private String titleOfMovie;
    private String yearOfMovie;

    public View() {
    }

    /*
        Main menu view

     */
    private final String welcome = "MENU MOVIES LIBRARY";
    private final String moviesList = "[1] Show movies list";
    private final String addMovie = "[2] Add new movie";
    private final String removeMovie = "[3] Remove movie";
    private final String exit = "[0] EXIT";

    public int displayMainMenuView() {
        System.out.println("\n\t" + welcome + "\n");
        System.out.println("\t\t" + moviesList);
        System.out.println("\t\t" + addMovie);
        System.out.println("\t\t" + removeMovie);
        System.out.println("\n\t\t" + exit);

        return mainMenuChoice();
    }

    /*
      Movies list view
   */
    private final String welcomeList = "MOVIES LIST";

    public int dispalyMoviesList(List<MovieModel> list) {
        System.out.println(borderLine);
        System.out.println("\n\t" + welcomeList + "\n");
        System.out.println(list);
        System.out.println("\t" + backTitle + "\n");

        return checkMoviesListInput();
    }

    /*
       Add movie view
    */
    private final String welcomeAdd = "ADD MOVIE TO LIST";
    private final String titleRequest = "Type in the title of movie:";
    private final String yearRequest = "Type in a year of production:";
    private final String saveRequest = "Do you want to save this movie Y/N";

    public boolean dispalyAdd() {
        answer = new Scanner(System.in);
        System.out.println(borderLine);
        System.out.println("\n\t" + welcomeAdd + "\n");

        System.out.println(titleRequest);
        this.titleOfMovie = answer.nextLine();

        System.out.println("\n" + yearRequest);
        this.yearOfMovie = yearInput();

        System.out.println("\n" + saveRequest);

        return selectYesNo();
    }

    /*
    Remove movie view
     */
    private final String welcomeRemove = "REMOVE MOVIE FROM LIST";
    private final String removeQuestion = "Type in number of the movie to be removed.";
    private final String removeConfirm = "Are you sure you want to delete this movie? Y/N";

    public int displayRemove() {
        answer = new Scanner(System.in);
        System.out.println(borderLine);
        System.out.println("\n\t" + welcomeRemove + "\n");
        System.out.println(removeQuestion);
        return answer.nextInt();
    }

    public boolean displayRemoveConfirm(MovieModel movie) {
        answer = new Scanner(System.in);
        System.out.println(movie);
        System.out.println(removeConfirm);
        return selectYesNo();
    }

    /*
    Methods to check input-------
    */

    //Check Main Menu choice
    private int mainMenuChoice() {
        int retchoise;
        while (true) {
            answer = new Scanner(System.in);
            try {
                int tempnumber = answer.nextInt();
                if (tempnumber == 1 || tempnumber == 2 || tempnumber == 3 || tempnumber == 0) {
                    retchoise = tempnumber;
                    break;
                } else {
                    System.out.println("Try again!");
                }
            } catch (Exception e) {
                System.out.println("Try again!");
            }
        }
        return retchoise;
    }

    //Check movies list input
    private int checkMoviesListInput() {
        int retAnswer = 0;
        while (true) {
            answer = new Scanner(System.in);
            try {
                if (answer.nextInt() == 9) {
                    retAnswer = 9;
                    break;
                } else System.out.println("Try again!");
            } catch (Exception e) {
                System.out.println("Try again!");
            }
        }
        return retAnswer;
    }

    //Checking Add New Movies input
    private String yearInput() {
        int tempYear;
        while (true) {
            answer = new Scanner(System.in);
            try {
                int imputYear = answer.nextInt();
                if (imputYear < 2020) {
                    tempYear = imputYear;
                    break;
                } else {
                    System.out.println("Try again!");
                }
            } catch (Exception e) {
                System.out.println("Try again!");
            }
        }
        return String.valueOf(tempYear);
    }

    // Checking the answer and return true if record need to be saved.

    private boolean selectYesNo() {
        boolean temReturn = false;
        while (true) {
            answer = new Scanner(System.in);
            String yesNo = answer.nextLine();
            if (yesNo.equalsIgnoreCase("y")) {
                temReturn = true;
                break;
            } else if (yesNo.equalsIgnoreCase("n")) {
                break;
            } else {
                System.out.println("Wrong answer!");
            }
        }
        return temReturn;
    }

    // Getters and cleaner to Add Film temporary variables.
    public String getTitleOfMovie() {
        return titleOfMovie;
    }

    public String getYearOfMovie() {
        return yearOfMovie;
    }

    public void clearTitleYear() {
        this.titleOfMovie = null;
        this.yearOfMovie = null;
    }


}
