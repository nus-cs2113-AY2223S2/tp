package command;

import data.Currency;
import data.Expense;
import data.ExpenseList;
import data.Time;
import org.junit.jupiter.api.Test;
import parser.Parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.math.BigDecimal;

public class CommandDeleteTest {

    public ArrayList<Expense> testExpenseList = new ArrayList<>();
    public ExpenseList expenseList = new ExpenseList();
    public Parser parser = new Parser();
    public DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    /**
     * Tests the correctness of CommandDelete.
     */
    @Test
    public void deleteExpense_successful() {
        BigDecimal twoPointFive = new BigDecimal("2.5");
        BigDecimal three = new BigDecimal("3");
        testExpenseList.add(new Expense(twoPointFive, new Time(LocalDate.parse("02-02-2012", formatter)),
                "food", Currency.SGD));
        new CommandAdd(expenseList.getExpenseList(),
                parser.extractAddParameters("add amt/2.5 " + "t/02-02-2012 cat/food")).execute();
        testExpenseList.add(new Expense(three, new Time(LocalDate.parse("05-02-2012", formatter)),
                "food", Currency.SGD));
        new CommandAdd(expenseList.getExpenseList(),
                parser.extractAddParameters("add amt/3.0 " + "t/05-02-2012 cat/food")).execute();
        testExpenseList.remove(0);
        new CommandDelete(expenseList.getExpenseList(), 1).execute();
        assertEquals(testExpenseList, expenseList.getExpenseList());

        testExpenseList.clear();
        expenseList.clear();

        // assertions to ensure both arrayLists are cleared after testing
        assert testExpenseList.isEmpty();
        assert expenseList.getExpenseList().isEmpty();
    }
}
