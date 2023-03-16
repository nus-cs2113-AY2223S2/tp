package seedu.brokeMan.entry;


import seedu.brokeMan.ui.Ui;

import java.util.LinkedList;

public class IncomeList extends EntryList{
    private static final LinkedList<Entry> incomeList = new LinkedList<>();

    /**
     * Adds new income to the list.
     * @param newIncome the new income to be added
     */
    public static void addIncome(Income newIncome) {
        addEntry(newIncome, incomeList);
    }

    /**
     * delete specific income in the list
     * @param index index of the income in the list
     */
    public static void deleteIncome(int index) {
        deleteEntry(index, incomeList);
    }

    /**
     * list out income in the list
     */
    public static void listIncome() {
        listEntry();
    }

    public static void listEntry() {
        //sortExpenses(); I think maybe no need to sort the list automatically without asking the user?
        Ui.showToUser("Here are the income you have made.");
        int counter = 1;
        for (Entry entryLog : incomeList) {
            String message = String.format("%d. %s", counter, entryLog.toString());
            Ui.showToUser(message);
            counter++;
        }
        Ui.showToUser("Total income: $" + getTotalAmount(incomeList));
        Ui.showToUserWithLineBreak("");
    }

    /**
     * Edits a specific income entry in the list
     * @param type entry type of the income to be changed
     * @param index index of the expense in the list
     * @param newEntry new entry that will replace current entry
     */
    public static void editIncome(String type, int index, String newEntry) {
        editEntry(type, index, newEntry, incomeList);
    }

    /**
     * Edits a specific index in the list
     * @param type entry type of the income to be changed
     * @param index index of the expense in the list
     * @param newIncome new income that will replace current entry
     */
    public static void editIncomeDouble(String type, int index, double newIncome) {
        editEntry(type, index, newIncome, incomeList);
    }

    /**
     * Sorts income using Entry comparator
     */
    private static void sortIncomeByAmount() {
        sortEntriesByAmount(incomeList);
    }
    private static void sortIncomeByDate() {
        sortEntriesByDate(incomeList);
    }

}
