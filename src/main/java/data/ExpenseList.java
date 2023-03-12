package data;


import parser.Parser;

import java.util.ArrayList;

import static common.MessageList.MESSAGE_DIVIDER;
import static common.MessageList.MESSAGE_DIVIDER_LIST;

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

    /**
     * Print the number of tasks when the "list" command is executed
     *
     * @return the number of tasks message
     *
     */
    public static String getAllMessage() {
        int count = expenseList.size();
        return "\t" + "Now you have " + count + " " + printExpensesOrExpense(count) + " in the list.";
    }

    /**
     * Print the word "expense" in plural or singular form
     *
     * @param count the number of expenses
     *
     * @return "expense" if there is 1 expense, "expenses" otherwise.
     *
     */
    private static String printExpensesOrExpense(int count) {
        return ((count == 1) ? "expense" : "expenses");
    }

    /**
     * Execution of the "list" command, and print to show to the user the list of tasks.
     *
     */
    public static void listExpense() {
        showToUser(MESSAGE_DIVIDER_LIST);
        for (int i = 0; i < expenseList.size(); i++) {
            System.out.print((i + 1) + ".");
            expenseList.get(i).printTask();
        }
        System.out.println("\n" + getAllMessage());
        showToUser(MESSAGE_DIVIDER);
    }

    /**
     * Show the message that the user inputs in into separated line.
     *
     * @param message the message that user want to separate, could be any number of strings
     *
     */
    private static void showToUser(String... message) {
        for (String i : message) {
            System.out.println(i);
        }
    }


}

