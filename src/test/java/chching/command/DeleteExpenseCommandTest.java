package chching.command;

import chching.record.Expense;
import chching.record.ExpenseList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeleteExpenseCommandTest {

    @Test
    void addDeleteIncome_positiveIntegerWithinSize_Success(){
        Expense expense0 = new Expense("groceries","too much groceries","1st apr 2023",500);
        ExpenseList expenses = new ExpenseList();
        expenses.addExpense(expense0);
        expenses.deleteExpense(0);
        assertEquals(0,expenses.size(),"Delete expense working");

    }

    @Test
    void addDeleteIncome_positiveIntegerOutsideSize_Fail() {
        Expense expense0 = new Expense("salary", "too much groceries","1st apr 2023", 500);
        ExpenseList expenses = new ExpenseList();
        expenses.addExpense(expense0);
        try {
            expenses.deleteExpense(3);
        } catch (Exception e) {
            assertEquals("The number is too big", e.getMessage(), "Delete expense with integer outside size is not captured");
        }
    }

    @Test
    void addDeleteIncome_negativeInteger_Fail() {
        Expense expense0 = new Expense("salary", "too much groceries","1st apr 2023", 500);
        ExpenseList expenses = new ExpenseList();
        expenses.addExpense(expense0);
        try {
            expenses.deleteExpense(-5);
        } catch (Exception e) {
            assertEquals("The number is too big", e.getMessage(), "Delete expense with negative integer is not captured");
        }
    }
}