package pl.kubie.catalogue;

import java.sql.SQLException;

class Controller {

    private FilmsModel model;
    private  View view;
    private  DAO dao;

    public Controller(){}

    public Controller(View view,DAO dao) {
        this.dao=dao;
        this.view = view;
        displayMainMenu();
    }

    public void displayMainMenu(){
        int menuAnswer=view.displayMainMenuView();
        switchMainMenu(menuAnswer);
    }

    public void displayFilmsList(){
        int menuAnswer=view.dispalyFilmsList(dao.displayAllRecords());
        switchFilmsList(menuAnswer);
    }

    public void displayAddFilm(){
        boolean saveAnswer=view.dispalyAdd();
        if(saveAnswer){
            saveFilm();
            switchAddFilm(9);
        }else{
            view.clearTitleYear();
            switchAddFilm(9);
        }
    }

    private void saveFilm(){
        try {
            model=new FilmsModel();
            model.setTitle(view.getTitleOfFilm());
            model.setYear(view.getYearOfFilm());
            dao.save(model);
            view.clearTitleYear();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
        Method to handle the selection in Main Menu
     */
    public void switchMainMenu(int number){
        switch(number){
            case 1:displayFilmsList();
                break;
            case 2:displayAddFilm();
                break;
            case 0: dao.closeConnetion();
                System.exit(0);
                break;
        }
    }

    /*
    Method to handle the selection in Films List
    */
    public void switchFilmsList(int number){
        switch(number) {
            case 9:
                displayMainMenu();
                break;
        }
    }

    /*
    Method to handle the selection in Add Film
    */
    public void switchAddFilm(int number){
        switch(number) {
            case 9:
                displayMainMenu();
                break;
        }
    }

}
