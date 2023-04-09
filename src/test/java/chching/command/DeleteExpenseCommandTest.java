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
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


/**
 * Junit Test for DeleteExpenseCommand
 */
class DeleteExpenseCommandTest {
    static final int OFFSET = 1;
    static final int CORRECT_INDEX = 1;
    static final int TOO_LARGE_INDEX = 5;
    static final int NEGATIVE_INDEX = -1;

    static final String SPENDING_CATEGORY = "entertainment";
    static final String SPENDING_DESCRIPTION = "movie";
    static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-uuuu");
    static final LocalDate SPENDING_DATE = LocalDate.parse("02-10-2023", FORMATTER);
    static final float SPENDING_EXPENSE_VALUE = (float) 10.50;

    static final String GROCERIES_CATEGORY = "entertainment";
    static final String GROCERIES_DESCRIPTION = "movie";
    static final LocalDate GROCERIES_DATE = LocalDate.parse("02-10-2023", FORMATTER);
    static final float GROCERIES_EXPENSE_VALUE = (float) 500;
    private Ui ui;
    private Storage storage;
    private Expense spending;
    private Expense groceries;
    private ExpenseList defaultExpenseList;
    private IncomeList emptyIncomeList;
    private Selector selector;
    private Converter converter;
    private TargetStorage targetStorage;

    @BeforeEach
    void setup() {
        ui = new Ui();
        spending = new Expense(SPENDING_CATEGORY, SPENDING_DESCRIPTION, SPENDING_DATE, SPENDING_EXPENSE_VALUE);
        groceries = new Expense(GROCERIES_CATEGORY, GROCERIES_DESCRIPTION, GROCERIES_DATE, GROCERIES_EXPENSE_VALUE);
        selector = new Selector();
        converter = new Converter();

        ArrayList<Expense> expenseList = new ArrayList<Expense>();
        expenseList.add(spending);
        expenseList.add(groceries);
        defaultExpenseList = new ExpenseList(expenseList);
    }

    /**
     * Junit Test when deleting an entry of index within the size of the ExpenseList
     */
    @Test
    void execute_positiveIntegerWithinSize_success() {
        int defaultExpenseListSize = defaultExpenseList.size();
        Command command = new DeleteExpenseCommand(CORRECT_INDEX);
        try {
            command.execute(emptyIncomeList, defaultExpenseList, ui, storage, selector, converter, targetStorage);
            assertEquals(defaultExpenseListSize - OFFSET, defaultExpenseList.size(), "Delete expense working");
        } catch (Exception e) {
            fail();
        }
    }

    /**
     * Junit Test when deleting an entry of index outside the size of the ExpenseList
     */
    @Test
    void execute_positiveIntegerOutsideSize_exceptionThrown() {
        String expectedOutput = "The number is too big";
        Command command = new DeleteExpenseCommand(TOO_LARGE_INDEX);
        try {
            command.execute(emptyIncomeList, defaultExpenseList, ui, storage, selector, converter, targetStorage);
            fail();
        } catch (Exception e) {
            assertEquals(expectedOutput, e.getMessage(), "Delete expense with integer outside size is not captured");
        }
    }

    /**
     * Junit Test when deleting a negative index of the ExpenseList
     */
    @Test
    void execute_negativeInteger_exceptionThrown() {
        String expectedOutput = "Negative/Zero index";
        Command command = new DeleteExpenseCommand(NEGATIVE_INDEX);
        try {
            command.execute(emptyIncomeList, defaultExpenseList, ui, storage, selector, converter, targetStorage);
            fail();
        } catch (Exception e) {
            assertEquals(expectedOutput, e.getMessage(), "Delete expense with negative integer is not captured");
        }
    }
}
