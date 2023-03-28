package command.overview;

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
    private static final String CATEGORY_TITLE =
            "Breakdown of expenses by category in descending order by category sum ";
    private static final String CATEGORY_DIVIDER = "----------------------------";
    private static final String EMPTY_MONTH = "No expenses tracked in ";


    public MonthlyOverview(ArrayList<Expense> expenses, String month, String year) throws IllegalArgumentException {
        this.expenses = expenses;
        this.month = month;
        this.year = year;
        obtainFilteredExpenses();
    }

    private void obtainFilteredExpenses() throws IllegalArgumentException {
        YearFilter yearFilter = new YearFilter(expenses, year);
        MonthFilter monthFilter = new MonthFilter(yearFilter.getYearlyExpenses(), month);
        filteredExpenses = monthFilter.getMonthlyExpenses();
    }

    private void printCategoryBreakdown(ArrayList<Expense> filteredExpenses) {
        ArrayList<Expense> expensesByCategorySum = new CommandSort(filteredExpenses).sortByCategorySum();
        System.out.println(TAB + CATEGORY_DIVIDER);
        for (Expense e : expensesByCategorySum) {
            System.out.println(TAB + WHITE_SPACE + e.getDescription() + WHITE_SPACE +
                    e.getExpenseAmount() + WHITE_SPACE + "SGD");
            System.out.println(TAB + CATEGORY_DIVIDER);
        }
    }

    public void printOverview() throws NullPointerException {
        if (filteredExpenses.isEmpty()) {
            System.out.println(EMPTY_MONTH + month.toUpperCase() + WHITE_SPACE + year);
        } else {
            CommandTotal commandTotal = new CommandTotal(filteredExpenses);
            System.out.println(TITLE + month.toUpperCase() + WHITE_SPACE + year);
            System.out.println();
            System.out.println(TAB + MONTHLY_OVERVIEW_TOTAL +
                    commandTotal.calculateTotal().setScale(2, RoundingMode.HALF_UP) + WHITE_SPACE + "SGD");
            System.out.println();
            System.out.println(TAB + CATEGORY_TITLE);
            printCategoryBreakdown(filteredExpenses);
        }
    }


}
