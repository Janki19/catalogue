package pl.kubie.catalogue;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
        Main menu view----------------------------------------------------
     */
    private final String welcome = "MOVIES LIBRARY MENU";
    private final String moviesList = "[1] Show movies list";
    private final String addMovie = "[2] Add new movie";
    private final String removeMovie = "[3] Remove movie";
    private final String movieRatings = "[4] Movies ratings";
    private final String movieSearch = "[5] Search movie";
    private final String exit = "[0] EXIT";

    public int displayMainMenuView() {
        System.out.println("\n\t" + welcome + "\n");
        System.out.println("\t\t" + moviesList);
        System.out.println("\t\t" + addMovie);
        System.out.println("\t\t" + removeMovie);
        System.out.println("\t\t" + movieRatings);
        System.out.println("\t\t" + movieSearch);
        System.out.println("\n\t\t" + exit);
        return mainMenuChoice();
    }

    /*
      Movies list view------------------------------------------------------
   */
    private final String welcomeList = "MOVIES LIST";

    public int displayMoviesList(List<Movie> list) {
        System.out.println(borderLine);
        System.out.println("\n\t" + welcomeList + "\n");
        System.out.println(list);
        System.out.println("\t" + backTitle + "\n");

        return checkIfBack();
    }

    /*
       Add movie view------------------------------------------------------------
    */
    private final String welcomeAdd = "ADD MOVIE TO LIST";
    private final String titleRequest = "Type in the title of movie:";
    private final String yearRequest = "Type in a year of production:";
    private final String saveRequest = "Do you want to addUpdMovie this movie Y/N";

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
    Remove movie view-----------------------------------------------------------------------
     */
    private final String welcomeRemove = "REMOVE MOVIE FROM LIST";
    private final String removeQuestion = "Type in number of the movie to be removed.";
    private final String removeConfirm = "Are you sure you want to delete this movie? Y/N";

    public int displayRemove() {
        answer = new Scanner(System.in);
        System.out.println(borderLine);
        System.out.println("\n\t" + welcomeRemove + "\n");
        System.out.println(removeQuestion);
        return ifInt();
    }

    public boolean displayRemoveConfirm(Movie movie) {
        answer = new Scanner(System.in);
        System.out.println(movie);
        System.out.println(removeConfirm);
        return selectYesNo();
    }

    /*
    Display movie rating
    */
    private final String ratingsWelcome = "MOVIES RATING";
    private final String movieNrQuestipn = "Enter the number of the movie you want to rate.";

    public int displayChoiseToRatings() {
        System.out.println(borderLine);
        System.out.println("\n\t" + ratingsWelcome + "\n");
        System.out.println(movieNrQuestipn);
        return ifInt();
    }

    private final String ratings = "Movie ratings from 1 to 7.";

    public int displayRatingMovie(Movie movie) {
        System.out.println(movie);
        System.out.println(ratings);
        return checkRatings();
    }

    /*
    Display search menu
     */
    private final String searchWelcome = "SEARCH MOVIE";
    private final String searchByTitle = "1. Search by title.";
    private final String searchByRate = "2. Search by rate.";
    private final String searchByDate = "3. Search by date.";

    public int displaySearchMenu() {
        System.out.println(borderLine);
        System.out.println("\n\t" + searchWelcome + "\n");
        System.out.println("\t\t" + searchByTitle);
        System.out.println("\t\t" + searchByRate);
        System.out.println("\t\t" + searchByDate);
        return searchChoice();
    }

    //Search by title
    private final String questionTitle = "Type in title of movie.";

    public String searchByTitle() {
        System.out.println(questionTitle);
        answer = new Scanner(System.in);
        return answer.nextLine();
    }

    //Display movies founded by title.
    private final String founded = "Founded movies with words: ";

    public int displaySearchByTitle(List<Movie> list, String title) {
        System.out.println(borderLine);
        System.out.println("\n\t" + founded + "' " + title + " '");
        System.out.println("\n" + list);
        System.out.println("\t" + backTitle + "\n");
        return checkIfBack();
    }

    //Search by date
    private final String questionDate = "Type in date of add to search (yyyyMMdd).";

    public LocalDate searchByDate() {
        System.out.println(questionDate);
        return checkIfDateIsCorrect();
    }

    //Display movies founded by date.
    private final String foundedDate = "Founded movies added on: ";

    public int displaySearchByDate(List<Movie> list, LocalDate date) {
        System.out.println(borderLine);
        System.out.println("\n\t" + foundedDate + "' " + date + " '");
        System.out.println("\n" + list);
        System.out.println("\t" + backTitle + "\n");
        return checkIfBack();
    }

    //Search by title
    private final String questionRate = "Type in rate of movie.";

    public int searchByRate() {
        System.out.println(questionRate);
        return ifInt();
    }

    //Display movies founded by rate.
    private final String foundedRate = "Founded movies with rating: ";

    public int displaySearchByRate(List<Movie> list, double rate) {
        int intRate= (int) rate;
        System.out.println(borderLine);
        System.out.println("\n\t" + foundedRate + "' " + intRate + " '");
        System.out.println("\n" + list);
        System.out.println("\t" + backTitle + "\n");
        return checkIfBack();
    }

    /*
    Methods to check inputs------------------------------------------------------------------------------------------
    */

    //Check Main Menu choice
    private int mainMenuChoice() {
        int retChoise;
        while (true) {
            answer = new Scanner(System.in);
            try {
                int tempNumber = answer.nextInt();
                if (tempNumber == 1 || tempNumber == 2 || tempNumber == 3 || tempNumber == 4 || tempNumber == 5 || tempNumber == 0) {
                    retChoise = tempNumber;
                    break;
                } else {
                    System.out.println("Try again!");
                }
            } catch (Exception e) {
                System.out.println("Try again!");
            }
        }
        return retChoise;
    }

    //Check search  choice
    private int searchChoice() {
        int retChoise;
        while (true) {
            answer = new Scanner(System.in);
            try {
                int tempnumber = answer.nextInt();
                if (tempnumber == 1 || tempnumber == 2 || tempnumber == 3 || tempnumber == 0) {
                    retChoise = tempnumber;
                    break;
                } else {
                    System.out.println("Try again!");
                }
            } catch (Exception e) {
                System.out.println("Try again!");
            }
        }
        return retChoise;
    }

    //Check movies list input
    private int checkIfBack() {
        int retAnswer;
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

    //Check if type is int
    private int ifInt() {
        int number;
        while (true) {
            answer = new Scanner(System.in);
            try {
                number = answer.nextInt();
                break;
            } catch (Exception e) {
                System.out.println("Try again!");
            }
        }
        return number;
    }

    //Check ratings between 1 to 7
    private int checkRatings() {
        int ratingAnswer;
        while (true) {
            answer = new Scanner(System.in);
            try {
                int ratingsNumber = answer.nextInt();
                if (ratingsNumber > 0 && ratingsNumber <= 7) {
                    ratingAnswer = ratingsNumber;
                    break;
                } else System.out.println("Try again!");
            } catch (Exception e) {
                System.out.println("Try again!");
            }
        }
        return ratingAnswer;
    }
    //Checks if the date has been entered correctly
    private LocalDate checkIfDateIsCorrect() {
        LocalDate date;
        while (true) {
            answer = new Scanner(System.in);
            String dateInput = answer.nextLine();
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
                date = LocalDate.parse(dateInput, formatter);
                break;
            } catch (DateTimeException e) {
                System.out.println("Entered date is incorrect. Try again.");
            }
        }
        return date;
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
