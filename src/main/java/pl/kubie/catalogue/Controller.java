package pl.kubie.catalogue;

import java.sql.SQLException;

public class Controller {

    private FilmsModel model;
    private View view;
    private DAO dao;

    public Controller(View view,DAO dao) {
        this.dao=dao;
        this.view = view;
        displayMainMenu();
    }

    public void displayMainMenu(){
        int menuAnswer=view.displayMainMenuView();
        switchViews(menuAnswer);
    }

    public void displayFilmsList(){
        int menuAnswer=view.dispalyFilmsList(dao.displayAllRecords());
        switchViews(menuAnswer);
    }

    public void displayAddFilm(){

        boolean saveAnswer=view.dispalyAdd();

        if(saveAnswer){
            saveFilm();
            switchViews(9);
        }else{
            view.clearTitleYear();
            switchViews(9);
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

    public void switchViews(int number){
        switch(number){
            case 1:displayFilmsList();
                break;
            case 2:displayAddFilm();
                break;
            case 9:displayMainMenu();
                break;
            case 0: dao.closeConnetion();
                    System.exit(0);
                break;
        }
    }


}
