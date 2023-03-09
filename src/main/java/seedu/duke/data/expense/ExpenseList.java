package seedu.duke.data.expense;

import java.util.ArrayList;

public class ExpenseList {
    private static ArrayList<Expense> expenseList = new ArrayList<>();
    private static int expenseCount = 0;


    public ExpenseList() {
        expenseList = new ArrayList<>();
        expenseCount = 0;
    }
    
    public ArrayList<Expense> getExpenseList() {
        return expenseList;
    }
    
    public int getExpenseCount() {
        return expenseCount;
    }
    
    public static void addExpense(Expense expense) {
        expenseList.add(expense);
        expenseCount++;
    }
    
    public static void printExpenseList() {
        for (int i = 1; i <= expenseCount; i++) {
            Expense expense = expenseList.get(i - 1);
            System.out.println(i + ". " + expense.toString());
        }
    }
}
