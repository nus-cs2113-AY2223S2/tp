package seedu.brokeMan.entry;

import seedu.brokeMan.ui.Ui;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

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
    public static void listEntry(List<Entry> entryList) {
        if (entryList.size() == 0) {
            Ui.showToUser("The requested list is empty\n|");
        } else if (entryList.size() > 0) {
            int counter = 1;
            for (Entry entryLog : entryList) {
                String message = String.format("%d. %s", counter, entryLog.toString());
                Ui.showToUser(message);
                counter++;
            }
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
            Ui.showToUserWithLineBreak("Successfully deleted.", "");
        } catch (IndexOutOfBoundsException | IllegalArgumentException e) {
            Ui.showToUserWithLineBreak("Invalid index! Please try again.", "");
        }
    }

    /**
     * Edits amount of money for an entry in the specific index in the list
     *
     * @param entryIndex index of the entry in the list
     * @param newAmount new entry that will replace current entry
     * @param entryList LinkedList that contains the entries
     *
     */
    public static void editEntryCost(int entryIndex, double newAmount, LinkedList<Entry> entryList) {
        try {
            Entry entryBeingEdited = entryList.get(entryIndex - 1);
            entryBeingEdited.editAmount(newAmount);
            Ui.showToUserWithLineBreak("Successfully edited amount.", "");
        } catch (IndexOutOfBoundsException | IllegalArgumentException e) {
            Ui.showToUserWithLineBreak("Invalid index! Please try again.", "");
        }
    }

    /**
     * Edits description of an entry in the specific index in the list
     *
     * @param entryIndex index of the entry in the list
     * @param newDescription new description that will replace current description
     * @param entryList LinkedList that contains the entries
     *
     */
    public static void editEntryDescription(int entryIndex, String newDescription, LinkedList<Entry> entryList) {
        try {
            Entry entryBeingEdited = entryList.get(entryIndex - 1);
            entryBeingEdited.editDescription(newDescription);
            Ui.showToUserWithLineBreak("Successfully edited description.", "");
        } catch (IndexOutOfBoundsException | IllegalArgumentException e) {
            Ui.showToUserWithLineBreak("Invalid index! Please try again.", "");
        }
    }

    /**
     * Edits time of an entry in the specific index in the list
     *
     * @param entryIndex index of the entry in the list
     * @param newTime new time that will replace current time
     * @param entryList LinkedList that contains the entries
     *
     */
    public static void editEntryTime(int entryIndex, LocalDateTime newTime, LinkedList<Entry> entryList) {
        try {
            Entry entryBeingEdited = entryList.get(entryIndex - 1);
            entryBeingEdited.editTime(newTime);
            Ui.showToUserWithLineBreak("Successfully edited time.", "");
        } catch (IndexOutOfBoundsException | IllegalArgumentException e) {
            Ui.showToUserWithLineBreak("Invalid index! Please try again.", "");
        }
    }

    /**
     * Edits category of an entry in the specific index in the list
     * @param entryIndex index of the entry in the list
     * @param newCategory new category that will replace current category
     * @param entryList LinkedList that contains the entries
     */
    public static void editEntryCategory(int entryIndex, Category newCategory, LinkedList<Entry> entryList) {
        try {
            Entry entryBeingEdited = entryList.get(entryIndex - 1);
            entryBeingEdited.editCategory(newCategory);
            Ui.showToUserWithLineBreak("Successfully edited category.", "");
        } catch (IndexOutOfBoundsException | IllegalArgumentException e) {
            Ui.showToUserWithLineBreak("Invalid index! Please try again.", "");
        }
    }

    /**
     * Sorts entries using Entry amount comparator
     * @param entryList LinkedList that contains the entries
     *
     */
    protected static void sortEntriesByAmount(LinkedList<Entry> entryList) {
        entryList.sort(new EntryAmountComparator());
        listEntry(entryList);
    }

    /**
     * Sorts entries using Entry date comparator
     * @param entryList LinkedList that contains the entries
     *
     */
    protected static void sortEntriesByDate(LinkedList<Entry> entryList) {
        entryList.sort(new EntryDateComparator());
        listEntry(entryList);
    }


    /**
     * Finds all entries that fit the specified category
     *
     * @param category      Category of interest
     * @param entryList     List of entries that are registered under the category of interest
     */
    protected static void findEntriesByCategory(Category category, LinkedList<Entry> entryList) {
        LinkedList<Entry> entriesByCategory = new LinkedList<Entry>();
        if (entryList.size() > 0) {
            for (Entry entryLog : entryList) {
                if (entryLog.getCategory() == category) {
                    entriesByCategory.add(entryLog);
                }
            }
        }
        if (entriesByCategory.size() > 0) {
            listEntry(entriesByCategory);
            Ui.showToUser("Total in this category: $" + getEntryListSum(entriesByCategory));
        } else {
            Ui.showToUser("No entries found under this category. ");
        }
    }

    /**
     * Returns list of entries that are made in the specified month
     *
     * @param year          Year of interest
     * @param month         Month of interest
     * @param entryList     List of available entries
     * @return              List of entries that are made in the month specified by the parameters
     */
    protected static List<Entry> selectEntryForDate(int year, Month month, LinkedList<Entry> entryList) {
        return entryList.stream().filter(x -> x.isSameMonth(year, month)).collect(Collectors.toList());
    }


    /**
     * Returns the sum of the amount attribute of entries in the list
     *
     * @param entryList List of entries
     * @return          Double representation of the sum of entry amounts
     */
    public static double getEntryListSum(List<Entry> entryList) {
        double sum = 0;
        for (Entry entry : entryList) {
            sum += entry.getAmount();
        }
        return sum;
    }
}
