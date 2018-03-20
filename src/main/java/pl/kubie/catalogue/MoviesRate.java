package pl.kubie.catalogue;

import javax.persistence.*;

@Entity
 class MoviesRate {
    @Id
    @GeneratedValue
    private int id;

    private int oneStar;
    private int twoStars;
    private int threeStars;
    private int fourStars;
    private int fiveStars;
    private int sixStars;
    private int sevenStars;

    @OneToOne(mappedBy = "movieRates")
    private Movie movie;

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOneStar() {
        return oneStar;
    }

    public void setOneStar(int oneStar) {
        this.oneStar = oneStar;
    }

    public int getTwoStars() {
        return twoStars;
    }

    public void setTwoStars(int twoStars) {
        this.twoStars = twoStars;
    }

    public int getThreeStars() {
        return threeStars;
    }

    public void setThreeStars(int threeStars) {
        this.threeStars = threeStars;
    }

    public int getFourStars() {
        return fourStars;
    }

    public void setFourStars(int fourStars) {
        this.fourStars = fourStars;
    }

    public int getFiveStars() {
        return fiveStars;
    }

    public void setFiveStars(int fiveStars) {
        this.fiveStars = fiveStars;
    }

    public int getSixStars() {
        return sixStars;
    }

    public void setSixStars(int sixStars) {
        this.sixStars = sixStars;
    }

    public int getSevenStars() {
        return sevenStars;
    }

    public void setSevenStars(int sevenStars) {
        this.sevenStars = sevenStars;
    }

    public void setRate(int rate){
       switch (rate){
           case 1:
               this.oneStar++;
               break;
           case 2:
               this.twoStars++;
               break;
           case 3:
               this.threeStars++;
               break;
           case 4:
               this.fourStars++;
               break;
           case 5:
               this.fiveStars++;
               break;
           case 6:
               this.sixStars++;
               break;
           case 7:
               this.sevenStars++;
               break;
       }

    }
}
