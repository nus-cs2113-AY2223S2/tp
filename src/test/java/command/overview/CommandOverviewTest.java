package command.overview;

import data.Expense;
import data.Time;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CommandOverviewTest {

    public DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final PrintStream standardOut = System.out;

    private static final String MONTH_NAME_ERROR =
            "Incorrect month name. Please key in the full english month name.";
    private static final String FUTURE_DATE_ERROR = "For yearly overview, please input a year before 2024. " +
            "For monthly overview, please input month before ";
    private static final String NO_SPECIFIC_MONTH_YEAR_ERROR =
            "Please specify the month and/or year of the overview you intend to view.";
    private static final String INVALID_YEAR_FORMAT_ERROR = "Invalid format for year. " +
            "Please enter month name in standard English Month and/or year as a 4 digit number.";
    private static final String NEGATIVE_YEAR_ERROR = "Year cannot be negative. Please input a valid year";
    private static final String INVALID_YEAR_ERROR = "Invalid year. Please enter a year between 1981 and ";

    @Test
    void invalidYearFormatError() {
        ArrayList<Expense> expenses = new ArrayList<>();
        expenses.add(new Expense(new BigDecimal("2.5"),
                new Time(LocalDate.parse("02-03-2012", formatter)),
                "travel", "SGD", new BigDecimal(1)));
        new CommandOverview(expenses, "March", "gibberish").execute();
        assertEquals(INVALID_YEAR_FORMAT_ERROR, outputStreamCaptor.toString().trim());
    }

    @Test
    void invalidOverviewInput() {
        new CommandOverview(new ArrayList<>(), "", "").execute();
        assertEquals(NO_SPECIFIC_MONTH_YEAR_ERROR, outputStreamCaptor.toString().trim());
    }

    @Test
    void negativeYearError() {
        new CommandOverview(new ArrayList<>(), "May", "-100").execute();
        assertEquals(NEGATIVE_YEAR_ERROR, outputStreamCaptor.toString().trim());
    }

    @Test
    void invalidMonthNameError() {
        new CommandOverview(new ArrayList<>(), "gibberish", "2021").execute();
        assertEquals(MONTH_NAME_ERROR, outputStreamCaptor.toString().trim());
    }

    @Test
    void futureMonthError() {
        new CommandOverview(new ArrayList<>(), "May", "2023").execute();
        assertEquals(FUTURE_DATE_ERROR + "MAY 2023.", outputStreamCaptor.toString().trim());
    }

    @Test
    void futureYearError() {
        new CommandOverview(new ArrayList<>(), "March", "2024").execute();
        assertEquals(INVALID_YEAR_ERROR + "2023 (inclusive).", outputStreamCaptor.toString().trim());
    }

    @Test
    void prevYearError() {
        new CommandOverview(new ArrayList<>(), "December", "1980").execute();
        assertEquals(INVALID_YEAR_ERROR + "2023 (inclusive).", outputStreamCaptor.toString().trim());
    }

    @Test
    void validMonthlyOverview() {
        new CommandOverview(new ArrayList<>(), "December", "2021").execute();
        assertEquals("No expenses tracked in DECEMBER 2021.", outputStreamCaptor.toString().trim());
    }

    @Test
    void validYearlyOverview() {
        new CommandOverview(new ArrayList<>(), null, "2021").execute();
        assertEquals("No expenses tracked in 2021.", outputStreamCaptor.toString().trim());
    }

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }
    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
}