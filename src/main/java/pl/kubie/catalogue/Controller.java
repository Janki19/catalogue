package pl.kubie.catalogue;

class Controller {

    private Movie model;
    private View view;
    private DAO dao;
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
        int menuAnswer = view.dispalyMoviesList(mDao.findAll());
        switchMoviesList(menuAnswer);
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

    public void displayRemoveMovie() {
        int id = view.displayRemove();
        if (view.displayRemoveConfirm(mDao.findById(id))) {
            mDao.deleteMovie(id);
            backToMainMenu();
        } else {
            backToMainMenu();
        }
    }

    public void displayMoviesRating() {
        model = new Movie();
        int id = view.displayChoiseToRatings();
        model = dao.displayById(id);
        int rating = view.displayRatingMovie(model);
        model.countRating(rating);
        dao.updateRating(model.getId(), model.getRating(), model.getVotes());
        backToMainMenu();
    }

    public void displayMoviesRatings() {
        model = new Movie();
        int id = view.displayChoiseToRatings();
        model = mDao.findById(id);
        int rating = view.displayRatingMovie(model);
        model.countRating(rating);
        mDao.addMovie(model);
        backToMainMenu();
    }

    private void saveMovie() {
        model = new Movie();
        model.setTitle(view.getTitleOfMovie());
        model.setYear(view.getYearOfMovie());
        mDao.addMovie(model);
        view.clearTitleYear();

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
            case 0:
                mDao.closeConnection();
                System.exit(0);
                break;
        }
    }

    /*
    Method to handle the selection in Films List
    */
    public void switchMoviesList(int number) {
        switch (number) {
            case 9:
                displayMainMenu();
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
