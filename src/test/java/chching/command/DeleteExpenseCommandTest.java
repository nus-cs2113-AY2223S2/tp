package chching.command;

import chching.Storage;
import chching.Ui;
import chching.record.Expense;
import chching.record.ExpenseList;
import chching.record.IncomeList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


class DeleteExpenseCommandTest {
    private Ui ui;
    private Storage storage;
    private Expense spending;
    private Expense groceries;
    private ExpenseList defaultExpenseList;
    private IncomeList emptyIncomeList;
    
    @BeforeEach
    void setup() {
        ui = new Ui();
        spending = new Expense("entertainment", "movie", "02/10/23", 10.50);
        groceries = new Expense("groceries","too much groceries","1st apr 2023",500);
        
        
        ArrayList<Expense> expenseList = new ArrayList<Expense>();
        expenseList.add(spending);
        expenseList.add(groceries);
        defaultExpenseList = new ExpenseList(expenseList);
    }

    @Test
    void execute_positiveIntegerWithinSize_success(){
        int defaultExpenseListSize = defaultExpenseList.size();
        Command command = new DeleteExpenseCommand(1);
        try {
            command.execute(emptyIncomeList, defaultExpenseList, ui, storage);
            assertEquals(defaultExpenseListSize - 1, defaultExpenseList.size(), "Delete expense working");
        } catch (Exception e) {
            fail(); // test should not reach this line
        }
    }

    @Test
    void execute_positiveIntegerOutsideSize_exceptionThrown() {
        String expectedOutput = "The number is too big";
        Command command = new DeleteExpenseCommand(5);
        try {
            command.execute(emptyIncomeList, defaultExpenseList, ui, storage);
            fail(); // test should not reach this line
        } catch (Exception e) {
            assertEquals(expectedOutput, e.getMessage(), "Delete expense with integer outside size is not captured");
        }
    }

    @Test
    void execute_negativeInteger_exceptionThrown() {
        String expectedOutput = "Negative/Zero index";
        Command command = new DeleteExpenseCommand(-1);
        try {
            command.execute(emptyIncomeList, defaultExpenseList, ui, storage);
            fail(); // test should not reach this line
        } catch (Exception e) {
            assertEquals(expectedOutput, e.getMessage(), "Delete expense with negative integer is not captured");
        }
    }
}
