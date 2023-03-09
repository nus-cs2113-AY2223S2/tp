package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.data.expense.Expense;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExpenseTest {
    @Test
    void getExpenseDescription_expected() {
        String test = "public transport";
        Expense exp = new Expense("public transport", "1st apr 2023", (float) 1.50);
        assertEquals("public transport", exp.getDescription());
    }
}
