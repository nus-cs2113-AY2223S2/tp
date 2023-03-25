package command.overview;

import data.Expense;

import java.time.Month;
import java.util.ArrayList;

public class MonthlyOverview {

    private String month;
    private String year;
    private ArrayList<Expense> expenses;

    public MonthlyOverview(ArrayList<Expense> expenses, String month, String year) {
        this.expenses = expenses;
        this.month = month;
        this.year = year;
        System.out.println("monthly overview");
    }


}
