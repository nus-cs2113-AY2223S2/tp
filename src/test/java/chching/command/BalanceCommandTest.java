package chching.command;

import chching.record.Expense;
import chching.record.ExpenseList;
import chching.record.Income;
import chching.record.IncomeList;

import static org.junit.jupiter.api.Assertions.*;

class BalanceCommandTest {
    void addDeleteIncome_positiveIntegerWithinSize_Success() {
        Income income0 = new Income("salary", "1st apr 2023", 5000);
        IncomeList incomes = new IncomeList();
        incomes.addIncome(income0);
        Expense expense0 = new Expense("salary", "too much groceries", "1st apr 2023", 500);
        ExpenseList expenses = new ExpenseList();
        expenses.addExpense(expense0);
        double balance = incomes.getBalance() - expenses.getBalance();
        assertEquals(4500, balance, "Balance calculation is right");
    }

}