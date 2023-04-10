package utils;

import data.Expense;

import java.time.Month;
import java.util.ArrayList;

public class MonthFilter {
    private ArrayList<Expense> expenses;
    private ArrayList<Expense> monthlyExpenses = new ArrayList<>();
    private String monthName; // English month name


    public MonthFilter(ArrayList<Expense> expenses, String monthName) {
        this.expenses = expenses;
        this.monthName = monthName;
    }

    /**
     * Converts English month name to its numeric representation
     *
     * @return numeric month as String in 2 char
     */
    protected String convertMonthName() throws IllegalArgumentException {
        int numericMonth = Month.valueOf(monthName.toUpperCase()).getValue();
        if (numericMonth < 10) {
            return "0" + numericMonth;
        } else {
            return Integer.toString(numericMonth);
        }
    }

    protected String getMonthFromExpenseTime(String expenseTime) {
        String[] date = expenseTime.split("-");
        return date[1];
    }

    protected void filter() throws IllegalArgumentException {
        for (Expense e : expenses) {
            if (getMonthFromExpenseTime(e.getExpenseTime()).equals(convertMonthName())) {
                monthlyExpenses.add(e);
            }
        }
    }


    public ArrayList<Expense> getMonthlyExpenses() throws IllegalArgumentException {
        filter();
        return monthlyExpenses;
    }
}
