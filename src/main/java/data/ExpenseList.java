package data;


import parser.Parser;

import java.util.ArrayList;

public class ExpenseList {
    public static ArrayList<Expense> expenseList = new ArrayList<>();
    protected Parser parser = new Parser();


    public ArrayList<Expense> getExpenseList() {
        return expenseList;
    }

    public void deleteExpense(String userInput) {
        int expenseIndex = parser.extractIndex(userInput);
        expenseList.remove(expenseIndex - 1); // change to 0-based indexing
    }

    //for list
    public static String getAllMessage() {
        int count = expenseList.size();
        // Align the output statement position with the start instead of using tab to indent
        return "Now you have " + count + " " + printExpensesOrExpense(count) + " in the list.";
    }

    private static String printExpensesOrExpense(int count) {
        return ((count == 1) ? "expense" : "expenses");
    }

    private static void showToUser(String... message) {
        for (String i : message) {
            System.out.println(i);
        }
    }

    // In order to clear the list for test
    public static void clear() {
        expenseList.clear();
    }

}

