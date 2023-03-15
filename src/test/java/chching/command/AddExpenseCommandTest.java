package chching.command;

import chching.record.Expense;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


public class AddExpenseCommandTest {

    final static String CATEGORY = "transport";
    final static String DESCRIPTION = "public transport";

    final static String DATE = "1st apr 2023";
    final static float EXPENSE_VALUE = (float) 1.50;
    final static float ZERO_EXPENSE_VALUE = (float) 0;
    final static float NEGATIVE_EXPENSE_VALUE = (float) -1.50;




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
