package seedu.brokeMan.entry;

import seedu.brokeMan.ui.Ui;

import java.util.LinkedList;

public class Expenses extends EntryList{
    private static final LinkedList<Entry> expenseList = new LinkedList<>();
    //private final EntryList expenseList;

    /**
     * Adds new expense to the list
     *
     * @param newExpense new expense to be added
     */
    public static void addExpense(Expense newExpense) {
        addEntry(newExpense, expenseList);
    }

    /**
     * lists out expenses in the list
     */
    public static void listExpense() {
        Ui.showToUser("Here are the expenses you have made.");
        listEntry(expenseList);
        Ui.showToUser("Total expenses: $" + getTotalAmount(expenseList));
        Ui.showToUserWithLineBreak("");
    }

    /**
     * deletes specific expense in the list
     *
     * @param expenseIndex Index of the expense in the list
     */
    public static void deleteExpense(int expenseIndex) {
        deleteEntry(expenseIndex, expenseList);
    }

    /**
     * Edits a specific index in the list
     *
     * @param type entry type of the expense to be changed
     * @param expenseIndex index of the expense in the list
     * @param newEntry new entry that will replace current entry
     */
    public static void editExpenseCost(String type, int expenseIndex, double newEntry) {
        editEntry(type, expenseIndex, newEntry, expenseList);
    }

    /**
     * Edits a specific index in the list
     *
     * @param type entry type of the expense to be changed
     * @param expenseIndex index of the expense in the list
     * @param newEntry new entry that will replace current entry
     */
    public static void editExpense(String type, int expenseIndex, String newEntry) {
        editEntry(type, expenseIndex, newEntry, expenseList);
    }


    /**
     * Sorts expenses using Entry comparator
     */
    private static void sortExpensesByAmount() {
        sortEntriesByAmount(expenseList);
    }
    private static void sortExpensesByDate() {
        sortEntriesByDate(expenseList);
    }


}
