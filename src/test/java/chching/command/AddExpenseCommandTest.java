package chching.command;

import chching.record.Expense;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class AddExpenseCommandTest {
    @Test
    void addExpenseCommand_nullExpense_exceptionThrown() {
        try {
            new AddExpenseCommand(null);
            fail(); // test should not reach this line
        } catch (Exception e) {
            assertEquals("No fields found", e.getMessage());
        }
    }
    
    @Test
    void addExpenseCommand_missingCategoryField_exceptionThrown() {
        Expense input = new Expense(null, "public transport", "1st apr 2023", (float) 1.50);
        try {
            new AddExpenseCommand(input);
            fail(); // test should not reach this line
        } catch (Exception e) {
            assertEquals("Missing category field", e.getMessage());
        }
    }
    
    @Test
    void addExpenseCommand_missingDescriptionField_exceptionThrown() {
        Expense input = new Expense("transport", null, "1st apr 2023", (float) 1.50);
        try {
            new AddExpenseCommand(input);
            fail(); // test should not reach this line
        } catch (Exception e) {
            assertEquals("Missing description field", e.getMessage());
        }
    }
    
    @Test
    void addExpenseCommand_missingDateField_exceptionThrown() {
        Expense input = new Expense("transport", "public transport", null, (float) 1.50);
        try {
            new AddExpenseCommand(input);
            fail(); // test should not reach this line
        } catch (Exception e) {
            assertEquals("Missing date field", e.getMessage());
        }
    }
    
    @Test
    void addExpenseCommand_zeroValueField_exceptionThrown() {
        Expense input = new Expense("transport", "public transport", "1st apr 2023", (float) 0);
        try {
            new AddExpenseCommand(input);
            fail(); // test should not reach this line
        } catch (Exception e) {
            assertEquals("Invalid/Missing expense value", e.getMessage());
        }
    }
    
    @Test
    void addExpenseCommand_negativeValueField_exceptionThrown() {
        Expense input = new Expense("transport", "public transport", "1st apr 2023", (float) -1.50);
        try {
            new AddExpenseCommand(input);
            fail(); // test should not reach this line
        } catch (Exception e) {
            assertEquals("Invalid/Missing expense value", e.getMessage());
        }
    }
}
