package MajorClasses;


import parser.Parser;
import java.util.ArrayList;

import static data.MessageList.MESSAGE_DIVIDER;
import static data.MessageList.MESSAGE_DIVIDER_LIST;

public class ExpenseList {
    public static ArrayList<Expense> expenseList = new ArrayList<>();

    public ArrayList<Expense> getExpenseList() {
        return expenseList;
    }

    public void deleteExpense(String userInput) {
        int expenseIndex = Parser.extractIndex(userInput);
        expenseList.remove(expenseIndex - 1); // change to 0-based indexing
    }

    //for list
    public static String getAllMessage() {
        int count = expenseList.size();
        return "\t" + "Now you have " + count + " " + printExpensesOrExpense(count) + " in the list.";
    }

    private static String printExpensesOrExpense(int count) {
        return ((count == 1) ? "expense" : "expenses");
    }


    public static void listExpense() {
        showToUser(MESSAGE_DIVIDER_LIST);
        for (int i = 0; i < expenseList.size(); i++) {
            System.out.print((i + 1) + ".");
            expenseList.get(i).printTask();
        }
        System.out.println("\n" + getAllMessage());
        showToUser(MESSAGE_DIVIDER);
    }

    private static void showToUser(String... message) {
        for (String i : message) {
            System.out.println(i);
        }
    }


}

