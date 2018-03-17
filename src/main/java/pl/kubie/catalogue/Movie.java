package pl.kubie.catalogue;

import javax.persistence.*;

@Entity
class Movie {
    @Id
    @GeneratedValue
    private int id;
    private String title;
    private String year;
    private float rating;
    private int votes;

    public Movie() {
    }

    public Movie(int id, String title, String year, float rating, int votes) {

        this.id = id;
        this.title = title;
        this.year = year;
        this.rating = rating;
        this.votes=votes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    //Calculates the average rating of the movie.
    // Need to be fixed
    public void countRating(int addRating){
        this.votes++;
        this.rating=(this.rating+addRating)/votes;
    }

    public String toString() {
        return String.format("\t%s. %s(%s) %s\n", id, title, year, rating);
    }

}
