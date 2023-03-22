package seedu.brokeMan.entry;

import seedu.brokeMan.ui.Ui;

import java.time.LocalDateTime;
import java.util.LinkedList;

public class ExpenseList extends EntryList {
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

    public static double getTotalExpense() {
        return getTotalAmount(expenseList);
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
     * Edits the description of the expense specified by the index in the list
     *
     * @param expenseIndex index of the expense in the list
     * @param newEntry new description that will replace current description
     */
    public static void editExpense(int expenseIndex, String newEntry) {
        editEntryDescription(expenseIndex, newEntry, expenseList);
    }

    /**
     * Edits the amount of expense specified by the index in the list
     * @param expenseIndex index of the expense in the list
     * @param newEntry new amount that will replace the current amount
     */
    public static void editExpense(int expenseIndex, Double newEntry) {
        editEntryCost(expenseIndex, newEntry, expenseList);
    }

    /**
     * Edits the time of expense specified by the index in the list
     *
     * @param expenseIndex index of the expense in the list
     * @param newEntry new time that will replace the current amount
     */
    public static void editExpense(int expenseIndex, LocalDateTime newEntry) {
        editEntryTime(expenseIndex, newEntry, expenseList);
    }

    /**
     * Sorts expenses by amount, from largest to smallest
     */
    public static void sortExpensesByAmount() {
        sortEntriesByAmount(expenseList);
    }

    /**
     * Sorts expenses by date, from latest to oldest
     */
    public static void sortExpensesByDate() {
        sortEntriesByDate(expenseList);
    }
}
