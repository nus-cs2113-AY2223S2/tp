package command.overview;

import command.Command;
import command.CommandRes;
import data.Expense;
import data.Time;
import exception.FutureDateException;
import exception.OverviewInputFormatException;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;

public class CommandOverview extends Command {

    private String month;
    private String year;
    private ArrayList<Expense> expenses;

    private static final String COMMAND_NAME = "overview";
    private static final String MONTH_NAME_ERROR = "Incorrect month name. Please key in the full english month name.";
    private static final String FUTURE_MONTH_ERROR = "Invalid month. Please input a month before ";
    private static final String NO_SPECIFIC_MONTH_ERROR =
            "Please specify the month and year of the overview you intend to view.";
    private static final String WHITE_SPACE = " ";


    public CommandOverview(ArrayList<Expense> expenses, String month, String year) throws ArrayIndexOutOfBoundsException {
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
        try {
            if (Time.isFutureMonth(year, month)) {
                throw new FutureDateException();
            }
            if (year.equals("-1")) {
                throw new OverviewInputFormatException();
            }
            if (isMonthlyOverview()) {
                new MonthlyOverview(expenses, month, year).printOverview();
            } else { // yearly overview
                new YearlyOverview();
            }
        } catch (IllegalArgumentException | NullPointerException e) {
            System.out.println(MONTH_NAME_ERROR);
        } catch (FutureDateException e) {
            System.out.println(FUTURE_MONTH_ERROR + Month.of(Time.getCurrentMonth() + 1) +
                    WHITE_SPACE + Time.getCurrentMonth());
        } catch (OverviewInputFormatException e) {
            System.out.println(NO_SPECIFIC_MONTH_ERROR);
        }
        return null;
    }

}
