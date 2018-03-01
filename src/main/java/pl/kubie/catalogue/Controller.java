package pl.kubie.catalogue;

import java.sql.SQLException;

class Controller {

    private MovieModel model;
    private View view;
    private DAO dao;

    public Controller(View view, DAO dao) {
        this.dao = dao;
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

    public void displayAddMovie() {
        while (true) {
            if (view.dispalyAdd()) {
                view.clearTitleYear();
                if (view.shouldAddNext()) {
                    view.dispalyAdd();
                } else {
                    switchAddMovie(9);
                    break;
                }
            } else {
                view.clearTitleYear();
                if (view.shouldAddNext()) {
                    view.dispalyAdd();
                } else {
                    switchAddMovie(9);
                    break;
                }
            }
        }
    }

    public void displayAddFilm() {
        boolean saveAnswer = view.dispalyAdd();
        if (saveAnswer) {
            saveMovie();
            view.clearTitleYear();
            switchAddMovie(9);
        } else {
            view.clearTitleYear();
            switchAddMovie(9);
        }
    }

    private void saveMovie() {
        try {
            model = new MovieModel();
            model.setTitle(view.getTitleOfMovie());
            model.setYear(view.getYearOfMovie());
            dao.save(model);
            view.clearTitleYear();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
                // displayAddMovie();
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
    public void switchAddMovie(int number) {
        switch (number) {
            case 9:
                displayMainMenu();
                break;
        }
    }

}
