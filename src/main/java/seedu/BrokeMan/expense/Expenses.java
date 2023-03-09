package seedu.BrokeMan.expense;

import java.util.LinkedList;

public class Expenses {
    private static final LinkedList<Expense> expenseList = new LinkedList<>();


//    public Expenses() {
//        this.expenseList = new LinkedList<Expense>();
//    }

    /**
     * Adds new expense to the list
     *
     * @param newExpense new expense to be added
     */
    public static void addExpense(Expense newExpense) {
        expenseList.add(newExpense);
    }

    /**
     * lists out expenses in the list
     */
    public static void listExpense() {
        sortExpenses();
        System.out.println("Here are the expenses you have made\n");
        int counter = 1;
        for (Expense expenseLog : expenseList) {
            String message = String.format("%s. %s", Integer.toString(counter), expenseLog.toString());
            System.out.println(message + '\n');
            counter++;
        }
    }

    /**
     * deletes specific expense in the list
     *
     * @param expenseIndex Index of the expense in the list
     */
    public static void deleteExpense(int expenseIndex) {
        try {
            expenseList.remove(expenseIndex);
            System.out.println("Successfully deleted expense.");
        } catch (IndexOutOfBoundsException | IllegalArgumentException e) {
            System.out.println("Invalid index! Please try again.\n");
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
            switch (type) {
            case "cost":
                expenseBeingEdited.editCost(newEntry);
                break;
            default:
                System.out.println("Invalid type parameter!");
            }
            System.out.println("Successfully edited expense.");
        } catch (IndexOutOfBoundsException | IllegalArgumentException e) {
            System.out.println("Invalid index! Please try again.\n");
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
                System.out.println("Invalid type parameter!");
            }
            System.out.println("Successfully edited expense.");
        } catch (IndexOutOfBoundsException | IllegalArgumentException e) {
            System.out.println("Invalid index! Please try again.\n");
        }
    }

    /**
     * Sorts expenses using Expense comparator
     */
    public static void sortExpenses() {
        expenseList.sort(new ExpenseCostComparator());
    }
}
