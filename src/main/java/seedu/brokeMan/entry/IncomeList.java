package seedu.brokeMan.entry;

import seedu.brokeMan.ui.Ui;

import java.util.LinkedList;

public class IncomeList extends EntryList{
    private static final LinkedList<Income> incomeList = new LinkedList<>();

    /**
     * Adds new income to the list.
     * @param newIncome the new income to be added
     */
    public static void addIncome(Income newIncome) {
        addEntry(newIncome);
    }

    /**
     * delete specific income in the list
     * @param index index of the income in the list
     */
    public static void deleteIncome(int index) {
        deleteEntry(index);
    }

    /**
     * list out income in the list
     */
    public static void listIncome() {
        listEntry();
    }

    /**
     * Edits a specific income entry in the list
     * @param type entry type of the income to be changed
     * @param index index of the expense in the list
     * @param newEntry new entry that will replace current entry
     */
    public static void editIncome(String type, int index, String newEntry) {
        editEntry(type, index, newEntry);
    }

    /**
     * Edits a specific index in the list
     * @param type entry type of the income to be changed
     * @param index index of the expense in the list
     * @param newIncome new income that will replace current entry
     */
    public static void editIncomeDouble(String type, int index, double newIncome) {
        editEntry(type, index, newIncome);
    }

    /**
     * print out the total income
     */
    public static void getTotalIncome() {
        double totalExpense = getTotalAmount();
        System.out.println("Your total expenses are " + totalExpense + ".");
    }
    /**
     * Sorts income using Entry comparator
     */
    private static void sortIncomeByAmount() {
        sortEntriesByAmount();
    }
    private static void sortIncomeByDate() {
        sortEntriesByDate();
    }

}
