package command.overview;

import command.CommandCategory;
import command.CommandSort;
import command.CommandTotal;
import data.Expense;
import utils.MonthFilter;
import utils.YearFilter;

import java.math.RoundingMode;
import java.util.ArrayList;
import static common.MessageList.TAB;

public class MonthlyOverview {

    private String month;
    private String year;
    private ArrayList<Expense> expenses;
    private ArrayList<Expense> filteredExpenses;

    private static final String WHITE_SPACE = " ";
    private static final String TITLE = "Overview for ";
    private static final String MONTHLY_OVERVIEW_TOTAL = "Total expenses: ";
    private static final String CATEGORY_TITLE = "Breakdown of expenses by category: ";

    public MonthlyOverview(ArrayList<Expense> expenses, String month, String year) {
        this.expenses = expenses;
        this.month = month;
        this.year = year;
        obtainFilteredExpenses();
    }

    private void obtainFilteredExpenses() {
        YearFilter yearFilter = new YearFilter(expenses, year);
        MonthFilter monthFilter = new MonthFilter(yearFilter.getYearlyExpenses(), month);
        filteredExpenses = monthFilter.getMonthlyExpenses();
    }

    // TODO: not actually doing grouping
    private void printCategoryBreakdown(ArrayList<Expense> monthlyExpenses) {
        new CommandSort(monthlyExpenses).sortByCategorySum();
        for (Expense e : monthlyExpenses) {
            System.out.println(e.getDescription() + WHITE_SPACE + e.getExpenseAmount());
        }
    }

    public void printOverview() {
        CommandTotal commandTotal = new CommandTotal(filteredExpenses);
        System.out.println(TITLE + month + WHITE_SPACE + year);
        System.out.println(TAB + MONTHLY_OVERVIEW_TOTAL + commandTotal.calculateTotal().setScale(2, RoundingMode.HALF_UP) + "SGD");
        System.out.println(TAB + CATEGORY_TITLE);
        printCategoryBreakdown(filteredExpenses);
    }


}
