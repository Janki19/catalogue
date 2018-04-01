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
    @Column(name = "date")
    private LocalDate dateNow = LocalDate.now();

    @ElementCollection
    private List<MoviesRate> rateList = new ArrayList<>();

    @ElementCollection
    private List<String> commentsList = new ArrayList<>();

    public Movie() {
    }

    public List<String> getCommentsList() {
        return commentsList;
    }

    public List<MoviesRate> getRateList() {
        return rateList;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public double calculateRating() {
        DecimalFormat df = new DecimalFormat("0.0");
        double sum = 0;
        for (MoviesRate rateList : rateList) {
            sum += rateList.getRate();
        }
        double retRate = Double.parseDouble(df.format(sum / rateList.size()));
        return retRate;
    }

    public String toString() {
        return String.format("\t%s. %s(%s) %s\n", id, title, year, rating);
    }

}
