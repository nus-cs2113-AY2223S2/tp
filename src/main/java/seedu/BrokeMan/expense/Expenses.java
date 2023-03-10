package seedu.BrokeMan.expense;

import seedu.BrokeMan.ui.Ui;

import java.util.LinkedList;

public class Expenses {
    private static final LinkedList<Expense> expenseList = new LinkedList<>();


    /**
     * Adds new expense to the list
     *
     * @param newExpense new expense to be added
     */
    public static void addExpense(Expense newExpense) {
        expenseList.add(newExpense);
        Ui.showToUserWithLineBreak("You have successfully added [" + newExpense + "]", "");
    }

    /**
     * lists out expenses in the list
     */
    public static void listExpense() {
        sortExpenses();
        Ui.showToUser("Here are the expenses you have made.");
        int counter = 1;
        for (Expense expenseLog : expenseList) {
            String message = String.format("%d. %s", counter, expenseLog.toString());
            Ui.showToUser(message);
            counter++;
        }
        Ui.showToUserWithLineBreak("");
    }

    /**
     * deletes specific expense in the list
     *
     * @param expenseIndex Index of the expense in the list
     */
    public static void deleteExpense(int expenseIndex) {
        try {
            expenseList.remove(expenseIndex - 1);
            Ui.showToUserWithLineBreak("Successfully deleted expense.", "");
        } catch (IndexOutOfBoundsException | IllegalArgumentException e) {
            Ui.showToUserWithLineBreak("Invalid index! Please try again.", "");
        }
    }

    /**
     * Edits a specific index in the list
     *
     * @param type entry type of the expense to be changed
     * @param expenseIndex index of the expense in the list
     * @param newEntry new entry that will replace current entry
     */
    public static void editExpenseCost(String type, int expenseIndex, double newEntry) {
        try {
            Expense expenseBeingEdited = expenseList.get(expenseIndex - 1);
            if (type.equals("cost")) {
                expenseBeingEdited.editCost(newEntry);
            } else {
                Ui.showToUserWithLineBreak("Invalid type Parameter!", "");
            }
            Ui.showToUserWithLineBreak("Successfully edited expense.", "");
        } catch (IndexOutOfBoundsException | IllegalArgumentException e) {
            Ui.showToUserWithLineBreak("Invalid index! Please try again.", "");
        }
    }

    /**
     * Edits a specific index in the list
     *
     * @param type entry type of the expense to be changed
     * @param expenseIndex index of the expense in the list
     * @param newEntry new entry that will replace current entry
     */
    public static void editExpense(String type, int expenseIndex, String newEntry) {
        try {
            Expense expenseBeingEdited = expenseList.get(expenseIndex - 1);
            switch (type) {
            case "info":
                expenseBeingEdited.editInfo(newEntry);
                break;
            case "time":
                expenseBeingEdited.editTime(newEntry);
                break;
            default:
                Ui.showToUserWithLineBreak("Invalid type parameter!", "");
            }
            Ui.showToUserWithLineBreak("Successfully edited expense.", "");
        } catch (IndexOutOfBoundsException | IllegalArgumentException e) {
            Ui.showToUserWithLineBreak("Invalid index! Please try again.", "");
        }
    }

    /**
     * Sorts expenses using Expense comparator
     */
    private static void sortExpenses() {
        expenseList.sort(new ExpenseCostComparator());
    }
}
