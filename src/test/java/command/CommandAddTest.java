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
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import java.math.BigDecimal;


class CommandAddTest {
    public ArrayList<Expense> testExpenseList = new ArrayList<>();
    public ExpenseList expenseList = new ExpenseList();
    public Parser parser = new Parser();
    public DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    @Test
    public void addExpense_successful() {
        testExpenseList.add(new Expense(new BigDecimal("2.5"), new Time(LocalDate.parse("02-02-2012", formatter)),
                "food", Currency.SGD));
        new CommandAdd(expenseList.getExpenseList(),
                parser.extractAddParameters("add amt/2.5 " + "t/02-02-2012 cat/food")).execute();
        assertEquals(testExpenseList.get(0), expenseList.getExpenseList().get(0));

        testExpenseList.add(new Expense(new BigDecimal("2.5"), new Time(LocalDate.parse("02-02-2012", formatter)),
                "food", Currency.SGD));
        new CommandAdd(expenseList.getExpenseList(),
                parser.extractAddParameters("add amt/2.5 " +
                        "t/02-02-2012 cur/USD cat/food")).execute();
        assertNotEquals(testExpenseList.get(1), expenseList.getExpenseList().get(1));

        testExpenseList.clear();
        expenseList.clear();
    }
}
