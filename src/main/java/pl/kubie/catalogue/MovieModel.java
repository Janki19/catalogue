package pl.kubie.catalogue;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="console")
class MovieModel {
    @Id
    @GeneratedValue
    private int id;
    private String title;
    private String year;
    private float rating;
    private int votes;

    public MovieModel() {
    }

    public MovieModel(int id, String title, String year, float rating,int votes) {

        this.id = id;
        this.title = title;
        this.year = year;
        this.rating = rating;
        this.votes=votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
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

    public int getVotes(){
        return votes;
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

    //Calculates the average rating of the movie.
    public void countRating(int addRating){
        this.votes++;
        this.rating=(this.rating+addRating)/votes;
    }

    public String toString() {
        return String.format("\t%s. %s(%s) %s\n", id, title, year, rating);
    }

}
