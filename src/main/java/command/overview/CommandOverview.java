package command.overview;

import command.Command;
import command.CommandRes;
import data.Expense;
import data.Time;
import exception.FutureDateException;
import exception.NegativeYearException;
import exception.OverviewInputFormatException;
import exception.YearNameException;

import java.time.Month;
import java.util.ArrayList;

import static common.MessageList.WHITESPACE;
import static common.MessageList.PERIOD;

public class CommandOverview extends Command {

    private static final String COMMAND_NAME = "overview";
    private static final String MONTH_NAME_ERROR =
            "Incorrect month name. Please key in the full english month name.";
    private static final String FUTURE_DATE_ERROR = "For yearly overview, please input a year before 2024. " +
            "For monthly overview, please input month before ";
    private static final String NO_SPECIFIC_MONTH_ERROR =
            "Please specify the month and year of the overview you intend to view.";
    private static final String INVALID_YEAR_FORMAT_ERROR = "Invalid format for year. " +
            "Please enter month name in standard English Month and/or year as a 4 digit number.";
    private static final String NEGATIVE_YEAR_ERROR = "Year cannot be negative. Please input a valid year";
    private static final String INVALID_YEAR_ERROR = "Invalid year. Please enter a year between 1981 and ";
    private static final String INCLUSIVE = "(inclusive)";
    private static final Integer MIN_YEAR = 1981;

    private String month;
    private String year;
    private ArrayList<Expense> expenses;


    public CommandOverview(ArrayList<Expense> expenses, String month, String year)
            throws ArrayIndexOutOfBoundsException {
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
            if (year.equals("-1")) {
                throw new OverviewInputFormatException();
            }
            if (Integer.parseInt(year) < 0) {
                throw new NegativeYearException();
            }
            if (Integer.parseInt(year) < MIN_YEAR | Integer.parseInt(year) > Time.getCurrentYear()) {
                throw new YearNameException();
            }
            if (Time.isFutureMonth(year, month)) {
                throw new FutureDateException();
            }
            if (isMonthlyOverview()) {
                new MonthlyOverview(expenses, month, year).printOverview();
            } else { // yearly overview
                new YearlyOverview(expenses, year).printYearlyOverview();
            }
        } catch (NumberFormatException e) {
            System.out.println(INVALID_YEAR_FORMAT_ERROR);
        } catch (NegativeYearException e) {
            System.out.println(NEGATIVE_YEAR_ERROR);
        } catch (YearNameException | NullPointerException e) {
            System.out.println(INVALID_YEAR_ERROR + Time.getCurrentYear() + WHITESPACE + INCLUSIVE + PERIOD);
        } catch (IllegalArgumentException e) {
            System.out.println(MONTH_NAME_ERROR);
        } catch (FutureDateException e) {
            System.out.println(FUTURE_DATE_ERROR + Month.of(Time.getCurrentMonth() + 1) + WHITESPACE +
                    Time.getCurrentYear() + PERIOD);
        } catch (OverviewInputFormatException e) {
            System.out.println(NO_SPECIFIC_MONTH_ERROR);
        }
        return null;
    }

}
