package seedu.brokeMan.income;

import seedu.brokeMan.ui.Ui;

import java.util.LinkedList;

public class IncomeList {
    private static final LinkedList<Income> incomeList = new LinkedList<>();
    private static int listLength = 0;

    /**
     * Adds new income to the list.
     * @param newIncome the new income to be added
     */
    public static void addIncome(Income newIncome) {
        incomeList.add(newIncome);
        listLength++;
        Ui.showToUserWithLineBreak("You have successfully added [" + newIncome + "]", "");
    }

    /**
     * delete specific income in the list
     * @param index index of the income in the list
     */
    public static void deleteIncome(int index) {
        try {
            incomeList.remove(index - 1);
            listLength--;
            Ui.showToUser("Successfully deleted income.");
        } catch (IndexOutOfBoundsException | IllegalArgumentException e) {
            Ui.showToUser("Invalid index! Please try again.");
        }
    }

    /**
     * return the income at specific index
     * @param index index of the income in the list
     * @return the income entry information
     */
    public static Income getIncome(int index) {
        return incomeList.get(index);
    }

    /**
     * list out income in the list
     */
    public static void listIncome() {
        Ui.showToUser("Here are the incomes you have made");
        int counter = 1;
        for (Income incomeLog : incomeList) {
            String message = String.format("%s. %s", Integer.toString(counter), incomeLog.toString());
            Ui.showToUser(message);
            counter++;
        }
        Ui.showToUserWithLineBreak("");
    }

    /**
     * Edits a specific income entry in the list
     * @param type entry type of the income to be changed
     * @param index index of the expense in the list
     * @param newEntry new entry that will replace current entry
     */
    public static void editIncome(String type, int index, String newEntry) {
        try {
            Income incomeBeingEdited = incomeList.get(index - 1);
            switch (type) {
            case "info":
                incomeBeingEdited.editInfo(newEntry);
                break;
            case "time":
                incomeBeingEdited.editTime(newEntry);
                break;
            default:
                // throw custom exception
                Ui.showToUserWithLineBreak("Invalid type parameter!", "");
            }
            Ui.showToUserWithLineBreak("Successfully edited income.", "");
        } catch (IndexOutOfBoundsException | IllegalArgumentException e) {
            Ui.showToUserWithLineBreak("Invalid index! Please try again.", "");
        }
    }

    /**
     * Edits a specific index in the list
     *
     * @param type entry type of the income to be changed
     * @param index index of the expense in the list
     * @param newIncome new income that will replace current entry
     */
    public static void editIncomeDouble(String type, int index, double newIncome) {
        try {
            Income incomeBeingEdited = incomeList.get(index - 1);
            if (type.equals("income")) {
                incomeBeingEdited.editIncome(newIncome);
            } else {
                // throw custom exception
                Ui.showToUserWithLineBreak("Invalid type Parameter!", "");
            }
            Ui.showToUserWithLineBreak("Successfully edited income.", "");
        } catch (IndexOutOfBoundsException | IllegalArgumentException e) {
            Ui.showToUserWithLineBreak("Invalid index! Please try again.", "");
        }
    }

}
