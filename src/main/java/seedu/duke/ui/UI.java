package seedu.duke.ui;

import seedu.duke.constants.MessageConstants;
import seedu.duke.constants.UIConstants;
import seedu.duke.entries.Entry;
import seedu.duke.util.UIUtil;

public class UI {
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
     * Print the exit message to the UI
     */
    public void printExit() {
        print(MessageConstants.MESSAGE_EXIT);
        printLine();
    }

    // TODO: Add expenditure edited

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
        printExpenditureAdded(entry.getDescription(), entry.getAmount(), entry.getCategoryString());
    }

    /**
     * Print the help message to the UI
     */
    public void printHelp() {
        print(MessageConstants.MESSAGE_HELP);
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
