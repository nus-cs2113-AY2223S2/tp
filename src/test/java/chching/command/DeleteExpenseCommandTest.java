package chching.command;

import chching.record.Expense;
import chching.record.ExpenseList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;


class DeleteExpenseCommandTest {
    private Expense spending;
    private Expense groceries;
    private ExpenseList defaultExpenseList;
    
    @BeforeEach
    void setup() {
        spending = new Expense("entertainment", "movie", "02/10/23", 10.50);
        groceries = new Expense("groceries","too much groceries","1st apr 2023",500);
        
        
        ArrayList<Expense> expenseList = new ArrayList<Expense>();
        expenseList.add(spending);
        expenseList.add(groceries);
        defaultExpenseList = new ExpenseList(expenseList);
    }

    @Test
    void deleteExpense_positiveIntegerWithinSize_success(){
        int defaultExpenseListSize = defaultExpenseList.size();
        defaultExpenseList.deleteExpense(1);
        assertEquals(defaultExpenseListSize - 1,defaultExpenseList.size(),"Delete expense working");
    }

    @Test
    void deleteExpense_positiveIntegerOutsideSize_fail() {
        String expectedOutput = "The number is too big";
        try {
            defaultExpenseList.deleteExpense(3);
        } catch (Exception e) {
            assertEquals(expectedOutput, e.getMessage(), "Delete expense with integer outside size is not captured");
        }
    }

    @Test
    void deleteExpense_negativeInteger_fail() {
        String expectedOutput = "Negative/Zero index";
        try {
            defaultExpenseList.deleteExpense(-5);
        } catch (Exception e) {
            assertEquals(expectedOutput, e.getMessage(), "Delete expense with negative integer is not captured");
        }
    }
}
