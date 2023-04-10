package seedu.brokeMan.budget;

import seedu.brokeMan.entry.Entry;
import seedu.brokeMan.entry.EntryList;
import seedu.brokeMan.parser.StringToTime;
import seedu.brokeMan.ui.Ui;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static seedu.brokeMan.entry.expense.ExpenseList.getExpensesMadeInMonth;
import static seedu.brokeMan.parser.StringToTime.createDateString;

public class Budget {
    public static HashMap<Integer, HashMap<Month, Double>> budgetEachMonth = new HashMap<>();

    public static void viewBudget(Optional<String> dateInString) {
        int year = StringToTime.createYearFromString(dateInString);
        Month month = StringToTime.createMonthFromString(dateInString);

        viewBudgetOfMonth(year, month);
    }

    public static void viewBudgetOfMonth(int yearOfInterest, Month monthOfInterest) {
        try {
            List<Entry> expensesInMonth = getExpensesMadeInMonth(yearOfInterest, monthOfInterest);
            double totalExpenses = EntryList.getEntryListSum(expensesInMonth);
            double budgetThisMonth = budgetEachMonth.get(yearOfInterest).get(monthOfInterest);
            double budgetLeft = budgetThisMonth - totalExpenses;
            BigDecimal BigDecimalBudgetLeft = new BigDecimal(budgetThisMonth - totalExpenses);
            Ui.showToUser(String.format("You have set your budget as $%.2f for %s.",
                    budgetThisMonth, createDateString(yearOfInterest, monthOfInterest)));
            if (budgetLeft >= 0) {
                Ui.showToUserWithLineBreak(
                        String.format("The amount of budget left is $%.2f", BigDecimalBudgetLeft), "");
            } else if (budgetLeft < 0){
                Ui.showToUserWithLineBreak(String.format(
                        "You have overspent your expenses by $%.2f", BigDecimalBudgetLeft), "");
            }
        } catch (NullPointerException npe) {
            Ui.showToUserWithLineBreak("Budget information for the given month does not exist!", "");
        }
    }


    public static boolean hasSetBudgetThisMonth() {
        Integer thisYear = LocalDate.now().getYear();
        if (!budgetEachMonth.containsKey(thisYear)) {
            budgetEachMonth.put(thisYear, new HashMap<>());
        }
        Month thisMonth = LocalDate.now().getMonth();
        return budgetEachMonth.get(thisYear).containsKey(thisMonth);
    }

    public static void setBudget(double budgetAmount, Optional<String> dateInString) {
        int year = StringToTime.createYearFromString(dateInString);
        Month month = StringToTime.createMonthFromString(dateInString);

        if (!budgetEachMonth.containsKey(year)) {
            budgetEachMonth.put(year, new HashMap<>());
        }
        budgetEachMonth.get(year).put(month, budgetAmount);
        Ui.showToUserWithLineBreak(String.format("You have successfully set $%.2f as your budget for %s.",
                        budgetAmount, StringToTime.createDateString(year, month)), "");
    }
}
