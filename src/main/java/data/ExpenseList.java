package data;


import parser.Parser;

import java.util.ArrayList;

public class ExpenseList {
    protected Parser parser = new Parser();
    private ArrayList<Expense> expenseList = new ArrayList<>();

    public void setExpenseList(ArrayList<Expense> expenseList) {
        this.expenseList = expenseList;
    }

    public ArrayList<Expense> getExpenseList() {
        return expenseList;
    }

    public static String getAllMessage(ArrayList<Expense> expenseList) {
        int count = expenseList.size();
        // Align the output statement position with the start instead of using tab to indent
        return "Now you have " + count + " " + printExpensesOrExpense(count) + " in the list.";
    }

    private static String printExpensesOrExpense(int count) {
        return ((count == 1) ? "expense" : "expenses");
    }

    // In order to clear the list for test
    public void clear() {
        expenseList.clear();
    }

}

