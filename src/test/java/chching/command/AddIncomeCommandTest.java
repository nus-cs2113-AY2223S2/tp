package chching.command;

import chching.record.Income;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class AddIncomeCommandTest {

    final static String DESCRIPTION = "salary";

    final static String DATE = "1st apr 2023";
    final static float INCOME_VALUE = (float) 500;
    final static float ZERO_INCOME_VALUE = (float) 0;
    final static float NEGATIVE_INCOME_VALUE = (float) -500;
    @Test
    void addIncomeCommand_nullExpense_exceptionThrown() {
        String expectedOutput = "No fields found";
        try {
            new AddIncomeCommand(null);
            fail(); // test should not reach this line
        } catch (Exception e) {
            assertEquals(expectedOutput, e.getMessage());
        }
    }
    
    @Test
    void addIncomeCommand_missingDescriptionField_exceptionThrown() {
        String expectedOutput = "Missing description field";
        Income input = new Income(null, DATE, INCOME_VALUE);
        try {
            new AddIncomeCommand(input);
            fail(); // test should not reach this line
        } catch (Exception e) {
            assertEquals(expectedOutput, e.getMessage());
        }
    }
    
    @Test
    void addIncomeCommand_missingDateField_exceptionThrown() {
        String expectedOutput = "Missing date field";
        Income input = new Income(DESCRIPTION, null, INCOME_VALUE);
        try {
            new AddIncomeCommand(input);
            fail(); // test should not reach this line
        } catch (Exception e) {
            assertEquals(expectedOutput, e.getMessage());
        }
    }
    
    @Test
    void addIncomeCommand_zeroValueField_exceptionThrown() {
        String expectedOutput = "Invalid/Missing income value";
        Income input = new Income(DESCRIPTION, DATE, ZERO_INCOME_VALUE);
        try {
            new AddIncomeCommand(input);
            fail(); // test should not reach this line
        } catch (Exception e) {
            assertEquals(expectedOutput, e.getMessage());
        }
    }
    
    @Test
    void addIncomeCommand_negativeValueField_exceptionThrown() {
        String expectedOutput = "Invalid/Missing income value";
        Income input = new Income(DESCRIPTION, DATE, NEGATIVE_INCOME_VALUE);
        try {
            new AddIncomeCommand(input);
            fail(); // test should not reach this line
        } catch (Exception e) {
            assertEquals(expectedOutput, e.getMessage());
        }
    }
}
