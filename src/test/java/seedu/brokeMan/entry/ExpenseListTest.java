package seedu.brokeMan.entry;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import seedu.brokeMan.entry.expense.Expense;
import seedu.brokeMan.entry.expense.ExpenseList;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.LinkedList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExpenseListTest {
    private static Expense Expense1;
    private static Expense Expense2;

    @BeforeAll
    public static void setUp() {
        Expense1 = new Expense(2000.0, "Food", LocalDateTime.of(2023, Month.APRIL,
                1, 10, 0), Category.FOOD);
        Expense2 = new Expense(500.0, "Book", LocalDateTime.of(2023, Month.APRIL,
                15, 15, 30), Category.ENTERTAINMENT);
        ExpenseList.addExpense(Expense1);
        ExpenseList.addExpense(Expense2);
    }

    @Test
    public void testAddExpense_validExpense_success() {
        Expense Expense3 = new Expense(100.0, "Drink", LocalDateTime.of(2023, Month.APRIL,
                25, 12, 0), Category.ENTERTAINMENT);
        ExpenseList.addExpense(Expense3);
        LinkedList<Expense> expectedExpenseList = new LinkedList<>();
        expectedExpenseList.add(Expense1);
        expectedExpenseList.add(Expense2);
        expectedExpenseList.add(Expense3);
        assertEquals(expectedExpenseList, ExpenseList.expenseList);
    }

}