import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Film {
    private String title;
    private final int baseDays = 2;
    private final double basePrice = 2;
    private final double extendedPrice = 1.5;
}
