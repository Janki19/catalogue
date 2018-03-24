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

    public void displayMainMenu() {
        int menuAnswer = view.displayMainMenuView();
        switchMainMenu(menuAnswer);
    }

    public void displayMoviesList() {
        int menuAnswer = view.displayMoviesList(mDao.showAll());
        switchToBackToMenu(menuAnswer);
    }

    public void displayAddMovie() {
        movieModel = new Movie();
        boolean saveAnswer = view.displayAdd();
        if (saveAnswer) {
            saveMovie(movieModel);
            backToMainMenu();
        } else {
            backToMainMenu();
        }
    }
    public void displayEditMovie() {
        int editId=view.choiceMovieToEdit();
        movieModel=new Movie();
        movieModel=mDao.searchById(editId);
        boolean saveAnswer = view.enterEditMovie(movieModel);

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

    public void displayRemoveMovie() {
        int id = view.displayRemove();
        if (view.displayRemoveConfirm(mDao.searchById(id))) {
            mDao.deleteMovie(id);
            backToMainMenu();
        } else {
            backToMainMenu();
        }
    }

    public void displayMoviesRatings() {
        movieModel = new Movie();
        MoviesRate moviesRate = new MoviesRate();
        int id = view.displayChoiseToRatings();
        movieModel = mDao.searchById(id);
        int rate = view.displayRatingMovie(movieModel);

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

    public void searchByRate(){
        double rate=view.searchByRate();
        int menu=view.displaySearchByRate(mDao.searchByRate(rate),rate);
        switchToBackToMenu(menu);
    }

    public void addComment(){
        int movieId=view.displayChoiseToComment();
        movieModel=new Movie();
        movieModel=mDao.searchById(movieId);
        movieModel.getCommentsList().add(view.displayEnterComment(movieModel));
        mDao.addMovie(movieModel);
        backToMainMenu();
    }

    public void showComment(){
        int movieId=view.displayShowComment();
        movieModel=new Movie();
        movieModel=mDao.searchById(movieId);
        int menu=view.displayComments(movieModel);
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

}
