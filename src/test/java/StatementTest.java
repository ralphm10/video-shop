import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StatementTest {

    private Film crazynotes;

    @BeforeEach
    void setUp() {
        crazynotes = Film.builder()
                .title("Crazynotes")
                .build();
    }

    @Test
    void statementForRegularMovieOneDay() {
        Statement statement = new Statement(List.of(Rental.builder()
                .film(crazynotes)
                .durationDays(1)
                .build()));
        var statementText = """
                Rental Record for Customer Name
                  Crazynotes  £2.0
                You owe £2.0
                You earned 1 frequent renter points""";
        assertEquals(statementText, statement.printStatement());
    }

    @Test
    void statementForRegularMovieTwoDays() {
        Statement statement = new Statement(List.of(Rental.builder()
                .film(Film.builder()
                        .title("Scarface")
                        .build())
                .durationDays(2)
                .build()));
        var statementText = """
                Rental Record for Customer Name
                  Scarface  £2.0
                You owe £2.0
                You earned 1 frequent renter points""";
        assertEquals(statementText, statement.printStatement());
    }

    @Test
    void statementForRegularMovieThreeDays() {
        Statement statement = new Statement(List.of(Rental.builder()
                .film(crazynotes)
                .durationDays(3)
                .build()));
        String statementText = """
                Rental Record for Customer Name
                  Crazynotes  £3.5
                You owe £3.5
                You earned 1 frequent renter points""";
        assertEquals(statementText, statement.printStatement());
    }

    @Test
    void statementForChildrensMovieOneDay() {
        Film insideOut = new ChildrensFilm("Inside Out");
        Statement statement = new Statement(List.of(Rental.builder()
                .film(insideOut)
                .durationDays(1)
                .build()));
        String statementText = """
                Rental Record for Customer Name
                  Inside Out  £1.5
                You owe £1.5
                You earned 1 frequent renter points""";
        assertEquals(statementText, statement.printStatement());
    }

    @Test
    void statementForChildrensMovieFourDays() {
        Film insideOut = new ChildrensFilm("Inside Out");
        Statement statement = new Statement(List.of(Rental.builder()
                .film(insideOut)
                .durationDays(4)
                .build()));
        String statementText = """
                Rental Record for Customer Name
                  Inside Out  £3.0
                You owe £3.0
                You earned 1 frequent renter points""";
        assertEquals(statementText, statement.printStatement());
    }

    @Test
    void statementForNewReleaseForOneDay() {
        NewReleaseFilm captainAmerica = new NewReleaseFilm("Captain America");
        Statement statement = new Statement(List.of(Rental.builder()
                .film(captainAmerica)
                .durationDays(1)
                .build()));

        String statementText = """
                Rental Record for Customer Name
                  Captain America  £3.0
                You owe £3.0
                You earned 1 frequent renter points""";
        assertEquals(statementText, statement.printStatement());
    }

    @Test
    void statementForNewReleaseForTwoDays() {
        NewReleaseFilm captainAmerica = new NewReleaseFilm("Captain America");
        Statement statement = new Statement(List.of(Rental.builder()
                .film(captainAmerica)
                .durationDays(2)
                .build()));

        String statementText = """
                Rental Record for Customer Name
                  Captain America  £6.0
                You owe £6.0
                You earned 2 frequent renter points""";
        assertEquals(statementText, statement.printStatement());
    }

    @Test
    void statementForMultipleRegularFilms() {
        var teeth = new Film("Teeth");
        var theWeb = new Film("The Web");

        var rental1 = Rental.builder()
                .film(crazynotes)
                .durationDays(1)
                .build();
        var rental2 = Rental.builder()
                .film(teeth)
                .durationDays(2)
                .build();
        var rental3 = Rental.builder()
                .film(theWeb)
                .durationDays(3)
                .build();

        var rentals = List.of(rental1, rental2, rental3);

        var thrown = assertThrows(ArrayIndexOutOfBoundsException.class, () -> rentals.get(20));
        System.out.println(thrown.getMessage());

        Statement statement = new Statement(rentals);
        String statementText = """
                Rental Record for Customer Name
                  Crazynotes  £2.0
                  Teeth  £2.0
                  The Web  £3.5
                You owe £7.5
                You earned 3 frequent renter points""";
        assertEquals(statementText, statement.printStatement());
    }
}

