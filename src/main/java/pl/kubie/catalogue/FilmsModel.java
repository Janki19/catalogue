package pl.kubie.catalogue;

class FilmsModel {
    private int id;
    private String title;
    private String year;
    private float rating;

    public FilmsModel() {
    }

    public FilmsModel(int id, String title, String year, float rating) {

        this.id = id;
        this.title = title;
        this.year = year;
        this.rating = rating;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getId() {

        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }

    public float getRating() {
        return rating;
    }

    public String toString() {
        return String.format("\t%s. %s(%s) %s\n", id, title, year, rating);
    }

}
