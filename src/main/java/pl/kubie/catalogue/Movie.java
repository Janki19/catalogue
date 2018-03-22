package pl.kubie.catalogue;

import javax.persistence.*;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.*;

@Entity
public class Movie {
    @Id
    @GeneratedValue
    private int id;

    private String title;
    private String year;
    private double rating;
    private int votes;
    @Column(name = "date")
    private LocalDate dateNow=LocalDate.now();

    @ElementCollection
    private List<MoviesRate>rateList=new ArrayList<>();

    @ElementCollection
    private List<String>commentsList=new ArrayList<>();

    public Movie() {
    }

    public Movie(int id, String title, String year, float rating, int votes) {

        this.id = id;
        this.title = title;
        this.year = year;
        this.rating = rating;
        this.votes = votes;
    }

    public List<String> getCommentsList() {
        return commentsList;
    }

    public void setCommentsList(List<String> commentsList) {
        this.commentsList = commentsList;
    }

    public List<MoviesRate> getRateList() {
        return rateList;
    }

    public void setRateList(List<MoviesRate> rateList) {
        this.rateList = rateList;
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

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    public LocalDate getDateNow() {
        return dateNow;
    }

    public void setDateNow(LocalDate dateNow) {
        this.dateNow = dateNow;
    }

    public double calculateRating(){
        DecimalFormat df=new DecimalFormat("0.0");
        double sum=0;
        for(MoviesRate rateList:rateList){
            sum+=rateList.getRate();
        }
        double retRate= Double.parseDouble(df.format(sum/rateList.size()));
        return retRate;
    }

    public String toString() {
        return String.format("\t%s. %s(%s) %s\n", id, title, year, rating);
    }

}
