package chching.command;

import chching.record.Income;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class AddIncomeCommandTest {
    @Test
    void AddIncomeCommand_nullExpense_exceptionThrown() {
        try {
            new AddIncomeCommand(null);
            fail(); // test should not reach this line
        } catch (Exception e) {
            assertEquals("No fields found", e.getMessage());
        }
    }
    
    @Test
    void AddIncomeCommand_missingDescriptionField_exceptionThrown() {
        Income input = new Income(null, "1st apr 2023", (float) 500);
        try {
            new AddIncomeCommand(input);
            fail(); // test should not reach this line
        } catch (Exception e) {
            assertEquals("Missing description field", e.getMessage());
        }
    }
    
    @Test
    void AddIncomeCommand_missingDateField_exceptionThrown() {
        Income input = new Income("salary", null, (float) 500);
        try {
            new AddIncomeCommand(input);
            fail(); // test should not reach this line
        } catch (Exception e) {
            assertEquals("Missing date field", e.getMessage());
        }
    }
    
    @Test
    void AddIncomeCommand_zeroValueField_exceptionThrown() {
        Income input = new Income("salary", "1st apr 2023", (float) 0);
        try {
            new AddIncomeCommand(input);
            fail(); // test should not reach this line
        } catch (Exception e) {
            assertEquals("Invalid/Missing income value", e.getMessage());
        }
    }
    
    @Test
    void AddIncomeCommand_negativeValueField_exceptionThrown() {
        Income input = new Income("salary", "1st apr 2023", (float) -500);
        try {
            new AddIncomeCommand(input);
            fail(); // test should not reach this line
        } catch (Exception e) {
            assertEquals("Invalid/Missing income value", e.getMessage());
        }
    }
}
