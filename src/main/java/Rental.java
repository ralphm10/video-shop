import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Rental {

    private Film film;
    private int durationDays;

    public Rental(Film film, int durationDays) {
        this.film = film;
        this.durationDays = durationDays;
    }

    public double getPrice() {
        if (durationDays > this.film.getBaseDays()) {
            return ((durationDays - this.film.getBaseDays()) * this.film.getExtendedPrice()) + this.film.getBasePrice();
        }
        return film.getBasePrice();
    }

    public String getFilmTitle() {
        return film.getTitle();
    }

    public int getPoints() {
        if (film instanceof NewReleaseFilm && durationDays > 1) {
            return 2;
        }
        return 1;
    }
}

