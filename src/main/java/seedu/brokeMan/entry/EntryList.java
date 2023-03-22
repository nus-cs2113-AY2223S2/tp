package seedu.brokeMan.entry;

import seedu.brokeMan.ui.Ui;

import java.util.LinkedList;

public abstract class EntryList {

    /**
     * Adds new expense/income to the list
     *
     * @param newEntry new entry to be added
     */
    public static void addEntry(Entry newEntry, LinkedList<Entry> entryList) {
        entryList.add(newEntry);
        Ui.showToUserWithLineBreak("You have successfully added [" + newEntry + "]", "");
    }

    /**
     * Lists the entries in the entryList
     *
     * @param entryList LinkedList that contains the entries
     */
    public static void listEntry(LinkedList<Entry> entryList) {
        int counter = 1;
        for (Entry entryLog : entryList) {
            String message = String.format("%d. %s", counter, entryLog.toString());
            Ui.showToUser(message);
            counter++;
        }
    }


    /**
     * deletes specific entry in the list
     *
     * @param entryIndex Index of the entry in the list
     * @param entryList LinkedList that contains the entries
     *
     */
    public static void deleteEntry(int entryIndex, LinkedList<Entry> entryList) {
        try {
            entryList.remove(entryIndex - 1);
            Ui.showToUserWithLineBreak("Successfully deleted expense.", "");
        } catch (IndexOutOfBoundsException | IllegalArgumentException e) {
            Ui.showToUserWithLineBreak("Invalid index! Please try again.", "");
        }
    }

    /**
     * Edits a specific index in the list
     *
     * @param type type of the entry to be changed (can be amount, info, time)
     * @param entryIndex index of the entry in the list
     * @param newEntry new entry that will replace current entry
     * @param entryList LinkedList that contains the entries
     *
     */
    public static void editEntry(String type, int entryIndex, double newEntry, LinkedList<Entry> entryList) {
        try {
            Entry entryBeingEdited = entryList.get(entryIndex - 1);
            entryBeingEdited.editAmount(newEntry);
            Ui.showToUserWithLineBreak("Successfully edited expense.", "");
        } catch (IndexOutOfBoundsException | IllegalArgumentException e) {
            Ui.showToUserWithLineBreak("Invalid index! Please try again.", "");
        }
    }

    /**
     * Edits a specific index in the list
     *
     * @param type entry type of the expense to be changed (can be amount, info, time)
     * @param entryIndex index of the entry in the list
     * @param newEntry new entry that will replace current entry
     * @param entryList LinkedList that contains the entries
     *
     */
    public static void editEntry(String type, int entryIndex, String newEntry, LinkedList<Entry> entryList) {
        try {
            Entry entryBeingEdited = entryList.get(entryIndex - 1);

            if (type.equals("info")) {
                entryBeingEdited.editInfo(newEntry);
            } else {
                entryBeingEdited.editTime(newEntry);
            }
            Ui.showToUserWithLineBreak("Successfully edited expense.", "");
        } catch (IndexOutOfBoundsException | IllegalArgumentException e) {
            Ui.showToUserWithLineBreak("Invalid index! Please try again.", "");
        }
    }

    protected static double getTotalAmount(LinkedList<Entry> entryList) {
        double totalAmount = 0;
        if (entryList.size() > 0) {
            for (Entry entryLog : entryList) {
                totalAmount += entryLog.getAmount();
            }
        }
        return totalAmount;
    }

    /**
     * Sorts entries using Entry comparator
     * @param entryList LinkedList that contains the entries
     *
     */
    protected static void sortEntriesByAmount(LinkedList<Entry> entryList) {
        entryList.sort(new EntryAmountComparator());
    }

    protected static void sortEntriesByDate(LinkedList<Entry> entryList) {
        entryList.sort(new EntryDateComparator());
    }
}
