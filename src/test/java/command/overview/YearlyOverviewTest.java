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

import static common.MessageList.TAB;
import static common.MessageList.WHITESPACE;
import static org.junit.jupiter.api.Assertions.assertEquals;

class YearlyOverviewTest {

    private static final String TITLE = "Yearly Overview for ";
    private static final String MONTH_DIVIDER = "----------------------------";
    private static final String YEARLY_OVERVIEW_TOTAL = "Total expenses: ";
    private static final String BREAKDOWN_TITLE = "Breakdown of expenses by month:";

    public DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();


    @Test
    void printOverviewEmptyArrayList_successful() {
        YearlyOverview yearlyOverview = new YearlyOverview(new ArrayList<>(), "2021");
        yearlyOverview.printYearlyOverview();
        assertEquals("No expenses tracked in 2021.", outputStreamCaptor.toString().trim());

    }

    @Test
    void printOverviewEmptyFilteredExpenses_successful() {
        ArrayList<Expense> testExpenses = new ArrayList<>();
        testExpenses.add(new Expense(new BigDecimal("2.5"),
                new Time(LocalDate.parse("01-03-2012", formatter)),
                "travel", "SGD", new BigDecimal(1)));
        YearlyOverview yearlyOverviewWithExpenses = new YearlyOverview(testExpenses, "2021");
        yearlyOverviewWithExpenses.printYearlyOverview();
        assertEquals("No expenses tracked in 2021.", outputStreamCaptor.toString().trim());
    }

    @Test
    void printOverview_successful() {
        ArrayList<Expense> testExpenses = new ArrayList<>();
        testExpenses.add(new Expense(new BigDecimal("2.5"),
                new Time(LocalDate.parse("01-05-2012", formatter)),
                "travel", "SGD", new BigDecimal(1)));
        testExpenses.add(new Expense(new BigDecimal("5"),
                new Time(LocalDate.parse("05-05-2012", formatter)),
                "travel", "SGD", new BigDecimal(1)));
        testExpenses.add(new Expense(new BigDecimal("10"),
                new Time(LocalDate.parse("12-01-2012", formatter)),
                "food", "SGD", new BigDecimal(1)));
        testExpenses.add(new Expense(new BigDecimal("2.5"),
                new Time(LocalDate.parse("10-03-2012", formatter)),
                "food", "SGD", new BigDecimal(1)));

        YearlyOverview yearlyOverview = new YearlyOverview(testExpenses, "2012");
        yearlyOverview.printYearlyOverview();
        String expectedOutput = TITLE + "2012" + System.lineSeparator() + System.lineSeparator() +
                TAB + YEARLY_OVERVIEW_TOTAL + "20.00 SGD" + System.lineSeparator() + System.lineSeparator() +
                TAB + BREAKDOWN_TITLE + System.lineSeparator() +
                TAB + MONTH_DIVIDER + System.lineSeparator() +
                TAB + WHITESPACE + "JANUARY 10.00 SGD" + System.lineSeparator() +
                TAB + MONTH_DIVIDER + System.lineSeparator() +
                TAB + WHITESPACE + "FEBRUARY 0.00 SGD" + System.lineSeparator() +
                TAB + MONTH_DIVIDER + System.lineSeparator() +
                TAB + WHITESPACE + "MARCH 2.50 SGD" + System.lineSeparator() +
                TAB + MONTH_DIVIDER + System.lineSeparator() +
                TAB + WHITESPACE + "APRIL 0.00 SGD" + System.lineSeparator() +
                TAB + MONTH_DIVIDER + System.lineSeparator() +
                TAB + WHITESPACE + "MAY 7.50 SGD" + System.lineSeparator() +
                TAB + MONTH_DIVIDER + System.lineSeparator() +
                TAB + WHITESPACE + "JUNE 0.00 SGD" + System.lineSeparator() +
                TAB + MONTH_DIVIDER + System.lineSeparator() +
                TAB + WHITESPACE + "JULY 0.00 SGD" + System.lineSeparator() +
                TAB + MONTH_DIVIDER + System.lineSeparator() +
                TAB + WHITESPACE + "AUGUST 0.00 SGD" + System.lineSeparator() +
                TAB + MONTH_DIVIDER + System.lineSeparator() +
                TAB + WHITESPACE + "SEPTEMBER 0.00 SGD" + System.lineSeparator() +
                TAB + MONTH_DIVIDER + System.lineSeparator() +
                TAB + WHITESPACE + "OCTOBER 0.00 SGD" + System.lineSeparator() +
                TAB + MONTH_DIVIDER + System.lineSeparator() +
                TAB + WHITESPACE + "NOVEMBER 0.00 SGD" + System.lineSeparator() +
                TAB + MONTH_DIVIDER + System.lineSeparator() +
                TAB + WHITESPACE + "DECEMBER 0.00 SGD" + System.lineSeparator() +
                TAB + MONTH_DIVIDER;
        assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
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
