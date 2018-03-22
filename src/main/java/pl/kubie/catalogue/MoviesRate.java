package pl.kubie.catalogue;

import javax.persistence.Embeddable;
import java.time.LocalDate;

@Embeddable
public class MoviesRate {

    private int rate;
    private LocalDate addDate = LocalDate.now();

    public LocalDate getAddDate() {
        return addDate;
    }

    public void setAddDate(LocalDate addDate) {
        this.addDate = addDate;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }


}