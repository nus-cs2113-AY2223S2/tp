package command.overview;

import command.CommandTotal;
import data.Expense;
import utils.MonthFilter;
import utils.YearFilter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.time.Month;
import java.util.LinkedHashMap;

import static common.MessageList.TAB;
import static common.MessageList.WHITESPACE;
import static common.MessageList.PERIOD;

public class YearlyOverview {

    private static final String TITLE = "Yearly Overview for ";
    private static final String MONTH_DIVIDER = "----------------------------";
    private static final String YEARLY_OVERVIEW_TOTAL = "Total expenses: ";
    private static final String BREAKDOWN_TITLE = "Breakdown of expenses by month:";

    private String year;
    private ArrayList<Expense> expenses;
    private ArrayList<Expense> filteredExpenses = new ArrayList<>();
    private LinkedHashMap<String, BigDecimal> monthlyTotal = new LinkedHashMap<>();


    public YearlyOverview(ArrayList<Expense> expenses, String year) {
        this.expenses = expenses;
        this.year = year;
        obtainFilteredExpenses();
    }

    private void obtainFilteredExpenses() {
        YearFilter yearFilter = new YearFilter(expenses, year);
        filteredExpenses = yearFilter.getYearlyExpenses();
    }

    private void totalByMonth() {
        for (int i = 1; i <= 12; i++) {
            String month = Month.of(i).toString();
            MonthFilter monthFilter = new MonthFilter(filteredExpenses, month);
            BigDecimal total = new CommandTotal(monthFilter.getMonthlyExpenses()).calculateTotal()
                    .setScale(2, RoundingMode.HALF_UP);
            monthlyTotal.put(month, total);
        }
    }

    private void printTotalByMonth() {
        totalByMonth();
        System.out.println(TAB + MONTH_DIVIDER);
        for (String month : monthlyTotal.keySet()) {
            System.out.println(TAB + WHITESPACE + month + WHITESPACE +
                    monthlyTotal.get(month) + WHITESPACE + "SGD");
            System.out.println(TAB + MONTH_DIVIDER);
        }
    }

    public void printYearlyOverview() {
        if (filteredExpenses.isEmpty()) {
            System.out.println("No expenses tracked in " + year + PERIOD);
        } else {
            CommandTotal commandTotal = new CommandTotal(filteredExpenses);
            System.out.println(TITLE + year);
            System.out.println();
            System.out.println(TAB + YEARLY_OVERVIEW_TOTAL + commandTotal.calculateTotal()
                    .setScale(2, RoundingMode.HALF_UP) + WHITESPACE + "SGD");
            System.out.println();
            System.out.println(TAB + BREAKDOWN_TITLE);
            printTotalByMonth();
        }
    }

}
