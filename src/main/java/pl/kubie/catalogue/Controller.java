package pl.kubie.catalogue;

import java.time.LocalDate;

class Controller {
    private final View view;
    private final MovieDao mDao;

    public Controller(View view, MovieDao mDao) {
        this.mDao = mDao;
        this.view = view;
    }

    public int displayMainMenu() {
        return view.displayMainMenuView();
    }

    public void displayMoviesList() {
        view.displayMoviesList(mDao.showAll());
    }

    public void displayAddMovie() {
        Movie movieModel = new Movie();
        movieModel.setTitle(view.displayAddTitle());
        movieModel.setYear(view.displayAddYear());
        if (view.displayAddConfirmation()) {
            mDao.addMovie(movieModel);
        }
    }

    public void displayEditMovie() {
        Movie movieModel;
        view.choiceMovieToEdit();
        movieModel = getMovieById();
        movieModel.setTitle(view.enterEditTitle(movieModel));
        movieModel.setYear(view.displayEditYear());
        boolean saveAnswer = view.displayEditConfirmation();
        if (saveAnswer) {
            mDao.addMovie(movieModel);
        }
    }

    public void displayRemoveMovie() {
        Movie movie;
        view.displayRemove();
        movie = getMovieById();
        boolean removeAnswer = view.displayRemoveConfirm(movie);
        if (removeAnswer) {
            mDao.deleteMovie(movie);
        }
    }

    public void displayMoviesRatings() {
        Movie movie;
        MoviesRate moviesRate = new MoviesRate();
        view.displayChoiceToRatings();
        movie = getMovieById();
        int rate = view.displayRatingMovie(movie);
        moviesRate.setRate(rate);
        movie.getRateList().add(moviesRate);
        movie.setRating(movie.calculateRating());
        mDao.addMovie(movie);
    }

    public int searchMovie() {
        return view.displaySearchMenu();
    }

    public void searchByTitle() {
        String answer = view.searchByTitle();
        view.displaySearchByTitle(mDao.searchByTitle(answer), answer);
    }

    public void searchByDate() {
        LocalDate date = view.searchByDate();
        view.displaySearchByDate(mDao.searchByDate(date), date);
    }

    public void searchByRate() {
        double rate = view.searchByRate();
        view.displaySearchByRate(mDao.searchByRate(rate), rate);
    }

    public void addComment() {
        Movie movie;
        view.displayChoiceToComment();
        movie = getMovieById();
        movie.getCommentsList().add(view.displayEnterComment(movie));
        mDao.addMovie(movie);
    }

    public void showComment() {
        Movie movie;
        view.displayShowComment();
        movie = getMovieById();
        view.displayComments(movie);
    }

    //Checking mDao.searchById return.
    private Movie getMovieById() {
        Movie movieModel;
        while (true) {
            movieModel = mDao.searchById(view.enterId());
            if (movieModel != null) {
                break;
            } else {
                view.idNotFounded();
            }
        }
        return movieModel;
    }
}
