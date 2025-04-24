import java.util.List;

public class Statement {

    public static final String HEADER = "Rental Record for Customer Name\n";
    private final List<Rental> rentals;

    public Statement(List<Rental> rentals) {
        this.rentals = rentals;
    }

    public String printStatement() {
        return HEADER +
                getBody() +
                getFooter();
    }

    private String getBody() {
        var rentalList = new StringBuilder();
        for (Rental rental : rentals) {
            rentalList
                    .append("  ")
                    .append(rental.getFilmTitle())
                    .append("  £").append(rental.getPrice())
                    .append("\n");
        }

        return rentalList.toString();
    }

    private String getFooter() {
        var points = rentals.stream().mapToInt(Rental::getPoints).sum();
        var price = rentals.stream().mapToDouble(Rental::getPrice).sum();
        return "You owe £" + price + "\n" + "You earned " + points + " frequent renter points";
    }
}

