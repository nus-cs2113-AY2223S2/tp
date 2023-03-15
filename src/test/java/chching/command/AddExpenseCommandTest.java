package chching.command;

import chching.record.Expense;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class AddExpenseCommandTest {
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
        Expense input = new Expense(null, "public transport", "1st apr 2023", (float) 1.50);
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
        Expense input = new Expense("transport", null, "1st apr 2023", (float) 1.50);
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
        Expense input = new Expense("transport", "public transport", null, (float) 1.50);
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
        Expense input = new Expense("transport", "public transport", "1st apr 2023", (float) 0);
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
        Expense input = new Expense("transport", "public transport", "1st apr 2023", (float) -1.50);
        try {
            new AddExpenseCommand(input);
            fail(); // test should not reach this line
        } catch (Exception e) {
            assertEquals(expectedOutput, e.getMessage());
        }
    }
}
