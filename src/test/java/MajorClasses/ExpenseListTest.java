package MajorClasses;

import Command.CommandAdd;
import org.junit.jupiter.api.Test;
import parser.Parser;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExpenseListTest {
    public ArrayList<Expense> testExpenseList = new ArrayList<>();
    public ExpenseList expenseList = new ExpenseList();
    public Parser parser = new Parser();

    @Test
    public void deleteExpense_successful() {
        testExpenseList.add(new Expense(2.5, "02/02/2012", "food", Currency.SGD));
        new CommandAdd(expenseList.getExpenseList(), parser.extractAddParameters("add amt/2.5 " +
                "t/02/02/2012 cat/food")).run();
        testExpenseList.remove(0);
        expenseList.deleteExpense("delete 1");
        assertEquals(testExpenseList, expenseList.getExpenseList());
    }
}
