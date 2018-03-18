package pl.kubie.catalogue;

import javax.persistence.*;

@Entity
 class MoviesRate {
    @Id
    @GeneratedValue
    private int id;

    @OneToOne(mappedBy="movieRates")
    private Movie movie;

    private int oneStar;
    private int twoStars;
    private int threeStars;
    private int fourStars;
    private int fiveStars;
    private int sixStars;
    private int sevenStars;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOneStar() {
        return oneStar;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
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

//    public void setRate(int rate){
//       switch (rate){
//           case 1:
//               oneStar=rate;
//               break;
//           case 2:
//               twoStars=rate;
//               break;
//           case 3:
//               threeStars=rate;
//               break;
//           case 4:
//               fourStars=rate;
//               break;
//           case 5:
//               fiveStars=rate;
//               break;
//           case 6:
//               sixStars=rate;
//               break;
//           case 7:
//               sevenStars=rate;
//               break;
//       }
//
//    }
}
