package pl.kubie.catalogue;

import java.sql.SQLException;

class Controller {

    private MovieModel model;
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
        int menuAnswer = view.dispalyMoviesList(dao.displayAllRecords());
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
        if (view.displayRemoveConfirm(dao.displayById(id))) {
            dao.deleteRecord1(id);
            backToMainMenu();
        } else {
            backToMainMenu();
        }
    }

    public void displayMoviesRating(){
        model=new MovieModel();
        int id=view.displayChoiseToRatings();
        model=dao.displayById(id);
        int rating=view.displayRatingMovie(model);
        model.countRating(rating);
        dao.updateRating(model.getId(),model.getRating(),model.getVotes());
        backToMainMenu();
    }

    private void saveMovie() {
 //       try {
            model = new MovieModel();
            model.setTitle(view.getTitleOfMovie());
            model.setYear(view.getYearOfMovie());
           // dao.save(model);
            mDao.save(model);
            view.clearTitleYear();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
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
                displayMoviesRating();
                break;
            case 0:
                dao.closeConnetion();
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
