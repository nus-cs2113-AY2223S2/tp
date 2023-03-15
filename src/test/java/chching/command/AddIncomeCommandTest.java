package chching.command;

import chching.record.Income;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class AddIncomeCommandTest {
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
        Income input = new Income(null, "1st apr 2023", (float) 500);
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
        Income input = new Income("salary", null, (float) 500);
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
        Income input = new Income("salary", "1st apr 2023", (float) 0);
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
        Income input = new Income("salary", "1st apr 2023", (float) -500);
        try {
            new AddIncomeCommand(input);
            fail(); // test should not reach this line
        } catch (Exception e) {
            assertEquals(expectedOutput, e.getMessage());
        }
    }
}
