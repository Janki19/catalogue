package pl.kubie.catalogue;


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
        int menuAnswer;
        menuAnswer=view.displayMainMenuView();


        switchViews(menuAnswer);
    }

    public void displayFilmsList(){

        int menuAnswer=view.dispalyFilmsList();

        switchViews(menuAnswer);
    }

    public void displayAddFilm(){

        boolean saveAnswer=view.dispalyAdd();
        if(saveAnswer==true){
            System.out.println(view.getTitleOfFilm());
            System.out.println(view.getYearOfFilm());
            switchViews(9);

        }else{
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
