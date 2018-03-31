package pl.kubie.catalogue;

public class ControllerLogic {
    private Controller controller=new Controller();

    public ControllerLogic() {
    }

    public void switchMainMenu(int number) {
        switch (number) {
            case 1:
                controller.displayMoviesList();
                break;
            case 2:
                controller.displayAddMovie();
                break;
            case 3:
                controller.displayRemoveMovie();
                break;
            case 4:
                controller.displayMoviesRatings();
                break;
            case 5:
                controller.searchMovie();
                break;
            case 6:
                controller.displayEditMovie();
                break;
            case 7:
                controller.addComment();
                break;
            case 8:
                controller.showComment();
                break;
            case 0:
//                mDao.closeConnection();
//                System.exit(0);
//                break;
        }
    }
}
