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
import java.math.BigDecimal;

public class CommandDeleteTest {

    private static final String MESSAGE_INVALID_INDEX_ERROR = "Invalid expense index. Please try again.";

    public ArrayList<Expense> testExpenseList = new ArrayList<>();
    public ExpenseList expenseList = new ExpenseList();
    public Parser parser = new Parser();
    public Currency currency = new Currency();
    public DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final PrintStream standardOut = System.out;

    /**
     * Tests the correctness of CommandDelete.
     */
    @Test
    public void deleteExpense_successful() {
        BigDecimal twoPointFive = new BigDecimal("2.5");
        BigDecimal three = new BigDecimal("3");
        testExpenseList.add(new Expense(twoPointFive, new Time(LocalDate.parse("02-02-2012", formatter)),
                "food", "SGD", new BigDecimal(1)));
        new CommandAdd(expenseList.getExpenseList(),
                parser.extractAddParameters("add amt/2.5 " + "t/02-02-2012 cat/food"), currency).execute();
        testExpenseList.add(new Expense(three, new Time(LocalDate.parse("05-02-2012", formatter)),
                "food", "SGD", new BigDecimal(1)));
        new CommandAdd(expenseList.getExpenseList(),
                parser.extractAddParameters("add amt/3.0 " + "t/05-02-2012 cat/food"), currency).execute();
        testExpenseList.remove(0);
        new CommandDelete(expenseList.getExpenseList(), 1).execute();
        assertEquals(testExpenseList, expenseList.getExpenseList());

        testExpenseList.clear();
        expenseList.clear();

        // assertions to ensure both arrayLists are cleared after testing
        assert testExpenseList.isEmpty();
        assert expenseList.getExpenseList().isEmpty();
    }

    @Test
    public void deleteExpenses_unsuccessful() {
        new CommandDelete(expenseList.getExpenseList(),1).execute();
        assertEquals(MESSAGE_INVALID_INDEX_ERROR, outputStreamCaptor.toString().trim());
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
