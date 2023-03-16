package seedu.pocketpal.ui;

import seedu.pocketpal.constants.MessageConstants;
import seedu.pocketpal.constants.UIConstants;
import seedu.pocketpal.entries.Entry;
import seedu.pocketpal.util.UIUtil;

import java.util.List;

public class UI {
    /**
     * Returns a string based on the details of the Entry object and entryID entered.
     *
     * @param entry   entry Object to be formatted
     * @param entryID ID of the entry Object
     * @return String of details about the entry
     */
    private String formatViewEntries(Entry entry, int entryID) {
        String description = entry.getDescription();
        double price = entry.getAmount();
        String category = entry.getCategoryString();
        return "<" + entryID + ">: " + description +
                " (" + category + ") - $" + UIUtil.formatPrice(price);
    }

    public void print(String output) {
        System.out.print(output);
    }

    public void printLine() {
        print(UIConstants.LINE);
    }

    /**
     * Prompt the user to make an input
     */
    public void printAwaitUserInput() {
        print(UIConstants.USER_INPUT_PROMPT);
    }

    /**
     * Print formatted exception message to the UI.
     *
     * @param exception Exception to be printed
     */
    public void printException(Exception exception) {
        print(exception.getMessage());
        printLine();
    }

    /**
     * Print the exit message to the UI.
     */
    public void printExit() {
        print(MessageConstants.MESSAGE_EXIT);
        printLine();
    }

    /**
     * Combines all individual entry strings into a list of entries and prints the
     * list, along with an acknowledgement message.
     *
     * @param entryList List of entry objects with the user-specified length
     * @param category  String denoting the category requested by the user
     */
    public void printEntriesToBeViewed(List<Entry> entryList, String category) {
        StringBuilder finalString = new StringBuilder();
        finalString.append("These are the latest ")
                   .append(entryList.size())
                   .append(" entries")
                   .append(category != null
                           ? " from the " + category + " category."
                           : ".")
                   .append(System.lineSeparator());

        for (int index = 0; index < entryList.size(); index++) {
            String formattedEntry = formatViewEntries(entryList.get(index), index + 1);
            finalString.append(formattedEntry)
                       .append(System.lineSeparator());
        }
        print(finalString.toString());
        printLine();
    }

    /**
     * Combines all individual entry strings into a list of entries and prints the list, along with an acknowledgement
     * message.
     *
     * @param entryList List of entry objects with the user-specified length
     */
    public void printEntriesToBeViewed(List<Entry> entryList) {
        printEntriesToBeViewed(entryList, null);
    }

    /**
     * Print formatted details of expenditure entry added to the UI.
     *
     * @param description Description of the entry
     * @param priceDouble Amount of the entry
     * @param category    Category of the entry
     */
    public void printExpenditureAdded(String description, double priceDouble, String category) {
        assert priceDouble >= 0 : "Expected a non-negative price";
        print(MessageConstants.MESSAGE_EXPENDITURE_ADDED
                + UIUtil.formatExpenditure(description, priceDouble, category));
        printLine();
    }

    /**
     * Print formatted details of expenditure entry added to the UI.
     *
     * @param entry Expenditure to be printed
     */
    public void printExpenditureAdded(Entry entry) {
        printExpenditureAdded(entry.getDescription(), entry.getAmount(), entry.getCategoryString());
    }

    /**
     * Print formatted details of expenditure entry edited in the UI.
     *
     * @param entry Expenditure to be printed
     */
    public void printExpenditureEdited(Entry entry) {
        print(MessageConstants.MESSAGE_EXPENDITURE_EDITED
                + UIUtil.formatExpenditure(entry.getDescription(), entry.getAmount(), entry.getCategoryString()));
        printLine();
    }

    /**
     * Print formatted details of expenditure entry deleted to the UI.
     *
     * @param description Description of the entry
     * @param priceDouble Amount of the entry
     * @param category    Category of the entry
     */
    public void printExpenditureDeleted(String description, double priceDouble, String category) {
        assert priceDouble >= 0 : "Expected a non-negative price";
        print(MessageConstants.MESSAGE_EXPENDITURE_DELETED
                + UIUtil.formatExpenditure(description, priceDouble, category));
        printLine();
    }

    /**
     * Print formatted details of expenditure entry deleted to the UI.
     *
     * @param entry Expenditure to be printed
     */
    public void printExpenditureDeleted(Entry entry) {
        printExpenditureDeleted(entry.getDescription(), entry.getAmount(), entry.getCategoryString());
    }

    /**
     * Print the help message to the UI
     */
    public void printHelp() {
        print(MessageConstants.MESSAGE_HELP + MessageConstants.MESSAGE_ADD_COMMAND
                + MessageConstants.MESSAGE_DELETE_COMMAND + MessageConstants.MESSAGE_EDIT_COMMAND
                + MessageConstants.MESSAGE_VIEW_COMMAND + MessageConstants.MESSAGE_HELP_COMMAND
                + MessageConstants.MESSAGE_BYE_COMMAND);
        printLine();
    }

    /**
     * Print the welcome message to the UI
     */
    public void printWelcome() {
        print(MessageConstants.MESSAGE_WELCOME);
        printLine();
    }
}
