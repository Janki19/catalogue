package pl.kubie.catalogue;

import java.time.LocalDate;

class Controller {
    private Movie movieModel;
    private View view;
    private MovieDao mDao;
    private ControllerLogic logic=new ControllerLogic();

    public Controller(){
    }

    public Controller(View view, MovieDao mDao) {
        this.mDao = mDao;
        this.view = view;
    }

    public void displayMainMenu() {
        int menuAnswer = view.displayMainMenuView();
        logic.switchMainMenu(menuAnswer);
    }

    public void displayMoviesList() {
        int menuAnswer = view.displayMoviesList(mDao.showAll());
        switchToBackToMenu(menuAnswer);
    }

    public void displayAddMovie() {
        movieModel = new Movie();
        movieModel.setTitle(view.displayAddTitle());
        movieModel.setYear(view.displayAddYear());
        boolean saveAnswer = view.displayAddConfirmation();
        if (saveAnswer) {
            mDao.addMovie(movieModel);
            backToMainMenu();
        } else {
            backToMainMenu();
        }
    }

    public void displayEditMovie() {
        movieModel = new Movie();
        view.choiceMovieToEdit();
        movieModel=ifNull();
        movieModel.setTitle(view.enterEditTitle(movieModel));
        movieModel.setYear(view.displayEditYear());
        boolean saveAnswer = view.displayEditConfirmation();
        if (saveAnswer) {
            mDao.addMovie(movieModel);
            backToMainMenu();
        } else {
            backToMainMenu();
        }
    }

    public void displayRemoveMovie() {
        view.displayRemove();
        boolean removeAnswer = view.displayRemoveConfirm(ifNull());
        if (removeAnswer) {
            mDao.deleteMovie(movieModel);
            backToMainMenu();
        } else {
            backToMainMenu();
        }
    }

    public void displayMoviesRatings() {
        MoviesRate moviesRate = new MoviesRate();
        view.displayChoiceToRatings();
        int rate = view.displayRatingMovie(ifNull());
        moviesRate.setRate(rate);
        movieModel.getRateList().add(moviesRate);
        movieModel.setRating(movieModel.calculateRating());
        mDao.addMovie(movieModel);
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

    public void searchByRate() {
        double rate = view.searchByRate();
        int menu = view.displaySearchByRate(mDao.searchByRate(rate), rate);
        switchToBackToMenu(menu);
    }

    public void addComment() {
        view.displayChoiceToComment();
        movieModel = ifNull();
        movieModel.getCommentsList().add(view.displayEnterComment(movieModel));
        mDao.addMovie(movieModel);
        backToMainMenu();
    }

    public void showComment() {
        view.displayShowComment();
        movieModel = ifNull();
        int menu = view.displayComments(movieModel);
        switchToBackToMenu(menu);
    }

    //Checking mDao.searchById return.
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
    public void backToMainMenu() {
        displayMainMenu();
    }

    /*
           Method to handle the selection in Main Menu
        */
//    public void switchMainMenu(int number) {
//        switch (number) {
//            case 1:
//                displayMoviesList();
//                break;
//            case 2:
//                displayAddMovie();
//                break;
//            case 3:
//                displayRemoveMovie();
//                break;
//            case 4:
//                displayMoviesRatings();
//                break;
//            case 5:
//                searchMovie();
//                break;
//            case 6:
//                displayEditMovie();
//                break;
//            case 7:
//                addComment();
//                break;
//            case 8:
//                showComment();
//                break;
//            case 0:
//                mDao.closeConnection();
//                System.exit(0);
//                break;
//        }
 //   }


}
