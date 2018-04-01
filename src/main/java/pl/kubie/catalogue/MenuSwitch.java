package pl.kubie.catalogue;

public class MenuSwitch {

    private final Controller controller;

    public MenuSwitch(Controller controller) {
        this.controller = controller;
    }

    public void switchMainMenu() {
        while (true) {
            int choice = controller.displayMainMenu();
            switch (choice) {
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
                    switch (controller.searchMovie()) {
                        case 1:
                            controller.searchByTitle();
                            break;
                        case 2:
                            controller.searchByRate();
                            break;
                        case 3:
                            controller.searchByDate();
                            break;
                    }
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
                    break;
            }
            if (choice == 0) {
                break;
            }
        }
    }


}
