/* @@author avielcx */

package chching.command;

import chching.Storage;
import chching.Ui;
import chching.currency.Converter;
import chching.currency.Selector;
import chching.record.Expense;
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
 * Junit Test for AddExpenseCommand
 */
public class AddExpenseCommandTest {

    static final String CATEGORY = "transport";
    static final String DESCRIPTION = "public transport";
    static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-uuuu");
    static final LocalDate DATE = LocalDate.parse("01-04-2023", FORMATTER);
    static final float EXPENSE_VALUE = (float) 1.50;
    static final float ZERO_EXPENSE_VALUE = (float) 0;
    static final float NEGATIVE_EXPENSE_VALUE = (float) -1.50;
    private Ui ui;
    private Storage storage;
    private Selector selector;
    private Converter converter;
    private TargetStorage targetStorage;
    private IncomeList incomes;
    private ExpenseList expenses;
    private Expense transport;
    
    /**
     * Set up the test environment.
     * creates an empty ExpenseList expenses and empty IncomeList incomes.
     * transport has category CATEGORY, description DESCRIPTION,
     * date DATE, value EXPENSE_VALUE.
     */
    @BeforeEach
    void setUp() {
        ui = new Ui();
        
        transport = new Expense(CATEGORY, DESCRIPTION, DATE, EXPENSE_VALUE);
        expenses = new ExpenseList();
        incomes = new IncomeList();
    }
    
    /**
     * Junit Test when there is valid expense input
     * to execute successfully and add to expenses
     */
    @Test
    void execute_normalScenario_success() {
        try {
            int expensesSize = expenses.getRecordCount();
            Command command = new AddExpenseCommand(transport);
            command.execute(incomes, expenses, ui, storage, selector, converter, targetStorage);
            assertEquals(expensesSize + 1, expenses.size());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            fail(); // test should not reach this line
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
