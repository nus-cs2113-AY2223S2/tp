package MajorClasses;

import MajorClasses.Expense;

import java.util.ArrayList;

public class ExpenseList {
    public ArrayList<Expense> expenseList = new ArrayList<>();
    public ArrayList<Expense> getExpenseList() {
        return expenseList;
    }
    public void deleteExpense (String userInput) {
        int expenseIndex = Integer.parseInt(userInput);
        expenseList.remove(expenseIndex-1); // change to 0-based indexing
    }
}

