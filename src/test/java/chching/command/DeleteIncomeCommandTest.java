package chching.command;

import chching.Storage;
import chching.Ui;
import chching.record.ExpenseList;
import chching.record.IncomeList;
import chching.record.Income;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


class DeleteIncomeCommandTest {
    private Ui ui;
    private Storage storage;
    private Income salary;
    private Income bonus;
    private ExpenseList emptyExpenseList;
    private IncomeList defaultIncomeList;
    
    @BeforeEach
    void setup() {
        salary = new Income("salary","1st apr 2023",5000);
        bonus = new Income("bonus", "5/2/23", 3000);
        
        ArrayList<Income> incomeList = new ArrayList<Income>();
        incomeList.add(salary);
        incomeList.add(bonus);
        defaultIncomeList = new IncomeList(incomeList);
    }

    @Test
    void deleteIncome_positiveIntegerWithinSize_success() {
        int defaultIncomeListSize = defaultIncomeList.size();
        Command command = new DeleteIncomeCommand(1);
        try {
            command.execute(defaultIncomeList, emptyExpenseList, ui, storage);
            assertEquals(defaultIncomeListSize - 1, defaultIncomeList.size(), "Delete income working");
        } catch (Exception e) {
            fail(); // test should not reach here
        }
    }

    @Test
    void deleteIncome_positiveIntegerOutsideSize_fail() {
        String expectedOutput = "The number is too big";
        Command command = new DeleteExpenseCommand(5);
        try {
            command.execute(defaultIncomeList, emptyExpenseList, ui, storage);
        } catch (Exception e) {
            assertEquals(expectedOutput, e.getMessage(), "Delete income with integer outside size is not captured");
        }
    }

    @Test
    void deleteIncome_negativeInteger_fail() {
        String expectedOutput = "Negative/Zero index";
        Command command = new DeleteExpenseCommand(-1);
        try {
            command.execute(defaultIncomeList, emptyExpenseList, ui, storage);
        } catch (Exception e) {
            assertEquals(expectedOutput, e.getMessage(), "Delete income with negative integer is not captured");
        }
    }
}
