import lombok.Getter;

@Getter
public class NewReleaseFilm extends Film {
    private final int baseDays = 1;
    private final double basePrice = 3;
    private final double extendedPrice = 3;

    NewReleaseFilm(String title) {
        super(title);
    }
}
