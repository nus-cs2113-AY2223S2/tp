package chching.command;

import chching.record.Expense;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


public class AddExpenseCommandTest {

    static final String CATEGORY = "transport";
    static final String DESCRIPTION = "public transport";
    static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    static final LocalDate DATE = LocalDate.parse("01-04-2023", FORMATTER);
    static final float EXPENSE_VALUE = (float) 1.50;
    static final float ZERO_EXPENSE_VALUE = (float) 0;
    static final float NEGATIVE_EXPENSE_VALUE = (float) -1.50;




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
