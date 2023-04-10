/* @@author avielcx */
package chching.command;

import chching.Storage;
import chching.Ui;
import chching.currency.Converter;
import chching.currency.Selector;
import chching.record.ExpenseList;
import chching.record.IncomeList;
import chching.record.Income;
import chching.record.TargetStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


/**
 * Junit Test for DeleteIncomeCommand
 */
class DeleteIncomeCommandTest {

    static final int OFFSET = 1;
    static final int CORRECT_INDEX = 1;
    static final int TOO_LARGE_INDEX = 5;
    static final int NEGATIVE_INDEX = -1;
    static final String SALARY_DESCRIPTION = "salary";
    static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-uuuu");
    static final LocalDate SALARY_DATE = LocalDate.parse("01-04-2023", FORMATTER);
    static final float SALARY_INCOME_VALUE = (float) 5000;
    static final String BONUS_DESCRIPTION = "salary";
    static final LocalDate BONUS_DATE = LocalDate.parse("05-02-2023", FORMATTER);
    static final float BONUS_INCOME_VALUE = (float) 3000;
    private Ui ui;
    private Storage storage;
    private Income salary;
    private Income bonus;
    private ExpenseList emptyExpenseList;
    private IncomeList defaultIncomeList;
    private Selector selector;
    private Converter converter;
    private TargetStorage targetStorage;

    @BeforeEach
    void setup() {
        salary = new Income(SALARY_DESCRIPTION, SALARY_DATE, SALARY_INCOME_VALUE);
        bonus = new Income(BONUS_DESCRIPTION, BONUS_DATE, BONUS_INCOME_VALUE);
        selector = new Selector();
        converter = new Converter();

        ArrayList<Income> incomeList = new ArrayList<Income>();
        incomeList.add(salary);
        incomeList.add(bonus);
        defaultIncomeList = new IncomeList(incomeList);
    }

    /**
     * Junit Test when deleting an entry of index within the size of the IncomeList
     */
    @Test
    void execute_positiveIntegerWithinSize_success() {
        int defaultIncomeListSize = defaultIncomeList.size();
        Command command = new DeleteIncomeCommand(CORRECT_INDEX);
        try {
            command.execute(defaultIncomeList, emptyExpenseList, ui, storage, selector, converter, targetStorage);
            assertEquals(defaultIncomeListSize - OFFSET, defaultIncomeList.size(), "Delete income working");
        } catch (Exception e) {
            fail();
        }
    }

    /**
     * Junit Test when deleting an entry of index outside the size of the IncomeList
     */
    @Test
    void execute_positiveIntegerOutsideSize_exceptionThrown() {
        String expectedOutput = "The number is too big";
        Command command = new DeleteIncomeCommand(TOO_LARGE_INDEX);
        try {
            command.execute(defaultIncomeList, emptyExpenseList, ui, storage, selector, converter, targetStorage);
            fail();
        } catch (Exception e) {
            assertEquals(expectedOutput, e.getMessage(), "Delete income with integer outside size is not captured");
        }
    }

    /**
     * Junit Test when deleting a negative index of the IncomeList
     */
    @Test
    void execute_negativeInteger_exceptionThrown() {
        String expectedOutput = "Negative/Zero index";
        Command command = new DeleteIncomeCommand(NEGATIVE_INDEX);
        try {
            command.execute(defaultIncomeList, emptyExpenseList, ui, storage, selector, converter, targetStorage);
            fail();
        } catch (Exception e) {
            assertEquals(expectedOutput, e.getMessage(), "Delete income with negative integer is not captured");
        }
    }
}
