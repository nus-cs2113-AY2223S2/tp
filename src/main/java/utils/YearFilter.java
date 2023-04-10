package utils;

import data.Expense;

import java.util.ArrayList;

public class YearFilter {
    private String year;
    private ArrayList<Expense> expenses;
    private final ArrayList<Expense> yearlyExpenses = new ArrayList<>();

    public YearFilter(ArrayList<Expense> expenses, String year) {
        this.year = year;
        this.expenses = expenses;
    }

    private String getYearFromExpenseTime(String expenseTime) {
        String[] date = expenseTime.split("-");
        return date[2];
    }

    private void filter() {
        for (Expense e : expenses) {
            if (getYearFromExpenseTime(e.getExpenseTime()).equals(year)) {
                yearlyExpenses.add(e);
            }
        }
    }

    public ArrayList<Expense> getYearlyExpenses() {
        filter();
        return yearlyExpenses;
    }
}
