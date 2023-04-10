/* @@author avielcx
   @@thomasjlalba */
package chching.command;

import chching.Storage;
import chching.Ui;
import chching.record.ExpenseList;
import chching.record.IncomeList;
import chching.record.TargetStorage;
import chching.record.Target;
import chching.record.Expense;
import chching.record.Income;
import chching.currency.Selector;
import chching.currency.Converter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Junit Test for BalanceCommand
 */
class BalanceCommandTest {
    static final String INCOME_DESCRIPTION = "salary";
    static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-uuuu");
    static final LocalDate INCOME_DATE = LocalDate.parse("01-04-2023", FORMATTER);
    static final float INCOME_VALUE = (float) 5000;
    static final String EXPENSE_CATEGORY = "grocery";
    static final String EXPENSE_DESCRIPTION = "too much groceries";
    static final LocalDate EXPENSE_DATE = LocalDate.parse("01-04-2023", FORMATTER);
    static final float EXPENSE_VALUE = (float) 500;

    private Ui ui;
    private Storage storage;
    private Income salary;
    private Expense groceries;
    private IncomeList defaultIncomeList;
    private ExpenseList defaultExpenseList;
    private Selector selector;
    private Converter converter;
    private TargetStorage targetStorage;
    
    @BeforeEach
    void setup() {
        ui = new Ui();
        converter = new Converter();
        
        Target target = new Target(100);
        targetStorage = new TargetStorage();
        targetStorage.addTarget(target);
        
        selector = new Selector();
        selector.setCurrency("SGD");
        
        salary = new Income(INCOME_DESCRIPTION, INCOME_DATE, INCOME_VALUE);
        groceries = new Expense(EXPENSE_CATEGORY, EXPENSE_DESCRIPTION, EXPENSE_DATE, EXPENSE_VALUE);

        
        ArrayList<Income> incomeList = new ArrayList<Income>();
        incomeList.add(salary);
        defaultIncomeList = new IncomeList(incomeList);
        
        ArrayList<Expense> expenseList = new ArrayList<Expense>();
        expenseList.add(groceries);
        defaultExpenseList = new ExpenseList(expenseList);
    }

    /**
     * Junit Test for when balance is correct
     */
    @Test
    void execute_normalScenario_success() {
        String expectedOutput = "4500.00";
        try {
            Command command = new BalanceCommand();
            command.execute(defaultIncomeList, defaultExpenseList, ui, storage, selector, converter, targetStorage);
            assertEquals(expectedOutput, ((BalanceCommand) command).showBalance(), "Balance calculation is right");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            fail();
        }
    }
}
