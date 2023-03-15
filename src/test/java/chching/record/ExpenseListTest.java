package chching.record;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ExpenseListTest {
    final static int EXPECTED_SIZE = 1;
    final static String CATEGORY = "entertainment";
    final static String DESCRIPTION = "beach party";

    final static String DATE = "23 may 2023";
    final static float EXPENSE_VALUE = (float) 50;

    @Test
    void getExpenseCount_one_expectOne() {

        Expense expenseOne = new Expense(CATEGORY, DESCRIPTION, DATE, EXPENSE_VALUE);
        ExpenseList list = new ExpenseList();
        list.addRecord(expenseOne);
        assertEquals(EXPECTED_SIZE, list.getRecordCount());
    }
}
