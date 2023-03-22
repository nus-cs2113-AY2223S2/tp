package chching.command;

import chching.record.Expense;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Junit Test for AddExpenseCommand
 */
public class AddExpenseCommandTest {

    static final String CATEGORY = "transport";
    static final String DESCRIPTION = "public transport";

    static final String DATE = "1st apr 2023";
    static final float EXPENSE_VALUE = (float) 1.50;
    static final float ZERO_EXPENSE_VALUE = (float) 0;
    static final float NEGATIVE_EXPENSE_VALUE = (float) -1.50;



    /**
     * Junit Test when there is null field for the expense input
     */
    @Test
    void execute_nullExpense_exceptionThrown() {
        String expectedOutput = "No fields found";
        try {
            new AddExpenseCommand(null);
            fail(); // test should not reach this line
        } catch (Exception e) {
            assertEquals(expectedOutput, e.getMessage());
        }
    }

    /**
     * Junit Test when there is missing category for the expense input
     */
    @Test
    void execute_missingCategoryField_exceptionThrown() {
        String expectedOutput = "Missing category field";
        Expense input = new Expense(null, DESCRIPTION , DATE, EXPENSE_VALUE);
        try {
            new AddExpenseCommand(input);
            fail(); // test should not reach this line
        } catch (Exception e) {
            assertEquals(expectedOutput, e.getMessage());
        }
    }

    /**
     * Junit Test when there is missing description for the expense input
     */
    @Test
    void execute_missingDescriptionField_exceptionThrown() {
        String expectedOutput = "Missing description field";
        Expense input = new Expense(CATEGORY, null, DATE, EXPENSE_VALUE);
        try {
            new AddExpenseCommand(input);
            fail(); // test should not reach this line
        } catch (Exception e) {
            assertEquals(expectedOutput, e.getMessage());
        }
    }

    /**
     * Junit Test when there is missing date for the expense input
     */
    @Test
    void execute_missingDateField_exceptionThrown() {
        String expectedOutput = "Missing date field";
        Expense input = new Expense(CATEGORY, DESCRIPTION, null, EXPENSE_VALUE);
        try {
            new AddExpenseCommand(input);
            fail(); // test should not reach this line
        } catch (Exception e) {
            assertEquals(expectedOutput, e.getMessage());
        }
    }

    /**
     * Junit Test when there is a zeroValue field
     */
    @Test
    void execute_zeroValueField_exceptionThrown() {
        String expectedOutput = "Invalid/Missing expense value";
        Expense input = new Expense(CATEGORY, DESCRIPTION, DATE, ZERO_EXPENSE_VALUE);
        try {
            new AddExpenseCommand(input);
            fail(); // test should not reach this line
        } catch (Exception e) {
            assertEquals(expectedOutput, e.getMessage());
        }
    }

    /**
     * Junit Test when there is a negative value field
     */
    @Test
    void execute_negativeValueField_exceptionThrown() {
        String expectedOutput = "Invalid/Missing expense value";
        Expense input = new Expense(CATEGORY, DESCRIPTION, DATE, NEGATIVE_EXPENSE_VALUE);
        try {
            new AddExpenseCommand(input);
            fail(); // test should not reach this line
        } catch (Exception e) {
            assertEquals(expectedOutput, e.getMessage());
        }
    }
}
