package command;

import data.Currency;
import data.Expense;
import data.ExpenseList;
import data.Time;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import parser.Parser;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import java.math.BigDecimal;


class CommandAddTest {
    public ArrayList<Expense> testExpenseList = new ArrayList<>();
    public ExpenseList expenseList = new ExpenseList();
    public Parser parser = new Parser();
    public Currency currency = new Currency();
    public DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @Test
    public void addExpense_successful() {
        testExpenseList.add(new Expense(new BigDecimal("2.5"),
                new Time(LocalDate.parse("02-02-2012", formatter)),
                "food", "SGD", new BigDecimal(1)));
        new CommandAdd(expenseList.getExpenseList(),
                parser.extractAddParameters("add amt/2.5 " + "t/02-02-2012 cat/food"), currency).execute();
        assertEquals(testExpenseList.get(0), expenseList.getExpenseList().get(0));
        testExpenseList.add(new Expense(new BigDecimal("2.5"),
                new Time(LocalDate.parse("02-02-2012", formatter)),
                "food", "SGD", new BigDecimal(1)));
        new CommandAdd(expenseList.getExpenseList(),
                parser.extractAddParameters("add amt/3.5 " +
                        "t/02-02-2012 cur/USD cat/food"), currency).execute();
        assertNotEquals(testExpenseList.get(1), expenseList.getExpenseList().get(1));

        testExpenseList.clear();
        expenseList.clear();
    }

    @Test
    public void addExpense_missingAmount_returnsErrorMessage() {
        new CommandAdd(expenseList.getExpenseList(),
                parser.extractAddParameters("add t/02-02-2012 cat/food"), currency).execute();
        assertEquals("Please input both the amount and date with amt/ and t/ respectively.",
                outputStreamCaptor.toString().trim());
    }
    @Test
    public void addExpense_missingTime_returnsErrorMessage() {
        new CommandAdd(expenseList.getExpenseList(),
                parser.extractAddParameters("add amt/24 cat/food"), currency).execute();
        assertEquals("Please input both the amount and date with amt/ and t/ respectively.",
                outputStreamCaptor.toString().trim());
    }
    @Test
    public void addExpense_invalidAmountType_returnsErrorMessage() {
        new CommandAdd(expenseList.getExpenseList(),
                parser.extractAddParameters("add amt/twenty t/02-02-2022 cat/food"), currency).execute();
        assertEquals("Please input a valid amount.", outputStreamCaptor.toString().trim());
    }

    @Test
    public void addExpense_invalidTimeType_returnsErrorMessage() {
        new CommandAdd(expenseList.getExpenseList(),
                parser.extractAddParameters("add amt/20 t/September cat/food"), currency).execute();
        assertEquals("Invalid date. Please input the date in dd-MM-yyyy format.",
                outputStreamCaptor.toString().trim());
    }
    @Test
    public void addExpense_futureTime_returnsErrorMessage() {
        new CommandAdd(expenseList.getExpenseList(),
                parser.extractAddParameters("add amt/20 t/02-02-2500 cat/food"), currency).execute();
        assertEquals("Invalid date. Please input a date before today's date.\nToday's date is: " +
                        LocalDate.now(),
                outputStreamCaptor.toString().trim());
    }
    @Test
    public void addExpense_nonExistentDate_returnsErrorMessage() {
        new CommandAdd(expenseList.getExpenseList(),
                parser.extractAddParameters("add amt/20 t/29-02-2022 cat/food"), currency).execute();
        assertEquals("Date does not exist, please try again.",
                outputStreamCaptor.toString().trim());
    }
    @Test
    public void addExpense_pastTime_returnsErrorMessage() {
        new CommandAdd(expenseList.getExpenseList(),
                parser.extractAddParameters("add amt/20 t/02-02-1979 cat/food"), currency).execute();
        assertEquals("Dates beyond 1981 are not supported. Please try again.",
                outputStreamCaptor.toString().trim());
    }

    @Test
    public void addExpense_invalidType_returnsWarningMessage() {
        new CommandAdd(expenseList.getExpenseList(),
                parser.extractAddParameters("add amount/20 time/notatime"), currency).execute();
        assertEquals("WARNING: Invalid input type for \"time/notatime\". Please check again."
                        + System.lineSeparator() + "WARNING: Invalid input type for \"amount/20\". Please check again."
                        + System.lineSeparator() + "Please input both the amount and date with amt/ and t/ " +
                        "respectively.",
                outputStreamCaptor.toString().trim());
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
