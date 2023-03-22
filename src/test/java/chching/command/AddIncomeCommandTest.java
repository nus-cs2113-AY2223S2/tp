package chching.command;

import chching.record.Income;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Junit Test for AddIncomeCommand
 */
public class AddIncomeCommandTest {

    static final String DESCRIPTION = "salary";
    static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    static final LocalDate DATE = LocalDate.parse("01-04-2023", FORMATTER);
    static final float INCOME_VALUE = (float) 500;
    static final float ZERO_INCOME_VALUE = (float) 0;
    static final float NEGATIVE_INCOME_VALUE = (float) -500;

    /**
     * Junit Test when there is a null entry in the income input
     */
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

    /**
     * Junit Test when there is a missing description in the income input
     */
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

    /**
     * Junit Test when there is a missing date in the income input
     */
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

    /**
     * Junit Test when there is a zeroValue field
     */
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

    /**
     * Junit Test when there is a negative value field
     */
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
