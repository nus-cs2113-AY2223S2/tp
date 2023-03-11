package seedu.duke.data;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExpenseTest {
    @Test
    void getExpenseDescription_expected() {
        String test = "public transport";
        Expense exp = new Expense("public transport", "1st apr 2023", (float) 1.50);
        assertEquals("public transport", exp.getDescription());
    }

    @Test
    void getExpenseDate_expected() {
        String test = "1st apr 2023";
        Expense exp = new Expense("public transport", "1st apr 2023", (float) 1.50);
        assertEquals("1st apr 2023", exp.getDate());
    }

    @Test
    void getExpenseValue_expected() {
        float test = (float) 1.50;
        Expense exp = new Expense("public transport", "1st apr 2023", (float) 1.50);
        assertEquals((float) 1.50, exp.getValue());
    }

    @Test
    void getValue_expected() {
        Expense exp = new Expense("public transport", "1st apr 2023", (float) 1.50);
        String expected = "Expense - public transport | Date - 1st apr 2023 | value - 1.5";
        assertEquals(expected, exp.toString());
    }
}

