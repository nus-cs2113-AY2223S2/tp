package seedu.duke.data.expense;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ExpenseListTest {

    @Test
    void getExpenseCount_one_expectOne() {

        Expense expenseOne = new Expense("beach party", "23 may 2023", 50);
        ExpenseList list = new ExpenseList();
        list.addExpense(expenseOne);
        assertEquals(1, list.getExpenseCount());
    }
}
