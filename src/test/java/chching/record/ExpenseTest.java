package chching.record;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Junit Test for Expense
 */
public class ExpenseTest {
    static final String CATEGORY = "transport";
    static final String DESCRIPTION = "public transport";
    static final String DATE = "1st apr 2023";
    static final float EXPENSE_VALUE = (float) 1.50;

    /**
     * Junit Test to get expense description
     */
    @Test
    void getExpenseDescription_expected() {
        String test = "public transport";
        Expense exp = new Expense(CATEGORY, DESCRIPTION, DATE, EXPENSE_VALUE);
        assertEquals("public transport", exp.getDescription());
    }

    /**
     * Junit Test to get expense date
     */
    @Test
    void getExpenseDate_expected() {
        String test = "1st apr 2023";
        Expense exp = new Expense(CATEGORY, DESCRIPTION, DATE, EXPENSE_VALUE);
        assertEquals("1st apr 2023", exp.getDate());
    }


    /**
     * Junit Test to get expense value
     */
    @Test
    void getExpenseValue_expected() {
        float test = (float) 1.50;
        Expense exp = new Expense(CATEGORY, DESCRIPTION, DATE, EXPENSE_VALUE);
        assertEquals((float) 1.50, exp.getValue());
    }

    /**
     * Junit Test to get expense entry as a String
     */
    @Test
    void getValue_expected() {
        Expense exp = new Expense(CATEGORY, DESCRIPTION, DATE, EXPENSE_VALUE);
        String expected = "Category - transport | Description - public transport | Date - 1st apr 2023 | Value - 1.50";
        assertEquals(expected, exp.toString());
    }
}

