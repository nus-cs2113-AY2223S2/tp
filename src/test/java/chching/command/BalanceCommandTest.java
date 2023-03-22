package chching.command;

import chching.Storage;
import chching.Ui;
import chching.record.Expense;
import chching.record.ExpenseList;
import chching.record.Income;
import chching.record.IncomeList;
import chching.currency.Selector;
import chching.currency.Converter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class BalanceCommandTest {
    static final String INCOME_DESCRIPTION = "salary";

    static final String INCOME_DATE = "1st apr 2023";
    static final float INCOME_VALUE = (float) 5000;

    static final String EXPENSE_CATEGORY = "grocery";
    static final String EXPENSE_DESCRIPTION = "too much groceries";

    static final String EXPENSE_DATE = "1st apr 2023";
    static final float EXPENSE_VALUE = (float) 500;

    private Ui ui;
    private Storage storage;
    private Income salary;
    private Expense groceries;
    private IncomeList defaultIncomeList;
    private ExpenseList defaultExpenseList;
    private Selector selector;
    private Converter converter;
    
    @BeforeEach
    void setup() {
        ui = new Ui();
        
        salary = new Income(INCOME_DESCRIPTION, INCOME_DATE, INCOME_VALUE);
        groceries = new Expense(EXPENSE_CATEGORY, EXPENSE_DESCRIPTION, EXPENSE_DATE, EXPENSE_VALUE);
        selector = new Selector();
        converter = new Converter();

        
        ArrayList<Income> incomeList = new ArrayList<Income>();
        incomeList.add(salary);
        defaultIncomeList = new IncomeList(incomeList);
        
        ArrayList<Expense> expenseList = new ArrayList<Expense>();
        expenseList.add(groceries);
        defaultExpenseList = new ExpenseList(expenseList);
    }
    @Test
    void execute_normalScenario_success() {
        String expectedOutput = "4500.00";
        Command command = new BalanceCommand();
        try {
            command.execute(defaultIncomeList, defaultExpenseList, ui, storage, selector, converter);
            assertEquals(expectedOutput, ((BalanceCommand) command).showBalance(), "Balance calculation is right");
        } catch (Exception e) {
            fail(); // test should not reach this line
        }
    }
}
