package seedu.brokeMan.entry;

import seedu.brokeMan.ui.Ui;

import java.util.LinkedList;

public abstract class EntryList {
    //static final LinkedList<Entry> entryList = new LinkedList<>();

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
     * deletes specific entry in the list
     *
     * @param entryIndex Index of the entry in the list
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
     */
    public static void editEntry(String type, int entryIndex, double newEntry, LinkedList<Entry> entryList) {
        try {
            Entry entryBeingEdited = entryList.get(entryIndex - 1);
            if (type.equals("cost") || type.equals("income")) {
                entryBeingEdited.editAmount(newEntry);
            } else {
                // throw a custom exception here
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
     * @param type entry type of the expense to be changed (can be amount, info, time)
     * @param entryIndex index of the entry in the list
     * @param newEntry new entry that will replace current entry
     */
    public static void editEntry(String type, int entryIndex, String newEntry, LinkedList<Entry> entryList) {
        try {
            Entry entryBeingEdited = entryList.get(entryIndex - 1);
            switch (type) {
            case "info":
                entryBeingEdited.editInfo(newEntry);
                break;
            case "time":
                entryBeingEdited.editTime(newEntry);
                break;
            default:
                Ui.showToUserWithLineBreak("Invalid type parameter!", "");
            }
            Ui.showToUserWithLineBreak("Successfully edited expense.", "");
        } catch (IndexOutOfBoundsException | IllegalArgumentException e) {
            Ui.showToUserWithLineBreak("Invalid index! Please try again.", "");
        }
    }

    public static double getTotalAmount(LinkedList<Entry> entryList) {
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
     */
    protected static void sortEntriesByAmount(LinkedList<Entry> entryList) {
        entryList.sort(new EntryAmountComparator());
    }

    protected static void sortEntriesByDate(LinkedList<Entry> entryList) {
        entryList.sort(new EntryDateComparator());
    }
}
