package command.overview;

import command.Command;
import command.CommandRes;
import data.Expense;

import java.util.ArrayList;

public class CommandOverview extends Command {

    private String month;
    private String year;
    private ArrayList<Expense> expenses;

    private static final String COMMAND_NAME = "overview";


    public CommandOverview(ArrayList<Expense> expenses, String month, String year) {
        super(COMMAND_NAME);
        this.expenses = expenses;
        this.month = month;
        this.year = year;
    }

    private boolean isMonthlyOverview() {
        return month != null;
    }

    @Override
    public CommandRes execute() {
        if (isMonthlyOverview()) {
            new MonthlyOverview(expenses, month, year).printOverview();
        } else { // yearly overview
            new YearlyOverview();
        }
        return null;
    }

}
