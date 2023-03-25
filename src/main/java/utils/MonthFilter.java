package utils;

import data.Expense;

import java.time.Month;
import java.util.ArrayList;

public class MonthFilter {
    private ArrayList<Expense> expenses;
    private ArrayList<Expense> monthlyExpenses = new ArrayList<>();
    private String month; // English month name

    public MonthFilter(ArrayList<Expense> expenses, String month) {
        this.expenses = expenses;
        this.month = month;
    }

    /**
     * Converts English month name to its numeric representation
     *
     * @param monthName English month name
     * @return numeric month as String in 2 char
     */
    private String convertMonthName(String monthName) {
        int numericMonth = Month.valueOf(monthName.toUpperCase()).getValue();
        if (numericMonth < 10) {
            return "0" + numericMonth;
        } else {
            return Integer.toString(numericMonth);
        }
    }

    private String getMonthFromExpenseTime(String expenseTime) {
        String[] date = expenseTime.split("/");
        return date[1];
    }

    private void filter() {
        for (Expense e : expenses) {
            if (getMonthFromExpenseTime(e.getExpenseTime()).equals(convertMonthName(month))) {
                monthlyExpenses.add(e);
            }
        }
    }

    public ArrayList<Expense> getMonthlyExpenses() {
        filter();
        return monthlyExpenses;
    }
}
