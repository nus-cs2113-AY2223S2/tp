package command.overview;

import command.CommandSort;
import command.CommandTotal;
import data.Expense;
import utils.MonthFilter;
import utils.YearFilter;

import java.math.RoundingMode;
import java.util.ArrayList;

import static common.MessageList.TAB;
import static common.MessageList.WHITESPACE;
import static common.MessageList.PERIOD;

public class MonthlyOverview {

    private static final String TITLE = "Monthly Overview for ";
    private static final String MONTHLY_OVERVIEW_TOTAL = "Total expenses: ";
    private static final String CATEGORY_TITLE =
            "Breakdown of expenses by category in descending order by category sum:";
    private static final String CATEGORY_DIVIDER = "----------------------------";
    private static final String EMPTY_MONTH = "No expenses tracked in ";
    private static final String PERIOD = ".";

    private String month;
    private String year;
    private ArrayList<Expense> expenses;
    private ArrayList<Expense> filteredExpenses;


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
            System.out.println(TAB + WHITESPACE + e.getDescription() + WHITESPACE +
                    e.getExpenseAmount() + WHITESPACE + "SGD");
            System.out.println(TAB + CATEGORY_DIVIDER);
        }
    }

    public void printOverview() throws NullPointerException {
        if (filteredExpenses.isEmpty()) {
            System.out.println(EMPTY_MONTH + month.toUpperCase() + WHITESPACE + year + PERIOD);
        } else {
            CommandTotal commandTotal = new CommandTotal(filteredExpenses);
            System.out.println(TITLE + month.toUpperCase() + WHITESPACE + year);
            System.out.println();
            System.out.println(TAB + MONTHLY_OVERVIEW_TOTAL +
                    commandTotal.calculateTotal().setScale(2, RoundingMode.HALF_UP) + WHITESPACE + "SGD");
            System.out.println();
            System.out.println(TAB + CATEGORY_TITLE);
            printCategoryBreakdown(filteredExpenses);
        }
    }


}
