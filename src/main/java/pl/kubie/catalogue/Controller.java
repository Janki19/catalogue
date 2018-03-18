package pl.kubie.catalogue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class Controller {

    private Movie model;
    private View view;
    private MovieDao mDao;

    public Controller(View view, MovieDao mDao) {
        this.mDao = mDao;
        this.view = view;
        displayMainMenu();
    }

    public void displayMainMenu() {
        int menuAnswer = view.displayMainMenuView();
        switchMainMenu(menuAnswer);
    }

    public void displayMoviesList() {
        int menuAnswer = view.displayMoviesList(mDao.findAll());
        switchToBackToMenu(menuAnswer);
    }

    public void displayAddFilm() {
        boolean saveAnswer = view.dispalyAdd();
        if (saveAnswer) {
            saveMovie();
            view.clearTitleYear();
            backToMainMenu();
        } else {
            view.clearTitleYear();
            backToMainMenu();
        }
    }

    private void saveMovie() {
        model = new Movie();
        model.setTitle(view.getTitleOfMovie());
        model.setYear(view.getYearOfMovie());
        mDao.addUpdMovie(model);
        view.clearTitleYear();
    }

    public void displayRemoveMovie() {
        int id = view.displayRemove();
        if (view.displayRemoveConfirm(mDao.findById(id))) {
            mDao.deleteMovie(id);
            backToMainMenu();
        } else {
            backToMainMenu();
        }
    }

    public void displayMoviesRatings() {
        model = new Movie();
        int id = view.displayChoiseToRatings();
        model = mDao.findById(id);
        int rating = view.displayRatingMovie(model);
        model.countRating(rating);
        mDao.addUpdMovie(model);
        backToMainMenu();
    }

    public void searchMovie() {
        int choice = view.displaySearchMenu();
        switchSearch(choice);
    }

    public void searchByTitle() {
        String answer = view.searchByTitle();
        int menu = view.displaySearchByTitle(mDao.searchByTitle(answer), answer);
        switchToBackToMenu(menu);
    }

    public void searchByDate() {
        LocalDate date = view.searchByDate();
        int menu = view.displaySearchByDate(mDao.searchByDate(date), date);
        switchToBackToMenu(menu);
    }

    /*
        Method to handle the selection in Main Menu
     */
    public void switchMainMenu(int number) {
        switch (number) {
            case 1:
                displayMoviesList();
                break;
            case 2:
                displayAddFilm();
                break;
            case 3:
                displayRemoveMovie();
                break;
            case 4:
                displayMoviesRatings();
                break;
            case 5:
                searchMovie();
                break;
            case 0:
                mDao.closeConnection();
                System.exit(0);
                break;
        }
    }


    /*
    Method to handle the selection in Films List
    */
    public void switchToBackToMenu(int number) {
        switch (number) {
            case 9:
                displayMainMenu();
                break;
        }
    }

    /*
    Method to handle the selection in Search Menu
 */
    public void switchSearch(int number) {
        switch (number) {
            case 1:
                searchByTitle();
                break;
            case 2:

                break;
            case 3:
                searchByDate();
                break;
            case 0:
                backToMainMenu();
                break;
        }
    }

    /*
    Method to handle the selection in Add Movie
    */
    public void backToMainMenu() {
        displayMainMenu();
    }

}
