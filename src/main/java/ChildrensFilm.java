import lombok.Getter;

@Getter
public class ChildrensFilm extends Film {
    private final int baseDays = 3;
    private final double basePrice = 1.5;
    private final double extendedPrice = 1.5;

    ChildrensFilm(String title) {
        super(title);
    }
}
