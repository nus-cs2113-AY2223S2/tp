package seedu.brokeMan.budget;

import seedu.brokeMan.ui.Ui;

import java.time.LocalDate;
import java.time.Month;
import java.util.HashMap;
import java.util.Optional;

import static seedu.brokeMan.entry.ExpenseList.getTotalExpense;

public class Budget {
    private static HashMap<Integer, HashMap<Month, Double>> budgetEachMonth = new HashMap<>();

    // will remove when read write function works
    static {
        HashMap<Month, Double> budget2022 = new HashMap<>();
        budget2022.put(Month.of(11), 1000.0);
        budget2022.put(Month.of(12), 1200.0);
        HashMap<Month, Double> budget2023 = new HashMap<>();
        budget2022.put(Month.of(1), 1400.0);
        budget2022.put(Month.of(2), 1600.0);
        budget2022.put(Month.of(3), 1800.0);
        budgetEachMonth.put(2023, budget2023);
        budgetEachMonth.put(2022, budget2022);
    }

    public static void viewBudget() {
        viewBudgetOfMonth(LocalDate.now().getYear(), LocalDate.now().getMonth());
    }

    public static void viewBudgetOfMonth(int yearOfInterest, Month monthOfInterest) {
        try {
            double totalExpenses = getTotalExpense();
            double budgetThisMonth = budgetEachMonth.get(yearOfInterest).get(monthOfInterest);
            double budgetLeft = budgetThisMonth - totalExpenses;
            Ui.showToUser(String.format("You have set your budget as %.2f for %s",
                    budgetThisMonth, createDateString(yearOfInterest, monthOfInterest)));
            if (budgetLeft >= 0) {
                Ui.showToUserWithLineBreak("The amount of budget left is $" + budgetLeft, "");
            } else if (budgetLeft < 0){
                Ui.showToUserWithLineBreak("You have overspent your expenses by $" + budgetLeft, "");
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
        LocalDate today = LocalDate.now();
        int year = today.getYear();
        Month month = today.getMonth();

        if (dateInString.isPresent()) {
            String[] yearAndMonth = dateInString.get().split("/");
            year = Integer.parseInt(yearAndMonth[0]);
            month = Month.of(Integer.parseInt(yearAndMonth[1]));
        }

        if (!budgetEachMonth.containsKey(year)) {
            budgetEachMonth.put(year, new HashMap<>());
        }
        budgetEachMonth.get(year).put(month, budgetAmount);
        Ui.showToUserWithLineBreak(String.format("You have successfully set %.2f as your budget for %s",
                        budgetAmount, createDateString(year, month)), "");
    }

    private static String createDateString(int year, Month month) {
        return Integer.toString(year) + "/" + month.toString();
    }
}
