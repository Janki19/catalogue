package pl.kubie.catalogue;


import java.sql.SQLException;
import java.util.List;

public class Controller {

    private ObjectModel model;
    private View view;
    private DAO dao;

    public Controller(ObjectModel model, View view) {
        this.model = model;
        this.view = view;
        displayMainMenu();
    }


    public void displayMainMenu(){
        int menuAnswer=view.displayMainMenuView();
        switchViews(menuAnswer);
    }

    public void displayFilmsList(){
        dao=new DAO();
        int menuAnswer=view.dispalyFilmsList(dao.displayAllRecords());
        switchViews(menuAnswer);
    }

    public void displayAddFilm(){

        boolean saveAnswer=view.dispalyAdd();

        if(saveAnswer==true){
            try {
                dao=new DAO();
                dao.insertNewRecord(view.getTitleOfFilm(),view.getYearOfFilm());
                view.clearTitleYear();
                switchViews(9);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else{
            view.clearTitleYear();
            switchViews(9);
        }
    }




    public void switchViews(int number){
        switch(number){
            case 1:displayFilmsList();
                break;
            case 2:displayAddFilm();
                break;
            case 3:view.dispalyEdit();
                break;
            case 4:view.dispalyDelete();
                break;
            case 9:displayMainMenu();
                break;
            case 0:System.exit(0);
                break;
        }
    }


}
