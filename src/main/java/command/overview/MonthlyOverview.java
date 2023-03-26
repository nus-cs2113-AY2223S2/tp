package command.overview;

import command.CommandTotal;
import data.Expense;
import utils.MonthFilter;
import utils.YearFilter;

import java.util.ArrayList;
import static common.MessageList.TAB;

public class MonthlyOverview {

    private String month;
    private String year;
    private ArrayList<Expense> expenses;

    private static final String WHITE_SPACE = " ";
    private static final String TITLE = "Overview for ";
    private static final String MONTHLY_OVERVIEW_TOTAL = "Total expenses: ";
    private static final String CATEGORY_TITLE = "Breakdown of expenses by category: ";

    public MonthlyOverview(ArrayList<Expense> expenses, String month, String year) {
        this.expenses = expenses;
        this.month = month;
        this.year = year;
        System.out.println("monthly overview");
    }

    private ArrayList<Expense> filteredExpenses(String month, String year) {
        YearFilter yearFilter = new YearFilter(expenses, year);
        MonthFilter monthFilter = new MonthFilter(yearFilter.getYearlyExpenses(), month);
        return monthFilter.getMonthlyExpenses();
    }

    private void printCategoryBreakdown(ArrayList<Expense> newExpenses) {

    }

    public void printOverview(ArrayList<Expense> newExpenses) {
        CommandTotal commandTotal = new CommandTotal(newExpenses);
        System.out.println(TITLE + month + WHITE_SPACE + year);
        System.out.println(TAB + MONTHLY_OVERVIEW_TOTAL + commandTotal.calculateTotal().toString() + "SGD");
        System.out.println(TAB + CATEGORY_TITLE);
        printCategoryBreakdown(newExpenses);
    }





}
