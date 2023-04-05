/* @@author avielcx */

package chching.command;

import chching.Storage;
import chching.Ui;
import chching.currency.Converter;
import chching.currency.Selector;
import chching.record.Income;
import chching.record.ExpenseList;
import chching.record.IncomeList;
import chching.record.TargetStorage;
import org.junit.jupiter.api.BeforeEach;
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
    static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-uuuu");
    static final LocalDate DATE = LocalDate.parse("01-04-2023", FORMATTER);
    static final float INCOME_VALUE = (float) 500;
    static final float ZERO_INCOME_VALUE = (float) 0;
    static final float NEGATIVE_INCOME_VALUE = (float) -500;
    private Ui ui;
    private Storage storage;
    private Selector selector;
    private Converter converter;
    private TargetStorage targetStorage;
    private IncomeList incomes;
    private ExpenseList expenses;
    private Income salary;
    
    /**
     * Set up the test environment.
     * creates an empty ExpenseList expenses and empty IncomeList incomes.
     * salary has description DESCRIPTION, date DATE, value INCOME_VALUE.
     */
    @BeforeEach
    void setUp() {
        ui = new Ui();
        
        salary = new Income(DESCRIPTION, DATE, INCOME_VALUE);
        expenses = new ExpenseList();
        incomes = new IncomeList();
    }
    
    /**
     * Junit Test when there is valid income input
     * to execute successfully and add to incomes
     */
    @Test
    void execute_normalScenario_success() {
        try {
            Command addIncomeCommand = new AddIncomeCommand(salary);
            addIncomeCommand.execute(incomes, expenses, ui, storage, selector, converter, targetStorage);
            assertEquals(1, incomes.size());
            assertEquals(salary, incomes.get(0));
        } catch (Exception e) {
            fail();
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
}
