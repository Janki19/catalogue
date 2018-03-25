package pl.kubie.catalogue;

import java.time.LocalDate;

class Controller {
    private Movie movieModel;
    private View view;
    private MovieDao mDao;

    public Controller(View view, MovieDao mDao) {
        this.mDao = mDao;
        this.view = view;
        displayMainMenu();
    }

    private void displayMainMenu() {
        int menuAnswer = view.displayMainMenuView();
        switchMainMenu(menuAnswer);
    }

    private void displayMoviesList() {
        int menuAnswer = view.displayMoviesList(mDao.showAll());
        switchToBackToMenu(menuAnswer);
    }

    private void displayAddMovie() {
        movieModel = new Movie();
        boolean saveAnswer = view.displayAdd();
        if (saveAnswer) {
            saveMovie(movieModel);
            backToMainMenu();
        } else {
            backToMainMenu();
        }
    }

    private void displayEditMovie() {
        view.choiceMovieToEdit();
        boolean saveAnswer = view.enterEditMovie(ifNull());
        if (saveAnswer) {
            saveMovie(movieModel);
            backToMainMenu();
        } else {
            backToMainMenu();
        }
    }

    private void saveMovie(Movie movie) {
        movie.setTitle(view.getTitleOfMovie());
        movie.setYear(view.getYearOfMovie());
        mDao.addMovie(movie);
    }

    private void displayRemoveMovie() {
        view.displayRemove();
        boolean removeAnswer = view.displayRemoveConfirm(ifNull());
        if (removeAnswer) {
            mDao.deleteMovie(movieModel);
            backToMainMenu();
        } else {
            backToMainMenu();
        }
    }

    private void displayMoviesRatings() {
        MoviesRate moviesRate = new MoviesRate();
        view.displayChoiceToRatings();
        int rate = view.displayRatingMovie(ifNull());
        moviesRate.setRate(rate);
        movieModel.getRateList().add(moviesRate);
        movieModel.setRating(movieModel.calculateRating());
        mDao.addMovie(movieModel);
        backToMainMenu();
    }

    private void searchMovie() {
        int choice = view.displaySearchMenu();
        switchSearch(choice);
    }

    private void searchByTitle() {
        String answer = view.searchByTitle();
        int menu = view.displaySearchByTitle(mDao.searchByTitle(answer), answer);
        switchToBackToMenu(menu);
    }

    private void searchByDate() {
        LocalDate date = view.searchByDate();
        int menu = view.displaySearchByDate(mDao.searchByDate(date), date);
        switchToBackToMenu(menu);
    }

    private void searchByRate() {
        double rate = view.searchByRate();
        int menu = view.displaySearchByRate(mDao.searchByRate(rate), rate);
        switchToBackToMenu(menu);
    }

    private void addComment() {
        view.displayChoiceToComment();
        movieModel = ifNull();
        movieModel.getCommentsList().add(view.displayEnterComment(movieModel));
        mDao.addMovie(movieModel);
        backToMainMenu();
    }

    private void showComment() {
        int movieId = view.displayShowComment();
        movieModel = new Movie();
        movieModel = mDao.searchById(movieId);
        int menu = view.displayComments(movieModel);
        switchToBackToMenu(menu);
    }

    /*
        Method to handle the selection in Main Menu
     */
    private void switchMainMenu(int number) {
        switch (number) {
            case 1:
                displayMoviesList();
                break;
            case 2:
                displayAddMovie();
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
            case 6:
                displayEditMovie();
                break;
            case 7:
                addComment();
                break;
            case 8:
                showComment();
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
    private void switchToBackToMenu(int number) {
        switch (number) {
            case 9:
                displayMainMenu();
                break;
        }
    }

    /*
    Method to handle the selection in Search Menu
 */
    private void switchSearch(int number) {
        switch (number) {
            case 1:
                searchByTitle();
                break;
            case 2:
                searchByRate();
                break;
            case 3:
                searchByDate();
                break;
        }
    }

    /*
    Method to handle the selection in Add Movie
    */
    private void backToMainMenu() {
        displayMainMenu();
    }


    //Checking searchById return.
    private Movie ifNull() {
        while (true) {
            movieModel = mDao.searchById(view.enterId());
            if (movieModel != null) {
                break;
            } else {
                view.idNotFounded();
            }
        }
        return movieModel;
    }

}
