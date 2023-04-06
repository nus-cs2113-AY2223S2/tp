package seedu.brokeMan.entry.income;

import seedu.brokeMan.entry.Category;
import seedu.brokeMan.entry.Entry;
import seedu.brokeMan.entry.EntryList;
import seedu.brokeMan.parser.StringToTime;
import seedu.brokeMan.ui.Ui;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class IncomeList extends EntryList {
    public static final LinkedList<Entry> incomeList = new LinkedList<>();

    /**
     * Adds new income to the list.
     *
     * @param newIncome the new income to be added
     */
    public static void addIncome(Income newIncome) {
        addEntry(newIncome, incomeList);
    }

    /**
     * delete specific income in the list
     *
     * @param index index of the income in the list
     */
    public static void deleteIncome(int index) {
        deleteEntry(index, incomeList);
    }

    /**
     * Lists incomes saved
     *
     * @param date Optional of String that contains information about the date
     */
    public static void listIncome(Optional<String> date) {
        int year = StringToTime.createYearFromString(date);
        Month month = StringToTime.createMonthFromString(date);

        List<Entry> incomeOfDate = getIncomesMadeInMonth(year, month);
        String dateInString = StringToTime.createDateString(year, month);

        Ui.showToUser("Here are the income you have made for " + dateInString + ".");
        listEntry(incomeOfDate);
        Ui.showToUser("Total income: $" + getEntryListSum(incomeOfDate));
        Ui.showToUserWithLineBreak("");
    }

    /**
     * list out income in the list
     */
    public static void listIncome() {
        Ui.showToUser("Here are the income you have made.");
        listEntry(incomeList);
        Ui.showToUser(String.format("Total income: $%.2f", getEntryListSum(incomeList)));
        Ui.showToUserWithLineBreak("");
    }

    /**
     * Edits the description of the income specified by the index in the list
     *
     * @param index        index of the income in the list
     * @param newEntry     new description that will replace current description
     */
    public static void editIncome(int index, String newEntry) {
        editEntryDescription(index, newEntry, incomeList);
    }

    /**
     * Edits the amount of income specified by the index in the list
     *
     * @param index        index of the income in the list
     * @param newEntry     new amount that will replace the current amount
     */
    public static void editIncome(int index, Double newEntry) {
        editEntryCost(index, newEntry, incomeList);
    }

    /**
     * Edits the time of income specified by the index in the list
     *
     * @param index        index of the income in the list
     * @param newEntry     new time that will replace the current time
     */
    public static void editIncome(int index, LocalDateTime newEntry) {
        editEntryTime(index, newEntry, incomeList);
    }

    /**
     * Edits the category of income specified by the index in the list
     *
     * @param index        index of the income in the list
     * @param newEntry     new category of the income
     */
    public static void editIncome(int index, Category newEntry) { editEntryCategory(index, newEntry, incomeList);}


    /**
     * Sorts incomes by amount, from largest to smallest
     */
    public static void sortIncomeByAmount() {
        sortEntriesByAmount(incomeList);
        Ui.showToUser(String.format("Total income: $%.2f", getEntryListSum(incomeList)));
        Ui.showToUserWithLineBreak("");
    }

    /**
     * Sorts incomes by date, from latest to oldest
     */
    public static void sortIncomeByDate() {
        sortEntriesByDate(incomeList);
        Ui.showToUser(String.format("Total income: $%.2f", getEntryListSum(incomeList)));
        Ui.showToUserWithLineBreak("");
    }

    /**
     * Finds list of all incomes that is under category specified
     *
     * @param category Category of the income
     */
    public static void findIncomeByCategory(Category category) {
        findEntriesByCategory(category, incomeList);
    }

    /**
     * Returns list of all incomes made in the month specified
     *
     * @param year Year of the income made
     * @param month Month of the income made
     * @return List of entries that contain incomes made in the specified month
     */
    public static List<Entry> getIncomesMadeInMonth(int year, Month month) {
        return selectEntryForDate(year, month, incomeList);
    }
}
