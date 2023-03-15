package chching.command;

import chching.Storage;
import chching.Ui;
import chching.record.Expense;
import chching.record.ExpenseList;
import chching.record.Income;
import chching.record.IncomeList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class BalanceCommandTest {
    private Ui ui;
    private Storage storage;
    private Income salary;
    private Expense groceries;
    private IncomeList defaultIncomeList;
    private ExpenseList defaultExpenseList;
    
    @BeforeEach
    void setup() {
        ui = new Ui();
        
        salary = new Income("salary", "1st apr 2023", 5000);
        groceries = new Expense("groceries", "too much groceries", "1st apr 2023", 500);
        
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
            command.execute(defaultIncomeList, defaultExpenseList, ui, storage);
            assertEquals(expectedOutput, ((BalanceCommand) command).showBalance(), "Balance calculation is right");
        } catch (Exception e) {
            fail(); // test should not reach this line
        }
    }
}
